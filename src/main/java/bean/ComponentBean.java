package bean;

import dao.ComponentDao;
import org.apache.struts2.json.annotations.JSON;
import po.ComponentPo;

import java.util.List;

public class ComponentBean extends PageBean<ComponentPo> {
    private ComponentDao componentDao;
    private int examId;
    public void setComponentDao(ComponentDao componentDao) {
        this.componentDao = componentDao;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    @JSON
    @Override
    public List<ComponentPo> getData() {
        return super.getData();
    }

    @Override
    public String execute() throws Exception {
        setData(componentDao.getObjectsByExamId(examId));
        return super.execute();
    }
}
