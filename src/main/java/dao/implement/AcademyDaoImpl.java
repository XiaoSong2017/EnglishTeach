package dao.implement;

import dao.AcademyDao;
import org.springframework.transaction.annotation.Transactional;
import po.AcademyPo;
@Transactional
public class AcademyDaoImpl extends BaseDaoImpl<AcademyPo> implements AcademyDao {
}
