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

import com.example.sfasystem.form.CurriculumForm;
import com.example.sfasystem.service.CurriculumService;

import lombok.RequiredArgsConstructor;

/**
 * カリキュラムコントローラー
 */
@Controller
@RequestMapping("/curriculums")
@RequiredArgsConstructor
public class CurriculumController {

	/** カリキュラムサービス */
	private final CurriculumService curriculumService;

	/**
	 * カリキュラム一覧画面を表示する
	 *
	 * @param model モデル
	 * @return カリキュラム一覧画面
	 */
	@GetMapping
	public String index(Model model) {
		model.addAttribute("curriculums", curriculumService.findAll());
		return "curriculum/index";
	}

	/**
	 * カリキュラム新規登録画面を表示する
	 *
	 * @param model モデル
	 * @return カリキュラム新規登録画面
	 */
	@GetMapping("/new")
	public String newCurriculum(Model model) {
		model.addAttribute("curriculumForm", new CurriculumForm());
		return "curriculum/new";
	}

	/**
	 * カリキュラムを新規登録する
	 *
	 * @param form          カリキュラムフォーム
	 * @param bindingResult バリデーション結果
	 * @return カリキュラム一覧画面へリダイレクト
	 */
	@PostMapping("/create")
	public String create(
			@Validated @ModelAttribute("curriculumForm") CurriculumForm form,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "curriculum/new";
		}
		curriculumService.create(form);
		return "redirect:/curriculums";
	}

	/**
	 * カリキュラム編集画面を表示する
	 *
	 * @param id    カリキュラムID
	 * @param model モデル
	 * @return カリキュラム編集画面
	 */
	@GetMapping("/{id}/edit")
	public String edit(@PathVariable Long id, Model model) {
		model.addAttribute("curriculumForm", curriculumService.getForm(id));
		model.addAttribute("id", id);
		return "curriculum/edit";
	}

	/**
	 * カリキュラムを更新する
	 *
	 * @param id            カリキュラムID
	 * @param form          カリキュラムフォーム
	 * @param bindingResult バリデーション結果
	 * @param model         モデル
	 * @return カリキュラム一覧画面へリダイレクト
	 */
	@PostMapping("/{id}/update")
	public String update(
			@PathVariable Long id,
			@Validated @ModelAttribute("curriculumForm") CurriculumForm form,
			BindingResult bindingResult,
			Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("id", id);
			return "curriculum/edit";
		}
		curriculumService.update(id, form);
		return "redirect:/curriculums";
	}

	/**
	 * カリキュラムを削除する
	 *
	 * @param id カリキュラムID
	 * @return カリキュラム一覧画面へリダイレクト
	 */
	@PostMapping("/{id}/delete")
	public String delete(@PathVariable Long id) {
		curriculumService.delete(id);
		return "redirect:/curriculums";
	}

}
