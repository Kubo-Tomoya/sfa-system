package com.example.sfasystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sfasystem.entity.Student;

/**
 * 生徒情報リポジトリ
 */
public interface StudentRepository extends JpaRepository<Student, Long> {

	/**
	 * 削除されていない生徒一覧を取得する
	 *
	 * @return 生徒一覧
	 */
	List<Student> findByDeletedAtIsNull();
}