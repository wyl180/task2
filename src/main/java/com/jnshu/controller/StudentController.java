package com.jnshu.controller;

import com.jnshu.pojo.Student;
import com.jnshu.result.Result;
import com.jnshu.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

/**
 * @author wyl
 * @version 1.0
 * @date 2020/7/7 17:18
 * @ClassName StudentController
 */
@Controller
@RequestMapping("/student")
public class StudentController {
    private static final Logger logger=LoggerFactory.getLogger(StudentController.class);
    @Autowired
    private StudentService studentService;
    @Autowired
    private MessageSource messageSource;
    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    Map<String, Object> post(@RequestBody Student student) {
        if (student.getName() == null || student.getName().equals("")) {
            return Result.set(400, messageSource.getMessage("name.null",
                    null, null));
        }else if(studentService.selectById(student.getId())!=null&&student.getId()==studentService.selectById(student.getId()).getId()){
            //判断id是否重复。
            return Result.set(405,messageSource.getMessage("id.repeat",new Integer[]{student.getId()},null));
        }

        studentService.insertStudent(student);
        return Result.set(200, "添加成功", student);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, consumes = "application/json")
    public @ResponseBody
    Map<String, Object> get(@PathVariable Integer id) {
        if(studentService.selectById(id)==null){
            return Result.set(404,messageSource.getMessage("id.null",null,null));
        }
        return Result.set(200, "查找学生首页成功", studentService.selectById(id));
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = "application/json")
    public @ResponseBody
    Map<String, Object> put(@RequestBody Student student) {
        //如果入参的学生id不等于数据库里的学生id就返回找不到该学生的信息
        if(studentService.selectById(student.getId())==null||student.getId()!=studentService.selectById(student.getId()).getId()){
            return Result.set(404,messageSource.getMessage("put.null",null,null));
        }
        studentService.updateById(student);
        return Result.set(200, "更改数据成功");
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public @ResponseBody
    Map<String, Object> delete(@PathVariable int id) {
        if(studentService.deleteById(id)==false){
            return Result.set(404,messageSource.getMessage("delete.null",null,null));
        }
        studentService.deleteById(id);
        return Result.set(200, "删除数据成功");
    }
}
