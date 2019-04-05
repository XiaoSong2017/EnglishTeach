package service;

import dao.*;
import org.springframework.stereotype.Service;
import po.ExaminationPaperPo;

import java.util.List;

@Service
public class ExaminationPaperService {
    private ExaminationPaperDao examinationPaperDao;
    private ComponentDao componentDao;
    private ProblemDao problemDao;
    private QuestionDao questionDao;
    private OptionsDao optionsDao;

    public void setExaminationPaperDao(ExaminationPaperDao examinationPaperDao) {
        this.examinationPaperDao = examinationPaperDao;
    }

    public void setComponentDao(ComponentDao componentDao) {
        this.componentDao = componentDao;
    }

    public void setProblemDao(ProblemDao problemDao) {
        this.problemDao = problemDao;
    }

    public void setQuestionDao(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    public void setOptionsDao(OptionsDao optionsDao) {
        this.optionsDao = optionsDao;
    }

    public List<ExaminationPaperPo> getExaminationPaperByTeacherId(String teacherId, String type) {
        return examinationPaperDao.getExaminationPaperByTeacherId(teacherId, type);
    }

    public void deleteExaminationPaperById(String id) {
        examinationPaperDao.delete(ExaminationPaperPo.class, Integer.valueOf(id));
    }
}
