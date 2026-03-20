package com.example.sfasystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sfasystem.entity.Field;

/**
 * 現場リポジトリ
 */
public interface FieldRepository extends JpaRepository<Field, Long>{

}
