
package spikes.mikeyp.configuration;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.codehaus.jettison.json.JSONObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableSpringConfigured;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@ComponentScan(basePackages = "spikes.mikeyp", excludeFilters = { @Filter(Configuration.class) })
@PropertySource("classpath:spikes/mikeyp/configuration/application.properties")
@EnableSpringConfigured
@EnableAsync
@ImportResource("classpath:spikes/mikeyp/configuration/spring-rabbit.xml")
public class MainConfig {


	@Bean
	public PropertySourcesPlaceholderConfigurer propertyPlaceHolderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
	
	@Bean 
	public BlockingQueue<JSONObject> updateQueue(){
		
		return new ArrayBlockingQueue<JSONObject>(1000); 
	}
	
}
