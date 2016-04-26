package com.wacajou.core.data.jpa.service.appli;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wacajou.core.data.jpa.domain.appli.Comments;
import com.wacajou.core.data.jpa.domain.appli.Module;
import com.wacajou.core.data.jpa.domain.appli.ParcoursModule;
import com.wacajou.core.data.jpa.domain.appli.User;
import com.wacajou.core.data.jpa.domain.appli.UserModule;

public interface ModuleRepository  extends JpaRepository<Module, Integer>{
	
	public static final String FIND_BY_USER = "SELECT m " + 
			"FROM Module m " + 
			"LEFT JOIN  m.userModule um " +
			"LEFT JOIN um.user u " +
			"WHERE u.id = :user ";
	
	Module findByName(String name);
	Module findByRespo(User respo);
	Module findByUserModule(UserModule userModule);
	Module findByComments(Comments comments);
	Page<Module> findAll(Pageable pageable);
	Page<Module> findByParcoursModule(ParcoursModule parcoursModule, Pageable pageable);

	@Query(FIND_BY_USER)
	Page<Module> findByUser(@Param("user") int id);
	
	
}
