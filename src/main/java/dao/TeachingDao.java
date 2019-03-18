package dao;

import org.springframework.transaction.annotation.Transactional;
import po.CoursePo;
import po.TeachingPo;

import java.util.List;
@Transactional
public interface TeachingDao extends BaseDao<TeachingPo> {
    public List<CoursePo> getCourseByTeacherId(String id);

    TeachingPo getTeachingByCourseIdAndTeacherId(String teacherId, String courseId);
}
