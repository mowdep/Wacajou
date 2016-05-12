package com.wacajou.architecture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

@Configuration()
@Profile("install")
public class CreateArchitecture {
	
	@Autowired
    Environment env;
	
	private String rootPath;

	@Bean
	public boolean InstallArchitecture(){
		String pathFile = env.getProperty("config.path") + "\\config\\";
		ConfigFile cf = new ConfigFile();
		cf.getFile(pathFile);
		if(cf.getPath() == null){
			cf.InstallConfigFile(pathFile);
		}
		try {
			createArchitecture(cf.getPath());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}
	
	public void createArchitecture(String rootPath) throws Exception {
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
			throw new Exception("Architecture non initialisée");
	}
}