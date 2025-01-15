package com.eventmanagement.eventapp;

import com.eventmanagement.eventapp.models.Address;
import com.eventmanagement.eventapp.models.Event;
import com.eventmanagement.eventapp.models.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class EventappApplication {

	public static void main(String[] args) {


		SpringApplication.run(EventappApplication.class, args);
	}



	@Bean
	public SessionFactory getSessionFactory() {
		Map<String, Object> settings = new HashMap<>();

		settings.put("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
		settings.put("hibernate.connection.url", "enter your URL");
		settings.put("hibernate.connection.username", "enter username");
		settings.put("hibernate.connection.password", "enter password");

		settings.put("hibernate.hbm2ddl.auto", "update");
		settings.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		settings.put("hibernate.dialect.storage_engine", "innodb");
		settings.put("hibernate.show-sql", "true");
		settings.put("hibernate.current_session_context_class", "org.hibernate.context.internal.ThreadLocalSessionContext");

		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(settings)
				.build();
		MetadataSources metaDataSources = new MetadataSources(serviceRegistry);
		metaDataSources.addPackage("com.eventmanagement.eventapp.models");
		metaDataSources.addAnnotatedClass(User.class);
		metaDataSources.addAnnotatedClass(Address.class);
		metaDataSources.addAnnotatedClass(Event.class);
		Metadata metaData = metaDataSources.buildMetadata();

		return metaData.getSessionFactoryBuilder().build();
	}
}
