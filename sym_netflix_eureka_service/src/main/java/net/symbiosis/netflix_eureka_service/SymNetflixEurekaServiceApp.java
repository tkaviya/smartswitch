package net.symbiosis.netflix_eureka_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/***************************************************************************
 *                                                                         *
 * Created:     09 / 02 / 2021                                             *
 * Author:      Tsungai Kaviya                                             *
 * System:      IntelliJ 2019 / Windows 10                                 *
 * Contact:     tsungai.kaviya@gmail.com                                   *
 *                                                                         *
 ***************************************************************************/

@EnableEurekaServer
@SpringBootApplication
public class SymNetflixEurekaServiceApp {

	public static void main(String[] args) {
		SpringApplication.run(SymNetflixEurekaServiceApp.class, args);
	}
}