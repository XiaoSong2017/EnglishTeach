package dao;

import org.springframework.transaction.annotation.Transactional;
import po.OptionsPo;
@Transactional
public interface OptionsDao extends BaseDao<OptionsPo> {
}
