package com.nchu.boot.mybatis;

import com.nchu.boot.mybatis.mapper.ArticleMapper;
import com.nchu.boot.mybatis.mapper.CommentMapper;
import com.nchu.boot.mybatis.pojo.Article;
import com.nchu.boot.mybatis.pojo.Comment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Decription
 * @Author yangsj
 * @Date 2020/11/18 上午11:23
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootApplicationTest {

    @Autowired
    private CommentMapper commentMapper;

    @Test
    public void testCommentMapper(){
        Comment comment = commentMapper.selectCommentByid(1);
        System.out.println(comment);
    }

    @Autowired
    private ArticleMapper articleMapper;

    @Test
    public void testArticleMapper(){
        Article article = articleMapper.selectArticleById(1);
        System.out.println(article);
    }
}
