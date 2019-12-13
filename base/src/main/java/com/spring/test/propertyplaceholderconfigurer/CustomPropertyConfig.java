package com.spring.test.propertyplaceholderconfigurer;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.Resource;
import org.springframework.util.DefaultPropertiesPersister;
import org.springframework.util.PropertiesPersister;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @Decription PropertyPlaceholderConfigurer 允许我们在 XML 配置文件中使用占位符，
 * 并将这些占位符所代表的资源单独配置到简单的 properties 文件中来加载
 * @Author yangsj
 * @Date 2019/12/11 10:16
 **/
public class CustomPropertyConfig extends PropertyPlaceholderConfigurer {
    private Resource[] locations;

    private PropertiesPersister propertiesPersister = new DefaultPropertiesPersister();

    @Override
    public void setLocations(Resource[] locations) {
        this.locations = locations;
    }

    @Override
    public void setLocalOverride(boolean localOverride) {
        super.setLocalOverride(localOverride);
    }
    /**
     * 覆盖这个方法，根据启动参数，动态读取配置文件
     * @param props
     * @throws IOException
     */
    @Override
    protected void loadProperties(Properties props) throws IOException {
        if(locations != null){
            // locations 里面就已经包含了那三个定义的文件
            for (Resource location : locations) {
                InputStream is = null;
                try{
                    String filename = location.getFilename();

                    String env = "application-" + System.getProperty("spring.profiles.active", "dev") + ".properties";

                    if(filename.contains(env)){
                        is = location.getInputStream();
                        //???
                        this.propertiesPersister.load(props,is);
                    }
                }catch (IOException e){
                    System.out.println(e.getMessage());
                }finally {
                    if (is != null)
                        is.close();
                }

            }
        }
    }
}
