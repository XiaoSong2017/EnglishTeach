package dao.implement;

import dao.ExaminationPaperDao;
import po.ExaminationPaperPo;

import java.util.List;

public class ExaminationPaperDaoImpl extends BaseDaoImpl<ExaminationPaperPo> implements ExaminationPaperDao {
    @Override
    public List<ExaminationPaperPo> getExaminationPaperByTeacherId(String teacherId,String type) {
        return getSessionFactory().getCurrentSession().createQuery(" select en from ExaminationPaperPo en where en.teacherByTId.id=?1 and en.type=?2").setParameter(1,teacherId).setParameter(2,Boolean.valueOf(type)).getResultList();
    }

    @Override
    public List<ExaminationPaperPo> getExaminationPaperByCourseId(String courseId, String type) {
        return getSessionFactory().getCurrentSession().createQuery(" select en from ExaminationPaperPo en where en.courseByCId.id=?1 and en.type=?2").setParameter(1,courseId).setParameter(2,Boolean.valueOf(type)).getResultList();
    }
}
