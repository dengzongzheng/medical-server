package com.dzz.medical.config.swagger;

import io.swagger.annotations.Api;
import java.util.ArrayList;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger 配置
 *
 * @author dzz
 * @version 1.0.0
 * @since 2019年08月06 18:46
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${swagger.enable}")
    private Boolean swaggerEnable;



    @Bean("SuperviseUserApis")
    public Docket superviseUserApis() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("监督用户模块")
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.regex("/api/supervise.*"))
                .build()
                .apiInfo(apiInfo())
                .securitySchemes(new ArrayList<>(
                        Collections.singletonList(new ApiKey("Bearer %token", "Authorization", "Header"))))
                .enable(swaggerEnable);
    }


    @Bean("SystemUserApis")
    public Docket userApis() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("系统用户模块")
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.regex("/api/system.*"))
                .build()
                .apiInfo(apiInfo())
                .securitySchemes(new ArrayList<>(
                        Collections.singletonList(new ApiKey("Bearer %token", "Authorization", "Header"))))
                .enable(swaggerEnable);
    }


    /**
     * 接口文档基本说明
     * @return 接口文档说明
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("xxx系统平台接口文档")
                .description("提供子模块1/子模块2/子模块3的文档")
                .version("1.0.0")
                .contact(new Contact(null, null, "dengzongzong@gmail.com"))
                .build();
    }

}
