package com.paymybuddy.paywebapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.sql.*;

@SpringBootApplication//(exclude =  {DataSourceAutoConfiguration.class })
public class PaywebappApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaywebappApplication.class, args);

	}
}
