package com.example.sfasystem.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * カリキュラム進捗エンティティ
 */
@Entity
@Table(name = "curriculum_progresses")
@Getter
@Setter
public class CurriculumProgress {

	/** カリキュラム進捗ID */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/** 生徒 */
	@ManyToOne
	@JoinColumn(name = "student_id", nullable = false)
	private Student student;

	/** カリキュラム */
	@ManyToOne
	@JoinColumn(name = "curriculum_id", nullable = false)
	private Curriculum curriculum;

	/** 開始日 */
	@Column(name = "start_date")
	private LocalDate startDate;

	/** 現在ステップ */
	@Column(name = "current_step")
	private Integer currentStep;

	/** 最終アクセス日時 */
	@Column(name = "last_accessed_at")
	private LocalDateTime lastAccessedAt;

	/** 完了日 */
	@Column(name = "completion_date")
	private LocalDateTime completionDate;

	/** 進捗ステータス */
	@Column(nullable = false)
	private Integer status;

	/** 遅延理由 */
	@Column(name = "delay_reason")
	private String delayReason;

	/** メンターコメント */
	@Column(name = "mentor_comment")
	private String mentorComment;

	/** 登録日時 */
	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt;

	/** 更新日時 */
	@Column(name = "updated_at", nullable = false)
	private LocalDateTime updatedAt;

}
