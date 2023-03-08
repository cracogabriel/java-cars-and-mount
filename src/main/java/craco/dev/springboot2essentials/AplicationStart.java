package craco.dev.springboot2essentials;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class AplicationStart {

    public static void main(String[] args) {
        SpringApplication.run(AplicationStart.class, args);
    }

}
