package dao;

import po.ElectiveCoursePo;
import vo.RelationShip;

import java.util.List;

public interface ElectiveCourseDao extends BaseDao<ElectiveCoursePo> {

    List getCoursesByStudentId(String id);
    void saveElectiveCoursesByAcademyId(String teachingId,String academyId);
    void saveElectiveCoursesBySpecialtyId(String teachingId,String specialtyId);
    void saveElectiveCoursesByClassesId(String teachingId,String classesId);
    void saveElectiveCourseByStudentId(String teachingId,String studentId);
    /**
     * 得到学生平均成绩
     * @param studentById 学号
     */
    RelationShip getAverageCore(String studentById);
    List<ElectiveCoursePo> getElectiveCourseByTeacherId(String teacherId);
}
