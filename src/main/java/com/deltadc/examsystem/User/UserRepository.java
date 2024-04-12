package com.deltadc.examsystem.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    Optional<User> findByUsername(String username);

    List<User> findByRoleContaining(String role);

    @Modifying
//    @Query("UPDATE User u set u.username = 'pdc updated' WHERE u.username = :username")
    @Query("DELETE FROM User u Where u.username = :username")
    void deleteByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.username = :username")
    List<NameOnly> findByCustomQuery(String username);

    <T> Collection<T> findByUsername(String username, Class<T> type);
}
