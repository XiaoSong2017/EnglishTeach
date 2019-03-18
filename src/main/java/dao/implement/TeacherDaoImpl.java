package dao.implement;

import dao.TeacherDao;
import org.springframework.transaction.annotation.Transactional;
import po.TeacherPo;

@Transactional
public class TeacherDaoImpl extends BaseDaoImpl<TeacherPo> implements TeacherDao {
}
