package com.jnchen.mkettle.repository;

import com.jnchen.mkettle.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by caojingchen on 2017/12/29.
 */
public interface UserRepository extends JpaRepository<User,Long> {
}
