package com.wacajou.architecture;

public class CreateArchi {

	private String rootPath;

	public CreateArchi(String rootPath) {
		if(!rootPath.equals("")){
			this.rootPath = rootPath;			
			CreateFolder cFolder = new CreateFolder(rootPath);
			cFolder.createRoot("Wacajou");
			cFolder.createOne("uploads");
			cFolder.createOne("module");
			cFolder.createOne("parcours");
			cFolder.createOne("user");
			cFolder.createOne("config");

		}else
			System.out.println("Architecture non initialisée");
	}
}
