package dao;

import org.springframework.transaction.annotation.Transactional;
import po.ComponentPo;

import java.util.List;

@Transactional
public interface ComponentDao extends BaseDao<ComponentPo> {
    ComponentPo getObjectByExamIdAndProblemId(int examinationById, int problemId);

    List<ComponentPo> getObjectsByExamId(int examId);
}
