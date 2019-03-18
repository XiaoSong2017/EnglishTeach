package dao;

import po.ClassesPo;

import java.util.List;

public interface ClassesDao extends BaseDao<ClassesPo>{
    List<ClassesPo> getListBySpecialtyId(String id);
}
