package com.springboot.form.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.springboot.form.app.interceptors.TiempoTranscurridoInterceptor;

@Configuration
public class MVCConfig implements WebMvcConfigurer {

	@Autowired
	private TiempoTranscurridoInterceptor tiempoTranscurridoInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		registry.addInterceptor(tiempoTranscurridoInterceptor).addPathPatterns("/form/**");
	}

}
