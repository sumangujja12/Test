package com.multibrand.resource;

import org.junit.runner.RunWith;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;

import com.multibrand.config.WSConfig;

@WebAppConfiguration
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
//@Import({WSConfig.class})
//@PropertySources({@PropertySource("classpath:properties/environment_mobile.properties")})
@PropertySource("classpath:properties/environment.properties")
//@ContextConfiguration(classes = { WSConfig.class, MockDatabaseConfig.class }, loader = AnnotationConfigWebContextLoader.class)
@ContextConfiguration(classes = { WSConfig.class }, loader = AnnotationConfigWebContextLoader.class)
//@ContextConfiguration(locations ="classpath:**/NRGREST-appContext.xml")
public abstract class AbstractJunitTest {

	
}
