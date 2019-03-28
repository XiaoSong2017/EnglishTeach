package dao.implement;

import dao.TopicDao;
import org.springframework.transaction.annotation.Transactional;
import po.TopicPo;
@Transactional
public class TopicDaoImpl extends BaseDaoImpl<TopicPo> implements TopicDao {
}
