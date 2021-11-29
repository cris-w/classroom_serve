package top.criswjh.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author wjh
 * @date 2021/11/29 6:49 下午
 */

@Configuration
@EnableSwagger2
@EnableKnife4j
public class Knife4jConfiguration {

    @Bean(value = "defaultApi2")
    public Docket defaultApi2() {
        Docket docket=new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .description("# 翻转课堂-API")
                        .termsOfServiceUrl("http://criswjh.top/")
                        .contact(new Contact("吴俊辉","www.baidu.com","1508710375@qq.com"))
                        .version("1.0")
                        .build())
                //分组名称
                .groupName("翻转课堂-API")
                .select()
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage("top.criswjh.controller"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }
}
