/**
 * 
 */
package fr.Enchere.BO;

/**
 * @author ilang
 * Class concernant les catégorie d'article
 */
public class Categorie {
	
	/**
	 * Liste des attributs de la class catégorie
	 */
	private int noCategorie;
	private String Libelle;
	
	/**
	 * @param libelle
	 */
	public Categorie(String libelle) {
		this.Libelle = libelle;
	}
	
	/**
	 * @param noCategorie
	 * @param Libelle
	 */
	public Categorie(int noCategorie, String Libelle) {
		this(Libelle);
		this.noCategorie = noCategorie;
	}

	/**
	 * @return the noCategorie
	 */
	public int getNoCategorie() {
		return noCategorie;
	}

	/**
	 * @param noCategorie the noCategorie to set
	 */
	public void setNoCategorie(int noCategorie) {
		this.noCategorie = noCategorie;
	}

	/**
	 * @return the libelle
	 */
	public String getLibelle() {
		return Libelle;
	}

	/**
	 * @param libelle the libelle to set
	 */
	public void setLibelle(String libelle) {
		Libelle = libelle;
	}
	
	
}
