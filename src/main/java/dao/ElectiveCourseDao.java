package dao;

import org.springframework.transaction.annotation.Transactional;
import po.CoursePo;
import po.ElectiveCoursePo;

import java.util.List;
@Transactional
public interface ElectiveCourseDao extends BaseDao<ElectiveCoursePo> {

    List<CoursePo> getCoursesByStudentId(String id);
    void saveElectiveCoursesByAcademyId(String teachingId,String academyId);
    void saveElectiveCoursesBySpecialtyId(String teachingId,String specialtyId);
    void saveElectiveCoursesByClassesId(String teachingId,String classesId);
    void saveElectiveCourseByStudentId(String teachingId,String studentId);

    List<ElectiveCoursePo> getElectiveCourseByTeacherId(String teacherId);
}
