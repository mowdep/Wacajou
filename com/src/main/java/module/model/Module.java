package module.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Module {
	@Id
	private int id;
	private int id_respo;
	private String path;
	private String module_name;
	
 	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_respo() {
		return id_respo;
	}

	public void setId_respo(int id_respo) {
		this.id_respo = id_respo;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getModule_name() {
		return module_name;
	}

	public void setModule_name(String module_name) {
		this.module_name = module_name;
	}
}
