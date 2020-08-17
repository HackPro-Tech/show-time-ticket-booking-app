package com.showtime.userservice.bean;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * @author Vengatesan Nagarajan
 *
 */
@Configuration
public class UtilityBeanConfiguration {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}