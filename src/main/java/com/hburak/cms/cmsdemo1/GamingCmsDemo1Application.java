package com.hburak.cms.cmsdemo1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class GamingCmsDemo1Application {

	public static void main(String[] args) {
		SpringApplication.run(GamingCmsDemo1Application.class, args);
	}
}

