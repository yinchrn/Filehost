package com.yinchrn.demo5.config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@EnableWebSecurity
public class SecurityConfig implements WebMvcConfigurer {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        //
        httpSecurity
                .formLogin()
                .usernameParameter("username")
                .passwordParameter("password")
                .loginPage("/")
                .loginProcessingUrl("/doLogin")
                .successHandler(MySuccessHandlerSimple())
                .failureHandler(MyFailureHandlerSimple())
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .permitAll()


                .and()
                .authorizeHttpRequests()
                .requestMatchers("/css/**","/fonts/**","/js/**","/static/**","/User/reg","/index.html").permitAll()
                .anyRequest().authenticated()


                .and()
                .rememberMe()
                .rememberMeParameter("remember")
                .tokenValiditySeconds(60*60)
                .and().csrf().disable().exceptionHandling().accessDeniedHandler(MyAccessDeniedHandlerSimple());

        return httpSecurity.build();
    }

    //指定密码加密器后不需要在加密后的密码前指定加密类型 exp:{bcrypt}
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public MySuccessHandler MySuccessHandlerSimple(){
        return new MySuccessHandler();
    }
    @Bean
    public MyFailureHandler MyFailureHandlerSimple(){
        return new MyFailureHandler();
    }
    @Bean
    public MyAccessDeniedHandler MyAccessDeniedHandlerSimple(){
        return new MyAccessDeniedHandler();
    }

}