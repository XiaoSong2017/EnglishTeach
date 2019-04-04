package dao.implement;

import dao.ComponentDao;
import org.springframework.transaction.annotation.Transactional;
import po.ComponentPo;
@Transactional
public class ComponentDaoImpl extends BaseDaoImpl<ComponentPo> implements ComponentDao {
}
