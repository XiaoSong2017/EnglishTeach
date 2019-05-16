package dao;

import po.QuestionPo;

public interface QuestionDao extends BaseDao<QuestionPo> {
    String getAnswerById(int id);
}
