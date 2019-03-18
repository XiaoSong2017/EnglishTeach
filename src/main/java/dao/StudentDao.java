package dao;


import org.springframework.transaction.annotation.Transactional;
import po.StudentPo;

import java.util.List;

@Transactional
public interface StudentDao extends BaseDao<StudentPo> {
    List<StudentPo> getStudentsByClassId(String id);
}
