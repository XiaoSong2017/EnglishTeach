package dao;

import org.springframework.transaction.annotation.Transactional;
import po.AnswerRecordPo;

import java.util.List;

@Transactional
public interface AnswerRecordDao extends BaseDao<AnswerRecordPo> {
    void saveOrUpdateAnswerRecords(int examinationById, int[] questionById, List<String>content, String studentById, float[] core);
}
