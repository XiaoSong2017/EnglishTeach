package action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.json.annotations.JSON;
import service.ElectiveCourseService;

import java.util.ArrayList;

import static org.hibernate.internal.CoreLogging.logger;

public class ElectiveCourseAction extends ActionSupport {
    private ElectiveCourseService electiveCourseService;
    private String electiveCourseId;
    private String courseId;
    private String studentId;
    private String teacherId;
    private String teachingId;
    private String grade;
    private String usualGrade;
    private String examGrade;
    private ArrayList<String> id;
    private ArrayList<String> level;
    private String resultCode;

    @JSON(serialize = false)
    public String getTeacherId() {
        return teacherId;
    }

    @JSON(serialize = false)
    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    @JSON
    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    @JSON(serialize = false)
    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    @JSON(serialize = false)
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    @JSON(serialize = false)
    public String getTeachingId() {
        return teachingId;
    }

    public void setTeachingId(String teachingId) {
        this.teachingId = teachingId;
    }

    @JSON(serialize = false)
    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @JSON(serialize = false)
    public String getUsualGrade() {
        return usualGrade;
    }

    public void setUsualGrade(String usualGrade) {
        this.usualGrade = usualGrade;
    }

    @JSON(serialize = false)
    public String getExamGrade() {
        return examGrade;
    }

    public void setExamGrade(String examGrade) {
        this.examGrade = examGrade;
    }

    @JSON(serialize = false)
    public String getElectiveCourseId() {
        return electiveCourseId;
    }

    public void setElectiveCourseId(String electiveCourseId) {
        this.electiveCourseId = electiveCourseId;
    }

    public void setElectiveCourseService(ElectiveCourseService electiveCourseService) {
        this.electiveCourseService = electiveCourseService;
    }

    public void setId(ArrayList<String> id) {
        this.id = id;
    }

    public void setLevel(ArrayList<String> level) {
        this.level = level;
    }

    @JSON
    public String deleteElectiveCourseById() throws Exception {
        logger("deleteElectiveCourseById:" + electiveCourseId);
        electiveCourseService.deleteElectiveCourseById(Integer.valueOf(electiveCourseId));
        return SUCCESS;
    }

    public String updateElectiveCourseById() throws Exception {
        resultCode = electiveCourseService.updateElectiveCourseById(electiveCourseId, studentId, teacherId, courseId) ? "true" : "false";
        return SUCCESS;
    }

    @JSON
    public String saveElectiveCourse() throws Exception {
        electiveCourseService.addElectiveCourseById(studentId, teachingId, usualGrade, examGrade, grade);
        return SUCCESS;
    }

    @JSON
    public String saveElectiveCourseByBatch() throws Exception {
        logger("saveElectiveCourseByBatch:"+level);
        for (int i = 0; i < level.size(); ++i) {
            switch (level.get(i)) {
                case "0":
                    electiveCourseService.saveElectiveCourseByAcademyId(id.get(i), teacherId, courseId);
                    break;
                case "1":
                    electiveCourseService.saveElectiveCourseBySpecialtyId(id.get(i), teacherId, courseId);
                    break;
                case "2":
                    electiveCourseService.saveElectiveCourseByClassesId(id.get(i), teacherId, courseId);
                    break;
                default:
                    electiveCourseService.saveElectiveCourseByStudentId(id.get(i), teacherId, courseId);
            }
        }
        return SUCCESS;
    }
}
