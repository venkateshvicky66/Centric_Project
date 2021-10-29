package com.product.project.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration
{

	/**
	 * bean for the modelmapper
	 * @return the object for model mapper
	 */

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	/**
	 * bean to configure the cross origin policy
	 * @return the web configurer object
	 */

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/*").allowedOrigins("*");
			}
		};
	}
}
