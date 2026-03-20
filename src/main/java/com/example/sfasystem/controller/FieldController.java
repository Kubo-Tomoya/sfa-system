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

import com.example.sfasystem.form.FieldForm;
import com.example.sfasystem.service.FieldService;

import lombok.RequiredArgsConstructor;

/**
 * 現場コントローラー
 */
@Controller
@RequestMapping("/fields")
@RequiredArgsConstructor
public class FieldController {
	
	/** 現場サービス */
	private final FieldService fieldService;

	/**
	 * 現場一覧画面を表示する
	 *
	 * @param model モデル
	 * @return 現場一覧画面
	 */
	@GetMapping
	public String index(Model model) {
		model.addAttribute("fields", fieldService.findAll());
		return "field/index";
	}

	/**
	 * 現場新規登録画面を表示する
	 *
	 * @param model モデル
	 * @return 現場新規登録画面
	 */
	@GetMapping("/new")
	public String newField(Model model) {
		model.addAttribute("fieldForm", new FieldForm());
		return "field/new";
	}

	/**
	 * 現場を新規登録する
	 *
	 * @param form          現場フォーム
	 * @param bindingResult バリデーション結果
	 * @return 現場一覧画面へリダイレクト
	 */
	@PostMapping("/create")
	public String create(
			@Validated @ModelAttribute("fieldForm") FieldForm form,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "field/new";
		}
		fieldService.create(form);
		return "redirect:/fields";
	}

	/**
	 * 現場編集画面を表示する
	 *
	 * @param id    現場ID
	 * @param model モデル
	 * @return 現場編集画面
	 */
	@GetMapping("/{id}/edit")
	public String edit(@PathVariable Long id, Model model) {
		model.addAttribute("fieldForm", fieldService.getForm(id));
		model.addAttribute("id", id);
		return "field/edit";
	}

	/**
	 * 現場を更新する
	 *
	 * @param id            現場ID
	 * @param form          現場フォーム
	 * @param bindingResult バリデーション結果
	 * @param model         モデル
	 * @return 現場一覧画面へリダイレクト
	 */
	@PostMapping("/{id}/update")
	public String update(
			@PathVariable Long id,
			@Validated @ModelAttribute("fieldForm") FieldForm form,
			BindingResult bindingResult,
			Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("id", id);
			return "field/edit";
		}
		fieldService.update(id, form);
		return "redirect:/fields";
	}

	/**
	 * 現場を削除する
	 *
	 * @param id 現場ID
	 * @return 現場一覧画面へリダイレクト
	 */
	@PostMapping("/{id}/delete")
	public String delete(@PathVariable Long id) {
		fieldService.delete(id);
		return "redirect:/fields";
	}


}
