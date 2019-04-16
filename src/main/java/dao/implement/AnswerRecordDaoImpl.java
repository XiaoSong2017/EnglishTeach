package dao.implement;

import dao.AnswerRecordDao;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.transaction.annotation.Transactional;
import po.AnswerRecordPo;

import java.util.Date;
import java.util.List;

@Transactional
public class AnswerRecordDaoImpl extends BaseDaoImpl<AnswerRecordPo> implements AnswerRecordDao {
    @Override
    public void saveOrUpdateAnswerRecords(int examinationById, int[] questionById, List<String> content, String studentById, float[] core) {
        Session session=getSessionFactory().getCurrentSession();
        for(int i=0;i<content.size();++i){
            AnswerRecordPo answerRecordPo=new AnswerRecordPo();
            answerRecordPo.setAnswer(content.get(i));
            answerRecordPo.setCore(core[i]);
            answerRecordPo.seteId(examinationById);
            answerRecordPo.setqId(questionById[i]);
            answerRecordPo.setsId(studentById);
            answerRecordPo.setTime(new Date(System.currentTimeMillis()));
            session.saveOrUpdate(answerRecordPo);
        }
    }
}
