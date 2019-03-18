package dao.implement;

import bean.PageBean;
import dao.FilesDao;
import org.springframework.transaction.annotation.Transactional;
import po.TeachResourcePo;

import java.util.List;

@Transactional
public class FilesDaoImpl extends BaseDaoImpl<TeachResourcePo> implements FilesDao {
    @Override
    public PageBean<TeachResourcePo> getByUser(Class<TeachResourcePo> filesPoClass, int pageNumber,int pageSize, String id) {
        return findByPage("select en from TeachResourcePo en where en.teacherByUploadUser.id=?1",pageNumber,pageSize,id);
    }
}
