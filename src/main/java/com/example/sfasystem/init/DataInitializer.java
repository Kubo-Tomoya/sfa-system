package com.example.sfasystem.init;

import java.time.LocalDateTime;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.sfasystem.entity.Admin;
import com.example.sfasystem.repository.AdminRepository;

import lombok.RequiredArgsConstructor;

/**
 * 初期データ投入クラス
 * アプリ起動時に管理者ユーザーが存在しない場合のみ登録する
 */
@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationRunner {

	/** 管理者リポジトリ */
	private final AdminRepository adminRepository;

	/** パスワードエンコーダー */
	private final PasswordEncoder passwordEncoder;

	/**
	 * アプリ起動時に初期データを投入する
	 *
	 * @param args アプリケーション引数
	 */
	@Override
	public void run(ApplicationArguments args) {
		if (adminRepository.count() == 0) {
			Admin admin = new Admin();
			admin.setUsername("admin");
			admin.setPassword(passwordEncoder.encode("password"));
			admin.setRole(1);
			admin.setCreatedAt(LocalDateTime.now());
			admin.setUpdatedAt(LocalDateTime.now());
			adminRepository.save(admin);
			System.out.println("初期管理者ユーザーを登録しました");
		}
	}
}