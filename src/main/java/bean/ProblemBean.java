package bean;

import org.apache.struts2.json.annotations.JSON;
import po.ProblemPo;
import service.ProblemService;

import java.util.List;

public class ProblemBean extends PageBean<ProblemPo> {
    private ProblemService problemService;

    public void setProblemService(ProblemService problemService) {
        this.problemService = problemService;
    }

    @Override
    @JSON
    public List<ProblemPo> getData() {
        return super.getData();
    }

    @Override
    public String execute() throws Exception {
        setData(problemService.getAll());
        return super.execute();
    }
}
