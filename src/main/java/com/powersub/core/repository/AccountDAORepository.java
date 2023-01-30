package com.powersub.core.repository;

import com.powersub.core.entity.AccountDAO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountDAORepository extends JpaRepository<AccountDAO, Long> {
    AccountDAO findByEmail(String email);
}
