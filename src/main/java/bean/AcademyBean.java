package bean;

import dao.AcademyDao;
import org.apache.struts2.json.annotations.JSON;
import po.AcademyPo;

import java.util.List;

public class AcademyBean extends PageBean<AcademyPo> {
    private AcademyDao academyDao;

    public void setAcademyDao(AcademyDao academyDao) {
        this.academyDao = academyDao;
    }
    @Override
    public String execute() throws Exception {
        super.setData(academyDao.getAll(AcademyPo.class));
        return super.execute();
    }

    @Override
    @JSON
    public List<AcademyPo> getData() {
        return super.getData();
    }
}
