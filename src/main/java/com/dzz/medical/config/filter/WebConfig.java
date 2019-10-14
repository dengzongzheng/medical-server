package com.dzz.medical.config.filter;


import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * web 相关配置
 * @author dzz
 * @since  2016年09月21 上午11:26
 * @version 1.0.0
 */
@Configuration
public class WebConfig extends WebMvcConfigurationSupport {



    /**
     * xss过滤,遇见特征属性字将会转义不中断请求
     * @param filterService 过滤器
     * @return 过滤器实体
     */
    @Bean
    public FilterRegistrationBean simpleFilterRegistration(SimpleFilterServiceImpl filterService) {

        FilterRegistrationBean<XssFilter> registration = new FilterRegistrationBean<>();
        registration.setName("simpleFilter");
        registration.setFilter(new XssFilter(filterService));
        registration.addUrlPatterns("/*");
        return registration;
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/templates/**")
                .addResourceLocations("classpath:/templates/");
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        super.addResourceHandlers(registry);

    }



    @Override
    protected void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedHeaders("*")
                .allowedMethods("*");
    }
}
