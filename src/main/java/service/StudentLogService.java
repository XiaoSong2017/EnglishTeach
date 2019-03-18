package service;

import dao.StudentDao;
import dao.StudentLogDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.stereotype.Service;
import po.StudentLogPo;
import po.StudentPo;

import java.sql.Timestamp;

import static com.opensymphony.xwork2.Action.SUCCESS;

@Service
public class StudentLogService {
    private StudentLogDao studentLogDao;
    private StudentDao studentDao;
    private String id;
    private final Log log= LogFactory.getLog(getClass());

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
    public Long save(Timestamp loginTime,Timestamp logoutTime,String id){
        long count=studentLogDao.count(StudentLogPo.class);
        StudentLogPo studentLogPo=new StudentLogPo();
        studentLogPo.setId(++count);
        studentLogPo.setLoginTime(loginTime);
        studentLogPo.setLogoutTime(logoutTime);
        studentLogPo.setStudentBySId(studentDao.getById(StudentPo.class,id));
        studentLogDao.save(studentLogPo);
        return count;
    }
    public String execute() throws Exception {
        log.info("LogId:"+id);
        updateById(new Timestamp(System.currentTimeMillis()),Integer.valueOf(id) );
        return SUCCESS;
    }

    public void updateById(Timestamp logoutTime,long id){
        StudentLogPo studentLogPo=studentLogDao.getById(StudentLogPo.class,id);
        studentLogPo.setLogoutTime(logoutTime);
        studentLogDao.update(studentLogPo);
    }
}
