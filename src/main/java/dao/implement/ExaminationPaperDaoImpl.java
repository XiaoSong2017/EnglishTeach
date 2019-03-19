package dao.implement;

import dao.ExaminationPaperDao;
import org.springframework.transaction.annotation.Transactional;
import po.ExaminationPaperPo;
@Transactional
public class ExaminationPaperDaoImpl extends BaseDaoImpl<ExaminationPaperPo> implements ExaminationPaperDao {
}
