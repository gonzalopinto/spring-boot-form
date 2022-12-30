package com.springboot.form.app.interceptors;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class TiempoTranscurridoInterceptor implements HandlerInterceptor {

	private static final Logger logger = LoggerFactory.getLogger(TiempoTranscurridoInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// Miramos si es el post, nos salimos del interceptor
		if (request.getMethod().equalsIgnoreCase("post"))
			return true;

		if (handler instanceof HandlerMethod) {
			HandlerMethod method = (HandlerMethod) handler;
			logger.info("Es un método del controlador: " + method.getMethod().getName());
		}

		logger.info("TiempoTranscurridoInterceptor: preHandle() entrando...");
		logger.info("Interceptando: " + handler);
		long tiempoInicio = System.currentTimeMillis();
		request.setAttribute("tiempoInicio", tiempoInicio);

		Random random = new Random();
		Integer delay = random.nextInt(200);
		Thread.sleep(delay);

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		if (!request.getMethod().equalsIgnoreCase("post")) {
			long tiempoFin = System.currentTimeMillis();
			long tiempoInicio = (Long) request.getAttribute("tiempoInicio");
			long tiempoTranscurrido = tiempoFin - tiempoInicio;

			if (handler instanceof HandlerMethod && modelAndView != null)
				modelAndView.addObject("tiempoTranscurrido", tiempoTranscurrido);

			logger.info("Tiempo Transcurrido: " + tiempoTranscurrido + " milliseconds");
			logger.info("TiempoTranscurridoInterceptor: postHandle() saliendo...");
		}
	}

}
