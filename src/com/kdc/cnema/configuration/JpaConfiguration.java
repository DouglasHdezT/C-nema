package com.kdc.cnema.configuration;



import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.kdc.cnema.repositories")
public class JpaConfiguration {

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em =  new LocalContainerEntityManagerFactoryBean();
		
		em.setDataSource(dataSource());
		em.setPersistenceUnitName("kdc");
		em.setPackagesToScan("com.kdc.cnema.domain");
		
		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(hibernateProperties());
		
		return em;
	}  
	
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		
		dataSource.setDriverClassName("org.postgresql.Driver");
		//dataSource.setUrl("jdbc:postgresql://127.0.0.1:5432/tareaNCapas");
		dataSource.setUrl("jdbc:postgresql://nhdkmarcnbdzbv:fbbb2d2d94099c537b5cb5716da5f6fb59342b98d243ecc10f5ac9b843ac8cf1@ec2-75-101-147-226.compute-1.amazonaws.com:5432/d8vp4ld2aaqaer");
		dataSource.setUsername("nhdkmarcnbdzbv");
		dataSource.setPassword("fbbb2d2d94099c537b5cb5716da5f6fb59342b98d243ecc10f5ac9b843ac8cf1");
		
		return dataSource;
	}
	
	@Bean
	JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager transactionManager =  new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory);
		return transactionManager;
	}
	
	Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.show_sql", "true");
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
		return properties;
		
	}
	
}
