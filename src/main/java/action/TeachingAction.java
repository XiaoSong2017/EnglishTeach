package action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.json.annotations.JSON;
import service.TeachingService;

public class TeachingAction extends ActionSupport {
    private TeachingService teachingService;
    private String tId;
    private String cId;
    private String usualProportion;
    private String examProportion;
    private String id;
    private String result;
    private final Log log = LogFactory.getLog(getClass());

    private String electiveCourseId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String gettId() {
        return tId;
    }

    public void settId(String tId) {
        this.tId = tId;
    }

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }

    public String getUsualProportion() {
        return usualProportion;
    }

    public void setUsualProportion(String usualProportion) {
        this.usualProportion = usualProportion;
    }

    public String getExamProportion() {
        return examProportion;
    }

    public void setExamProportion(String examProportion) {
        this.examProportion = examProportion;
    }

    public String getElectiveCourseId() {
        return electiveCourseId;
    }

    public void setElectiveCourseId(String electiveCourseId) {
        this.electiveCourseId = electiveCourseId;
    }

    public void setTeachingService(TeachingService teachingService) {
        this.teachingService = teachingService;
    }
    @JSON
    public String getResult() {
        return result;
    }
    public void setResult(String result) {
        this.result = result;
    }

    @JSON
    public String deleteTeachingById() throws Exception {
        log.info(electiveCourseId);
        teachingService.deleteTeachingById(Integer.valueOf(electiveCourseId));
        return SUCCESS;
    }

    public String saveTeaching() throws Exception {
        if(teachingService.getTeachingByCourseIdAndTeacherId(tId,cId)==null){
            teachingService.saveTeaching(cId, tId, usualProportion, examProportion);
            setResult(String.valueOf(teachingService.getTeachingByCourseIdAndTeacherId(tId,cId).getId()));
        }else setResult("repeat");
        return SUCCESS;
    }

    @JSON
    public String updateTeachingById() throws Exception {
        teachingService.updateTeachingById(id, cId, tId, usualProportion, examProportion);
        return SUCCESS;
    }
}
