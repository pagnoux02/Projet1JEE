/**
 * 
 */
package fr.Enchere.BO;

/**
 * @author ilang
 * Liste les diff�rents �tats de vente
 */
public enum EtatVente {
	Créée,
	EnCours,
	Terminer,
	Retrait;
	
	public static EtatVente StringToEtatVente(String etat) {
		EtatVente ev = null;
		switch(etat) {
		case "Créée":
			ev = Créée;
			break;
		case "EnCours":
			ev = EnCours;
			break;
		case "Terminer":
			ev = Terminer;
			break;
		case "Retrait":
			ev = Retrait;
			break;
		}
		return ev;
	}
}
