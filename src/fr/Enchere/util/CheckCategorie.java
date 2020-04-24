/**
 * 
 */
package fr.Enchere.util;

import java.util.regex.Pattern;

import fr.Enchere.BO.Categorie;
import fr.Enchere.Exception.ParameterException;

/**
 * @author ilang
 *
 */
public class CheckCategorie {
	
	public static final Pattern PATTERNALPHAFULL = Pattern.compile("[a-zA-Z_0-9]");
	
	public static final Pattern PATTERMNUM = Pattern.compile("[0-9]");
	
	public  static void checkCategorie(Categorie categorie) throws ParameterException {
		if(categorie == null) {
			throw new ParameterException("L'objet categorie est null");
		}
	}
	
	 /**
	  * 
	  * @param noCategorie
	  * @throws ParameterException
	  */
	public static void checkNoCategorie(String noCategorie) throws ParameterException {
		if(noCategorie == null || noCategorie.isEmpty()) {
			throw new ParameterException("Le numero de la categorie est null ou égal à 0");
		}
		if(!PATTERMNUM.matcher(noCategorie).find()) {
			throw new ParameterException("Le numero de la categorie doit être composé de chiffres");
		}
	}
	
	/**
	 * 
	 * @param Libelle
	 * @throws ParameterException
	 */
	public static void checkLibelleCategorie(String Libelle) throws ParameterException {
		if(Libelle == null || Libelle.isEmpty()) {
			throw new ParameterException("Le libelle de la categorie est null ou vide");
		}
		if(!PATTERNALPHAFULL.matcher(Libelle).find()) {
			throw new ParameterException("Le libelle de la categorie contient des charactères invalides");
		}
	}
}
