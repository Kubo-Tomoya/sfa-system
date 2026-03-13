package com.example.sfasystem.form;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

/**
 * 生徒情報フォームクラス
 */
@Getter
@Setter
public class StudentForm {

	/** 生徒コード */
	@NotBlank(message = "生徒コードは必須です")
	@Size(max = 20, message = "生徒コードは20文字以内で入力してください")
	private String studentCode;

	/** 姓 */
	@NotBlank(message = "姓は必須です")
	@Size(max = 50, message = "姓は50文字以内で入力してください")
	private String lastName;

	/** 名 */
	@NotBlank(message = "名は必須です")
	@Size(max = 50, message = "名は50文字以内で入力してください")
	private String firstName;

	/** 姓（カナ） */
	@Size(max = 50, message = "姓（カナ）は50文字以内で入力してください")
	private String lastNameKana;

	/** 名（カナ） */
	@Size(max = 50, message = "名（カナ）は50文字以内で入力してください")
	private String firstNameKana;

	/** メールアドレス */
	@NotBlank(message = "メールアドレスは必須です")
	@Email(message = "メールアドレスの形式が正しくありません")
	private String email;

	/** 電話番号 */
	@Size(max = 20, message = "電話番号は20文字以内で入力してください")
	private String phoneNumber;

	/** 性別 */
	private String gender;

	/** 生年月日 */
	private LocalDate birthDate;

	/** 入校日 */
	@NotNull(message = "入校日は必須です")
	private LocalDate entryDate;

	/** 修了予定日 */
	private LocalDate expectedGraduationDate;

	/** 在籍ステータス */
	@NotNull(message = "在籍ステータスは必須です")
	private Integer status;

	/** 最終学歴 */
	@Size(max = 50, message = "最終学歴は50文字以内で入力してください")
	private String educationLevel;

	/** 実務経験年数 */
	private Integer experienceYears;

	/** 備考 */
	private String note;
}