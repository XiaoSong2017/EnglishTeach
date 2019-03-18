package dao.implement;

import dao.ClassesDao;
import org.springframework.transaction.annotation.Transactional;
import po.ClassesPo;

import java.util.List;
@Transactional
public class ClassesDaoImpl extends BaseDaoImpl<ClassesPo> implements ClassesDao {
    @Override
    public List<ClassesPo> getListBySpecialtyId(String id) {
        return getSessionFactory().getCurrentSession().createQuery(
                "select en from ClassesPo en where en.specialtyBySpecialty.id=?1").setParameter(1,Integer.valueOf(id)).getResultList();
    }
}
