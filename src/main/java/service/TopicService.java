package service;

import dao.TopicDao;
import org.springframework.stereotype.Service;
import po.TopicPo;

import java.util.List;

@Service
public class TopicService {
    private TopicDao topicDao;

    public void setTopicDao(TopicDao topicDao) {
        this.topicDao = topicDao;
    }

    public List<TopicPo> getAll(){
        return topicDao.getAll(TopicPo.class);
    }
}
