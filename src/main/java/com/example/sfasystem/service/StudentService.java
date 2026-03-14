package com.example.sfasystem.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.sfasystem.entity.Student;
import com.example.sfasystem.form.StudentForm;
import com.example.sfasystem.repository.StudentRepository;

import lombok.RequiredArgsConstructor;

/**
 * 生徒情報サービス
 */
@Service
@RequiredArgsConstructor
public class StudentService {

	/** 生徒情報リポジトリ */
	private final StudentRepository studentRepository;

	/**
	 * 削除されていない生徒一覧を取得する
	 *
	 * @return 生徒一覧
	 */
	public List<Student> findAll() {
		return studentRepository.findByDeletedAtIsNull();
	}

	/**
	 * 生徒情報を1件取得する
	 *
	 * @param id 生徒ID
	 * @return 生徒情報
	 */
	public Student findById(Long id) {
		return studentRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("生徒が見つかりません"));
	}

	/**
	 * 生徒情報を新規登録する
	 *
	 * @param form 生徒フォーム
	 */
	public void create(StudentForm form) {
		Student student = new Student();
		setStudentFields(student, form);
		student.setCreatedAt(LocalDateTime.now());
		student.setUpdatedAt(LocalDateTime.now());
		studentRepository.save(student);
	}

	/**
	 * 生徒情報を更新する
	 *
	 * @param id   生徒ID
	 * @param form 生徒フォーム
	 */
	public void update(Long id, StudentForm form) {
		Student student = findById(id);
		setStudentFields(student, form);
		student.setUpdatedAt(LocalDateTime.now());
		studentRepository.save(student);
	}

	/**
	 * 生徒情報を論理削除する
	 *
	 * @param id 生徒ID
	 */
	public void delete(Long id) {
		Student student = findById(id);
		student.setDeletedAt(LocalDateTime.now());
		student.setUpdatedAt(LocalDateTime.now());
		studentRepository.save(student);
	}

	/**
	 * フォームの内容をエンティティにセットする
	 *
	 * @param student 生徒エンティティ
	 * @param form    生徒フォーム
	 */
	private void setStudentFields(Student student, StudentForm form) {
		student.setStudentCode(form.getStudentCode());
		student.setLastName(form.getLastName());
		student.setFirstName(form.getFirstName());
		student.setLastNameKana(form.getLastNameKana());
		student.setFirstNameKana(form.getFirstNameKana());
		student.setEmail(form.getEmail());
		student.setPhoneNumber(form.getPhoneNumber());
		student.setGender(form.getGender());
		student.setBirthDate(form.getBirthDate());
		student.setEntryDate(form.getEntryDate());
		student.setExpectedGraduationDate(form.getExpectedGraduationDate());
		student.setStatus(form.getStatus());
		student.setEducationLevel(form.getEducationLevel());
		student.setExperienceYears(form.getExperienceYears());
		student.setNote(form.getNote());
	}

	/**
	 * 生徒情報をフォームに変換する
	 *
	 * @param id 生徒ID
	 * @return 生徒フォーム
	 */
	public StudentForm getForm(Long id) {
		Student student = findById(id);
		StudentForm form = new StudentForm();
		form.setStudentCode(student.getStudentCode());
		form.setLastName(student.getLastName());
		form.setFirstName(student.getFirstName());
		form.setLastNameKana(student.getLastNameKana());
		form.setFirstNameKana(student.getFirstNameKana());
		form.setEmail(student.getEmail());
		form.setPhoneNumber(student.getPhoneNumber());
		form.setGender(student.getGender());
		form.setBirthDate(student.getBirthDate());
		form.setEntryDate(student.getEntryDate());
		form.setExpectedGraduationDate(student.getExpectedGraduationDate());
		form.setStatus(student.getStatus());
		form.setEducationLevel(student.getEducationLevel());
		form.setExperienceYears(student.getExperienceYears());
		form.setNote(student.getNote());
		return form;
	}
}