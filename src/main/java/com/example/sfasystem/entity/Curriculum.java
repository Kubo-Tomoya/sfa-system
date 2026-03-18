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
 * カリキュラムエンティティ
 */
@Entity
@Table(name = "curriculums")
@Getter
@Setter
public class Curriculum {

	/** カリキュラムID */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/** カリキュラムコード */
	@Column(name = "curriculum_code", nullable = false, length = 20)
	private String curriculumCode;

	/** カリキュラム名 */
	@Column(nullable = false, length = 100)
	private String name;

	/** 説明 */
	private String description;

	/** カテゴリ */
	@Column(length = 50)
	private String category;

	/** 難易度 */
	@Column(name = "difficulty_level")
	private Integer difficultyLevel;

	/** 想定学習時間 */
	@Column(name = "estimated_hours")
	private Integer estimatedHours;

	/** バージョン */
	@Column(length = 20)
	private String version;

	/** 有効フラグ */
	@Column(name = "is_active", nullable = false)
	private Boolean isActive;

	/** 登録日時 */
	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt;

	/** 更新日時 */
	@Column(name = "updated_at", nullable = false)
	private LocalDateTime updatedAt;
}