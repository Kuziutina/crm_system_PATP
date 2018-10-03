package app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	DataSource dataSource;

	@Autowired
    UserDetailsService userDetailsService;

	@Autowired
    AuthenticationProvider authenticationProvider;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	    auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
	    auth.authenticationProvider(authenticationProvider);
		auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery("select login, password," +
                " 1 as enabled from users where login = ?").authoritiesByUsernameQuery("select login, role from users where login = ?");
	}


    @Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
			.antMatchers("/crm/**").access("hasRole('MANAGER_ROLE')")
				.antMatchers("/profile/**").hasAnyRole()
            .antMatchers("/registration").permitAll()
			.antMatchers("/**").permitAll()
			.and().formLogin()
				.loginPage("/login")
				.loginProcessingUrl("/login")
				.defaultSuccessUrl("/main")
				.permitAll()
			.and().logout()
				.logoutUrl("/logout")
				.logoutSuccessUrl("/main")
				.deleteCookies("JSESSIONID")
                .permitAll();

		http.csrf().disable();

		http.rememberMe()
				.key("uniqueAndSecret")
				.tokenValiditySeconds(86400)
				.tokenRepository(persistentTokenRepository())
				.userDetailsService(userDetailsService);

	}

	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
		tokenRepository.setDataSource(dataSource);
		return tokenRepository;
	}

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(12);
    }
}