package dao.implement;

import bean.PageBean;
import dao.FilesDao;
import po.TeachResourcePo;

import java.util.List;

public class FilesDaoImpl extends BaseDaoImpl<TeachResourcePo> implements FilesDao {
    @Override
    public PageBean<TeachResourcePo> getByUser(Class<TeachResourcePo> filesPoClass, int pageNumber,int pageSize, String id) {
        return findByPage("select en from TeachResourcePo en where en.teacherByUploadUser.id=?1",pageNumber,pageSize,id);
    }

    @Override
    public List<TeachResourcePo> getFilesByCourseId(String courseId) {
        return getSessionFactory().getCurrentSession().createQuery("select en from TeachResourcePo en where en.courseByCourse.id=?1").setParameter(1,courseId).getResultList();
    }
}
