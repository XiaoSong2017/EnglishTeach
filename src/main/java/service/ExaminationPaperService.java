package service;

import dao.ExaminationPaperDao;
import org.springframework.stereotype.Service;
import po.ExaminationPaperPo;

import java.util.List;

@Service
public class ExaminationPaperService {
    private ExaminationPaperDao examinationPaperDao;

    public void setExaminationPaperDao(ExaminationPaperDao examinationPaperDao) {
        this.examinationPaperDao = examinationPaperDao;
    }

    public List<ExaminationPaperPo> getExaminationPaperByTeacherId(String teacherId, String type) {
        return examinationPaperDao.getExaminationPaperByTeacherId(teacherId, type);
    }

    public void deleteExaminationPaperById(String id) {
        examinationPaperDao.delete(ExaminationPaperPo.class, Integer.valueOf(id));
    }
}
