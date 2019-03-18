package bean;

import dao.StudentDao;
import org.apache.struts2.json.annotations.JSON;
import po.StudentPo;

import java.util.List;

public class StudentBean extends PageBean<StudentPo> {
    StudentDao studentDao;
    String id;

    @JSON(serialize = false)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @JSON
    @Override
    public List<StudentPo> getData() {
        setData(studentDao.getStudentsByClassId(id));
        return super.getData();
    }
}
