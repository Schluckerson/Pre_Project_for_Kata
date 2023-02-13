import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(AppConfig.class);
        HelloWorld bean1 =
                applicationContext.getBean("helloworld", HelloWorld.class);
        HelloWorld bean2 =
                applicationContext.getBean("helloworld", HelloWorld.class);

        Cat catBean1 = applicationContext.getBean("cat", Cat.class);
        Cat catBean2 = applicationContext.getBean("cat", Cat.class);

        boolean helloworld = (bean1 == bean2);
        boolean cat = (catBean1 == catBean2);

        System.out.println(helloworld);
        System.out.println(cat);
    }
}