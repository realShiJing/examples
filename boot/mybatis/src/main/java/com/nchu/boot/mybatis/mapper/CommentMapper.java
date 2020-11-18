package com.nchu.boot.mybatis.mapper;

import com.nchu.boot.mybatis.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

//@Mapper //标识该接口是mybatis的接口文件，并且让springboot能够扫描到该接口，生成该接口的代理对象，存到容器中
public interface CommentMapper {
    /**
     * @Description  根据id查询文章
     * @Author yangsj
     * @Date 2020/11/18 上午11:21
     **/
    @Select("SELECT * FROM t_comment WHERE id = #{id}")
    Comment selectCommentByid(Integer id);
}
