package dao;

import org.springframework.transaction.annotation.Transactional;
import po.AcademyPo;
@Transactional
public interface AcademyDao extends BaseDao<AcademyPo> {
}
