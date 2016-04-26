package com.wacajou.core.data.jpa.service.appli;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.wacajou.core.data.jpa.domain.appli.Comments;
import com.wacajou.core.data.jpa.domain.appli.Module;
import com.wacajou.core.data.jpa.domain.appli.Parcours;
import com.wacajou.core.data.jpa.domain.appli.Rating;
import com.wacajou.core.data.jpa.domain.appli.User;

public interface CommentsRepository extends JpaRepository<Comments, Long> {
	Page<Comments> findByParcours(Parcours parcoues, Pageable pageable);
	Page<Comments> findByParcoursAndRating(Parcours parcours, Rating rating, Pageable pageable);
	Page<Comments> findByModule(Module module, Pageable pageable);
	Page<Comments> findByModuleAndRating(Module module, Rating rating, Pageable pageable);
	Page<Comments> findByUser(User user, Pageable pageable);
	Page<Comments> findByUserAndRating(User user, Rating rating, Pageable pageable);
	Page<Comments> findByParcoursAndModule(Parcours parcours, Module module, Pageable pageable);
	Page<Comments> findByParcoursAndModuleAndRating(Parcours parcours, Module module, Rating rating, Pageable pageable);
	Page<Comments> findByModuleAndUser(Module module, User user, Pageable pageable);
	Page<Comments> findByParcoursAndUser(Parcours parcours, User user, Pageable pageable);
	Page<Comments> findByParcoursAndModuleAndUser(Parcours parcours, Module module, User user, Pageable pageable);
}
