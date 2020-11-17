package com.nchu.boot.annotation.importselector;

import com.nchu.boot.annotation.pojo.Blue;
import com.nchu.boot.annotation.pojo.Green;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @Decription
 * @Author yangsj
 * @Date 2020/11/16 下午3:23
 **/
public class ColorImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{Blue.class.getName(), Green.class.getName()};
    }
}
