package com.scaler.splitwiseeveningsept2026.Repositories;

import com.scaler.splitwiseeveningsept2026.Models.Group;
import com.scaler.splitwiseeveningsept2026.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
