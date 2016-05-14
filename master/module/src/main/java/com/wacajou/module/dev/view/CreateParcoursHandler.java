package com.wacajou.module.dev.view;

import java.util.List;

public interface CreateParcoursHandler {
	void create();
	void addModule(String moduleName);
	void delModule(String moduleName);
	void onViewEnter(String parameters);
	void urlChange(String params);
	List<String> getModules();
}