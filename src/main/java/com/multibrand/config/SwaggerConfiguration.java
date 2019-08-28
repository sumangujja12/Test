package com.multibrand.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration extends WebMvcConfigurationSupport {
	
	
	@Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("All")
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }
				
	@Bean
	public Docket apiBillingResource() {

		return new Docket(DocumentationType.SWAGGER_2).groupName("BillingResource")
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.multibrand.resources"))
				.paths(PathSelectors.regex("/billResource/.*")).build()
				  .useDefaultResponseMessages(false)
		            .forCodeGeneration(true);
	}
	
	
	@Bean
	public Docket apiAuthenticationResource() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("AuthenticationResource")
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.multibrand.resources"))
				.paths(PathSelectors.regex("/authorization.*"))
				.build()
				
				.apiInfo(metaData());
	}
	
	
	@Bean
	public Docket apiAutoPayResource() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("AutoPayResource")
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.multibrand.resources"))
				.paths(PathSelectors.regex("/autoPay.*"))
				.build()
				
				.apiInfo(metaData());
	}
	
	
	@Bean
	public Docket apiContentResource() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("ContentResource")
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.multibrand.resources"))
				.paths(PathSelectors.regex("/personalize.*"))
				.build()
				
				.apiInfo(metaData());
	}
	
	
	@Bean
	public Docket apiCslrCcsResource() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("CslrCcsResource")
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.multibrand.resources"))
				.paths(PathSelectors.regex("/cslrCcsResource.*"))
				.build()
				
				.apiInfo(metaData());
	}
	
	
	
	@Bean
	public Docket apiCSLRSalesforceResource() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("CSLRSalesforceResource")
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.multibrand.resources"))
				.paths(PathSelectors.regex("/cslrSalesforceResource.*"))
				.build()
				
				.apiInfo(metaData());
	}
	
	
	@Bean
	public Docket apiECommerceResource() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("ECommerceResource")
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.multibrand.resources"))
				.paths(PathSelectors.regex("/public/ecommerceResource.*"))
				.build()
				
				.apiInfo(metaData());
	}
	
	
	@Bean
	public Docket apiEmailResource() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("EmailResource")
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.multibrand.resources"))
				.paths(PathSelectors.regex("/emails.*"))
				.build()
				
				.apiInfo(metaData());
	}
	
	
	@Bean
	public Docket apiEsidResource() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("EsidResource")
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.multibrand.resources"))
				.paths(PathSelectors.regex("/esidResource.*"))
				.build()
				
				.apiInfo(metaData());
	}
	
	
	@Bean
	public Docket apiGMEPrepayResource() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("GMEPrepayResource")
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.multibrand.resources"))
				.paths(PathSelectors.regex("/gmePrepay.*"))
				.build()
				
				.apiInfo(metaData());
	}
	
	
	@Bean
	public Docket apiHistoryResource() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("HistoryResource")
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.multibrand.resources"))
				.paths(PathSelectors.regex("/history.*"))
				.build()
				
				.apiInfo(metaData());
	}
	
	
	@Bean
	public Docket apiIRWResource() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("IRWResource")
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.multibrand.resources"))
				.paths(PathSelectors.regex("/irw.*"))
				.build()
				
				.apiInfo(metaData());
	}
	
	@Bean
	public Docket apiOEResource() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("OEResource")
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.multibrand.resources"))
				.paths(PathSelectors.regex("/oeResource.*"))
				.build()
				
				.apiInfo(metaData());
	}
	
	@Bean
	public Docket apiPreferencesResource() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("PreferencesResource")
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.multibrand.resources"))
				.paths(PathSelectors.regex("/autoPay.*"))
				.build()
				
				.apiInfo(metaData());
	}
	
	@Bean
	public Docket apiProfileResource() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("ProfileResource")
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.multibrand.resources"))
				.paths(PathSelectors.regex("/profile.*"))
				.build()
				
				.apiInfo(metaData());
	}
	
	
	@Bean
	public Docket apiRegistration() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("Registration")
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.multibrand.resources"))
				.paths(PathSelectors.regex("/registration.*"))
				.build()
				
				.apiInfo(metaData());
	}
	
	
	@Bean
	public Docket apiRetailServicesResource() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("RetailServicesResource")
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.multibrand.resources"))
				.paths(PathSelectors.regex("/retailServices.*"))
				.build()
				
				.apiInfo(metaData());
	}
	
	@Bean
	public Docket apiSwapResource() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("SwapResource")
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.multibrand.resources"))
				.paths(PathSelectors.regex("/swapResource.*"))
				.build()
				
				.apiInfo(metaData());
	}
	
	
	@Bean
	public Docket apiTCSCslrResource() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("TCSCslrResource")
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.multibrand.resources"))
				.paths(PathSelectors.regex("/tcsCslrResource.*"))
				.build()
				
				.apiInfo(metaData());
	}
	
	
	@Bean
	public Docket apiTokenResource() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("TokenResource")
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.multibrand.resources"))
				.paths(PathSelectors.regex("/tokenResource.*"))
				.build()
				
				.apiInfo(metaData());
	}
	
	
	@Bean
	public Docket apiTOSResource() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("TOSResource")
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.multibrand.resources"))
				.paths(PathSelectors.regex("/tos.*"))
				.build()
				
				.apiInfo(metaData());
	}
	
	@Bean
	public Docket apiTPVResource() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("TPVResource")
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.multibrand.resources"))
				.paths(PathSelectors.regex("/tpvApi.*"))
				.build()
				
				.apiInfo(metaData());
	}
	
	
		
	@Bean
	public Docket apiWebAgentResource() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("WebAgentResource")
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.multibrand.resources"))
				.paths(PathSelectors.regex("/webAgent.*"))
				.build()
				
				.apiInfo(metaData());
	}
	
		

	private ApiInfo metaData() {
		return new ApiInfoBuilder().title("NRG Rest Service")
				.description("\"NRG REST Service contains OAM, OE backend support calls for WEB & Mobile Application Systems\"").version("1.0.0")
				.license("NRG Energy").licenseUrl("")
				.contact(new Contact("", "", ""))
				.build();
	}

	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
	
	

}
