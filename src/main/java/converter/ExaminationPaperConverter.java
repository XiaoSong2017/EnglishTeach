package converter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import dao.*;
import org.apache.struts2.util.StrutsTypeConverter;
import po.*;

import java.sql.Timestamp;
import java.util.*;

public class ExaminationPaperConverter extends StrutsTypeConverter {
    private TeacherDao teacherDao;
    private CourseDao courseDao;
    private ExaminationPaperDao examinationPaperDao;

    public void setTeacherDao(TeacherDao teacherDao) {
        this.teacherDao = teacherDao;
    }

    public void setCourseDao(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    public void setExaminationPaperDao(ExaminationPaperDao examinationPaperDao) {
        this.examinationPaperDao = examinationPaperDao;
    }

    @Override
    public Object convertFromString(Map map, String[] strings, Class aClass) {
//        logger(strings.toString());
        JSONObject jsonObject = JSON.parseObject(strings[0]);
        TeacherPo teacherPo = teacherDao.getById(TeacherPo.class, jsonObject.getString("tId"));
        CoursePo coursePo = courseDao.getById(CoursePo.class, jsonObject.getString("cId"));
        ExaminationPaperPo examinationPaperPo;
        Set<ComponentPo> componentPos;
        if (jsonObject.containsKey("id")) {
            examinationPaperPo = examinationPaperDao.getById(ExaminationPaperPo.class, jsonObject.getInteger("id"));
            componentPos = examinationPaperPo.getComponentsById();
        } else {
            examinationPaperPo = new ExaminationPaperPo();
            componentPos = new HashSet<>();
        }
        examinationPaperPo.setName(jsonObject.getString("name"));
        examinationPaperPo.setType(jsonObject.getBoolean("type"));
        examinationPaperPo.setStartTime(jsonObject.getTimestamp("startTime"));
        examinationPaperPo.setEndTime(jsonObject.getTimestamp("endTime"));
        examinationPaperPo.setcId(jsonObject.getString("cId"));
        examinationPaperPo.setCourseByCId(coursePo);
        examinationPaperPo.settId(jsonObject.getString("tId"));
        examinationPaperPo.setTeacherByTId(teacherPo);
        JSONArray jsonArray = jsonObject.getJSONArray("componentsById");
        for (int i = 0; i < jsonArray.size(); ++i) {
            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
            ComponentPo componentPo = null;
            ProblemPo problemPo = null;
            Set<QuestionPo> questionPos = null;
            if (jsonObject1.containsKey("id")) {
                for (ComponentPo componentPo1 : componentPos) {
                    if (jsonObject1.getInteger("id").equals(componentPo1.getId())) {
                        componentPo = componentPo1;
                        problemPo = componentPo.getProblemByQId();
                        questionPos = problemPo.getQuestionsById();
                        break;
                    }
                }
            } else {
                componentPo = new ComponentPo();
                problemPo = new ProblemPo();
                questionPos = new HashSet<>();
            }
            componentPo.setExaminationPaperByEId(examinationPaperPo);
            componentPo.setProblemNumber(jsonObject1.getInteger("problemNumber"));
            problemPo.setType(jsonObject1.getInteger("type"));
            problemPo.setContent(jsonObject1.getString("content"));
            problemPo.setcId(jsonObject.getString("cId"));
            problemPo.setCourseByCId(coursePo);
            problemPo.settId(jsonObject.getString("tId"));
            problemPo.setTeacherByTId(teacherPo);
            problemPo.setTime(new Timestamp(System.currentTimeMillis()));
            componentPo.setProblemByQId(problemPo);
            componentPo.setqId(problemPo.getId());
            componentPo.setCore(jsonObject1.getInteger("core"));
            JSONArray jsonArray1 = jsonObject1.getJSONArray("question");
            for (int j = 0; j < jsonArray1.size(); ++j) {
                QuestionPo questionPo = null;
                Set<OptionsPo> optionsPos = null;
                if (jsonArray1.getJSONObject(j).containsKey("id")) {
                    for (QuestionPo questionPo1 : questionPos) {
                        if (jsonArray1.getJSONObject(j).getInteger("id").equals(questionPo1.getId())) {
                            questionPo = questionPo1;
                            optionsPos = questionPo.getOptionsById();
                            break;
                        }
                    }
                } else {
                    questionPo = new QuestionPo();
                    optionsPos = new HashSet<>();
                }
                questionPo.setProblemByProblem(problemPo);
                questionPo.setContent(jsonArray1.getJSONObject(j).getString("question"));
                questionPo.setAnswer(jsonArray1.getJSONObject(j).getString("answer"));
                questionPo.setQuestionNumber((Integer) jsonArray1.getJSONObject(j).getOrDefault("questionNumber", j + 1));
                questionPo.setProblem(problemPo.getId());
                questionPos.add(questionPo);
                JSONArray jsonArray2 = jsonArray1.getJSONObject(j).getJSONArray("option");
                for (int k = 0; k < jsonArray2.size(); ++k) {
                    OptionsPo optionsPo = null;
                    if (jsonArray2.getJSONObject(k).containsKey("id")) {
                        for (OptionsPo optionsPo1 : optionsPos) {
                            if (jsonArray2.getJSONObject(k).getInteger("id").equals(optionsPo1.getId())) {
                                optionsPo = optionsPo1;
                                break;
                            }
                        }
                    } else {
                        optionsPo = new OptionsPo();
                    }
                    optionsPo.setQuestionByQuestion(questionPo);
                    optionsPo.setMark(jsonArray2.getJSONObject(k).getString("mark"));
                    optionsPo.setContent(jsonArray2.getJSONObject(k).getString("content"));
                    optionsPo.setQuestion(questionPo.getId());
                    optionsPos.add(optionsPo);
                }
                questionPo.setOptionsById(optionsPos);
            }
            problemPo.setQuestionsById(questionPos);
            componentPos.add(componentPo);
        }
        examinationPaperPo.setComponentsById(componentPos);
        return examinationPaperPo;
    }

    @Override
    public String convertToString(Map map, Object o) {
        return JSON.toJSONString(o);
    }
}
