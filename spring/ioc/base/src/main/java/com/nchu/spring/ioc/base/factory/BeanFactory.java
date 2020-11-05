package com.nchu.spring.ioc.base.factory;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Decription 工厂类 使用反射技术 生产对象
 *
 * 任务一：读取解析xml，通过反射技术实例化对象并且存储待用（map集合）
 * 任务二：对外提供获取实例对象的接口（根据id获取）
 *
 * @Author yangsj
 * @Date 2020/11/4 9:34 下午
 **/
public class BeanFactory {
    private static Map<String, Object> MAP = new HashMap<>();
    // 读取解析xml，通过反射技术实例化对象并且存储待用（map集合）
    static {
        // 加载 xml
        InputStream inputStream = BeanFactory.class.getClassLoader().getResourceAsStream("beans.xml");
        // 解析 xml
        SAXReader saxReader = new SAXReader();

        try {
            // 获取 xml 文档
            Document document = saxReader.read(inputStream);
            // 获取根节点
            Element rootElement = document.getRootElement();

            List<Element> beanList = rootElement.selectNodes("//bean");
            for (int i = 0; i < beanList.size(); i++) {
                Element element = beanList.get(i);
                // 处理每个bean元素，获取到该元素的id 和 class 属性
                String id = element.attributeValue("id");// transferService

                String clazz = element.attributeValue("class");//com.nchu.spring.ioc.base.service.TransferService
                // 由类的全限定名反射Class
                Class<?> aClass = Class.forName(clazz);

                //创建实例
                Object o = aClass.newInstance();
                //将解析出的对象存放到map集合中
                MAP.put(id,o);
            }


            // 实例化完成之后维护对象的依赖关系，检查哪些对象需要传值进入，根据它的配置，我们传入相应的值
            // 有property子元素的bean就有传值需求
            List<Element> propertyList = rootElement.selectNodes("//property");
            for (int j = 0; j < propertyList.size(); j++) {
                Element element = propertyList.get(j);
                String name = element.attributeValue("name");
                String ref = element.attributeValue("ref");
                // 获取需要被注入的属性实例
                Object property = MAP.get(ref);
                // 找到当前需要被处理依赖关系的bean
                Element parent = element.getParent();
                String parentId = parent.attributeValue("id");
                Object parentBean = MAP.get(parentId);
                //注入属性
                Method[] methods = parentBean.getClass().getMethods();
                for (int i = 0; i < methods.length; i++) {
                    Method method = methods[i];
                    String methodName = method.getName();
                    // 如果是注入属性的方法
                    if(("set"+name).equals(methodName)){
                        method.invoke(parentBean,property);
                    }

                }

            }
        } catch (DocumentException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    public static Object getBean(String id){
        return MAP.get(id);
    }
}
