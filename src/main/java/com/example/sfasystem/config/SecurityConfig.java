package com.example.sfasystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Spring Security設定クラス
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

	/**
	 * パスワードエンコーダーを生成する
	 *
	 * @return BCryptPasswordEncoder
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * インメモリユーザー認証を設定する
	 *
	 * @return InMemoryUserDetailsManager
	 */
	@Bean
	public InMemoryUserDetailsManager userDetailsService() {
		UserDetails user = User.builder()
				.username("admin")
				.password(passwordEncoder().encode("password"))
				.roles("ADMIN")
				.build();
		return new InMemoryUserDetailsManager(user);
	}

	/**
	 * セキュリティフィルターチェーンを設定する
	 *
	 * @param http HttpSecurity
	 * @return SecurityFilterChain
	 * @throws Exception 例外
	 */
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/login").permitAll()
						.anyRequest().authenticated())
				.formLogin(login -> login
						.loginPage("/login")
						.defaultSuccessUrl("/students")
						.permitAll())
				.logout(logout -> logout
						.logoutSuccessUrl("/login")
						.permitAll());
		return http.build();
	}
}