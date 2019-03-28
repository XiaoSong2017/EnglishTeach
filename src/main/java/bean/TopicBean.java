package bean;

import org.apache.struts2.json.annotations.JSON;
import po.TopicPo;
import service.TopicService;

import java.util.List;

public class TopicBean extends PageBean<TopicPo> {
    private TopicService topicService;

    public void setTopicService(TopicService topicService) {
        this.topicService = topicService;
    }

    @Override
    @JSON
    public List<TopicPo> getData() {
        return super.getData();
    }

    @Override
    public String execute() throws Exception {
        setData(topicService.getAll());
        return super.execute();
    }
}
