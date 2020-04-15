/**
 * 
 */
package fr.Enchere.BO;

import java.time.LocalDate;

/**
 * @author ilang
 * Class concernants les Articles vendu
 */
public class ArticleVendu {
	
	private int noArticle;
	private String nomArticle;
	private String description;
	private LocalDate dateDebutEncheres;
	private LocalDate dateFinEncheres;
	private int miseAPrix;
	private int prixVente;
	private EtatVente etatVente;
}
