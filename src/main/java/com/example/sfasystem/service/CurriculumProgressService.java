package com.example.sfasystem.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.sfasystem.entity.CurriculumProgress;
import com.example.sfasystem.form.CurriculumProgressForm;
import com.example.sfasystem.repository.CurriculumProgressRepository;
import com.example.sfasystem.repository.CurriculumRepository;
import com.example.sfasystem.repository.StudentRepository;

import lombok.RequiredArgsConstructor;

/**
 * カリキュラム進捗サービス
 */
@Service
@RequiredArgsConstructor
public class CurriculumProgressService {

	/** カリキュラム進捗リポジトリ */
	private final CurriculumProgressRepository curriculumProgressRepository;

	/** 生徒リポジトリ */
	private final StudentRepository studentRepository;

	/** カリキュラムリポジトリ */
	private final CurriculumRepository curriculumRepository;

	/**
	 * カリキュラム進捗一覧を取得する
	 *
	 * @return カリキュラム進捗一覧
	 */
	public List<CurriculumProgress> findAll() {
		return curriculumProgressRepository.findAll();
	}

	/**
	 * カリキュラム進捗を1件取得する
	 *
	 * @param id カリキュラム進捗ID
	 * @return カリキュラム進捗情報
	 */
	public CurriculumProgress findById(Long id) {
		return curriculumProgressRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("カリキュラム進捗が見つかりません"));
	}

	/**
	 * カリキュラム進捗を新規登録する
	 *
	 * @param form カリキュラム進捗フォーム
	 */
	public void create(CurriculumProgressForm form) {
		CurriculumProgress progress = new CurriculumProgress();
		setProgressFields(progress, form);
		progress.setCreatedAt(LocalDateTime.now());
		progress.setUpdatedAt(LocalDateTime.now());
		curriculumProgressRepository.save(progress);
	}

	/**
	 * カリキュラム進捗を更新する
	 *
	 * @param id   カリキュラム進捗ID
	 * @param form カリキュラム進捗フォーム
	 */
	public void update(Long id, CurriculumProgressForm form) {
		CurriculumProgress progress = findById(id);
		setProgressFields(progress, form);
		progress.setUpdatedAt(LocalDateTime.now());
		curriculumProgressRepository.save(progress);
	}

	/**
	 * カリキュラム進捗を削除する
	 *
	 * @param id カリキュラム進捗ID
	 */
	public void delete(Long id) {
		curriculumProgressRepository.deleteById(id);
	}

	/**
	 * フォームの内容をエンティティにセットする
	 *
	 * @param progress カリキュラム進捗エンティティ
	 * @param form     カリキュラム進捗フォーム
	 */
	private void setProgressFields(CurriculumProgress progress,
			CurriculumProgressForm form) {
		progress.setStudent(studentRepository.findById(form.getStudentId())
				.orElseThrow(() -> new RuntimeException("生徒が見つかりません")));
		progress.setCurriculum(curriculumRepository.findById(form.getCurriculumId())
				.orElseThrow(() -> new RuntimeException("カリキュラムが見つかりません")));
		progress.setStartDate(form.getStartDate());
		progress.setCurrentStep(form.getCurrentStep());
		progress.setLastAccessedAt(form.getLastAccessedAt());
		progress.setCompletionDate(form.getCompletionDate());
		progress.setStatus(form.getStatus());
		progress.setDelayReason(form.getDelayReason());
		progress.setMentorComment(form.getMentorComment());
	}

	/**
	 * カリキュラム進捗情報をフォームに変換する
	 *
	 * @param id カリキュラム進捗ID
	 * @return カリキュラム進捗フォーム
	 */
	public CurriculumProgressForm getForm(Long id) {
		CurriculumProgress progress = findById(id);
		CurriculumProgressForm form = new CurriculumProgressForm();
		form.setStudentId(progress.getStudent().getId());
		form.setCurriculumId(progress.getCurriculum().getId());
		form.setStartDate(progress.getStartDate());
		form.setCurrentStep(progress.getCurrentStep());
		form.setLastAccessedAt(progress.getLastAccessedAt());
		form.setCompletionDate(progress.getCompletionDate());
		form.setStatus(progress.getStatus());
		form.setDelayReason(progress.getDelayReason());
		form.setMentorComment(progress.getMentorComment());
		return form;
	}

}
