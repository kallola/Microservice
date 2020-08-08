package com.bean.config;

import java.util.HashMap;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.bean.user.repository.UserRepository;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef="userEntityManagerFactory",transactionManagerRef="usertransactionManager",
						basePackages="com.bean.user.repository",basePackageClasses = { UserRepository.class })
 class UserDBConfig {
	@Primary
	@Bean(name="userdataSource")
	@ConfigurationProperties(prefix="spring.user.datasource")
	public DataSource dataSource()
	{
	return DataSourceBuilder.create().build();
	}
	
	@Primary
	@Bean(name="userEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean userEntityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("userdataSource")DataSource dataSource){
		HashMap<String, Object>  properties=new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", "create");
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		return builder.dataSource(dataSource).properties(properties).packages("com.bean.model").persistenceUnit("User").build();
		//return builder.dataSource(dataSource).packages("com.bean.model").persistenceUnit("User").build();
		
	}
	
	@Primary
	@Bean(name="usertransactionManager")
	public PlatformTransactionManager transactionManager(@Qualifier("userEntityManagerFactory")EntityManagerFactory entityManagerFactory)
	{
		return new JpaTransactionManager(entityManagerFactory);
	}
}
