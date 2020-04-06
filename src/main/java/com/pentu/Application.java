package com.pentu;

import com.pentu.config.properties.EmailProperties;
import com.pentu.constants.base.SystemConstants;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.TimeZone;


@SpringBootApplication(scanBasePackages = {SystemConstants.COMPONENT_BASE_PACKAGES})
@EnableConfigurationProperties({EmailProperties.class})
public class Application extends SpringBootServletInitializer {

    @EventListener(ContextRefreshedEvent.class)
    public void contextRefreshedEvent() {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
    }

    /**
     * Used when run as JAR
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    /**
     * Used when run as WAR
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return super.configure(builder);
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

}
