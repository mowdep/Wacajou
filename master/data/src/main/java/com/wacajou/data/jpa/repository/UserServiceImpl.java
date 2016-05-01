package com.wacajou.data.jpa.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.wacajou.data.jpa.domain.Module;
import com.wacajou.data.jpa.domain.Parcours;
import com.wacajou.data.jpa.domain.Statut;
import com.wacajou.data.jpa.domain.User;

@Component("userService")
@Transactional
public class UserServiceImpl implements UserService{

	private final UserRepository userRepository;
	private final ModuleRepository moduleRepository;
	private final ParcoursRepository parcoursRepository;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository, ModuleRepository moduleRepository, ParcoursRepository parcoursRepository){
		this.userRepository = userRepository;
		this.moduleRepository = moduleRepository;
		this.parcoursRepository = parcoursRepository;
	}
	
	@Override
	public User getUser(int id) {
		Assert.notNull(id, "Id must be not null");
		return this.userRepository.findOne(id);
	}

	@Override
	public User getUser(String fname, String lname) {
		Assert.notNull(fname, "firstname must be not null");
		Assert.notNull(lname, "lastname must be not null");
		return this.userRepository.findByFnameAndLname(fname, lname);
	}

	@Override
	public Page<User> getUserByPromo(String promo, Pageable pageable) {
		Assert.notNull(promo);
		return this.userRepository.findByPromo(promo, pageable);
	}

	@Override
	public Page<User> getUserByModuleName(String moduleName, Pageable pageable) {
		Assert.notNull(moduleName);
		Module module = this.moduleRepository.findByName(moduleName);
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<User> getUserByModule(Module module, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<User> getUserByParcours(Parcours parcours, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<User> getUserBySatut(Statut statut, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<User> getUserByParcoursName(String parcoursName, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

}
