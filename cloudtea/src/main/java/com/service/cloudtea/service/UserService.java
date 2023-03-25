package com.service.cloudtea.service;

import com.service.cloudtea.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    Page<User> findAll(Pageable pageable);
    User create(User user);
    User findById(Long id);
    List<User> findByUser(String useName);
    void delete(Long id);
}
