package service;

import dao.ClassesDao;
import dao.StudentDao;
import dao.TeacherDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import po.ClassesPo;
import po.StudentPo;
import po.TeacherPo;

import java.util.List;

@Service
public class UserService {
    private StudentDao studentDao;
    private StudentPo studentPo;
    private TeacherDao teacherDao;
    private ClassesDao classesDao;

    public StudentPo getStudentPo() {
        return studentPo;
    }

    public TeacherPo getTeacherPo() {
        return teacherPo;
    }

    private TeacherPo teacherPo;

    public void setClassesDao(ClassesDao classesDao) {
        this.classesDao = classesDao;
    }

    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public void setTeacherDao(TeacherDao teacherDao) {
        this.teacherDao = teacherDao;
    }
    public boolean validStudentLogin(String name,String password){
        studentPo=studentDao.getById(StudentPo.class,name);
        if (studentPo==null)return false;
        return studentPo.getPassword().equals(password);
    }
    public boolean validTeacherLogin(String name,String password){
        teacherPo =teacherDao.getById(TeacherPo.class,name);
        if (teacherPo==null)return false;
        return teacherPo.getPassword().equals(password);
    }

    @Transactional
    public void addStudent(String id, String name, String classes) {
        StudentPo studentPo=new StudentPo();
        studentPo.setId(id);
        studentPo.setName(name);
        studentPo.setPassword("123456");
        studentPo.setClassesByClazz(classesDao.getById(ClassesPo.class,Integer.valueOf(classes)));
        studentDao.save(studentPo);
    }

    @Transactional
    public void deleteStudent(String id){
        studentDao.delete(StudentPo.class,id);
    }

    @Transactional
    public void updateStudent(String id, String name, String classes,String password) {
        StudentPo studentPo=studentDao.getById(StudentPo.class,id);
        if(studentPo==null)studentPo=new StudentPo();
        studentPo.setId(id);
        studentPo.setName(name);
        studentPo.setPassword(password);
        studentPo.setClassesByClazz(classesDao.getById(ClassesPo.class,Integer.valueOf(classes)));
        studentDao.saveOrUpdate(studentPo);
    }

    public List<StudentPo> getAllStudent(){
        return studentDao.getAll(StudentPo.class);
    }

    public List<TeacherPo> getAllTeacher(){
        return teacherDao.getAll(TeacherPo.class);
    }
}
