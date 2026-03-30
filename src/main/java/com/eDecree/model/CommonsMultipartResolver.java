package com.eDecree.model;

import org.springframework.context.annotation.Bean;

public class CommonsMultipartResolver {
	@Bean
	public CommonsMultipartResolver multipartResolver() {
	    return new CommonsMultipartResolver();
	}
}
