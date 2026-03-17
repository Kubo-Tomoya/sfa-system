package com.example.sfasystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sfasystem.entity.Admin;

/**
 * 管理者リポジトリ
 */
public interface AdminRepository extends JpaRepository<Admin, Long>{
	
	/**
	 * ユーザー名で管理者を取得する
	 * 
	 */
	Optional<Admin>findByUsername(String usename);
}
