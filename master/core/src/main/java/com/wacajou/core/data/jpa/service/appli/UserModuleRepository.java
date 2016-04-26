package com.wacajou.core.data.jpa.service.appli;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.wacajou.core.data.jpa.domain.appli.Module;
import com.wacajou.core.data.jpa.domain.appli.User;
import com.wacajou.core.data.jpa.domain.appli.UserModule;

public interface UserModuleRepository extends JpaRepository<UserModule, Long>{
	Page<UserModule> findAll(Pageable pageable);
	Page<UserModule> findByModule(Module module, Pageable pageable);
	Page<UserModule> findByUser(User user, Pageable pageable);
	Page<UserModule> findByUserAndModule(User user, Module module, Pageable pageable);
	
}
