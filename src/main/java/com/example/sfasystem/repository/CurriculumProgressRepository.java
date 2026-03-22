package com.example.sfasystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sfasystem.entity.CurriculumProgress;

/**
 * カリキュラム進捗リポジトリ
 */
public interface CurriculumProgressRepository extends JpaRepository<CurriculumProgress, Long>{

}
