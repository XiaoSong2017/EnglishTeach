package dao;

import po.ComponentPo;

import java.util.List;

public interface ComponentDao extends BaseDao<ComponentPo> {
    ComponentPo getObjectByExamIdAndProblemId(int examinationById, int problemId);

    List<ComponentPo> getObjectsByExamId(int examId);
}
