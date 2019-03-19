package bean;

import org.apache.struts2.json.annotations.JSON;
import po.ElectiveCoursePo;
import service.ElectiveCourseService;

import java.util.List;

public class ElectiveCourseBean extends PageBean<ElectiveCoursePo> {
    private ElectiveCourseService electiveCourseService;
    private String teacherId;

    @JSON(serialize = false)
    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public void setElectiveCourseService(ElectiveCourseService electiveCourseService) {
        this.electiveCourseService = electiveCourseService;
    }

    @Override
    @JSON
    public List<ElectiveCoursePo> getData() {
        setData(electiveCourseService.getElectiveCourseByTeacherId(teacherId));
        return super.getData();
    }
}
