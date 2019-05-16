package dao.implement;

import dao.SpecialtyDao;
import po.SpecialtyPo;

import java.util.List;

public class SpecialtyDaoImpl extends BaseDaoImpl<SpecialtyPo> implements SpecialtyDao {
    @Override
    public List<SpecialtyPo> getListByAcademyId(String id) {
        return getSessionFactory().getCurrentSession().createQuery("select en from SpecialtyPo en where en.academyByAcademy.id=?1").setParameter(1, Integer.valueOf(id)).getResultList();
    }
}
