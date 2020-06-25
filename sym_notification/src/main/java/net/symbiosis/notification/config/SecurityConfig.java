//package net.symbiosis.notification.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//
///***************************************************************************
// *                                                                         *
// * Created:     23 / 06 / 2020                                             *
// * Author:      Tsungai Kaviya                                             *
// * System:      IntelliJ 2019 / Windows 10                                 *
// * Contact:     tsungai.kaviya@gmail.com                                   *
// *                                                                         *
// ***************************************************************************/
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//	@Override
//	public void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests()
//			.antMatchers("/hello").permitAll()
//			.antMatchers("/sms").hasRole("ROLE_SMS_SEND")
//			.antMatchers("/authedHello").hasRole("ROLE_SMS_SEND")
//			.anyRequest().authenticated()
//			.and()
//			.formLogin()
//			.and()
//			.oauth2Login();
//	}
//
//	//	@Override
////	protected void configure(HttpSecurity http) throws Exception {
////		http
////				.authorizeRequests()
////				.antMatchers("/hello").permitAll()
////				.anyRequest().authenticated()
////				.and()
////				.oauth2Login()
////				.permitAll()
////				.and()
////				.logout()
////				.permitAll();
////	}
////
////	@Bean
////	@Override
////	public UserDetailsService userDetailsService() {
////		UserDetails user =
////				User.withDefaultPasswordEncoder()
////						.username("user")
////						.password("password")
////						.roles("ROLE_SMS_SEND")
////						.build();
////
////		return new InMemoryUserDetailsManager(user);
////	}
//}
