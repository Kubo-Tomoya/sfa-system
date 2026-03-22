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
 * 配属エンティティ
 */
@Entity
@Table(name = "assignments")
@Getter
@Setter
public class Assignment {
	
	/** 配属ID */
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

	/** 役割 */
	@Column(length = 50)
	private String role;

	/** 配属区分 */
	@Column(name = "assignment_type", nullable = false)
	private Integer assignmentType;

	/** 開始日 */
	@Column(name = "start_date", nullable = false)
	private LocalDate startDate;

	/** 終了日 */
	@Column(name = "end_date")
	private LocalDate endDate;

	/** 稼働時間 */
	@Column(name = "working_hours")
	private Integer workingHours;

	/** 契約形態 */
	@Column(name = "contract_type")
	private Integer contractType;

	/** 登録日時 */
	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt;

	/** 更新日時 */
	@Column(name = "updated_at", nullable = false)
	private LocalDateTime updatedAt;


}
