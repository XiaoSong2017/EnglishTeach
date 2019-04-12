package dao.implement;

import dao.ComponentDao;
import org.springframework.transaction.annotation.Transactional;
import po.ComponentPo;
@Transactional
public class ComponentDaoImpl extends BaseDaoImpl<ComponentPo> implements ComponentDao {
    @Override
    public ComponentPo getObjectByExamIdAndProblemId(int examinationById, int problemId) {
        return (ComponentPo) getSessionFactory().getCurrentSession().createQuery("select en from ComponentPo en where en.eId=?1 and en.qId=?2").setParameter(1,examinationById).setParameter(2,problemId).getSingleResult();
    }
}
