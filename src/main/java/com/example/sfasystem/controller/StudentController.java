package com.example.sfasystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.sfasystem.form.StudentForm;
import com.example.sfasystem.service.StudentService;

import lombok.RequiredArgsConstructor;

/**
 * 生徒情報コントローラー
 */
@Controller
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

	/** 生徒情報サービス */
	private final StudentService studentService;

	/**
	 * 生徒一覧画面を表示する
	 *
	 * @param model モデル
	 * @return 生徒一覧画面
	 */
	@GetMapping
	public String index(Model model) {
		model.addAttribute("students", studentService.findAll());
		return "student/index";
	}

	/**
	 * 生徒新規登録画面を表示する
	 *
	 * @param model モデル
	 * @return 生徒新規登録画面
	 */
	@GetMapping("/new")
	public String newStudent(Model model) {
		model.addAttribute("studentForm", new StudentForm());
		return "student/new";
	}

	/**
	 * 生徒情報を新規登録する
	 *
	 * @param form          生徒フォーム
	 * @param bindingResult バリデーション結果
	 * @return 生徒一覧画面へリダイレクト
	 */
	@PostMapping("/create")
	public String create(
			@Validated @ModelAttribute("studentForm") StudentForm form,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "student/new";
		}
		studentService.create(form);
		return "redirect:/students";
	}

	/**
	 * 生徒編集画面を表示する
	 *
	 * @param id    生徒ID
	 * @param model モデル
	 * @return 生徒編集画面
	 */
	@GetMapping("/{id}/edit")
	public String edit(@PathVariable Long id, Model model) {
		model.addAttribute("studentForm", studentService.getForm(id));
		model.addAttribute("id", id);
		return "student/edit";
	}

	/**
	 * 生徒情報を更新する
	 *
	 * @param id            生徒ID
	 * @param form          生徒フォーム
	 * @param bindingResult バリデーション結果
	 * @param model         モデル
	 * @return 生徒一覧画面へリダイレクト
	 */
	@PostMapping("/{id}/update")
	public String update(
			@PathVariable Long id,
			@Validated @ModelAttribute("studentForm") StudentForm form,
			BindingResult bindingResult,
			Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("id", id);
			return "student/edit";
		}
		studentService.update(id, form);
		return "redirect:/students";
	}

	/**
	 * 生徒情報を削除する
	 *
	 * @param id 生徒ID
	 * @return 生徒一覧画面へリダイレクト
	 */
	@PostMapping("/{id}/delete")
	public String delete(@PathVariable Long id) {
		studentService.delete(id);
		return "redirect:/students";
	}
}