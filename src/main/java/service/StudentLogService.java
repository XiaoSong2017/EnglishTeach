package service;

import dao.ElectiveCourseDao;
import dao.StudentDao;
import dao.StudentLogDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.stereotype.Service;
import po.StudentLogPo;
import po.StudentPo;
import vo.RelationShip;

import javax.management.relation.Relation;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.opensymphony.xwork2.Action.SUCCESS;

@Service
public class StudentLogService {
    private StudentLogDao studentLogDao;
    private StudentDao studentDao;
    private final Log log = LogFactory.getLog(getClass());
    private String id;
    private ElectiveCourseDao electiveCourseDao;

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

    public void setStudentLogDao(StudentLogDao studentLogDao) {
        this.studentLogDao = studentLogDao;
    }

    public Long save(Timestamp loginTime, Timestamp logoutTime, String id) {
        StudentLogPo studentLogPo = new StudentLogPo();
        studentLogPo.setLoginTime(loginTime);
        studentLogPo.setLogoutTime(logoutTime);
        studentLogPo.setStudentBySId(studentDao.getById(StudentPo.class, id));
        studentLogDao.save(studentLogPo);
        return studentLogPo.getId();
    }

    public String execute() {
        log.info("LogId:" + id);
        updateById(new Timestamp(System.currentTimeMillis()), Integer.valueOf(id));
        return SUCCESS;
    }

    public void updateById(Timestamp logoutTime, long id) {
        StudentLogPo studentLogPo = studentLogDao.getById(StudentLogPo.class, id);
        studentLogPo.setLogoutTime(logoutTime);
        studentLogDao.update(studentLogPo);
    }

    public void setElectiveCourseDao(ElectiveCourseDao electiveCourseDao) {
        this.electiveCourseDao = electiveCourseDao;
    }

    /**
     * @param studentById 学号
     *                    统计用户在线时长
     */
    private double onlineTime(String studentById) {
        List<StudentLogPo> list = studentLogDao.getByStudentId(studentById);
        double time = 0;
        for (StudentLogPo studentLogPo : list) {
            time += (studentLogPo.getLogoutTime().getTime() - studentLogPo.getLoginTime().getTime()) / 3600000.0;
        }
        return time;
    }

    /**
     * 学习时间分配比例和学习成绩对比
     */
    public List<RelationShip> getStudentOnlineTimeAndCore() {
        List<RelationShip> data = new ArrayList<>();
        for (StudentPo studentPo : studentDao.getAll(StudentPo.class)) {
            data.add(new RelationShip(electiveCourseDao.getAverageCore(studentPo.getId()), onlineTime(studentPo.getId())));
        }
        return data;
    }
}
