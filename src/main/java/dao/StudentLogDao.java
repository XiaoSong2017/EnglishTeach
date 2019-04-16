package dao;

import po.StudentLogPo;

import java.util.List;

public interface StudentLogDao extends BaseDao<StudentLogPo> {
    List getByStudentId(String studentById);
}
