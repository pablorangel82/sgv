package br.com.sgv;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author Pablo Rangel <pablo.rangel@gmail.com>
 * @date 13/05/2021
 * @brief class SecurityConfig
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                
                .authorizeRequests()
                .antMatchers("/usuarios/**").access("hasAuthority('ADMIN')")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginProcessingUrl("/efetuarLogin")
                .loginPage("/login").permitAll()
                .usernameParameter("login")
                .passwordParameter("senha")
                .and()
                .logout().logoutUrl("/efetuarLogout").permitAll();

        httpSecurity.csrf()
          .ignoringAntMatchers("/h2-console/**");
        httpSecurity.headers()
          .frameOptions()
          .sameOrigin();
        httpSecurity
                .requiresChannel()
                .anyRequest()
                .requiresSecure();
        
        
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(passwordEncoder())
                .usersByUsernameQuery("select login as username, senha as password, true"
                        + " from Usuario where login=?")
                .authoritiesByUsernameQuery("select login as username, papel as role"
                        + " from Usuario where login=?");
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    @Bean   
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
