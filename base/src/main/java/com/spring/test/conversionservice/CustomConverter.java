package com.spring.test.conversionservice;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

/**
 * @Decription TODO
 * @Author yangsj
 * @Date 2019/12/12 10:58
 **/
public class CustomConverter implements Converter<String, Student> {
    @Override
    public Student convert(String source) {
        if (StringUtils.hasLength(source)) {
            String[] sources = source.split("#");
            Student testBean = new Student();
            testBean.setAge(Integer.parseInt(sources[0]));
            testBean.setName(sources[1]);
            return testBean;
        }
        return null;
    }
}
