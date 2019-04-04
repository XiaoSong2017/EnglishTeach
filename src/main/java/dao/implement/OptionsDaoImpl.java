package dao.implement;

import dao.OptionsDao;
import org.springframework.transaction.annotation.Transactional;
import po.OptionsPo;
@Transactional
public class OptionsDaoImpl extends BaseDaoImpl<OptionsPo> implements OptionsDao {
}
