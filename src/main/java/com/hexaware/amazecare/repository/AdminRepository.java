package com.hexaware.amazecare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hexaware.amazecare.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer>{
	@Query("Select a from Admin a where a.user.id=?1")
	Admin getAdminById(int id);

}
