package dao.implement;

import dao.StudentLogDao;
import org.springframework.transaction.annotation.Transactional;
import po.StudentLogPo;

@Transactional
public class StudentLogDaoImpl extends BaseDaoImpl<StudentLogPo> implements StudentLogDao {
}
