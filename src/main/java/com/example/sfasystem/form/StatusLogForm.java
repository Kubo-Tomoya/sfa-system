package com.example.sfasystem.form;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

/**
 * 状況ログフォームクラス
 */
@Getter
@Setter
public class StatusLogForm {

	/** 生徒ID */
	@NotNull(message = "生徒は必須です")
	private Long studentId;

	/** 現場ID */
	@NotNull(message = "現場は必須です")
	private Long fieldId;

	/** 管理者ID */
	@NotNull(message = "管理者は必須です")
	private Long adminId;

	/** 稼働ステータス */
	@NotNull(message = "稼働ステータスは必須です")
	private Integer status;

	/** 業務負荷 */
	private Integer workloadLevel;

	/** 評価 */
	private String performanceEvaluation;

	/** コメント */
	private String comment;

	/** 記録日時 */
	private LocalDateTime recordedAt;

}
