package com.nchu.boot.mybatis.mapper;

import com.nchu.boot.mybatis.pojo.Article;
import org.apache.ibatis.annotations.Mapper;

//@Mapper
public interface ArticleMapper {
    Article selectArticleById(Integer id);
}
