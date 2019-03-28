package dao;

import org.springframework.transaction.annotation.Transactional;
import po.TopicPo;
@Transactional
public interface TopicDao extends BaseDao<TopicPo> {
}
