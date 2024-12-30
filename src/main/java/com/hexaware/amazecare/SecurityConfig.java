package com.hexaware.amazecare;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.hexaware.amazecare.service.UserSecurityService;

@Configuration
public class SecurityConfig {
	@Autowired
	private UserSecurityService userSecurityService;

	@Autowired
	private JwtFilter jwtFilter;
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf((csrf) -> csrf.disable())
				.authorizeHttpRequests(authorize -> authorize
						.requestMatchers(HttpMethod.POST, "/api/token").permitAll()
						.requestMatchers(HttpMethod.POST, "/auth/sign-up/inPatient").permitAll()
						.requestMatchers(HttpMethod.POST, "/auth/sign-up/outPatient").permitAll()
						.requestMatchers(HttpMethod.POST, "/auth/sign-up/lab-operator").hasAuthority("EXECUTIVE")
						.requestMatchers(HttpMethod.POST, "/auth/sign-up/doctor").hasAuthority("EXECUTIVE")
						.requestMatchers(HttpMethod.GET,"/api/doctor/all").hasAuthority("EXECUTIVE")
						.requestMatchers(HttpMethod.GET,"/api/labOperator/all").hasAuthority("EXECUTIVE")
						.requestMatchers(HttpMethod.GET,"/api/executive/getDetailsById/{eid}").hasAuthority("EXECUTIVE")
						.requestMatchers(HttpMethod.POST,"/api/executive/update/{eid}").hasAuthority("EXECUTIVE")
						.requestMatchers(HttpMethod.GET,"/api/hello").authenticated()
						.requestMatchers(HttpMethod.GET,"/department/all").permitAll()
						.requestMatchers(HttpMethod.GET,"/doctor/getDetails/{did}").permitAll()
						.requestMatchers(HttpMethod.GET,"/doctor/getAllTestType").permitAll()
						.requestMatchers(HttpMethod.GET,"/doctor/getAllTimeSlots").permitAll()
						.requestMatchers(HttpMethod.GET,"/auth/userDetails").authenticated()
						.requestMatchers(HttpMethod.POST,"/api/addAdmin").permitAll()
						.requestMatchers(HttpMethod.GET,"/getDoctorByDepartment/{dept}").permitAll()
						.requestMatchers(HttpMethod.GET,"/inpatient/getDetails/{pid}").hasAuthority("IN_PATIENT")
						.requestMatchers(HttpMethod.POST,"/inpatient/updateDetails/{pid}").hasAuthority("IN_PATIENT")
						.requestMatchers(HttpMethod.GET,"/api/getAllExecutives").hasAuthority("ADMIN")
						.requestMatchers(HttpMethod.POST,"/auth/sign-up/executive").hasAuthority("ADMIN")
						.requestMatchers(HttpMethod.GET,"/api/users-stat").hasAuthority("ADMIN")
						.requestMatchers(HttpMethod.GET,"/appointment/completed/{aid}").hasAuthority("DOCTOR")
						.requestMatchers(HttpMethod.POST,"/doctor/updateDoctor/{did}").hasAuthority("DOCTOR")
						.requestMatchers(HttpMethod.POST,"/doctor/setSchedule/{did}").hasAuthority("DOCTOR")
						.requestMatchers(HttpMethod.GET,"/doctor/appointment/getAll/{did}").hasAuthority("DOCTOR")
						.requestMatchers(HttpMethod.POST,"/doctor/generateMedicalRecord/{did}/{pid}").hasAuthority("DOCTOR")
						.requestMatchers(HttpMethod.POST,"/doctor/testnscans/{did}/{pid}").hasAuthority("DOCTOR")
						.requestMatchers(HttpMethod.GET,"/medicalrecord/{pid}").hasAnyAuthority("DOCTOR","IN_PATIENT","OUT_PATIENT")
						.requestMatchers(HttpMethod.GET,"/reports/fetch/{pid}").hasAnyAuthority("DOCTOR","IN_PATIENT","OUT_PATIENT")
						.requestMatchers(HttpMethod.GET, "/api/doctor/hello").hasAuthority("DOCTOR").anyRequest().permitAll())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

				.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	@Bean
	BCryptPasswordEncoder getEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	AuthenticationManager authManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

	@Bean
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userSecurityService);
		authenticationProvider.setPasswordEncoder(getEncoder());
		return authenticationProvider;
	}

}
