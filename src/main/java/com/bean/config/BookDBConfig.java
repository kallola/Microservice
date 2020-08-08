package com.bean.config;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.bean.book.repository.BookRepository;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef="bookEntityManagerFactory",transactionManagerRef="booktransactionManager",
						basePackages="com.bean.book.repository",basePackageClasses = { BookRepository.class })
public class BookDBConfig {

	@Bean(name="bookdataSource")
	@ConfigurationProperties(prefix="spring.book.datasource")
	public DataSource dataSource()
	{
	return DataSourceBuilder.create().build();
	}
	
	
	@Bean(name="bookEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean bookEntityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("bookdataSource")DataSource dataSource){
		HashMap<String, Object>  properties=new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", "create");
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		return builder.dataSource(dataSource).properties(properties).packages("com.bean.model").persistenceUnit("BOOK").build();
		//return builder.dataSource(dataSource).packages("com.bean.model").persistenceUnit("BOOK").build();
		
	}
	
	@Bean(name="booktransactionManager")
	public PlatformTransactionManager transactionManager(@Qualifier("bookEntityManagerFactory")EntityManagerFactory entityManagerFactory)
	{
		return new JpaTransactionManager(entityManagerFactory);
	}
}
