package top.criswjh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author wjh
 */
@SpringBootApplication
@EnableTransactionManagement
public class ClassroomServeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClassroomServeApplication.class, args);
    }

}
