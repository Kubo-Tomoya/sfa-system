package com.example.sfasystem.entity;

import java.time.LocalDate;
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
 * 生徒情報エンティティ
 */
@Entity
@Table(name = "students")
@Getter
@Setter
public class Student {

	/** 生徒ID */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/** 生徒コード */
	@Column(name = "student_code", nullable = false, length = 20)
	private String studentCode;

	/** 姓 */
	@Column(name = "last_name", nullable = false, length = 50)
	private String lastName;

	/** 名 */
	@Column(name = "first_name", nullable = false, length = 50)
	private String firstName;

	/** 姓（カナ） */
	@Column(name = "last_name_kana", length = 50)
	private String lastNameKana;

	/** 名（カナ） */
	@Column(name = "first_name_kana", length = 50)
	private String firstNameKana;

	/** メールアドレス */
	@Column(nullable = false, length = 255)
	private String email;

	/** 電話番号 */
	@Column(name = "phone_number", length = 20)
	private String phoneNumber;

	/** 性別 */
	@Column(length = 1)
	private String gender;

	/** 生年月日 */
	@Column(name = "birth_date")
	private LocalDate birthDate;

	/** 入校日 */
	@Column(name = "entry_date", nullable = false)
	private LocalDate entryDate;

	/** 修了予定日 */
	@Column(name = "expected_graduation_date")
	private LocalDate expectedGraduationDate;

	/** 在籍ステータス */
	@Column(nullable = false)
	private Integer status;

	/** 最終学歴 */
	@Column(name = "education_level", length = 50)
	private String educationLevel;

	/** 実務経験年数 */
	@Column(name = "experience_years")
	private Integer experienceYears;

	/** 備考 */
	private String note;

	/** 登録日時 */
	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt;

	/** 更新日時 */
	@Column(name = "updated_at", nullable = false)
	private LocalDateTime updatedAt;

	/** 削除日時 */
	@Column(name = "deleted_at")
	private LocalDateTime deletedAt;
}