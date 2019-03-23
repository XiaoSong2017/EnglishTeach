package action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.json.annotations.JSON;
import service.ProblemService;

public class ProblemAction extends ActionSupport {
    private int problemId;
    private ProblemService problemService;
    private String requestCode;
    private String resultCode;

    public void setProblemService(ProblemService problemService) {
        this.problemService = problemService;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public void setRequestCode(String requestCode) {
        this.requestCode = requestCode;
    }

    @JSON(serialize = false)
    public int getProblemId() {
        return problemId;
    }

    public void setProblemId(int problemId) {
        this.problemId = problemId;
    }

    public void deleteProblemById(){
        try {
            problemService.deleteProblemById(problemId);
            resultCode=SUCCESS;
        }catch (Exception e){
            resultCode=ERROR;
        }
    }
    @JSON
    public String getResultCode() {
        return resultCode;
    }

    @Override
    public String execute() throws Exception {
        switch (requestCode){
            case "delete":deleteProblemById();
            break;
            default:
        }
        return super.execute();
    }
}
