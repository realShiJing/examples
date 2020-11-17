package com.nchu.boot.annotation.register;

import com.nchu.boot.annotation.pojo.Black;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @Decription
 * @Author yangsj
 * @Date 2020/11/16 下午3:27
 **/
public class ColorImportBeanDefinitionRegister implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        registry.registerBeanDefinition("black",new RootBeanDefinition(Black.class));

    }
}
