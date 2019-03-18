package dao;

import org.springframework.transaction.annotation.Transactional;
import po.TeacherPo;

@Transactional
public interface TeacherDao extends BaseDao<TeacherPo>{
}
