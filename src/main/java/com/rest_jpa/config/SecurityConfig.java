package com.rest_jpa.config;


import com.rest_jpa.security.JwtConfigurer;
import com.rest_jpa.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String ADMIN_ENDPOINT = "/api/v1/admin/**";
    private static final String LOGIN_ENDPOINT = "/api/v1/auth/signin";
    private static final String REGISTER_ENDPOINT = "/api/v1/auth/signup";
    private static final String AUTH_ENDPOINT = "/api/v1/auth/**";

    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public SecurityConfig(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
//                .cors().disable()
                .cors().and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(AUTH_ENDPOINT).permitAll()
                .antMatchers(ADMIN_ENDPOINT).hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/users/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .apply(new JwtConfigurer(jwtTokenProvider));
    }

   /* @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:4200").allowCredentials(true);
            }
        };
    }*/

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH");
            }
        };
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

   /* @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .cors().and()
                // csrf для rest дизейблится
//                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//                .and()
                //for rest services (STATELESS - не хранит сессию на бэке, бывает что
                // время жизни токена может быть меньше сессии, а если не хранить сессию и не выбрать
                // remember me , то вообще не даст залогиниться)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/resources/static/**").permitAll()

                //todo открыл для того чтобы можно было дергать api из angular
                .antMatchers("/items/**").permitAll()

//                .antMatchers(HttpMethod.GET, "/users/**").hasRole("ADMIN")
//                .antMatchers(HttpMethod.GET, "/users/**").hasAuthority("READ")
                .antMatchers(HttpMethod.DELETE, "/users/**").hasRole("ADMIN")
//                .antMatchers(HttpMethod.PUT, "/users/**").hasRole("ADMIN")
//                .antMatchers("/**").authenticated()
                .anyRequest().authenticated()
                .and()

//                .httpBasic()
                .formLogin()
//                    .loginPage("/views/form_login.html").permitAll()
                .and()
                // выдает токен и по истечении действия токена, система разлогинит пользователя
                .rememberMe()
                .key("myAppKey")
                .tokenValiditySeconds(60 * 60 * 12)
                .userDetailsService(userDetailsService)
                .and()

                .logout();

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    */
}
