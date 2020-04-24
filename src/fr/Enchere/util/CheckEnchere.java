package fr.Enchere.util;

import java.time.LocalDate;
import java.util.regex.Pattern;

import fr.Enchere.BO.Enchere;
import fr.Enchere.Exception.ParameterException;

public class CheckEnchere {
	
	public static void checkEncheres(Enchere enchere) throws ParameterException {
		if(enchere == null) {
			throw new ParameterException("L'objet enchere est null");
		}
	}
	
	
	public static void  checkNumeroUtiEnchere(int numeroUtilisateur) throws ParameterException {
		if(numeroUtilisateur <= 0 ) {
			throw new ParameterException("Le numero utilisateur ne peut être inférieur à 0");
		}
	}
	
	public static void  checkNumeroArtiEnchere(int numeroArticle) throws ParameterException {
		if(numeroArticle <= 0) {
			throw new ParameterException("Le numero article ne peut être inférieur ou égale à 0");
		}
	}
	public static void checkDateEnchere(LocalDate dateEnchere) throws ParameterException {
		if(dateEnchere == null) {
			throw new ParameterException("La date de l'enchère est null");
		}
		if(dateEnchere.isAfter(LocalDate.now())) {
			throw new ParameterException("La date de l'enchère ne peut pas être antérieur à la date du jour");
		}
	}
	
	public static void  checkMontant(int montant) throws ParameterException {
		if(montant <= 0 ) {
			throw new ParameterException("Le numero utilisateur ne peut être inférieur à 0");
		}
	}
}