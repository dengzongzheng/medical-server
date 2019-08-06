package com.dzz.medical.config.filter;


import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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
        super.addResourceHandlers(registry);
        registry.addResourceHandler("classpath:/**");
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**");
            }
        };
    }

}
