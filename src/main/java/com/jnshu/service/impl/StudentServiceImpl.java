package com.jnshu.service.impl;

import com.jnshu.dao.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import com.jnshu.pojo.Student;
import com.jnshu.service.StudentService;
import javax.annotation.Resource;
import java.util.List;

/**
 * @author 韦延伦
 * service具体实现类
 * @version 1.0
 */
@Service
public class StudentServiceImpl implements StudentService {
    @Resource
    private StudentMapper studentMapper;
    @Override
    public int insertStudent(Student student) {
        return studentMapper.insertStudent(student);
    }
    @Override
    public boolean deleteById(int id) {
        return studentMapper.deleteById(id);
    }
    @Override
    public boolean updateById(Student student) {
        return studentMapper.updateById(student);
    }
    @Override
    public List<Student> selectAll() {
        return studentMapper.selectAll();
    }
    @Override
    public Student selectById(int id) {
        return studentMapper.selectById(id);
    }
    @Override
    public List<Student> selectByNumber(String number) {
        return studentMapper.selectByNumber(number);
    }
    @Override
    public List<Student> selectByName(String name) {
        return studentMapper.selectByName(name);
    }
}
