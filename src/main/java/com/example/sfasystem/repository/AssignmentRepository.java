package com.example.sfasystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sfasystem.entity.Assignment;

/**
 * 配属リポジトリ
 */
public interface AssignmentRepository extends JpaRepository<Assignment, Long>{

}
