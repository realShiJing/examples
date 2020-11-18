package com.nchu.boot.mybatis.pojo;

/**
 * @Decription 文章
 * @Author yangsj
 * @Date 2020/11/18 上午11:07
 **/
public class Article {

    private Integer id;
    // 文章表头
    private String title;
    // 文章内容
    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
