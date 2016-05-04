package com.wacajou.module.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.wacajou.data.jpa.repository.ModuleRepository;

@Component("moduleService")
@Transactional
public class ModuleServiceImpl implements ModuleService{
	
	private final ModuleRepository moduleRepository;
	
	@Autowired
	public ModuleServiceImpl(ModuleRepository moduleRepository){
		this.moduleRepository = moduleRepository;
	}
}
