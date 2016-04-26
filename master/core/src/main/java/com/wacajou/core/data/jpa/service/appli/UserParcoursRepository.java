package com.wacajou.core.data.jpa.service.appli;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.wacajou.core.data.jpa.domain.appli.Parcours;
import com.wacajou.core.data.jpa.domain.appli.User;
import com.wacajou.core.data.jpa.domain.appli.UserParcours;

public interface UserParcoursRepository extends JpaRepository<UserParcours, Long> {
	Page<UserParcours> findAll(Pageable pageable);
	Page<UserParcours> findByParcours(Parcours parcours, Pageable pageable);
	Page<UserParcours> findByUser(User user, Pageable pageable);
	Page<UserParcours> findByUserAndParcours(User user, Parcours parcours, Pageable pageable);
	
	
}
