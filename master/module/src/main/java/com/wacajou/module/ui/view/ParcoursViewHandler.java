package com.wacajou.module.ui.view;

import java.util.List;

public interface ParcoursViewHandler {
	void create();
	void addModule(String moduleName);
	void delModule(String moduleName);
	void onViewEnter(String parameters);
	void urlChange(String params);
	List<String> getModules();
}