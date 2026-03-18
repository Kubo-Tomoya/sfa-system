package com.example.sfasystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sfasystem.entity.Curriculum;

/*:
 * カリキュラムリポジトリ
 */
public interface CurriculumRepository extends JpaRepository<Curriculum, Long>{

}
