package dao.implement;

import dao.ElectiveCourseDao;
import org.springframework.transaction.annotation.Transactional;
import po.CoursePo;
import po.ElectiveCoursePo;
import po.StudentPo;
import po.TeachingPo;

import java.util.List;
@Transactional
public class ElectiveCourseDaoImpl extends BaseDaoImpl<ElectiveCoursePo> implements ElectiveCourseDao {
    @Override
    public List<CoursePo> getCoursesByStudentId(String id) {
        return getSessionFactory().getCurrentSession().createQuery("select en.teachingByEId.courseByCId from ElectiveCoursePo en where en.studentBySId.id=?1").setParameter(1,id).getResultList();
    }

    @Override
    public void saveElectiveCoursesByAcademyId(String teachingId, String academyId) {
        List<StudentPo> studentPos=getSessionFactory().getCurrentSession().createQuery("select en from StudentPo en where en.classesByClazz.specialtyBySpecialty.academyByAcademy.id=?1").setParameter(1,academyId).getResultList();
        saveElectiveCourseByStudents(teachingId, studentPos);
    }

    @Override
    public void saveElectiveCoursesBySpecialtyId(String teachingId, String specialtyId) {
        List<StudentPo> studentPos=getSessionFactory().getCurrentSession().createQuery("select en from StudentPo en where en.classesByClazz.specialtyBySpecialty.id=?1").setParameter(1,Integer.valueOf(specialtyId)).getResultList();
        saveElectiveCourseByStudents(teachingId, studentPos);
    }

    private void saveElectiveCourseByStudents(String teachingId, List<StudentPo> studentPos) {
        Integer teaching=Integer.valueOf(teachingId);
        List<TeachingPo> teachingPos=getSessionFactory().getCurrentSession().createQuery("select en from TeachingPo en where en.id=?1").setParameter(1,teaching).getResultList();
        int size=0;
        for(StudentPo studentPo:studentPos){
            for(TeachingPo teachingPo:teachingPos){
                ElectiveCoursePo electiveCoursePo=new ElectiveCoursePo();
                electiveCoursePo.setTeachingByEId(teachingPo);
                electiveCoursePo.setStudentBySId(studentPo);
                save(electiveCoursePo);
                if(size++%20==0){
                    getSessionFactory().getCurrentSession().flush();
                    getSessionFactory().getCurrentSession().clear();
                }
            }
        }
    }

    @Override
    public void saveElectiveCoursesByClassesId(String teachingId, String classesId) {
        List<StudentPo> studentPos=getSessionFactory().getCurrentSession().createQuery("select en from StudentPo en where en.classesByClazz.id=?1").setParameter(1,Integer.valueOf(classesId)).getResultList();
        saveElectiveCourseByStudents(teachingId, studentPos);
    }

    @Override
    public void saveElectiveCourseByStudentId(String teachingId, String studentId) {
        List<StudentPo> studentPos=getSessionFactory().getCurrentSession().createQuery("select en from StudentPo en where en.id=?1").setParameter(1,studentId).getResultList();
        saveElectiveCourseByStudents(teachingId, studentPos);
    }

}
