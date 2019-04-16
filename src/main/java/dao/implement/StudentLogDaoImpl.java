package dao.implement;

import dao.StudentLogDao;
import org.springframework.transaction.annotation.Transactional;
import po.StudentLogPo;

import java.util.List;

@Transactional
public class StudentLogDaoImpl extends BaseDaoImpl<StudentLogPo> implements StudentLogDao {
    @Override
    public List getByStudentId(String studentById) {
        return getSessionFactory().getCurrentSession().createQuery("select en from StudentLogPo en where en.sId=?1").setParameter(1,studentById).getResultList();
    }
}
