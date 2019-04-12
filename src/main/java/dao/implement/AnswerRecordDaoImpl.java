package dao.implement;

import dao.AnswerRecordDao;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.transaction.annotation.Transactional;
import po.AnswerRecordPo;
@Transactional
public class AnswerRecordDaoImpl extends BaseDaoImpl<AnswerRecordPo> implements AnswerRecordDao {
    @Override
    public void saveOrUpdateAnswerRecords(int examinationById, int[] questionById, String[] content, String studentById, float[] core) {
        Session session=getSessionFactory().getCurrentSession();
        Transaction transaction=session.getTransaction();
        for(int i=0;i<content.length;++i){
            AnswerRecordPo answerRecordPo=new AnswerRecordPo();
            answerRecordPo.setAnswer(content[i]);
            answerRecordPo.setCore(core[i]);
            answerRecordPo.seteId(examinationById);
            answerRecordPo.setqId(questionById[i]);
            answerRecordPo.setsId(studentById);
            session.saveOrUpdate(answerRecordPo);
        }
        transaction.commit();
    }
}
