package service;

import dao.ElectiveCourseDao;
import dao.StudentDao;
import dao.TeachingDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import po.ElectiveCoursePo;
import po.StudentPo;
import po.TeachingPo;

import java.util.List;

@Service
public class ElectiveCourseService {
    private ElectiveCourseDao electiveCourseDao;
    private StudentDao studentDao;
    private TeachingDao teachingDao;

    public void setElectiveCourseDao(ElectiveCourseDao electiveCourseDao) {
        this.electiveCourseDao = electiveCourseDao;
    }

    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public void setTeachingDao(TeachingDao teachingDao) {
        this.teachingDao = teachingDao;
    }

    @Transactional(readOnly = true)
    public List<ElectiveCoursePo> getAll() {
        return electiveCourseDao.getAll(ElectiveCoursePo.class);
    }

    @Transactional
    public void deleteElectiveCourseById(int electiveCourseId) {
        electiveCourseDao.delete(ElectiveCoursePo.class, electiveCourseId);
    }

    @Transactional
    public boolean updateElectiveCourseById(String electiveCourseId, String studentId, String teacherId, String courseId) {
        TeachingPo teachingPo = teachingDao.getTeachingByCourseIdAndTeacherId(teacherId, courseId);
        if (teachingPo == null) return false;
        else {
            ElectiveCoursePo electiveCoursePo = electiveCourseDao.getById(ElectiveCoursePo.class, Integer.valueOf(electiveCourseId));
            electiveCoursePo.setStudentBySId(studentDao.getById(StudentPo.class, studentId));
            electiveCoursePo.setTeachingByEId(teachingPo);
            electiveCourseDao.update(electiveCoursePo);
            return true;
        }
    }

    private void setElectiveCourse(ElectiveCoursePo electiveCoursePo, String studentId, String teachingId, String usualGrade, String examGrade, String grade) {
        electiveCoursePo.setStudentBySId(studentDao.getById(StudentPo.class, studentId));
        electiveCoursePo.setTeachingByEId(teachingDao.getById(TeachingPo.class, Integer.valueOf(teachingId)));
        electiveCoursePo.setUsualGrade(Integer.valueOf(usualGrade));
        electiveCoursePo.setExamGrade(Integer.valueOf(examGrade));
        electiveCoursePo.setGrade(Integer.valueOf(grade));
    }

    @Transactional
    public void addElectiveCourseById(String studentId, String teachingId, String usualGrade,
                                      String examGrade, String grade) {
        ElectiveCoursePo electiveCoursePo = new ElectiveCoursePo();
        setElectiveCourse(electiveCoursePo, studentId, teachingId, usualGrade, examGrade, grade);
        electiveCourseDao.save(electiveCoursePo);
    }

    @Transactional(readOnly = true)
    public List<ElectiveCoursePo> getElectiveCourseByTeacherId(String teacherId) {
        return electiveCourseDao.getElectiveCourseByTeacherId(teacherId);
    }

    @Transactional
    public void saveElectiveCourseByAcademyId(String s, String teacherId, String courseId) {
        TeachingPo teachingPo=teachingDao.getTeachingByCourseIdAndTeacherId(teacherId,courseId);
        if(teachingPo!=null){
            electiveCourseDao.saveElectiveCoursesByAcademyId(String.valueOf(teachingPo.getId()), s);
        }
    }

    @Transactional
    public void saveElectiveCourseBySpecialtyId(String s, String teacherId, String courseId) {
        TeachingPo teachingPo = teachingDao.getTeachingByCourseIdAndTeacherId(teacherId, courseId);
        if (teachingPo != null) {
            electiveCourseDao.saveElectiveCoursesBySpecialtyId(String.valueOf(teachingPo.getId()), s);
        }
    }

    @Transactional
    public void saveElectiveCourseByClassesId(String s, String teacherId, String courseId) {
        TeachingPo teachingPo = teachingDao.getTeachingByCourseIdAndTeacherId(teacherId, courseId);
        if (teachingPo != null) {
            electiveCourseDao.saveElectiveCoursesByClassesId(String.valueOf(teachingPo.getId()), s);
        }
    }

    @Transactional
    public void saveElectiveCourseByStudentId(String s, String teacherId, String courseId) {
        TeachingPo teachingPo = teachingDao.getTeachingByCourseIdAndTeacherId(teacherId, courseId);
        if (teachingPo != null) {
            electiveCourseDao.saveElectiveCourseByStudentId(String.valueOf(teachingPo.getId()), s);
        }
    }
}
