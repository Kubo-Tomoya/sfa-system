package com.example.sfasystem.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

/**
 * カリキュラムフォームクラス
 */
@Getter
@Setter
public class CurriculumForm {

	/** カリキュラムコード */
	@NotBlank(message = "カリキュラムコードは必須です")
	@Size(max = 20, message = "カリキュラムコードは20文字以内で入力してください")
	private String curriculumCode;

	/** カリキュラム名 */
	@NotBlank(message = "カリキュラム名は必須です")
	@Size(max = 100, message = "カリキュラム名は100文字以内で入力してください")
	private String name;

	/** 説明 */
	private String description;

	/** カテゴリ */
	@Size(max = 50, message = "カテゴリは50文字以内で入力してください")
	private String category;

	/** 難易度 */
	private Integer difficultyLevel;

	/** 想定学習時間 */
	private Integer estimatedHours;

	/** バージョン */
	@Size(max = 20, message = "バージョンは20文字以内で入力してください")
	private String version;

	/** 有効フラグ */
	@NotNull(message = "有効フラグは必須です")
	private Boolean isActive;
}