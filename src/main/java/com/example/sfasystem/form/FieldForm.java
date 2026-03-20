package com.example.sfasystem.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

/**
 * 現場フォームクラス
 */
@Getter
@Setter
public class FieldForm {

	/** 現場コード */
	@NotBlank(message = "現場コードは必須です")
	@Size(max = 20, message = "現場コードは20文字以内で入力してください")
	private String fieldCode;

	/** 現場名 */
	@NotBlank(message = "現場名は必須です")
	@Size(max = 100, message = "現場名は100文字以内で入力してください")
	private String name;

	/** クライアント名 */
	@Size(max = 100, message = "クライアント名は100文字以内で入力してください")
	private String clientName;

	/** 業界 */
	@Size(max = 50, message = "業界は50文字以内で入力してください")
	private String industry;

	/** 勤務地 */
	@Size(max = 100, message = "勤務地は100文字以内で入力してください")
	private String location;

	/** 現場説明 */
	private String description;

	/** 必要スキル */
	private String requiredSkills;

}
