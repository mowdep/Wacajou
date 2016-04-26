package com.wacajou.core.data.jpa.service.appli;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.wacajou.core.data.jpa.domain.appli.Module;
import com.wacajou.core.data.jpa.domain.appli.Parcours;
import com.wacajou.core.data.jpa.domain.appli.ParcoursModule;

public interface ParcoursModuleRepository extends JpaRepository<ParcoursModule, Long>{
	Page<ParcoursModule> findAll(Pageable page);
	Page<ParcoursModule> findByParcours(Parcours parcours, Pageable pageable);
	Page<ParcoursModule> findByModule(Module module, Pageable pageable);
	Page<ParcoursModule> findByParcoursAndOptional(Parcours parcours, boolean optional, Pageable pageable);
	Page<ParcoursModule> findByModuleAndOptional(Module module, boolean optional, Pageable pageable);
	Page<ParcoursModule> findByParcoursAndModule(Parcours parcours, Module module, Pageable pageable);
	Page<ParcoursModule> findByParcoursAndModuleAndOptional(Parcours parcours, Module module, boolean optional, Pageable pageable);

}
