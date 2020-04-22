/**
 * 
 */
package fr.Enchere.BO;

/**
 * @author ilang
 * Liste les diff�rents �tats de vente
 */
public enum EtatVente {
	Cr��e,
	EnCours,
	Terminer,
	Retrait;
	
	public static EtatVente StringToEtatVente(String etat) {
		EtatVente ev = null;
		switch(etat) {
		case "Cr��e":
			ev = Cr��e;
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
