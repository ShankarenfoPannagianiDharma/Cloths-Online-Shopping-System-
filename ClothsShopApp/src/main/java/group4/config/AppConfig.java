package group4.config;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import org.hsqldb.util.DatabaseManagerSwing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

//The @Configuration is a class-level annotation indicating 
//that an object is a source of bean definitions.
//
//The @EnableWebMvc enables default Spring MVC configuration 
//and provides the functionality equivalent to 
//mvc:annotation-driven/ element in XML based configuration.
//
//The @ComponentScan scans the stereotype annotations 
//(@Controller, @Service etc...) in a package specified by 
//basePackages attribute.

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "group4" })
public class AppConfig implements WebMvcConfigurer {

	@Bean
	public InternalResourceViewResolver resolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setViewClass(JstlView.class);
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
	
	
}