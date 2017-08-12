package rs.odgajivacnica.admin.conf;

import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Locale;


//import org.apache.velocity.app.VelocityEngine;
//import org.apache.velocity.exception.VelocityException;
//import org.springframework.mail.javamail.JavaMailSenderImpl;
//import org.springframework.ui.velocity.VelocityEngineFactoryBean;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class AppConfiguration extends WebMvcConfigurerAdapter implements ApplicationContextAware {
	@Value("${ui.templates.cached}")
	private boolean ui_templatesCached;

	private ApplicationContext applicationContext;

	@Autowired
	private Environment env;

	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver slr = new SessionLocaleResolver();
		slr.setDefaultLocale(Locale.US);
		return slr;
	}

	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
		lci.setParamName("lang");
		return lci;
	}



	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("/static/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}/**/

	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor());
	}

	@Bean
	public SpringSecurityDialect securityDialect() {
		return new SpringSecurityDialect();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(11);
	}
	
	@Bean(name = "multipartResolver")
    public StandardServletMultipartResolver resolver() {
        return new StandardServletMultipartResolver();
    }
	
    /*@Bean(name="multipartResolver") 
    public CommonsMultipartResolver getResolver() throws IOException{
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
         
        //Set the maximum allowed size (in bytes) for each individual file.
        resolver.setMaxUploadSizePerFile(5242880);//5MB
         
        //You may also set other available properties.
         
        return resolver;
    }*/
	

//	@Bean
//	public JavaMailSenderImpl javaMailSenderImpl() {
//		final JavaMailSenderImpl mailSenderImpl = new JavaMailSenderImpl();
//		mailSenderImpl.setHost(env.getProperty("smtp.host"));
//		mailSenderImpl.setPort(env.getProperty("smtp.port", Integer.class));
//		mailSenderImpl.setProtocol(env.getProperty("smtp.protocol"));
//		mailSenderImpl.setUsername(env.getProperty("smtp.username"));
//		mailSenderImpl.setPassword(env.getProperty("smtp.password"));
//		mailSenderImpl.setDefaultEncoding("UTF-8");
//		final Properties javaMailProps = new Properties();
//		javaMailProps.put("mail.smtp.auth", true);
//		// javaMailProps.put("mail.smtps.host", env.getProperty("smtp.host"));
//		javaMailProps.put("mail.smtp.starttls.enable", true);
//		mailSenderImpl.setJavaMailProperties(javaMailProps);
//		return mailSenderImpl;
//	}
//
//	@Bean
//	public VelocityEngine velocityEngine() throws VelocityException, IOException {
//		VelocityEngineFactoryBean factory = new VelocityEngineFactoryBean();
//		Properties props = new Properties();
//		props.put("resource.loader", "class");
//		props.put("class.resource.loader.class",
//				"org.apache.velocity.runtime.resource.loader." + "ClasspathResourceLoader");
//		factory.setVelocityProperties(props);
//
//		return factory.createVelocityEngine();
//	}

}
