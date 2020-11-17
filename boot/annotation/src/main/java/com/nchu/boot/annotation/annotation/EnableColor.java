package com.nchu.boot.annotation.annotation;

import com.nchu.boot.annotation.config.ColorRegisterConfiguration;
import com.nchu.boot.annotation.importselector.ColorImportSelector;
import com.nchu.boot.annotation.pojo.Red;
import com.nchu.boot.annotation.register.ColorImportBeanDefinitionRegister;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
// @Import 可以传入四种类型：普通类、配置类、ImportSelector 的实现类，ImportBeanDefinitionRegistrar 的实现类
@Import({Red.class, ColorRegisterConfiguration.class, ColorImportSelector.class, ColorImportBeanDefinitionRegister.class})
public @interface EnableColor {
}
