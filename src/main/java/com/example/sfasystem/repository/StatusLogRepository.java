package com.example.sfasystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sfasystem.entity.StatusLog;

/**
 * 状況ログリポジトリ
 */
public interface StatusLogRepository extends JpaRepository<StatusLog, Long>{

}
