package com.example.sfasystem.entity;

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
 * 状況ログエンティティ
 */
@Entity
@Table(name = "status_logs")
@Getter
@Setter

public class StatusLog {

	/** 状況ログID */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/** 生徒 */
	@ManyToOne
	@JoinColumn(name = "student_id", nullable = false)
	private Student student;

	/** 現場 */
	@ManyToOne
	@JoinColumn(name = "field_id", nullable = false)
	private Field field;

	/** 管理者 */
	@ManyToOne
	@JoinColumn(name = "admin_id", nullable = false)
	private Admin admin;

	/** 稼働ステータス */
	@Column(nullable = false)
	private Integer status;

	/** 業務負荷 */
	@Column(name = "workload_level")
	private Integer workloadLevel;

	/** 評価 */
	@Column(name = "performance_evaluation", length = 1)
	private String performanceEvaluation;

	/** コメント */
	private String comment;

	/** 記録日時 */
	@Column(name = "recorded_at")
	private LocalDateTime recordedAt;

	/** 登録日時 */
	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt;

}
