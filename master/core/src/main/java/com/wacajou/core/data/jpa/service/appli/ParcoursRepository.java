package com.wacajou.core.data.jpa.service.appli;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.wacajou.core.data.jpa.domain.appli.Comments;
import com.wacajou.core.data.jpa.domain.appli.Parcours;
import com.wacajou.core.data.jpa.domain.appli.ParcoursModule;
import com.wacajou.core.data.jpa.domain.appli.User;
import com.wacajou.core.data.jpa.domain.appli.UserParcours;

public interface ParcoursRepository extends JpaRepository<Parcours, Integer>{
	Parcours findByName(String name);
	Parcours findByComments(Comments comments);
	Parcours findByParcoursModule(ParcoursModule parcoursModule);
	Parcours findByUserParcours(UserParcours userParcours);
	Parcours findByRespo(User respo);
	Page<Parcours> findAll(Pageable page);
	
}
