package com.example.sfasystem.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.sfasystem.entity.Admin;
import com.example.sfasystem.repository.AdminRepository;

import lombok.RequiredArgsConstructor;

/**
 * 管理者認証サービス
 */
@Service
@RequiredArgsConstructor
public class AdminDetailsService implements UserDetailsService {

	/** 管理者リポジトリ */
	private final AdminRepository adminRepository;

	/**
	 * ユーザー名で管理者情報を取得する
	 *
	 * @param username ユーザー名
	 * @return UserDetails
	 * @throws UsernameNotFoundException ユーザーが見つからない場合
	 */
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		Admin admin = adminRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException(
						"ユーザーが見つかりません: " + username));

		String role = admin.getRole() == 1 ? "ADMIN" : "MENTOR";

		return User.builder()
				.username(admin.getUsername())
				.password(admin.getPassword())
				.roles(role)
				.build();
	}
}