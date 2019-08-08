package com.multibrand.config;

import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jndi.JndiObjectFactoryBean;

import com.multibrand.dao.jdbc.sp.ProcedureConfigPackageScanner;
import com.multibrand.dao.jdbc.sp.ProcedureTemplate;
import com.multibrand.dao.jdbc.sp.ProcedureTemplateHelper;

@Configuration
public class DatabaseConfig {


	@Bean("cpdbDataSource" )
	public JndiObjectFactoryBean cpdbDataSource() {
		JndiObjectFactoryBean jndiObjectFactoryBean = new JndiObjectFactoryBean();
		jndiObjectFactoryBean.setJndiName("jdbc/cpdb1_main_user");
		jndiObjectFactoryBean.setResourceRef(true);
		jndiObjectFactoryBean.setProxyInterface(DataSource.class);
		jndiObjectFactoryBean.setCache(true);
		jndiObjectFactoryBean.setLookupOnStartup(false);
		return jndiObjectFactoryBean;
	}

	@Bean("pennywiseDataSource")
	public JndiObjectFactoryBean PennywiseDataSource() {
		JndiObjectFactoryBean jndiObjectFactoryBean = new JndiObjectFactoryBean();
		jndiObjectFactoryBean.setJndiName("dbc/mb_cpdb1_user");
		jndiObjectFactoryBean.setResourceRef(true);
		jndiObjectFactoryBean.setProxyInterface(DataSource.class);
		jndiObjectFactoryBean.setCache(true);
		jndiObjectFactoryBean.setLookupOnStartup(false);
		return jndiObjectFactoryBean;
	}

	@Bean("cirroDataSource")
	public JndiObjectFactoryBean cirroDataSource() {
		JndiObjectFactoryBean jndiObjectFactoryBean = new JndiObjectFactoryBean();
		jndiObjectFactoryBean.setJndiName("jdbc/cirro_ro");
		jndiObjectFactoryBean.setResourceRef(true);
		jndiObjectFactoryBean.setProxyInterface(DataSource.class);
		jndiObjectFactoryBean.setCache(true);
		jndiObjectFactoryBean.setLookupOnStartup(false);
		return jndiObjectFactoryBean;
	}

	@Bean("podDataSource")
	public JndiObjectFactoryBean podDataSource() {
		JndiObjectFactoryBean jndiObjectFactoryBean = new JndiObjectFactoryBean();
		jndiObjectFactoryBean.setJndiName("jdbc/web_read");
		jndiObjectFactoryBean.setResourceRef(true);
		jndiObjectFactoryBean.setProxyInterface(DataSource.class);
		jndiObjectFactoryBean.setCache(true);
		jndiObjectFactoryBean.setLookupOnStartup(false);
		return jndiObjectFactoryBean;
	}

	@Bean("choiceDataSource")
	public JndiObjectFactoryBean choiceDataSource() {
		JndiObjectFactoryBean jndiObjectFactoryBean = new JndiObjectFactoryBean();
		jndiObjectFactoryBean.setJndiName("jdbc/wb_user");
		jndiObjectFactoryBean.setResourceRef(true);
		jndiObjectFactoryBean.setProxyInterface(DataSource.class);
		jndiObjectFactoryBean.setCache(true);
		jndiObjectFactoryBean.setLookupOnStartup(false);
		return jndiObjectFactoryBean;
	}

	@Bean("gmeResDataSource")
	public JndiObjectFactoryBean gmeResDataSource() {
		JndiObjectFactoryBean jndiObjectFactoryBean = new JndiObjectFactoryBean();
		jndiObjectFactoryBean.setJndiName("jdbc/gme_res_user");
		jndiObjectFactoryBean.setResourceRef(true);
		jndiObjectFactoryBean.setProxyInterface(DataSource.class);
		jndiObjectFactoryBean.setCache(true);
		jndiObjectFactoryBean.setLookupOnStartup(false);
		return jndiObjectFactoryBean;
	}

	@Bean("smartMainDataSource")
	public JndiObjectFactoryBean smartMainDataSource() {
		JndiObjectFactoryBean jndiObjectFactoryBean = new JndiObjectFactoryBean();
		jndiObjectFactoryBean.setJndiName("jdbc/smart_user");
		jndiObjectFactoryBean.setResourceRef(true);
		jndiObjectFactoryBean.setProxyInterface(DataSource.class);
		jndiObjectFactoryBean.setCache(true);
		jndiObjectFactoryBean.setLookupOnStartup(false);
		return jndiObjectFactoryBean;
	}

	@Bean("smartMeterDataSource")
	public JndiObjectFactoryBean smartMeterDataSource() {
		JndiObjectFactoryBean jndiObjectFactoryBean = new JndiObjectFactoryBean();
		jndiObjectFactoryBean.setJndiName("jdbc/smart_user");
		jndiObjectFactoryBean.setResourceRef(true);
		jndiObjectFactoryBean.setProxyInterface(DataSource.class);
		jndiObjectFactoryBean.setCache(true);
		jndiObjectFactoryBean.setLookupOnStartup(false);
		return jndiObjectFactoryBean;
	}

	@Bean("cslrDataSource")
	public JndiObjectFactoryBean cslrDataSource() {
		JndiObjectFactoryBean jndiObjectFactoryBean = new JndiObjectFactoryBean();
		jndiObjectFactoryBean.setJndiName("jdbc/cslr_user");
		jndiObjectFactoryBean.setResourceRef(true);
		jndiObjectFactoryBean.setProxyInterface(DataSource.class);
		jndiObjectFactoryBean.setCache(true);
		jndiObjectFactoryBean.setLookupOnStartup(false);
		return jndiObjectFactoryBean;
	}

	@Bean("solprefUser")
	public JndiObjectFactoryBean solprefUser() {
		JndiObjectFactoryBean jndiObjectFactoryBean = new JndiObjectFactoryBean();
		jndiObjectFactoryBean.setJndiName("jdbc/solpref_user");
		jndiObjectFactoryBean.setResourceRef(true);
		jndiObjectFactoryBean.setProxyInterface(DataSource.class);
		jndiObjectFactoryBean.setCache(true);
		jndiObjectFactoryBean.setLookupOnStartup(false);
		return jndiObjectFactoryBean;
	}
	
	@Bean("SVTDATADataSource")
	public JndiObjectFactoryBean svtDataDatasource() {
		JndiObjectFactoryBean jndiObjectFactoryBean = new JndiObjectFactoryBean();
		jndiObjectFactoryBean.setJndiName("jdbc/cirsvt_user");
		jndiObjectFactoryBean.setResourceRef(true);
		jndiObjectFactoryBean.setProxyInterface(DataSource.class);
		jndiObjectFactoryBean.setCache(true);
		jndiObjectFactoryBean.setLookupOnStartup(false);
		return jndiObjectFactoryBean;
	}
	
	
	@Bean("tcsReadDataSource")
	public JndiObjectFactoryBean tcsReadDataSource() {
		JndiObjectFactoryBean jndiObjectFactoryBean = new JndiObjectFactoryBean();
		jndiObjectFactoryBean.setJndiName("jdbc/tcs_read");
		jndiObjectFactoryBean.setResourceRef(true);
		jndiObjectFactoryBean.setProxyInterface(DataSource.class);
		jndiObjectFactoryBean.setCache(true);
		jndiObjectFactoryBean.setLookupOnStartup(false);
		return jndiObjectFactoryBean;
	}
	
	
	@Bean("cpdbJdbcTemplate")
	public JdbcTemplate cpdbJdbcTemplate() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate((DataSource) cpdbDataSource().getObject());
		return jdbcTemplate;
	}
	
	@Bean("pennywiseJdbcTemplate")
	public JdbcTemplate pennywiseJdbcTemplate() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate((DataSource) PennywiseDataSource().getObject());
		return jdbcTemplate;
	}
	
	
	@Bean("cirroJdbcTemplate")
	public JdbcTemplate cirroJdbcTemplate() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate((DataSource) cirroDataSource().getObject());
		return jdbcTemplate;
	}
	
	
	@Bean("podJdbcTemplate")
	public JdbcTemplate podJdbcTemplate() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate((DataSource) podDataSource().getObject());
		return jdbcTemplate;
	}
	
	
	@Bean("choiceJdbcTemplate")
	public JdbcTemplate choiceJdbcTemplate() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate((DataSource) choiceDataSource().getObject());
		return jdbcTemplate;
	}
	
	@Bean("gmeResJdbcTemplate")
	public JdbcTemplate gmeResJdbcTemplate() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate((DataSource) gmeResDataSource().getObject());
		return jdbcTemplate;
	}
	
	@Bean("smartMainJdbcTemplate")
	public JdbcTemplate smartMainJdbcTemplate() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate((DataSource) smartMainDataSource().getObject());
		return jdbcTemplate;
	}
	
	@Bean("smartMeterJdbcTemplate")
	public JdbcTemplate smartMeterJdbcTemplate() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate((DataSource) smartMeterDataSource().getObject());
		return jdbcTemplate;
	}
	
	@Bean("svtJdbcTemplate")
	public JdbcTemplate svtJdbcTemplate() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate((DataSource) svtDataDatasource().getObject());
		return jdbcTemplate;
	}
	
	
	@Bean("cslrJdbcTemplate")
	public JdbcTemplate cslrJdbcTemplate() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate((DataSource) cslrDataSource().getObject());
		return jdbcTemplate;
	}
	
	
	@Bean("tcsReadJdbcTemplate")
	public JdbcTemplate tcsReadJdbcTemplate() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate((DataSource) tcsReadDataSource().getObject());
		return jdbcTemplate;
	}
	
	
	@Bean("solprefUserTemplate")
	public JdbcTemplate solprefUserTemplate() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate((DataSource) solprefUser().getObject());
		return jdbcTemplate;
	}
	
	@Bean("procedureConfigPackageScanner")
	public ProcedureConfigPackageScanner getProcedureConfigPackageScanner(@Qualifier("procedureTemplateHelper")  ProcedureTemplateHelper procedureTemplateHelper) {
		List<String> packageList = new LinkedList<String>();
		packageList.add("com.multibrand.dto.request");
		ProcedureConfigPackageScanner scanner = new ProcedureConfigPackageScanner(packageList,procedureTemplateHelper);
		return scanner;
	}
	
	
	@Bean("choiceDataSourceProcedureTemplate")
	public ProcedureTemplate getProcedureTemplate(@Qualifier("procedureTemplateHelper")  ProcedureTemplateHelper procedureTemplateHelper) {
		ProcedureTemplate procedureTemplate = new ProcedureTemplate((DataSource)choiceDataSource().getObject(),procedureTemplateHelper);
		return procedureTemplate;
	}
	
	@Bean(name = "dbProcedureMappingMsgSourceId")
	public ReloadableResourceBundleMessageSource reloadableResourceSQLBundleMessageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("/WEB-INF/classes/properties/db/dbProcedureMapping");
		messageSource.setCacheSeconds(0);
		return messageSource;
	}
	
	
	

}
