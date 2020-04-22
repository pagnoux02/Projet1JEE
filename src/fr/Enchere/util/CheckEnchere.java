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
			throw new ParameterException("Le numero utilisateur ne peut �tre inf�rieur � 0");
		}
	}
	
	public static void  checkNumeroArtiEnchere(int numeroArticle) throws ParameterException {
		if(numeroArticle <= 0) {
			throw new ParameterException("Le numero article ne peut �tre inf�rieur ou �gale � 0");
		}
	}
	public static void checkDateEnchere(LocalDate dateEnchere) throws ParameterException {
		if(dateEnchere == null) {
			throw new ParameterException("La date de l'ench�re est null");
		}
		if(dateEnchere.isAfter(LocalDate.now())) {
			throw new ParameterException("La date de l'ench�re ne peut pas �tre ant�rieur � la date du jour");
		}
	}
	
	public static void  checkMontant(int montant) throws ParameterException {
		if(montant <= 0 ) {
			throw new ParameterException("Le numero utilisateur ne peut �tre inf�rieur � 0");
		}
	}
}