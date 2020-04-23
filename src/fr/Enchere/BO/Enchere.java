package fr.Enchere.BO;

import java.sql.Date;
import java.time.LocalDateTime;

public class Enchere {
	private int noUtilisateur;
	private int noArticle;
	private ArticleVendu articleVendu;
	private Utilisateur utilisateur;
	private Date dateEnchere;
	private int montant_enchere;

	public Enchere() {

	}

	public Enchere(int noUtilisateur, int noArticle, Date dateEnchere, int montant_enchere) {
		super();
		this.noUtilisateur = noUtilisateur;
		this.noArticle = noArticle;
		this.dateEnchere = dateEnchere;
		this.montant_enchere = montant_enchere;
	}

	public Enchere(int noUtilisateur, int noArticle, ArticleVendu articleVendu, Utilisateur utilisateur,
			Date dateEnchere, int montant_enchere) {
		super();
		this.noUtilisateur = noUtilisateur;
		this.noArticle = noArticle;
		this.articleVendu = articleVendu;
		this.utilisateur = utilisateur;
		this.dateEnchere = dateEnchere;
		this.montant_enchere = montant_enchere;
	}

	public int getNoUtilisateur() {
		return noUtilisateur;
	}

	public void setNoUtilisateur(int noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}

	public int getNoArticle() {
		return noArticle;
	}

	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}

	public ArticleVendu getArticleVendu() {
		return articleVendu;
	}

	public void setArticleVendu(ArticleVendu articleVendu) {
		this.articleVendu = articleVendu;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public Date getDateEnchere() {
		return dateEnchere;
	}

	public void setDateEnchere(Date date) {
		this.dateEnchere = date;
	}

	public int getMontant_enchere() {
		return montant_enchere;
	}

	public void setMontant_enchere(int montant_enchere) {
		this.montant_enchere = montant_enchere;
	}

}
