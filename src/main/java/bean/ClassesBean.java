package bean;

import dao.ClassesDao;
import org.apache.struts2.json.annotations.JSON;
import po.ClassesPo;

import javax.persistence.Transient;
import java.util.List;

public class ClassesBean extends PageBean<ClassesPo> {
    private ClassesDao classesDao;
    private String id;

    public void setClassesDao(ClassesDao classesDao) {
        this.classesDao = classesDao;
    }

    public void setId(String id) {
        this.id = id;
    }

    @JSON(serialize = false)
    public ClassesDao getClassesDao() {
        return classesDao;
    }

    @JSON(serialize = false)
    public String getId() {
        return id;
    }

    @Override
    @JSON
    public List<ClassesPo> getData() {
        super.setData(classesDao.getListBySpecialtyId(id));
        return super.getData();
    }

    @Override
    public String execute() throws Exception {
        return super.execute();
    }
}
