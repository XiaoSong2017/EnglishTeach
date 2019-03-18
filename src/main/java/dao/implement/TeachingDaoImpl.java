package dao.implement;

import dao.TeachingDao;
import org.hibernate.query.Query;
import org.springframework.transaction.annotation.Transactional;
import po.CoursePo;
import po.TeachingPo;

import javax.persistence.NoResultException;
import java.util.List;

@Transactional
public class TeachingDaoImpl extends BaseDaoImpl<TeachingPo> implements TeachingDao {
    @Override
    public List<CoursePo> getCourseByTeacherId(String id) {
        return getSessionFactory().getCurrentSession().createQuery("select en.courseByCId from TeachingPo en " +
                "where en.teacherByTId.id=?1").setParameter(1, id).getResultList();
    }

    @Override
    public TeachingPo getTeachingByCourseIdAndTeacherId(String teacherId, String courseId) {
        try {
            Query query = getSessionFactory().getCurrentSession().createQuery(" select en from TeachingPo en where " +
                    "en.courseByCId.id=?1 and en.teacherByTId.id=?2").setParameter(1, courseId).setParameter(2, teacherId);
            return (TeachingPo) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
