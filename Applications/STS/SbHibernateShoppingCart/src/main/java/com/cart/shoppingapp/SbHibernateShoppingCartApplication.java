package com.cart.shoppingapp;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@SpringBootApplication
@EnableAutoConfiguration(exclude= {
		DataSourceAutoConfiguration.class,
		DataSourceTransactionManagerAutoConfiguration.class,
		HibernateJpaAutoConfiguration.class
})
public class SbHibernateShoppingCartApplication {
	
	@Autowired
	private Environment env;

	public static void main(String[] args) {
		SpringApplication.run(SbHibernateShoppingCartApplication.class, args);
	}
	
	@Bean(name ="dataSource")
	public DataSource getDataSource()
	{
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		
		//Binding with application.properties
		dataSource.setDriverClassName(env.getProperty("com.mysql.jdbc.Driver"));
		dataSource.setUrl(env.getProperty("jdbc:mysql://localhost:3306/shoppingapp"));
		dataSource.setUsername(env.getProperty("root"));
		dataSource.setPassword(env.getProperty("root"));
		return dataSource;
	}
	
	@Autowired
	@Bean(name="sessionFactory")
	public SessionFactory getSessionFactory() throws Exception
	{
		Properties properties = new Properties();
		properties.put("hibernate.dialect", env.getProperty("org.hibernate.dialect.MySQLDialect"));
		properties.put("hibernate.show_sql", env.getProperty("true"));
		properties.put("current_session_context-class", env.getProperty("org.springframework.orm.hibernate5.SpringSessionContext"));
		
		LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
		
		//Containing Entity Classes
		factoryBean.setPackagesToScan(new String[] {""});
		factoryBean.setDataSource(getDataSource());
		factoryBean.setHibernateProperties(properties);
		factoryBean.afterPropertiesSet();
		
		SessionFactory sf = factoryBean.getObject();
		System.out.println("getSessionFactory: "+sf);
		return sf;
		
	}
	@Autowired
	@Bean(name="transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory)
	{
		HibernateTransactionManager transactionManager= new HibernateTransactionManager(sessionFactory);
		return transactionManager;
	}

}
