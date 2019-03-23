package dao;

import org.springframework.transaction.annotation.Transactional;
import po.ProblemPo;
@Transactional
public interface ProblemDao extends BaseDao<ProblemPo> {
}
