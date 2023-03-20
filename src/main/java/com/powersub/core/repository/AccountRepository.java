package com.powersub.core.repository;

import com.powersub.core.entity.Account;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    @EntityGraph(attributePaths = {"channels"})
    Optional<Account> findById(Long aLong);

    Account findByEmail(String email);
}
