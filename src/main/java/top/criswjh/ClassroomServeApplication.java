package top.criswjh;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author wjh
 */
@MapperScan("top.criswjh.mapper")
@SpringBootApplication
public class ClassroomServeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClassroomServeApplication.class, args);
    }

}
