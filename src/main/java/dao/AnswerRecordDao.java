package dao;

import dao.implement.BaseDaoImpl;
import org.springframework.transaction.annotation.Transactional;
import po.AnswerRecordPo;

@Transactional
public interface AnswerRecordDao extends BaseDao<AnswerRecordPo> {
    void saveOrUpdateAnswerRecords(int examinationById, int[] questionById, String[] content, String studentById, float[] core);
}
