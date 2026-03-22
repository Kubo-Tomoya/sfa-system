package com.example.sfasystem.form;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

/**
 * 配属フォームクラス
 */
@Getter
@Setter
public class AssignmentForm {
	
	/** 生徒ID */
	@NotNull(message = "生徒は必須です")
	private Long studentId;

	/** 現場ID */
	@NotNull(message = "現場は必須です")
	private Long fieldId;

	/** 役割 */
	private String role;

	/** 配属区分 */
	@NotNull(message = "配属区分は必須です")
	private Integer assignmentType;

	/** 開始日 */
	@NotNull(message = "開始日は必須です")
	private LocalDate startDate;

	/** 終了日 */
	private LocalDate endDate;

	/** 稼働時間 */
	private Integer workingHours;

	/** 契約形態 */
	private Integer contractType;


}
