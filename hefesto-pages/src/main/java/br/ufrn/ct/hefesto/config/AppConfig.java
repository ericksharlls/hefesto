package br.ufrn.ct.hefesto.config;

import static org.hibernate.cfg.AvailableSettings.DIALECT;
import static org.hibernate.cfg.AvailableSettings.FORMAT_SQL;
import static org.hibernate.cfg.AvailableSettings.SHOW_SQL;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import br.ufrn.ct.hefesto.web.ViewScope;

@Configuration
@PropertySource("classpath:/br/ufrn/ct/hefesto/db/db.properties")
@EnableTransactionManagement
@ComponentScans(value = { @ComponentScan("dev.modulo.adaptador.apiufrn"), @ComponentScan("br.ufrn.ct.hefesto.persistence.dao"), @ComponentScan("br.ufrn.ct.hefesto.service"), @ComponentScan("br.ufrn.ct.hefesto.converter"),  
		@ComponentScan("br.ufrn.ct.hefesto.controller"), @ComponentScan("dev.modulo.service.context"), @ComponentScan("br.ufrn.ct.hefesto.web.security")})
public class AppConfig implements EnvironmentAware {
	/*
	 * A interface EnvironmentAware est� sendo implementada para resolver o problema do Environment ser nulo durante a execu��o dessa classe
	 * � como se o @Autowired do Spring para o Environment estivesse acontecendo tarde demais.. parece q � um bug do Spring
	 */

	@Autowired
	private Environment env;

	@Bean
	public DriverManagerDataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("mysql.driver"));
		dataSource.setUrl(env.getProperty("mysql.jdbcUrl"));
		dataSource.setUsername(env.getProperty("mysql.username"));
		dataSource.setPassword(env.getProperty("mysql.password"));
		return dataSource;
	}
	
	@Bean
	public LocalSessionFactoryBean getSessionFactory() {
	    LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
	    Properties props = new Properties();
	    
	    // Setting Hibernate properties
	    props.put(DIALECT, env.getProperty("hibernate.dialect"));
	    props.put(SHOW_SQL, env.getProperty("hibernate.show_sql"));
	    props.put(FORMAT_SQL, env.getProperty("hibernate.format_sql"));
	    
	    // Enable second level cache (default value is true)
	    props.put("hibernate.cache.use_second_level_cache", "true");
	    // Habilita o cahe de queries
	    props.put("hibernate.cache.use_query_cache", "true");
	    
	    // Configurando o RegionFactory com o provedor JCache (API padrão de caching do Java. Especificação JSR 107)
    	/*
    	 * Specify cache region factory class
    	 * O cache L2 do Hibernate foi projetado para desconhecer o provedor de cache real usado. 
    	 * O Hibernate precisa ser fornecido com uma implementação (provedor de cache real) da 
    	 * interface org.hibernate.cache.spi.RegionFactory.
    	 * Aqui foi escolhido o provedor JCache. 
    	 * The JCacheRegionFactory configura um javax.cache.CacheManager.
    	 */
    	props.put("hibernate.cache.region.factory_class", "org.hibernate.cache.jcache.JCacheRegionFactory");

    	// Configurando o CacheManager e o Provider do JCache
    	// Além da interface Cache, a especificação JCache define as interfaces: CachingProvider and CacheManager.
    	// Enquanto o CacheManager é usado para criar/recuperar um cache, o CachingProvider é usado para obter/acessar um CacheManager.
    	// Se não forem especificadas as propriedades abaixo, o JCacheRegionFactory carregará o provedor JCache padrão e criará o CacheManager padrão
    	
    	/*
    	 * Especifica o provedor de cache
    	 */
    	props.put("hibernate.javax.cache.provider", "org.ehcache.jsr107.EhcacheCachingProvider");
    	/*
    	 * Especifica o arquivo de configuração para o CacheManager e para os Caches.
    	 * Somente especificando essa propriedade, se terá um CacheManager por SessionFactory 
    	 */
    	props.put("hibernate.javax.cache.uri", "file:src/main/resources/br/ufrn/ct/hefesto/db/ehcache.xml");
    	//props.put("hibernate.javax.cache.uri", "file:/opt/apache-tomcat-8.5.14/webapps/hefesto/WEB-INF/classes/br/ufrn/ct/hefesto/db/ehcache.xml");
	    
	    factoryBean.setDataSource(getDataSource());
	    factoryBean.setHibernateProperties(props);
	    factoryBean.setPackagesToScan(env.getProperty("hibernate.package_entities"));
	    return factoryBean;
	  }

	@Bean
	public HibernateTransactionManager getTransactionManager() {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(getSessionFactory().getObject());
		return transactionManager;
	}
	
	@Bean
	public CustomScopeConfigurer getCustomScopeConfigurer() {
		CustomScopeConfigurer customScopeConfigurer = new CustomScopeConfigurer();
		Map<String, Object> scopeView = new HashMap<String, Object>();
		scopeView.put("view", ViewScope.class);
		customScopeConfigurer.setScopes(scopeView);
		return customScopeConfigurer;
	}
	
	@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.addBasenames("br/ufrn/ct/hefesto/messages/messages-spring-security");
		return messageSource;
	}

	/*
	 * M�todo setEnvironment sendo implementado para for�ar o Spring a fazer cedo o @Autowired da depend�ncia 'env'
	 */ 
    public void setEnvironment(final Environment environment) {
        this.env = environment;
    }

}
