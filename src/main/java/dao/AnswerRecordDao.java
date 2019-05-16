package dao;

import po.AnswerRecordPo;

import java.util.List;

public interface AnswerRecordDao extends BaseDao<AnswerRecordPo> {
    void saveOrUpdateAnswerRecords(int examinationById, List<Integer> questionById, List<String>content, String studentById, List<Float> core);

    List<AnswerRecordPo> getObjectsByQuestionId(int i);
}
