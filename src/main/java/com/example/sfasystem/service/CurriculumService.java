package com.example.sfasystem.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.sfasystem.entity.Curriculum;
import com.example.sfasystem.form.CurriculumForm;
import com.example.sfasystem.repository.CurriculumRepository;

import lombok.RequiredArgsConstructor;

/**
 * カリキュラムサービス
 */
@Service
@RequiredArgsConstructor
public class CurriculumService {
	
	/**カリキュラムリポジトリ*/
	private final CurriculumRepository curriculumRepository;
	
	/**
	 * カリキュラム一覧を取得する
	 *
	 * @return カリキュラム一覧
	 */
	public List<Curriculum> findAll() {
		return curriculumRepository.findAll();
	}
	
	/**
	 * カリキュラムを1件取得する
	 *
	 * @param id カリキュラムID
	 * @return カリキュラム情報
	 */
	public Curriculum findById(Long id) {
		return curriculumRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("カリキュラムが見つかりません"));
	}
	
	/**
	 * カリキュラムを新規登録する
	 *
	 * @param form カリキュラムフォーム
	 */
	public void create(CurriculumForm form) {
		Curriculum curriculum = new Curriculum();
		setCurriculumFields(curriculum, form);
		curriculum.setCreatedAt(LocalDateTime.now());
		curriculum.setUpdatedAt(LocalDateTime.now());
		curriculumRepository.save(curriculum);
	}
	
	/**
	 * カリキュラムを更新する
	 *
	 * @param id   カリキュラムID
	 * @param form カリキュラムフォーム
	 */
	public void update(Long id, CurriculumForm form) {
		Curriculum curriculum = findById(id);
		setCurriculumFields(curriculum, form);
		curriculum.setUpdatedAt(LocalDateTime.now());
		curriculumRepository.save(curriculum);
	}
	
	/**
	 * カリキュラムを削除する
	 *
	 * @param id カリキュラムID
	 */
	public void delete(Long id) {
		curriculumRepository.deleteById(id);
	}

	/**
	 * フォームの内容をエンティティにセットする
	 *
	 * @param curriculum カリキュラムエンティティ
	 * @param form       カリキュラムフォーム
	 */
	private void setCurriculumFields(Curriculum curriculum, CurriculumForm form) {
		curriculum.setCurriculumCode(form.getCurriculumCode());
		curriculum.setName(form.getName());
		curriculum.setDescription(form.getDescription());
		curriculum.setCategory(form.getCategory());
		curriculum.setDifficultyLevel(form.getDifficultyLevel());
		curriculum.setEstimatedHours(form.getEstimatedHours());
		curriculum.setVersion(form.getVersion());
		curriculum.setIsActive(form.getIsActive());
	}
	
	/**
	 * カリキュラム情報をフォームに変換する
	 *
	 * @param id カリキュラムID
	 * @return カリキュラムフォーム
	 */
	public CurriculumForm getForm(Long id) {
		Curriculum curriculum = findById(id);
		CurriculumForm form = new CurriculumForm();
		form.setCurriculumCode(curriculum.getCurriculumCode());
		form.setName(curriculum.getName());
		form.setDescription(curriculum.getDescription());
		form.setCategory(curriculum.getCategory());
		form.setDifficultyLevel(curriculum.getDifficultyLevel());
		form.setEstimatedHours(curriculum.getEstimatedHours());
		form.setVersion(curriculum.getVersion());
		form.setIsActive(curriculum.getIsActive());
		return form;
	}

}
