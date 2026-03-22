package com.example.sfasystem.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.sfasystem.entity.Assignment;
import com.example.sfasystem.form.AssignmentForm;
import com.example.sfasystem.repository.AssignmentRepository;
import com.example.sfasystem.repository.FieldRepository;
import com.example.sfasystem.repository.StudentRepository;

import lombok.RequiredArgsConstructor;

/**
 * 配属サービス
 */
@Service
@RequiredArgsConstructor
public class AssignmentService {
	
	/** 配属リポジトリ */
	private final AssignmentRepository assignmentRepository;

	/** 生徒リポジトリ */
	private final StudentRepository studentRepository;

	/** 現場リポジトリ */
	private final FieldRepository fieldRepository;

	/**
	 * 配属一覧を取得する
	 *
	 * @return 配属一覧
	 */
	public List<Assignment> findAll() {
		return assignmentRepository.findAll();
	}

	/**
	 * 配属を1件取得する
	 *
	 * @param id 配属ID
	 * @return 配属情報
	 */
	public Assignment findById(Long id) {
		return assignmentRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("配属情報が見つかりません"));
	}

	/**
	 * 配属を新規登録する
	 *
	 * @param form 配属フォーム
	 */
	public void create(AssignmentForm form) {
		Assignment assignment = new Assignment();
		setAssignmentFields(assignment, form);
		assignment.setCreatedAt(LocalDateTime.now());
		assignment.setUpdatedAt(LocalDateTime.now());
		assignmentRepository.save(assignment);
	}

	/**
	 * 配属を更新する
	 *
	 * @param id   配属ID
	 * @param form 配属フォーム
	 */
	public void update(Long id, AssignmentForm form) {
		Assignment assignment = findById(id);
		setAssignmentFields(assignment, form);
		assignment.setUpdatedAt(LocalDateTime.now());
		assignmentRepository.save(assignment);
	}

	/**
	 * 配属を削除する
	 *
	 * @param id 配属ID
	 */
	public void delete(Long id) {
		assignmentRepository.deleteById(id);
	}

	/**
	 * フォームの内容をエンティティにセットする
	 *
	 * @param assignment 配属エンティティ
	 * @param form       配属フォーム
	 */
	private void setAssignmentFields(Assignment assignment, AssignmentForm form) {
		assignment.setStudent(studentRepository.findById(form.getStudentId())
				.orElseThrow(() -> new RuntimeException("生徒が見つかりません")));
		assignment.setField(fieldRepository.findById(form.getFieldId())
				.orElseThrow(() -> new RuntimeException("現場が見つかりません")));
		assignment.setRole(form.getRole());
		assignment.setAssignmentType(form.getAssignmentType());
		assignment.setStartDate(form.getStartDate());
		assignment.setEndDate(form.getEndDate());
		assignment.setWorkingHours(form.getWorkingHours());
		assignment.setContractType(form.getContractType());
	}

	/**
	 * 配属情報をフォームに変換する
	 *
	 * @param id 配属ID
	 * @return 配属フォーム
	 */
	public AssignmentForm getForm(Long id) {
		Assignment assignment = findById(id);
		AssignmentForm form = new AssignmentForm();
		form.setStudentId(assignment.getStudent().getId());
		form.setFieldId(assignment.getField().getId());
		form.setRole(assignment.getRole());
		form.setAssignmentType(assignment.getAssignmentType());
		form.setStartDate(assignment.getStartDate());
		form.setEndDate(assignment.getEndDate());
		form.setWorkingHours(assignment.getWorkingHours());
		form.setContractType(assignment.getContractType());
		return form;
	}


}
