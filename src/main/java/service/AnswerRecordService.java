package service;

import dao.AnswerRecordDao;
import dao.ComponentDao;
import dao.QuestionDao;
import po.ComponentPo;
import po.QuestionPo;

public class AnswerRecordService {
    private AnswerRecordDao answerRecordDao;
    private ComponentDao componentDao;
    private QuestionDao questionDao;
    public void setAnswerRecordDao(AnswerRecordDao answerRecordDao) {
        this.answerRecordDao = answerRecordDao;
    }

    public void setComponentDao(ComponentDao componentDao) {
        this.componentDao = componentDao;
    }

    public void setQuestionDao(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    public void saveAnswerRecord(int examinationById, int[] questionById, String[] content, String studentById) {
        float[] core=new float[content.length];
        for(int i=0;i<content.length;++i){
            if(isObjectTopic(questionById[i])){
                core[i]=onlineJudgment(questionById[i],content[i])?componentDao.getObjectByExamIdAndProblemId(examinationById,questionById[i]).getCore():0;
            }
        }
        answerRecordDao.saveOrUpdateAnswerRecords(examinationById,questionById,content,studentById,core);
    }

    public boolean onlineJudgment(int question,String content){
        QuestionPo questionPo=questionDao.getById(QuestionPo.class,question);
        return questionPo!=null&&questionPo.getAnswer().equals(content);
    }

    public boolean isObjectTopic(int question){
        QuestionPo questionPo=questionDao.getById(QuestionPo.class,question);
        if(questionPo!=null){
            int topic=questionPo.getProblemByProblem().getTopicByType().getId();
            return topic!=7&&topic!=8;
        }else return false;
    }
}
