package dao.implement;

import dao.StudentDao;
import org.springframework.transaction.annotation.Transactional;
import po.StudentPo;

import java.util.List;

@Transactional
public class StudentDaoImpl extends BaseDaoImpl<StudentPo> implements StudentDao {
    @Override
    public List<StudentPo> getStudentsByClassId(String id) {
        return getSessionFactory().getCurrentSession().createQuery("select en from StudentPo en where en.classesByClazz.id=?1").setParameter(1,Integer.valueOf(id)).getResultList();
    }
}
