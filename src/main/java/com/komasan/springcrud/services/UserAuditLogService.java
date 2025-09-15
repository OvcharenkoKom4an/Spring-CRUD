package com.komasan.springcrud.services;

import com.komasan.springcrud.repository.UserLogRepository;
import com.komasan.springcrud.user.UserAuditLog;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class UserAuditLogService {

    private final UserLogRepository userLogRepository;

    public void logAction(String action, String entity, Long entity_id) {
        UserAuditLog userAuditLog = new UserAuditLog();

        userAuditLog.setAction(action);
        userAuditLog.setEntity(entity);
        userAuditLog.setEntity_id(entity_id);

        userLogRepository.save(userAuditLog);
    }
}
