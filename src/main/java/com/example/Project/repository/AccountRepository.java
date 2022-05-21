package com.example.Project.repository;




import com.example.Project.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {



    Account getAccountByUsername(String username);
}
