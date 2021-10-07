package com.sort.sortcore.security;

import com.sort.sortcore.security.jwt.AuthEntryPointJwt;
import com.sort.sortcore.security.jwt.AuthTokenFilter;
import com.sort.sortcore.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        // securedEnabled = true,
        // jsr250Enabled = true,
        prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    String[] whitelistResources = new String[]{
            "/", "/login", "/api/auth/**", "/h2-console/**", "/resources/**", "/static/**", "/templates/**", "/api/**", "/css/**", "/js/**"
    };

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests().antMatchers(whitelistResources).permitAll()
                .antMatchers("/api/sort/**", "/api/test/**").permitAll()
                .anyRequest().authenticated();
        http.headers().frameOptions().disable();

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    /*@Autowired
    private CustomOAuth2UserService oauth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                //.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests().antMatchers(whitelistResources).permitAll()
                .anyRequest().authenticated().and()
                .formLogin().permitAll().loginPage("/login")
                .usernameParameter("email").passwordParameter("pass")
                .defaultSuccessUrl("/").and().oauth2Login().loginPage("/login").userInfoEndpoint()
                .userService(oauth2UserService).and()
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                                        Authentication authentication) throws IOException, ServletException {
                        CustomOAuth2User oauthUser = (CustomOAuth2User) authentication.getPrincipal();
                        //authController.processOAuthPostLogin(oauthUser.getEmail(), oauthUser.getSocialSiteName());
                        userDetailsService.processOAuthPostLogin(oauthUser.getEmail(), oauthUser.getSocialSiteName());
                        response.sendRedirect("/");
                    }
                })
                .and().logout().logoutSuccessUrl("/").permitAll().and()
                .exceptionHandling().accessDeniedPage("/403");

        http.headers().frameOptions().disable();
        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }*/
}