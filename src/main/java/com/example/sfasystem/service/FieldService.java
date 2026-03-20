package com.example.sfasystem.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.sfasystem.entity.Field;
import com.example.sfasystem.form.FieldForm;
import com.example.sfasystem.repository.FieldRepository;

import lombok.RequiredArgsConstructor;

/**
 * 現場サービス
 */
@Service
@RequiredArgsConstructor
public class FieldService {
	
	/** 現場リポジトリ */
	private final FieldRepository fieldRepository;

	/**
	 * 現場一覧を取得する
	 *
	 * @return 現場一覧
	 */
	public List<Field> findAll() {
		return fieldRepository.findAll();
	}

	/**
	 * 現場を1件取得する
	 *
	 * @param id 現場ID
	 * @return 現場情報
	 */
	public Field findById(Long id) {
		return fieldRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("現場が見つかりません"));
	}

	/**
	 * 現場を新規登録する
	 *
	 * @param form 現場フォーム
	 */
	public void create(FieldForm form) {
		Field field = new Field();
		setFieldFields(field, form);
		field.setCreatedAt(LocalDateTime.now());
		field.setUpdatedAt(LocalDateTime.now());
		fieldRepository.save(field);
	}

	/**
	 * 現場を更新する
	 *
	 * @param id   現場ID
	 * @param form 現場フォーム
	 */
	public void update(Long id, FieldForm form) {
		Field field = findById(id);
		setFieldFields(field, form);
		field.setUpdatedAt(LocalDateTime.now());
		fieldRepository.save(field);
	}

	/**
	 * 現場を削除する
	 *
	 * @param id 現場ID
	 */
	public void delete(Long id) {
		fieldRepository.deleteById(id);
	}

	/**
	 * フォームの内容をエンティティにセットする
	 *
	 * @param field 現場エンティティ
	 * @param form  現場フォーム
	 */
	private void setFieldFields(Field field, FieldForm form) {
		field.setFieldCode(form.getFieldCode());
		field.setName(form.getName());
		field.setClientName(form.getClientName());
		field.setIndustry(form.getIndustry());
		field.setLocation(form.getLocation());
		field.setDescription(form.getDescription());
		field.setRequiredSkills(form.getRequiredSkills());
	}

	/**
	 * 現場情報をフォームに変換する
	 *
	 * @param id 現場ID
	 * @return 現場フォーム
	 */
	public FieldForm getForm(Long id) {
		Field field = findById(id);
		FieldForm form = new FieldForm();
		form.setFieldCode(field.getFieldCode());
		form.setName(field.getName());
		form.setClientName(field.getClientName());
		form.setIndustry(field.getIndustry());
		form.setLocation(field.getLocation());
		form.setDescription(field.getDescription());
		form.setRequiredSkills(field.getRequiredSkills());
		return form;
	}


}
