package dao;

import org.springframework.transaction.annotation.Transactional;
import po.ExaminationPaperPo;

import java.util.List;

/**
 * (ExaminationPaper)表数据库访问层
 *
 * @author wangsong
 * @since 2019-03-19 19:40:20
 */
@Transactional
public interface ExaminationPaperDao extends BaseDao<ExaminationPaperPo> {

    List<ExaminationPaperPo> getExaminationPaperByTeacherId(String teacherId,String type);
}