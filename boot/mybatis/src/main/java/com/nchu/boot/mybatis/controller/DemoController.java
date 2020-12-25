package com.nchu.boot.mybatis.controller;

import com.nchu.boot.mybatis.mapper.CommentMapper;
import com.nchu.boot.mybatis.pojo.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Decription TODO
 * @Author yangsj
 * @Date 2020/12/25 11:05
 **/
@RestController()
@RequestMapping("/mybatis")
public class DemoController {
    @Autowired
    private CommentMapper commentMapper;

    @GetMapping("/comment")
    public Comment testCommentMapper(){
        Comment comment = commentMapper.selectCommentByid(1);
       return comment;
    }
}
