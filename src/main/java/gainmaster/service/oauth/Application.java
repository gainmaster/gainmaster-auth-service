package gainmaster.service.oauth;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class Application {

    public static void main(String[] args) {
    	SpringApplication app = new SpringApplication(Application.class);
        app.setShowBanner(false);
    	app.run(args);
    }

}
