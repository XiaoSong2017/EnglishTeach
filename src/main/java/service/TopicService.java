package service;

import dao.TopicDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import po.TopicPo;

import java.util.List;

@Service
public class TopicService {
    private TopicDao topicDao;

    public void setTopicDao(TopicDao topicDao) {
        this.topicDao = topicDao;
    }

    @Transactional(readOnly = true)
    public List<TopicPo> getAll(){
        return topicDao.getAll(TopicPo.class);
    }
}
