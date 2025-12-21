package edu.snhu.garrison.windwalker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Framework, Architectural Style, Language, and Library Explanation: 
 * ---------------------------------------------------------------------------
 * REST - Stateless Primary Architecture used throughout the application. Describes how clients and servers interact over HTTP in a stateless way: each request must contain all necessary info.
 * 
 * Thymeleaf - The template engine used by Spring Boot to generate HTML on the server. Works with Spring's 
 * view resolution process. Instead of returning raw data to the view by default, the controller returns the view name and Thymeleaf renders the HTML. 
 * 
 * Bootstrap - Content Delivery Network to handle CSS. 
 * 
 * HTML - Markup Language used to create the views.
 * 
 * JavaScript - Scripting language used to handle the logic behind the views. In the Browser, it acts as the REST Client (when fetch or AJAX is used) in the REST architecture.
 *  
 * Java - Programming language used to handle the logic behind the application. Acts as the Server in the REST architecture. 
 * 
 * Hibernate - Hibernate is a JPA implementation (an Object Relational Mapping (ORM)). It maps Java objects to database tables, generates SQL, and executes it through JDBC. 
 * Hibernate uses JDBC and the driver to communicate with the database.
 * 
 * JDBC - Java Databse Connectivity - JDBC is the standard Java API for executing SQL and managing database connections — the contract all drivers implement.
 * 
 * JPA - Java Persistence API (Jakarta Persistence API) - Defines how java objects map to database tables.
 * 
 * H2 - H2 is an in-memory relational database engine. The H2 JDBC driver implements the JDBC API so Hibernate (via Spring Boot) can talk to H2 using SQL. The org.h2.Driver handles the database’s internal protocol.
 */
@SpringBootApplication
public class WindwalkerApplication {

	public static void main(String[] args) {
		SpringApplication.run(WindwalkerApplication.class, args);
	}

}
