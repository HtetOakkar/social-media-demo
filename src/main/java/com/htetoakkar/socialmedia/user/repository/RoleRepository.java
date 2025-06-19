package com.htetoakkar.socialmedia.user.repository;

import com.htetoakkar.socialmedia.user.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
