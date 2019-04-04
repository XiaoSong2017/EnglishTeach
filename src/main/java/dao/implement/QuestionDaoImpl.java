package dao.implement;

import dao.QuestionDao;
import org.springframework.transaction.annotation.Transactional;
import po.QuestionPo;
@Transactional
public class QuestionDaoImpl extends BaseDaoImpl<QuestionPo> implements QuestionDao {
}
