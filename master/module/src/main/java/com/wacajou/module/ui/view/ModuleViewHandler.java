package com.wacajou.module.ui.view;

public interface ModuleViewHandler {
	void create();
	void onViewEnter(String parameters);
	void urlChange(String params);
}
