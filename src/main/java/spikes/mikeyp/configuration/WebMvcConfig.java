package spikes.mikeyp.configuration;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.tiles2.TilesConfigurer;
import org.thymeleaf.extras.tiles2.dialect.TilesDialect;
import org.thymeleaf.extras.tiles2.spring.web.configurer.ThymeleafTilesConfigurer;
import org.thymeleaf.extras.tiles2.spring.web.view.ThymeleafTilesView;
import org.thymeleaf.spring3.SpringTemplateEngine;
import org.thymeleaf.spring3.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

@Configuration
@EnableWebMvc
@EnableScheduling
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	@Override
	public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
		configurer.setDefaultTimeout(30*1000L);
	}
	
	@Bean 
	public ServletContextTemplateResolver templateResolver(){
		ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(); 
		templateResolver.setPrefix("/WEB-INF/templates"); 
		templateResolver.setSuffix(".html"); 
		templateResolver.setTemplateMode("HTML5"); 
		templateResolver.setCacheable(false); 
		return templateResolver ;
	}
	@Bean 
	public SpringTemplateEngine templateEngine(ServletContextTemplateResolver templateResolver)
	{
		SpringTemplateEngine templateEngine = new SpringTemplateEngine(); 
		templateEngine.setTemplateResolver(templateResolver); 
		templateEngine.addDialect(new TilesDialect()); 
		return templateEngine; 
		
	}
	
	@Bean
	public ViewResolver viewResolver(SpringTemplateEngine templateEngine) {
		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver(); 
		viewResolver.setTemplateEngine(templateEngine);
		viewResolver.setViewClass(ThymeleafTilesView.class) ; 
		return viewResolver; 
	}

	@Bean
	public TilesConfigurer tilesConfigurer() {
		TilesConfigurer configurer = new ThymeleafTilesConfigurer();
		configurer.setDefinitions(new String[] {
				"/WEB-INF/templates/layouts/tiles.xml",
				"/WEB-INF/templates/views/tiles.xml"                          
		});
		configurer.setCheckRefresh(true);
		return configurer;
	}

	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/");
	}
	
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("/WEB-INF/messages/messages");
        return messageSource;
    }
}
