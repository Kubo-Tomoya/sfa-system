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

import com.example.sfasystem.form.AssignmentForm;
import com.example.sfasystem.service.AssignmentService;
import com.example.sfasystem.service.FieldService;
import com.example.sfasystem.service.StudentService;

import lombok.RequiredArgsConstructor;

/**
 * 配属コントローラー
 */
@Controller
@RequestMapping("/assignments")
@RequiredArgsConstructor
public class AssignmentController {
	
	/** 配属サービス */
	private final AssignmentService assignmentService;

	/** 生徒サービス */
	private final StudentService studentService;

	/** 現場サービス */
	private final FieldService fieldService;

	/**
	 * 配属一覧画面を表示する
	 *
	 * @param model モデル
	 * @return 配属一覧画面
	 */
	@GetMapping
	public String index(Model model) {
		model.addAttribute("assignments", assignmentService.findAll());
		return "assignment/index";
	}

	/**
	 * 配属新規登録画面を表示する
	 *
	 * @param model モデル
	 * @return 配属新規登録画面
	 */
	@GetMapping("/new")
	public String newAssignment(Model model) {
		model.addAttribute("assignmentForm", new AssignmentForm());
		model.addAttribute("students", studentService.findAll());
		model.addAttribute("fields", fieldService.findAll());
		return "assignment/new";
	}

	/**
	 * 配属を新規登録する
	 *
	 * @param form          配属フォーム
	 * @param bindingResult バリデーション結果
	 * @param model         モデル
	 * @return 配属一覧画面へリダイレクト
	 */
	@PostMapping("/create")
	public String create(
			@Validated @ModelAttribute("assignmentForm") AssignmentForm form,
			BindingResult bindingResult,
			Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("students", studentService.findAll());
			model.addAttribute("fields", fieldService.findAll());
			return "assignment/new";
		}
		assignmentService.create(form);
		return "redirect:/assignments";
	}

	/**
	 * 配属編集画面を表示する
	 *
	 * @param id    配属ID
	 * @param model モデル
	 * @return 配属編集画面
	 */
	@GetMapping("/{id}/edit")
	public String edit(@PathVariable Long id, Model model) {
		model.addAttribute("assignmentForm", assignmentService.getForm(id));
		model.addAttribute("students", studentService.findAll());
		model.addAttribute("fields", fieldService.findAll());
		model.addAttribute("id", id);
		return "assignment/edit";
	}

	/**
	 * 配属を更新する
	 *
	 * @param id            配属ID
	 * @param form          配属フォーム
	 * @param bindingResult バリデーション結果
	 * @param model         モデル
	 * @return 配属一覧画面へリダイレクト
	 */
	@PostMapping("/{id}/update")
	public String update(
			@PathVariable Long id,
			@Validated @ModelAttribute("assignmentForm") AssignmentForm form,
			BindingResult bindingResult,
			Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("students", studentService.findAll());
			model.addAttribute("fields", fieldService.findAll());
			model.addAttribute("id", id);
			return "assignment/edit";
		}
		assignmentService.update(id, form);
		return "redirect:/assignments";
	}

	/**
	 * 配属を削除する
	 *
	 * @param id 配属ID
	 * @return 配属一覧画面へリダイレクト
	 */
	@PostMapping("/{id}/delete")
	public String delete(@PathVariable Long id) {
		assignmentService.delete(id);
		return "redirect:/assignments";
	}


}
