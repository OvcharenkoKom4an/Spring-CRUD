package com.komasan.springcrud.repository;

import com.komasan.springcrud.user.UserAuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLogRepository extends JpaRepository<UserAuditLog, Long> {

}
