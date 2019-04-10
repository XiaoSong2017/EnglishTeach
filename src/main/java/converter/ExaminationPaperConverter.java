package converter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import dao.CourseDao;
import dao.TeacherDao;
import org.apache.struts2.util.StrutsTypeConverter;
import po.*;

import java.sql.Timestamp;
import java.util.*;

public class ExaminationPaperConverter extends StrutsTypeConverter {
    private TeacherDao teacherDao;
    private CourseDao courseDao;

    public void setTeacherDao(TeacherDao teacherDao) {
        this.teacherDao = teacherDao;
    }

    public void setCourseDao(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    @Override
    public Object convertFromString(Map map, String[] strings, Class aClass) {
//        logger(strings.toString());
        JSONObject jsonObject = JSON.parseObject(strings[0]);
        TeacherPo teacherPo=teacherDao.getById(TeacherPo.class,jsonObject.getString("tId"));
        CoursePo coursePo=courseDao.getById(CoursePo.class,jsonObject.getString("cId"));
        ExaminationPaperPo examinationPaperPo = new ExaminationPaperPo();
        examinationPaperPo.setName(jsonObject.getString("name"));
        examinationPaperPo.setType(jsonObject.getBoolean("type"));
        examinationPaperPo.setStartTime(jsonObject.getTimestamp("startTime"));
        examinationPaperPo.setEndTime(jsonObject.getTimestamp("endTime"));
        examinationPaperPo.setcId(jsonObject.getString("cId"));
        examinationPaperPo.setCourseByCId(coursePo);
        examinationPaperPo.settId(jsonObject.getString("tId"));
        examinationPaperPo.setTeacherByTId(teacherPo);
        Set<ComponentPo> componentPos=new HashSet<>();
        JSONArray jsonArray = jsonObject.getJSONArray("componentsById");
        for (int i = 0; i < jsonArray.size(); ++i) {
            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
            ComponentPo componentPo = new ComponentPo();
            componentPo.setExaminationPaperByEId(examinationPaperPo);
            componentPo.setProblemNumber(jsonObject1.getInteger("problemNumber"));
            ProblemPo problemPo = new ProblemPo();
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
            Set<QuestionPo> questionPos=new HashSet<>();
            JSONArray jsonArray1 = jsonObject1.getJSONArray("question");
            for (int j = 0; j < jsonArray1.size(); ++j) {
                QuestionPo questionPo = new QuestionPo();
                questionPo.setProblemByProblem(problemPo);
                questionPo.setContent(jsonArray1.getJSONObject(j).getString("question"));
                questionPo.setAnswer(jsonArray1.getJSONObject(j).getString("answer"));
                questionPo.setQuestionNumber((Integer) jsonArray1.getJSONObject(j).getOrDefault("questionNumber",j+1));
                questionPo.setProblem(problemPo.getId());
                questionPos.add(questionPo);
                Set<OptionsPo> optionsPos=new HashSet<>();
                JSONArray jsonArray2 = jsonArray1.getJSONObject(j).getJSONArray("option");
                for (int k = 0; k < jsonArray2.size(); ++k) {
                    OptionsPo optionsPo = new OptionsPo();
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
