package service;

import dao.AnswerRecordDao;
import dao.ComponentDao;
import dao.QuestionDao;
import po.QuestionPo;

import java.util.List;

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

    public void saveAnswerRecord(int examinationById, List<String> questionById, List<String> content, String studentById) {
        float[] core=new float[content.size()];
        int[] questionId=new int[content.size()];
        for(int i=0;i<content.size();++i){
            questionId[i]=Integer.valueOf(questionById.get(i));
            if(isObjectTopic(questionId[i])){
                core[i]=onlineJudgment(questionId[i],content.get(i))?componentDao.getObjectByExamIdAndProblemId(examinationById,questionId[i]).getCore():0;
            }
        }
        answerRecordDao.saveOrUpdateAnswerRecords(examinationById,questionId,content,studentById,core);
    }

    private boolean onlineJudgment(int question, String content){
        QuestionPo questionPo=questionDao.getById(QuestionPo.class,question);
        return questionPo!=null&&questionPo.getAnswer().equals(content);
    }

    private boolean isObjectTopic(int question){
        QuestionPo questionPo=questionDao.getById(QuestionPo.class,question);
        if(questionPo!=null){
            int topic=questionPo.getProblemByProblem().getTopicByType().getId();
            return topic!=7&&topic!=8;
        }else return false;
    }
}
