package com.example.sfasystem.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.sfasystem.entity.StatusLog;
import com.example.sfasystem.form.StatusLogForm;
import com.example.sfasystem.repository.AdminRepository;
import com.example.sfasystem.repository.FieldRepository;
import com.example.sfasystem.repository.StatusLogRepository;
import com.example.sfasystem.repository.StudentRepository;

import lombok.RequiredArgsConstructor;

/**
 * 状況ログサービス
 */
@Service
@RequiredArgsConstructor

public class StatusLogService {

	/** 状況ログリポジトリ */
	private final StatusLogRepository statusLogRepository;

	/** 生徒リポジトリ */
	private final StudentRepository studentRepository;

	/** 現場リポジトリ */
	private final FieldRepository fieldRepository;

	/** 管理者リポジトリ */
	private final AdminRepository adminRepository;

	/**
	 * 状況ログ一覧を取得する
	 *
	 * @return 状況ログ一覧
	 */
	public List<StatusLog> findAll() {
		return statusLogRepository.findAll();
	}

	/**
	 * 状況ログを1件取得する
	 *
	 * @param id 状況ログID
	 * @return 状況ログ情報
	 */
	public StatusLog findById(Long id) {
		return statusLogRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("状況ログが見つかりません"));
	}

	/**
	 * 状況ログを新規登録する
	 *
	 * @param form 状況ログフォーム
	 */
	public void create(StatusLogForm form) {
		StatusLog statusLog = new StatusLog();
		setStatusLogFields(statusLog, form);
		statusLog.setCreatedAt(LocalDateTime.now());
		statusLogRepository.save(statusLog);
	}

	/**
	 * 状況ログを更新する
	 *
	 * @param id   状況ログID
	 * @param form 状況ログフォーム
	 */
	public void update(Long id, StatusLogForm form) {
		StatusLog statusLog = findById(id);
		setStatusLogFields(statusLog, form);
		statusLogRepository.save(statusLog);
	}

	/**
	 * 状況ログを削除する
	 *
	 * @param id 状況ログID
	 */
	public void delete(Long id) {
		statusLogRepository.deleteById(id);
	}

	/**
	 * フォームの内容をエンティティにセットする
	 *
	 * @param statusLog 状況ログエンティティ
	 * @param form      状況ログフォーム
	 */
	private void setStatusLogFields(StatusLog statusLog, StatusLogForm form) {
		statusLog.setStudent(studentRepository.findById(form.getStudentId())
				.orElseThrow(() -> new RuntimeException("生徒が見つかりません")));
		statusLog.setField(fieldRepository.findById(form.getFieldId())
				.orElseThrow(() -> new RuntimeException("現場が見つかりません")));
		statusLog.setAdmin(adminRepository.findById(form.getAdminId())
				.orElseThrow(() -> new RuntimeException("管理者が見つかりません")));
		statusLog.setStatus(form.getStatus());
		statusLog.setWorkloadLevel(form.getWorkloadLevel());
		statusLog.setPerformanceEvaluation(form.getPerformanceEvaluation());
		statusLog.setComment(form.getComment());
		statusLog.setRecordedAt(form.getRecordedAt());
	}

	/**
	 * 状況ログ情報をフォームに変換する
	 *
	 * @param id 状況ログID
	 * @return 状況ログフォーム
	 */
	public StatusLogForm getForm(Long id) {
		StatusLog statusLog = findById(id);
		StatusLogForm form = new StatusLogForm();
		form.setStudentId(statusLog.getStudent().getId());
		form.setFieldId(statusLog.getField().getId());
		form.setAdminId(statusLog.getAdmin().getId());
		form.setStatus(statusLog.getStatus());
		form.setWorkloadLevel(statusLog.getWorkloadLevel());
		form.setPerformanceEvaluation(statusLog.getPerformanceEvaluation());
		form.setComment(statusLog.getComment());
		form.setRecordedAt(statusLog.getRecordedAt());
		return form;
	}

}
