package service;

import com.sun.istack.NotNull;
import dao.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import po.*;

import java.util.*;

@Service
public class ExaminationPaperService {
    private ExaminationPaperDao examinationPaperDao;

    public void setExaminationPaperDao(ExaminationPaperDao examinationPaperDao) {
        this.examinationPaperDao = examinationPaperDao;
    }

    public List<ExaminationPaperPo> getExaminationPaperByTeacherId(String teacherId, String type) {
        return examinationPaperDao.getExaminationPaperByTeacherId(teacherId, type);
    }

    @Transactional
    public void deleteExaminationPaperById(String id) {
        examinationPaperDao.delete(ExaminationPaperPo.class, Integer.valueOf(id));
    }

    @Transactional
    public void saveExaminationPaper(ExaminationPaperPo examinationPaperPo) {
        examinationPaperDao.saveOrUpdate(examinationPaperPo);
    }

    @Transactional
    public void updateExaminationPaper(@NotNull ExaminationPaperPo examinationPaperPo){
        examinationPaperDao.update(examinationPaperPo);
    }

    public Map<String, Object> getExaminationPaperDetailById(int id) {
        Map<String, Object> list = new HashMap<>();
        ExaminationPaperPo examinationPaperPo = examinationPaperDao.getById(ExaminationPaperPo.class, id);
        if (examinationPaperPo != null) {
            list.put("id", examinationPaperPo.getId());
            list.put("name", examinationPaperPo.getName());
            list.put("startTime", examinationPaperPo.getStartTime());
            list.put("endTime", examinationPaperPo.getEndTime());
            List problemPos = new ArrayList();
            for (ComponentPo componentPo : examinationPaperPo.getComponentsById()) {
                Map<String, Object> problem = new HashMap<>();
                ProblemPo problemPo = componentPo.getProblemByQId();
                problem.put("id", problemPo.getId());
                problem.put("type", problemPo.getType());
                problem.put("content", problemPo.getContent());
                problem.put("problemNumber", componentPo.getProblemNumber());
                List questions = new ArrayList();
                for (QuestionPo questionPo : problemPo.getQuestionsById()) {
                    Map<String, Object> question = new HashMap<>();
                    question.put("id", questionPo.getId());
                    question.put("questionNumber", questionPo.getQuestionNumber());
                    question.put("content", questionPo.getContent());
                    List options = new ArrayList();
                    for (OptionsPo optionsPo : questionPo.getOptionsById()) {
                        Map<String, Object> option = new HashMap<>();
                        option.put("mark", optionsPo.getMark());
                        option.put("content", optionsPo.getContent());
                        options.add(option);
                    }
                    question.put("option", options);
                    questions.add(question);
                }
                problem.put("question", questions);
                problemPos.add(problem);
            }
            list.put("problem", problemPos);
        }
        return list;
    }

    public List<ExaminationPaperPo> getExaminationPaperByCourseId(String courseId, String type) {
        return examinationPaperDao.getExaminationPaperByCourseId(courseId, type);
    }
}
