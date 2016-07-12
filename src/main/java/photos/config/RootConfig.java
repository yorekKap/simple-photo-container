package photos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import photos.FileManager;
import photos.PhotoManager;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(basePackages = "photos", excludeFilters = @Filter(type = FilterType.ANNOTATION, 
			   value = EnableWebMvc.class))
public class RootConfig {

	@Bean
	FileManager fileManager(){
		
		return new PhotoManager();
		
	}
	
}
