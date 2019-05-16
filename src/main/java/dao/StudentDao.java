package dao;


import po.StudentPo;

import java.util.List;

public interface StudentDao extends BaseDao<StudentPo> {
    List<StudentPo> getStudentsByClassId(String id);
}
