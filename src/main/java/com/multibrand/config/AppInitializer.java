package com.multibrand.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import com.multibrand.web.i18n.WebI18nMessageSource;

public class AppInitializer {/*implements WebApplicationInitializer {

		
	@Override
    public void onStartup(ServletContext container) {
		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(AppConfig.class);
        ctx.setServletContext(container);
        container.addListener(new ContextLoaderListener(ctx));
        
      
        ServletRegistration.Dynamic servlet = container.addServlet("restDispatcher", new DispatcherServlet(ctx));
        servlet.setLoadOnStartup(1);
        servlet.addMapping("/rest/*");
    }
*/
}