package dao.implement;

import dao.ProblemDao;
import org.springframework.transaction.annotation.Transactional;
import po.ProblemPo;
@Transactional
public class ProblemDaoImpl extends BaseDaoImpl<ProblemPo> implements ProblemDao {
}
