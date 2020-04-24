package fr.Enchere.util;

import java.time.LocalDate;
import java.util.regex.Pattern;

import fr.Enchere.BO.ArticleVendu;
import fr.Enchere.BO.EtatVente;
import fr.Enchere.Exception.ParameterException;

/**
 * @author ilang
 *
 */
public class CheckArticleVendu {
	
	public static final Pattern PATTERNALPHAFULL = Pattern.compile("[a-zA-Z_0-9]");
	
	public static final Pattern PATTERMNUM = Pattern.compile("[0-9]");
	
	/**
	 * 
	 * @param articleVendu
	 * @throws ParameterException
	 */
	public static void checkArticleVendu(ArticleVendu articleVendu) throws ParameterException {
		if(articleVendu == null) {
			throw new ParameterException("L'objet article vendu est null");
		}
	}
	
	/**
	 * 
	 * @param noArticle
	 * @throws ParameterException
	 */
	public static void checkNoArticle(String noArticle) throws ParameterException {
		if(noArticle == null || noArticle.isEmpty()) {
			throw new ParameterException("Le numero de l'article est null ou égal à 0");
		}
		if(!PATTERMNUM.matcher(noArticle).find()) {
			throw new ParameterException("Le numero de l'article doit être composé de chiffres");
		}
	}
	
	/**
	 * 
	 * @param nomArticle
	 * @throws ParameterException
	 */
	public static void checkNomArticle(String nomArticle) throws ParameterException {
		if(nomArticle == null || nomArticle.isEmpty()) {
			throw new ParameterException("Le nom de l'article est null ou vide");
		}
		if(!PATTERNALPHAFULL.matcher(nomArticle).find()) {
			throw new ParameterException("Le nom de l'article contient des charactères invalides");
		}
	}
	
	/**
	 * 
	 * @param description
	 * @throws ParameterException
	 */
	public static void checkDescription(String description) throws ParameterException {
		if(description == null || description.isEmpty()) {
			throw new ParameterException("La description de l'article est null ou vide");
		}
		if(!PATTERNALPHAFULL.matcher(description).find()) {
			throw new ParameterException("La description de l'article contient des charactères invalides");
		}
	}
	
	/**
	 * 
	 * @param dateDebutEncheres
	 * @throws ParameterException
	 */
	public static void checkDateDebutEncheres(LocalDate dateDebutEncheres) throws ParameterException {
		if(dateDebutEncheres == null) {
			throw new ParameterException("La date de début de l'enchère est null");
		}
		if(dateDebutEncheres.isBefore(LocalDate.now().minusDays(1))) {
			throw new ParameterException("La date de début de l'enchère ne peut pas être antérieur a la date du jour");
		}
	}
	
	/**
	 * 
	 * @param dateFinEncheres
	 * @throws ParameterException
	 */
	public static void checkDateFinEncheres(LocalDate dateFinEncheres) throws ParameterException {
		if(dateFinEncheres == null) {
			throw new ParameterException("La date de fin de l'enchère est null");
		}
		if(dateFinEncheres.isBefore(LocalDate.now())) {
			throw new ParameterException("La date de fin de l'enchère ne peut pas être avant à la date du jour");
		}
	}
	
	/**
	 * 
	 * @param miseAPrix
	 * @throws ParameterException
	 */
	public static void checkMiseAPrix(int miseAPrix) throws ParameterException {
		if(miseAPrix < 0) {
			throw new ParameterException("La mise à prix ne peut être inférieur à 0");
		}
	}
	
	/**
	 * 
	 * @param prixVente
	 * @throws ParameterException
	 */
	public static void checkPrixVente(int prixVente) throws ParameterException {
		if(prixVente < 0) {
			throw new ParameterException("Le prix de vente ne peut être inférieur à 0");
		}
	}
	
	/**
	 * 
	 * @param etatVente
	 * @throws ParameterException
	 */
	public static void checkEtatVente(EtatVente etatVente) throws ParameterException {
		if(etatVente == null) {
			throw new ParameterException("L'état de vente ne peut être null");
		}
	}
}
