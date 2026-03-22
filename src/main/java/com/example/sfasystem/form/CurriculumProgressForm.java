package com.example.sfasystem.form;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

/**
 * カリキュラム進捗フォームクラス
 */
@Getter
@Setter
public class CurriculumProgressForm {

	/** 生徒ID */
	@NotNull(message = "生徒は必須です")
	private Long studentId;

	/** カリキュラムID */
	@NotNull(message = "カリキュラムは必須です")
	private Long curriculumId;

	/** 開始日 */
	private LocalDate startDate;

	/** 現在ステップ */
	private Integer currentStep;

	/** 最終アクセス日時 */
	private LocalDateTime lastAccessedAt;

	/** 完了日 */
	private LocalDateTime completionDate;

	/** 進捗ステータス */
	@NotNull(message = "進捗ステータスは必須です")
	private Integer status;

	/** 遅延理由 */
	private String delayReason;

	/** メンターコメント */
	private String mentorComment;

}
