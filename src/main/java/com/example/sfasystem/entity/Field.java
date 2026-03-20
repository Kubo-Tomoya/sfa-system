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
 * 現場エンティティ
 */
@Entity
@Table(name="fields")
@Getter
@Setter
public class Field {
	
	/** 現場ID */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/** 現場コード */
	@Column(name = "field_code", nullable = false, length = 20)
	private String fieldCode;

	/** 現場名 */
	@Column(nullable = false, length = 100)
	private String name;

	/** クライアント名 */
	@Column(name = "client_name", length = 100)
	private String clientName;

	/** 業界 */
	@Column(length = 50)
	private String industry;

	/** 勤務地 */
	@Column(length = 100)
	private String location;

	/** 現場説明 */
	private String description;

	/** 必要スキル */
	@Column(name = "required_skills")
	private String requiredSkills;

	/** 登録日時 */
	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt;

	/** 更新日時 */
	@Column(name = "updated_at", nullable = false)
	private LocalDateTime updatedAt;


}
