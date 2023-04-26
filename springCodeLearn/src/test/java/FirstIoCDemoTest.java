import com.ocean.entity.PeopleBean;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class FirstIoCDemoTest {
    @Test
    public void testIoC() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("first-ioc.xml");
        PeopleBean people = context.getBean("people", PeopleBean.class);
        people.setName("ocean");
        System.out.println(people.getName());
    }
}
