package dao;

import po.ExaminationPaperPo;

import java.util.List;

/**
 * (ExaminationPaper)表数据库访问层
 *
 * @author wangsong
 * @since 2019-03-19 19:40:20
 */
public interface ExaminationPaperDao extends BaseDao<ExaminationPaperPo> {

    List<ExaminationPaperPo> getExaminationPaperByTeacherId(String teacherId, String type);

    List<ExaminationPaperPo> getExaminationPaperByCourseId(String courseId, String type);
}