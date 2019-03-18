package dao;

import po.SpecialtyPo;

import java.util.List;

public interface SpecialtyDao extends BaseDao<SpecialtyPo> {
    List<SpecialtyPo> getListByAcademyId(String id);
}
