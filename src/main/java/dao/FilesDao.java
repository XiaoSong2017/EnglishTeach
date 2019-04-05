package dao;

import bean.PageBean;
import org.springframework.transaction.annotation.Transactional;
import po.TeachResourcePo;

import java.util.List;

@Transactional
public interface FilesDao extends BaseDao<TeachResourcePo> {
    PageBean<TeachResourcePo> getByUser(Class<TeachResourcePo> filesPoClass, int pageNumber,int pageSize, String id);

    List<TeachResourcePo> getFilesByCourseId(String courseId);
}
