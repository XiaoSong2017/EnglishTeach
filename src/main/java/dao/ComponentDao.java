package dao;

import org.springframework.transaction.annotation.Transactional;
import po.ComponentPo;
@Transactional
public interface ComponentDao extends BaseDao<ComponentPo> {
}
