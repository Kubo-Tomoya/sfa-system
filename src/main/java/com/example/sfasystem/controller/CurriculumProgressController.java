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

import com.example.sfasystem.form.CurriculumProgressForm;
import com.example.sfasystem.service.CurriculumProgressService;
import com.example.sfasystem.service.CurriculumService;
import com.example.sfasystem.service.StudentService;

import lombok.RequiredArgsConstructor;

/**
 * カリキュラム進捗コントローラー
 */
@Controller
@RequestMapping("/curriculum-progresses")
@RequiredArgsConstructor
public class CurriculumProgressController {

	/** カリキュラム進捗サービス */
	private final CurriculumProgressService curriculumProgressService;

	/** 生徒サービス */
	private final StudentService studentService;

	/** カリキュラムサービス */
	private final CurriculumService curriculumService;

	/**
	 * カリキュラム進捗一覧画面を表示する
	 *
	 * @param model モデル
	 * @return カリキュラム進捗一覧画面
	 */
	@GetMapping
	public String index(Model model) {
		model.addAttribute("progresses", curriculumProgressService.findAll());
		return "curriculum-progress/index";
	}

	/**
	 * カリキュラム進捗新規登録画面を表示する
	 *
	 * @param model モデル
	 * @return カリキュラム進捗新規登録画面
	 */
	@GetMapping("/new")
	public String newProgress(Model model) {
		model.addAttribute("curriculumProgressForm", new CurriculumProgressForm());
		model.addAttribute("students", studentService.findAll());
		model.addAttribute("curriculums", curriculumService.findAll());
		return "curriculum-progress/new";
	}

	/**
	 * カリキュラム進捗を新規登録する
	 *
	 * @param form          カリキュラム進捗フォーム
	 * @param bindingResult バリデーション結果
	 * @param model         モデル
	 * @return カリキュラム進捗一覧画面へリダイレクト
	 */
	@PostMapping("/create")
	public String create(
			@Validated @ModelAttribute("curriculumProgressForm") CurriculumProgressForm form,
			BindingResult bindingResult,
			Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("students", studentService.findAll());
			model.addAttribute("curriculums", curriculumService.findAll());
			return "curriculum-progress/new";
		}
		curriculumProgressService.create(form);
		return "redirect:/curriculum-progresses";
	}

	/**
	 * カリキュラム進捗編集画面を表示する
	 *
	 * @param id    カリキュラム進捗ID
	 * @param model モデル
	 * @return カリキュラム進捗編集画面
	 */
	@GetMapping("/{id}/edit")
	public String edit(@PathVariable Long id, Model model) {
		model.addAttribute("curriculumProgressForm",
				curriculumProgressService.getForm(id));
		model.addAttribute("students", studentService.findAll());
		model.addAttribute("curriculums", curriculumService.findAll());
		model.addAttribute("id", id);
		return "curriculum-progress/edit";
	}

	/**
	 * カリキュラム進捗を更新する
	 *
	 * @param id            カリキュラム進捗ID
	 * @param form          カリキュラム進捗フォーム
	 * @param bindingResult バリデーション結果
	 * @param model         モデル
	 * @return カリキュラム進捗一覧画面へリダイレクト
	 */
	@PostMapping("/{id}/update")
	public String update(
			@PathVariable Long id,
			@Validated @ModelAttribute("curriculumProgressForm") CurriculumProgressForm form,
			BindingResult bindingResult,
			Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("students", studentService.findAll());
			model.addAttribute("curriculums", curriculumService.findAll());
			model.addAttribute("id", id);
			return "curriculum-progress/edit";
		}
		curriculumProgressService.update(id, form);
		return "redirect:/curriculum-progresses";
	}

	/**
	 * カリキュラム進捗を削除する
	 *
	 * @param id カリキュラム進捗ID
	 * @return カリキュラム進捗一覧画面へリダイレクト
	 */
	@PostMapping("/{id}/delete")
	public String delete(@PathVariable Long id) {
		curriculumProgressService.delete(id);
		return "redirect:/curriculum-progresses";
	}

}
