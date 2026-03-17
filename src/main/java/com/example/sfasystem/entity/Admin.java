package com.example.sfasystem.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * 管理者エンティティ
 */
@Entity
@Table(name = "admin")
@Getter
@Setter
public class Admin {

	/** 管理者ID */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/** ログイン名 */
	@Column(nullable = false, length = 50)
	private String username;

	/** パスワード */
	@Column(nullable = false, length = 255)
	private String password;

	/** ロール */
	@Column(nullable = false)
	private Integer role;

	/** 登録日時 */
	@Column(name = "created_at")
	private LocalDateTime createdAt;

	/** 更新日時 */
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;
}