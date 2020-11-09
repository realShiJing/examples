import com.nchu.spring.ioc.xml.dao.AccountDao;
import com.nchu.spring.ioc.xml.pojo.Account;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;

/**
 * @Decription Jave EE Spring IOC启动方式
 * @Author yangsj
 * @Date 2020/11/6 2:59 下午
 **/
public class IOCTest {
    @Test
    public void testSpringIoc() throws SQLException {
        // 通过读取classpath下的xml文件来启动容器（xml模式SE应用下推荐）
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

        // 不推荐使用
        //ApplicationContext applicationContext1 = new FileSystemXmlApplicationContext("文件系统的绝对路径");

        AccountDao accountDao = (AccountDao) applicationContext.getBean("accountDao");
        Account account = accountDao.queryAccountByCardNo("6029621011000");

        System.out.println(account);

        // 获取工厂Bean创建的对象
        Account account1 = (Account) applicationContext.getBean("account");

        System.out.println("工厂Bean创建的bean:"+account1);

        //获取工厂Bean，在bean id 前加上 & 标识
        Object bean = applicationContext.getBean("&account");
        System.out.println("工厂Bean:"+bean);


    }
}
