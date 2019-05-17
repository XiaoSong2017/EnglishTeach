package service;

import algorithm.Levenshtein;
import dao.AnswerRecordDao;
import dao.ComponentDao;
import dao.QuestionDao;
import dao.SubjectiveAnswerRecordPoDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import po.AnswerRecordPo;
import po.QuestionPo;
import po.SubjectiveAnswerRecordPo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AnswerRecordService {
    private AnswerRecordDao answerRecordDao;
    private SubjectiveAnswerRecordPoDao subjectiveAnswerRecordPoDao;
    private ComponentDao componentDao;
    private QuestionDao questionDao;
    private Levenshtein levenshtein;

    public void setAnswerRecordDao(AnswerRecordDao answerRecordDao) {
        this.answerRecordDao = answerRecordDao;
    }

    public void setSubjectiveAnswerRecordPoDao(SubjectiveAnswerRecordPoDao subjectiveAnswerRecordPoDao) {
        this.subjectiveAnswerRecordPoDao = subjectiveAnswerRecordPoDao;
    }

    public void setLevenshtein(Levenshtein levenshtein) {
        this.levenshtein = levenshtein;
    }

    public void setComponentDao(ComponentDao componentDao) {
        this.componentDao = componentDao;
    }

    public void setQuestionDao(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    @Transactional
    public void saveAnswerRecord(int examinationById, List<String> questionById, List<String> content, String studentById) {
        List<Float> core = new ArrayList<>();
        List<Integer> questionId = new ArrayList<>();
        for (int i = 0; i < content.size(); ++i) {
            int questionIdById = Integer.valueOf(questionById.get(i));
            if (isObjectTopic(questionIdById)) {
                questionId.add(questionIdById);
                core.add(onlineJudgment(questionIdById, content.get(i)) ? componentDao.getObjectByExamIdAndProblemId(examinationById, questionIdById).getCore() : 0);
            } else {
                float similarity = 0;
                for (AnswerRecordPo answerRecordPo : answerRecordDao.getObjectsByQuestionId(questionIdById)) {
                    /**
                     * 截取除去Summernote框架输出字符串<p></p>之间的结果进行计算
                     */
                    similarity = Math.max(similarity, levenshtein.getSimilarity(content.get(i).substring(content.get(i).indexOf(">")+1, content.get(i).lastIndexOf("<")-1), answerRecordPo.getAnswer().substring(answerRecordPo.getAnswer().indexOf(">")+1, answerRecordPo.getAnswer().lastIndexOf("<")-1)));
                }
                SubjectiveAnswerRecordPo subjectiveAnswerRecordPo = new SubjectiveAnswerRecordPo();
                subjectiveAnswerRecordPo.setSimilarity(similarity);
                subjectiveAnswerRecordPo.setqId(questionIdById);
                subjectiveAnswerRecordPo.seteId(examinationById);
                subjectiveAnswerRecordPo.setsId(studentById);
                subjectiveAnswerRecordPo.setTime(new Date(System.currentTimeMillis()));
                subjectiveAnswerRecordPo.setAnswer(content.get(i));
                subjectiveAnswerRecordPoDao.saveOrUpdate(subjectiveAnswerRecordPo);
                content.remove(i);
            }
        }
        answerRecordDao.saveOrUpdateAnswerRecords(examinationById, questionId, content, studentById, core);
    }

    private boolean onlineJudgment(int question, String content) {
        QuestionPo questionPo = questionDao.getById(QuestionPo.class, question);
        return questionPo != null && questionPo.getAnswer().equals(content);
    }

    private boolean isObjectTopic(int question) {
        QuestionPo questionPo = questionDao.getById(QuestionPo.class, question);
        if (questionPo != null) {
            int topic = questionPo.getProblemByProblem().getTopicByType().getId();
            return topic != 7 && topic != 8;
        } else return false;
    }

    public List<SubjectiveAnswerRecordPo> getSubjectiveAnswerRecords() {
        return subjectiveAnswerRecordPoDao.getAll(SubjectiveAnswerRecordPo.class);
    }
}
