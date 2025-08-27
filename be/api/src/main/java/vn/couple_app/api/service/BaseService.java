package vn.couple_app.api.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class BaseService {
    protected String getUserId() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
