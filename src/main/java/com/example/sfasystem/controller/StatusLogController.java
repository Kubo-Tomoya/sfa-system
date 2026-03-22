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

import com.example.sfasystem.form.StatusLogForm;
import com.example.sfasystem.repository.AdminRepository;
import com.example.sfasystem.service.FieldService;
import com.example.sfasystem.service.StatusLogService;
import com.example.sfasystem.service.StudentService;

import lombok.RequiredArgsConstructor;

/**
 * 状況ログコントローラー
 */
@Controller
@RequestMapping("/status-logs")
@RequiredArgsConstructor
public class StatusLogController {

	/** 状況ログサービス */
	private final StatusLogService statusLogService;

	/** 生徒サービス */
	private final StudentService studentService;

	/** 現場サービス */
	private final FieldService fieldService;

	/** 管理者リポジトリ */
	private final AdminRepository adminRepository;

	/**
	 * 状況ログ一覧画面を表示する
	 *
	 * @param model モデル
	 * @return 状況ログ一覧画面
	 */
	@GetMapping
	public String index(Model model) {
		model.addAttribute("statusLogs", statusLogService.findAll());
		return "status-log/index";
	}

	/**
	 * 状況ログ新規登録画面を表示する
	 *
	 * @param model モデル
	 * @return 状況ログ新規登録画面
	 */
	@GetMapping("/new")
	public String newStatusLog(Model model) {
		model.addAttribute("statusLogForm", new StatusLogForm());
		model.addAttribute("students", studentService.findAll());
		model.addAttribute("fields", fieldService.findAll());
		model.addAttribute("admins", adminRepository.findAll());
		return "status-log/new";
	}

	/**
	 * 状況ログを新規登録する
	 *
	 * @param form          状況ログフォーム
	 * @param bindingResult バリデーション結果
	 * @param model         モデル
	 * @return 状況ログ一覧画面へリダイレクト
	 */
	@PostMapping("/create")
	public String create(
			@Validated @ModelAttribute("statusLogForm") StatusLogForm form,
			BindingResult bindingResult,
			Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("students", studentService.findAll());
			model.addAttribute("fields", fieldService.findAll());
			model.addAttribute("admins", adminRepository.findAll());
			return "status-log/new";
		}
		statusLogService.create(form);
		return "redirect:/status-logs";
	}

	/**
	 * 状況ログ編集画面を表示する
	 *
	 * @param id    状況ログID
	 * @param model モデル
	 * @return 状況ログ編集画面
	 */
	@GetMapping("/{id}/edit")
	public String edit(@PathVariable Long id, Model model) {
		model.addAttribute("statusLogForm", statusLogService.getForm(id));
		model.addAttribute("students", studentService.findAll());
		model.addAttribute("fields", fieldService.findAll());
		model.addAttribute("admins", adminRepository.findAll());
		model.addAttribute("id", id);
		return "status-log/edit";
	}

	/**
	 * 状況ログを更新する
	 *
	 * @param id            状況ログID
	 * @param form          状況ログフォーム
	 * @param bindingResult バリデーション結果
	 * @param model         モデル
	 * @return 状況ログ一覧画面へリダイレクト
	 */
	@PostMapping("/{id}/update")
	public String update(
			@PathVariable Long id,
			@Validated @ModelAttribute("statusLogForm") StatusLogForm form,
			BindingResult bindingResult,
			Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("students", studentService.findAll());
			model.addAttribute("fields", fieldService.findAll());
			model.addAttribute("admins", adminRepository.findAll());
			model.addAttribute("id", id);
			return "status-log/edit";
		}
		statusLogService.update(id, form);
		return "redirect:/status-logs";
	}

	/**
	 * 状況ログを削除する
	 *
	 * @param id 状況ログID
	 * @return 状況ログ一覧画面へリダイレクト
	 */
	@PostMapping("/{id}/delete")
	public String delete(@PathVariable Long id) {
		statusLogService.delete(id);
		return "redirect:/status-logs";
	}

}
