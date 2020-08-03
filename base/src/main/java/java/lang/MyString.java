package java.lang;

/**
 * @Decription 模拟在 java.lang 包下新建自己的类
 * @Author yangsj
 * @Date 2020/8/3 16:38
 **/
public class MyString {

    /**
     * @Description 该包下类的加载会交给启动类加载器，但是启动类加载器不识别
     * 运行该方法时会抛出异常
     *          java.lang.SecurityException: Prohibited package name: java.lang
     *     双亲委派模型保证了应用的安全、防止了代码入侵
     * @Author yangsj
     * @Date 2020/8/3 16:39
     **/
    public static void main(String[] args) {
        System.out.println("hello world!");
    }
}
