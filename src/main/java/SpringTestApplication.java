import controller.SampleController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author shenshuwen
 * @date 18/7/12
 */
@SpringBootApplication
public class SpringTestApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SampleController.class, args);
    }

}
