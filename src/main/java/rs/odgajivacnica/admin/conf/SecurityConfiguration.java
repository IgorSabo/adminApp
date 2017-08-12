package rs.odgajivacnica.admin.conf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import rs.odgajivacnica.admin.service.implementation.MyUserDetailsService;


@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = {"rs.odgajivacnica.admin.service"})
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	//private MyUserDetailsService customUserDetailsService;

    @Autowired
    private MyUserDetailsService userDetailsService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.csrf().disable().authorizeRequests()
            //.antMatchers("/register**", "/about").permitAll().anyRequest().permitAll()
            .antMatchers("/resources/static/css/**").permitAll()
            .antMatchers("/resources/static/js/**").permitAll()
            .antMatchers("/resources/images/**").permitAll()
            .antMatchers("/login**").permitAll()
            .antMatchers("/logout**").permitAll()
            .antMatchers("/registration**").permitAll()
            .antMatchers("/webjars/**").permitAll().anyRequest().authenticated();

            //.antMatchers("/index/**").hasRole("ADMIN");


        httpSecurity
                .formLogin().loginPage("/login").defaultSuccessUrl("/index.html")
                .and()
                .logout()
                .logoutUrl("/logout").deleteCookies("remember-me").permitAll()
            //.anyRequest().authenticated();
//            .and()
//            .formLogin().loginPage("/login") .defaultSuccessUrl("/index.html")
//            .permitAll()
//            .and()
//            .logout()
//            .logoutUrl("/logout").deleteCookies("remember-me")
//            .and()
//            .rememberMe().rememberMeParameter("remember-me").tokenRepository(persistentTokenRepository()).tokenValiditySeconds(31536000);
            /*.tokenValiditySeconds(31536000)*/;
        httpSecurity.authorizeRequests().antMatchers("/resources/**").permitAll();



	}
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        /*auth
            .inMemoryAuthentication()
                .withUser("Cukara").password("bog").roles("USER");*/
		
		//auth.authenticationProvider(authProvider);
		auth.userDetailsService(userDetailsService)
		.passwordEncoder(passwordEncoder);
    }
	
    /*@Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepositoryImpl = new JdbcTokenRepositoryImpl();
        tokenRepositoryImpl.setDataSource(dataSource);
        return tokenRepositoryImpl;
    }*/
}
