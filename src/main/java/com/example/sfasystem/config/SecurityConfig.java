package com.example.sfasystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.sfasystem.service.AdminDetailsService;

import lombok.RequiredArgsConstructor;

/**
 * Spring Security設定クラス
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	/**管理者認証サービス*/
	private final AdminDetailsService adminDetailsService;
	
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
	 * セキュリティフィルターチェーンを設定する
	 *
	 * @param http HttpSecurity
	 * @return SecurityFilterChain
	 * @throws Exception 例外
	 */
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.userDetailsService(adminDetailsService)
				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/login").permitAll()
						.anyRequest().authenticated())
				.formLogin(login -> login
						.loginPage("/login")
						.defaultSuccessUrl("/students", true)
						.permitAll())
				.logout(logout -> logout
						.invalidateHttpSession(true)
						.deleteCookies("JSESSIONID")
						.logoutSuccessUrl("/login")
						.permitAll())
				.sessionManagement(session -> session
						.invalidSessionUrl("/login"));
		return http.build();
	}
}