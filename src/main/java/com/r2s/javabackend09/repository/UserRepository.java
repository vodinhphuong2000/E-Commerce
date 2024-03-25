package com.r2s.javabackend09.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.r2s.javabackend09.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findByUserName(String userName);

	List<User> findByName(String name);
	
	List<User> findByNameContains(String name, Pageable pageable); // Containing
	
	List<User> findByNameLike(String name);
	
	@Query(nativeQuery = true, value = "SELECT * FROM jbe092023.table_users where col_name like %?1%")
	List<User> findByNameCoChua(String name);
}
