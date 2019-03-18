package bean;

import dao.SpecialtyDao;
import org.apache.struts2.json.annotations.JSON;
import po.AcademyPo;
import po.SpecialtyPo;

import java.util.List;

public class SpecialtyBean extends PageBean<SpecialtyPo> {
    private SpecialtyDao specialtyDao;
    private String id;
    public void setSpecialtyDao(SpecialtyDao specialtyDao) {
        this.specialtyDao = specialtyDao;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String execute() throws Exception {
        super.setData(specialtyDao.getListByAcademyId(id));
        return super.execute();
    }
    @Override
    @JSON
    public List<SpecialtyPo> getData() {
        return super.getData();
    }
}
