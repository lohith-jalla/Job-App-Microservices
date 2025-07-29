package com.lohith.SpringsecDemo.repos;

import com.lohith.SpringsecDemo.models.User;
import org.springframework.data.domain.Limit;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    User findUserByUserName(String username);

    boolean getByUserName(String userName);
}
