package com.wacajou.data.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.wacajou.data.jpa.domain.Module;
import com.wacajou.data.jpa.domain.Parcours;
import com.wacajou.data.jpa.domain.Statut;
import com.wacajou.data.jpa.domain.User;

public interface UserService {
	User getUser(int id);
	User getUser(String fname, String lname);
	Page<User> getUserByPromo(String promo, Pageable pageable);
	Page<User> getUserByModule(Module module, Pageable pageable);
	Page<User> getUserByParcoursName(String parcoursName, Pageable pageable);
	Page<User> getUserByModuleName(String moduleName, Pageable pageable);
	Page<User> getUserByParcours(Parcours parcours, Pageable pageable);

	Page<User> getUserBySatut(Statut statut, Pageable pageable); 
}
