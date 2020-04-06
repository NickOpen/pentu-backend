package com.pentu.config;


import com.pentu.utils.jackson.JacksonMessageConverter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy
@EnableSpringDataWebSupport
public class MVCConfig implements WebMvcConfigurer {

    /**
     * HTTP消息转换器
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        //JSON转换
        converters.add(JacksonMessageConverter.getMessageConverter());
        //XML转换
        converters.add(new Jaxb2RootElementHttpMessageConverter());
        //String转换
        converters.add(new StringHttpMessageConverter());
    }

    /**
     * 开启异步支持
     */
    @Bean
    public AsyncSupportConfigurer asyncSupportConfigurer() {
        return new AsyncSupportConfigurer();
    }

    /**
     * 线程池配置
     */
    @Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(20);
        executor.setQueueCapacity(50);
        executor.setThreadNamePrefix("sync-thread-");
        executor.initialize();
        return executor;
    }

    @Bean
    @SuppressWarnings(value = "unchecked")
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setForceEncoding(true);
        characterEncodingFilter.setEncoding(StandardCharsets.UTF_8.name());
        registrationBean.setFilter(characterEncodingFilter);
        return registrationBean;
    }
}
