package dao.implement;

import dao.CourseDao;
import org.springframework.transaction.annotation.Transactional;
import po.CoursePo;
@Transactional
public class CourseDaoImpl extends BaseDaoImpl<CoursePo> implements CourseDao {
}
