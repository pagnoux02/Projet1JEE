package fr.Enchere.BO;

public class Retrait {
	private int id ;
	private String rue;
	private int code_postale;
	private String ville;

	public Retrait(String rue, int code_postale, String ville) {
		super();
		this.rue = rue;
		this.code_postale = code_postale;
		this.ville = ville;
	}


	public Retrait() {
		// TODO Auto-generated constructor stub
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public int getCode_postale() {
		return code_postale;
	}

	public void setCode_postale(int code_postale) {
		this.code_postale = code_postale;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}
	
	
	
}
