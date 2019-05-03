package dao.implement;

import dao.AnswerRecordDao;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;
import po.AnswerRecordPo;

import java.util.Date;
import java.util.List;

@Transactional
public class AnswerRecordDaoImpl extends BaseDaoImpl<AnswerRecordPo> implements AnswerRecordDao {
    @Override
    public void saveOrUpdateAnswerRecords(int examinationById, List<Integer> questionById, List<String> content, String studentById, List<Float> core) {
        Session session=getSessionFactory().getCurrentSession();
        for(int i=0;i<content.size();++i){
            AnswerRecordPo answerRecordPo=new AnswerRecordPo();
            answerRecordPo.setAnswer(content.get(i));
            answerRecordPo.setCore(core.get(i));
            answerRecordPo.seteId(examinationById);
            answerRecordPo.setqId(questionById.get(i));
            answerRecordPo.setsId(studentById);
            answerRecordPo.setTime(new Date(System.currentTimeMillis()));
            session.saveOrUpdate(answerRecordPo);
        }
    }

    @Override
    public List<AnswerRecordPo> getObjectsByQuestionId(int i) {
        return getSessionFactory().getCurrentSession().createQuery("from AnswerRecordPo en where en.qId=?1").setParameter(1,i).getResultList();
    }
}
