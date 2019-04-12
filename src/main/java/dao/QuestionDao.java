package dao;

import org.springframework.transaction.annotation.Transactional;
import po.QuestionPo;
@Transactional
public interface QuestionDao extends BaseDao<QuestionPo> {
    String getAnswerById(int id);
}
