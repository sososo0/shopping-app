package com.server.myselectshop.repository;

import com.server.myselectshop.entity.ApiUseTime;
import com.server.myselectshop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApiUseTimeRepository extends JpaRepository<ApiUseTime,Long> {

    Optional<ApiUseTime> findByUser(User loginUser);
}
