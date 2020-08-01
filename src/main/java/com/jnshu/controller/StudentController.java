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
    @Autowired
    private StudentService studentService;
    @Autowired
    private MessageSource messageSource;
    /**
     * post接口
     * @param student
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    Map<String, Object> post(@RequestBody Student student) {
        //判断学生姓名是否为空
        if(student.getName().equals("")){
            return Result.set(404,messageSource.getMessage("name.null",null,null));
        }else {
            studentService.insertStudent(student);
            return Result.set(200, "添加成功", student);
        }
    }
    /**
     * get接口
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, consumes = "application/json")
    public @ResponseBody
    Map<String, Object> get(@PathVariable Integer id) {
        //判断查找的学生是否存在
        Student student=studentService.selectById(id);
        if(null==student){
            return Result.set(404, messageSource.getMessage("id.null",null,null));
        }else {
            return Result.set(200, "查找学生首页成功", student);
        }
    }

    /**
     * put接口
     * @param student
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT, consumes = "application/json")
    public @ResponseBody
    Map<String, Object> put(@RequestBody Student student) {
        //判断要更新的学生是否存在，存在就更新数据
        if(null==studentService.selectById(student.getId())){
            return Result.set(404,messageSource.getMessage("put.null",null,null));
        }else {
            studentService.updateById(student);
            return Result.set(200, "更改数据成功");
        }
    }

    /**
     * delete接口
     * @param id
     * @return
     */
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public @ResponseBody
    Map<String, Object> delete(@PathVariable int id) {
        if(null==studentService.selectById(id)){
            return Result.set(404,messageSource.getMessage("delete.null",null,null));
        }else {
            studentService.deleteById(id);
            return Result.set(200, "删除数据成功");
        }
    }
}
