package com.example.demo.security;

import com.example.demo.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/list_pacientes.html", "/post_paciente.html","/list_odontologo.html", "/post_odontologo.html").hasAuthority("ROLE_ADMIN")
                .antMatchers("/list_turnos.html", "post_turno.html").permitAll()
                .anyRequest().authenticated()
                .and().formLogin()
                .and().logout()
                .and().exceptionHandling().accessDeniedPage("/accessDenied.html");
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/list_turnos.html")
//                .hasAnyRole("USER", "ADMIN")
//                .antMatchers("/list_pacientes", "/list_odontologo", "/post_odontologo", "/post_paciente", "/post_turno")
//                .hasRole("ADMIN")
//                .anyRequest()
//                .authenticated().and().formLogin();
//    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(usuarioService);
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        return provider;
    }




}
