package dao;

import dao.implement.BaseDaoImpl;
import org.springframework.transaction.annotation.Transactional;
import po.AnswerRecordPo;

@Transactional
public interface AnswerRecordDao extends BaseDao<AnswerRecordPo> {
}
