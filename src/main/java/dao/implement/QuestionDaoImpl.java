package dao.implement;

import dao.QuestionDao;
import org.springframework.transaction.annotation.Transactional;
import po.QuestionPo;
@Transactional
public class QuestionDaoImpl extends BaseDaoImpl<QuestionPo> implements QuestionDao {
    @Override
    public String getAnswerById(int id) {
        return String.valueOf(getSessionFactory().getCurrentSession().createQuery("select en.answer from QuestionPo en where en.id=?1").setParameter(1,id).getResultList().get(0));
    }
}
