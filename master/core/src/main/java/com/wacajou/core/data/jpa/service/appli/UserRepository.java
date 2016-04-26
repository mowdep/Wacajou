package com.wacajou.core.data.jpa.service.appli;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wacajou.core.data.jpa.domain.appli.Comments;
import com.wacajou.core.data.jpa.domain.appli.Statut;
import com.wacajou.core.data.jpa.domain.appli.User;
import com.wacajou.core.data.jpa.domain.appli.UserModule;
import com.wacajou.core.data.jpa.domain.appli.UserParcours;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	public final static String FIND_BY_MODULE = "SELECT u " + 
			"FROM User u " + 
			"LEFT JOIN u.userModule um " + 
			"LEFT JOIN um.module m " + 
			"WHERE m.name = :moduleName";
	
	public final static String FIND_BY_PARCOURS = "SELECT u " + 
			"FROM User u " + 
			"LEFT JOIN u.userParcours up " + 
			"LEFT JOIN up.parcours p " + 
			"WHERE p.name = :parcoursName";

	User findByComments(Comments comments);

	User findByFnameAndLname(String fname, String lname);

	User findByMail(String mail);

	Page<User> findAll(Pageable pageable);

	Page<User> findByPromo(String promo, Pageable pageable);

	Page<User> findByUserParcours(UserParcours parcours, Pageable pageable);

	Page<User> findByUserModule(UserModule module, Pageable pageable);

	Page<User> findByStatut(Statut statut, Pageable pageable);

	@Query(FIND_BY_MODULE)
	Page<User> findByModule(@Param("moduleName") String moduleName, Pageable pageable);
	
	@Query(FIND_BY_PARCOURS)
	Page<User> findByParcours(@Param("parcoursName") String parcoursName, Pageable pageable);

}
