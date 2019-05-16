package dao.implement;

import dao.ComponentDao;
import po.ComponentPo;

import java.util.List;

public class ComponentDaoImpl extends BaseDaoImpl<ComponentPo> implements ComponentDao {
    @Override
    public ComponentPo getObjectByExamIdAndProblemId(int examinationById, int problemId) {
        return (ComponentPo) getSessionFactory().getCurrentSession().createQuery("select en from ComponentPo en where en.eId=?1 and en.qId=?2").setParameter(1,examinationById).setParameter(2,problemId).getSingleResult();
    }

    @Override
    public List<ComponentPo> getObjectsByExamId(int examId) {
        return getSessionFactory().getCurrentSession().createQuery("from ComponentPo en where en.eId=?1 order by en.problemNumber").setParameter(1,examId).getResultList();
    }
}
