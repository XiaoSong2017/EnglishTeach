package dao;

import po.CoursePo;
import po.TeachingPo;

import java.util.List;
public interface TeachingDao extends BaseDao<TeachingPo> {
    public List<CoursePo> getCourseByTeacherId(String id);

    TeachingPo getTeachingByCourseIdAndTeacherId(String teacherId, String courseId);
}
