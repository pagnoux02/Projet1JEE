package fr.Enchere.util;



import java.util.regex.Pattern;

import fr.Enchere.BO.Retrait;
import fr.Enchere.Exception.ParameterException;

public class CheckRetrait {
	public static final Pattern PATTERNALPHAFULL = Pattern.compile("[a-zA-Z_0-9]");
	
	public static void checkRetrait(Retrait retrait) throws ParameterException {
		if(retrait == null) {
			throw new ParameterException("L'objet retrait est null");
		}
	}
	
	
	public static void  checkarti(int numeroArticle) throws ParameterException {
		if(numeroArticle <= 0 ) {
			throw new ParameterException("Le numero article ne peut être inférieur à 0");
		}
	}
	
	public static void checkRue(String rue) throws ParameterException {
		if(rue == null || rue.isEmpty()) {
			throw new ParameterException("la rue est null ou égal à 0");
		}
		if(!PATTERNALPHAFULL.matcher(rue).find()) {
			throw new ParameterException("Le nom de la rue contient des charactères invalides");
		}
	}
	public static void  checkCode_postale(int Code_postale) throws ParameterException {
		if(Code_postale <= 0 ) {
			throw new ParameterException("Le Code postale ne peut être inférieur à 0");
		}
	}
	
	public static void checkVille(String ville) throws ParameterException {
		if(ville == null || ville.isEmpty()) {
			throw new ParameterException("la ville est null ou égal à 0");
		}
		if(!PATTERNALPHAFULL.matcher(ville).find()) {
			throw new ParameterException("Le nom de la ville contient des charactères invalides");
		}
	}
}
