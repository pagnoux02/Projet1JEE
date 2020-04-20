package fr.Enchere.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;

import fr.Enchere.BO.Utilisateur;
import fr.Enchere.Exception.FunctionnalException;
import fr.Enchere.Exception.ParameterException;

public class CheckDataUtil {
	
	public static final Pattern PATTERNALPHAFULL = Pattern.compile("[a-zA-Z_0-9]");
	
	
	public static final Pattern PATTERMNUM = Pattern.compile("[0-9]");
	
	public static final Pattern PATTERNPASS = Pattern.compile("[a-zA-Z_0-9]");
	
	/**
	 * 
	 * @param utilisateur
	 * @throws ParameterException
	 */
	public static void checkUtilisateur(Utilisateur utilisateur) throws ParameterException {
		if(utilisateur == null) {
			throw new ParameterException("L'objet utiisateur est null");
		}
	}

	/**
	 * 
	 * @param numeroUtilisateur
	 * @throws ParameterException
	 */
	public static void  checkNumeroUtilisateur(String numeroUtilisateur) throws ParameterException {
		if(numeroUtilisateur == null || numeroUtilisateur.isEmpty()) {
			throw new ParameterException("Le numero utilisateur est null ou �gal � 0");
		}
		if(!PATTERMNUM.matcher(numeroUtilisateur).find()) {
			throw new ParameterException("Le numero de l'utilisateur doit �tre des chiffres");
		}
	}
	
	/**
	 * 
	 * @param pseudo
	 * @throws ParameterException
	 */
	public static void checkPseudo(String pseudo) throws ParameterException {
		if(pseudo == null || pseudo.isEmpty()) {
			throw new ParameterException("Le pseudo est vide ou null");
		}
		if(!PATTERNALPHAFULL.matcher(pseudo).find() || pseudo.trim().length() > 30) {
			throw new ParameterException("Le pseudo doit �tre des carat�res aphanumerique et pas d�passer 30 caract�res");
		}
	}
	
	/**
	 * 
	 * @param noString
	 * @throws ParameterException
	 */
	public static void checkNom(String noString) throws ParameterException {
		if(noString == null || noString.isEmpty()) {
			throw new ParameterException("le nom est vide ou null");
		}
		if(!PATTERNALPHAFULL.matcher(noString).find() || noString.trim().length() > 30) {
			throw new ParameterException("Le nom doit �tre des caract�res alphanumerique et pas d�passer 30 caract�res");
		}
	}
	
	/**
	 * 
	 * @param preString
	 * @throws ParameterException
	 */
	public static void checkPrenom(String preString) throws ParameterException {
		if(preString == null || preString.isEmpty()) {
			throw new ParameterException("Le prenom est vide ou null");
		}
		if(!PATTERNALPHAFULL.matcher(preString).find() || preString.trim().length() > 30 ) {
			throw new ParameterException("Le prenom doit est des caract�res alphanumerique et pas d�passer 30 caract�res");
		}
	}
	
	/**
	 * 
	 * @param emailString
	 * @throws ParameterException
	 */
	public static void checkEmail(String emailString) throws ParameterException {
		if(emailString == null || emailString.isEmpty()) {
			throw new ParameterException("L'email est vide ou null");
		}
		if(emailString.trim().length() > 50) {
			throw new ParameterException("L'email doit pas depasser 50 caract�res");
		}
	}
	
	/**
	 * 
	 * @param telephonne
	 * @throws ParameterException
	 */
	public static void checkTelephonne(String telephonne) throws ParameterException {
		if(telephonne == null || telephonne.isEmpty()) {
			throw new ParameterException("Le numero de telephone est vide ou null");
		}
		if(!PATTERMNUM.matcher(telephonne).find() || telephonne.trim().length() > 15) {
			throw new ParameterException("le numero dot �tre des chiffres et pas d�passer 15 caract�res");
		}
	}
	
	/**
	 * 
	 * @param rue
	 * @throws ParameterException
	 */
	public static void checkRue(String rue) throws ParameterException {
		if(rue == null) {
			throw new ParameterException("La rue est vide ou null");
		}
		if(!PATTERNALPHAFULL.matcher(rue).find() || rue.trim().length() > 30 ) {
			throw new ParameterException("La rue doit �tre des caract�res alphanumerique et pas d�passer 30 caract�res");
		}
	}
	
	/**
	 * 
	 * @param codePostal
	 * @throws ParameterException
	 */
	public static void checkCodePostal(String codePostal) throws ParameterException {
		if(codePostal == null || codePostal.isEmpty()) {
			throw new ParameterException("Le code posta est vide ou null");
		}
		if(!PATTERMNUM.matcher(codePostal).find() || codePostal.trim().length() > 10) {
			throw new ParameterException("Le code postal doit des chiffres et pas d�passer 10 caract�res");
		}
	}
	
	/**
	 * 
	 * @param ville
	 * @throws ParameterException
	 */
	public static void checkVille(String ville) throws ParameterException {
		if(ville == null || ville.isEmpty()) {
			throw new ParameterException("La ville est vide ou null");
		}
		if(!PATTERNALPHAFULL.matcher(ville).find() || ville.trim().length() > 30 ) {
			throw new ParameterException("La ville doit �tre des carat�res alphanumerique et pas d�passer 30 caract�res");
		}
	}
	
	public static void checkMotDePasse(String pass) throws ParameterException {
		if(pass == null || pass.isEmpty()) {
			throw new ParameterException("le mot de pass est vide ou null");
		}
		if(!PATTERNPASS.matcher(pass).find() || pass.trim().length() > 256 ) {
			throw new ParameterException("La mot de passe doit �tre des carat�res alphanumeric sans espace et pas d�passer 256 caract�res");
		}
	}
	
	/**
	 * 
	 * @param pass
	 * @throws ParameterException
	 */
	public static void checkMotDePasseConfirmation(String pass) throws ParameterException {
		if(pass == null || pass.isEmpty()) {
			throw new ParameterException("le mot de pass confirmation est vide ou null");
		}
		if(!PATTERNPASS.matcher(pass).find() || pass.trim().length() > 256 ) {
			throw new ParameterException("La mot de passe confirmation doit �tre des carat�res alphanumeric sans espace et pas d�passer 256 caract�res");
		}
	}
	
	/**
	 * 
	 * @param Motdepasse
	 * @param MotdePasseConf
	 * @throws ParameterException
	 */
	public static void checkMotdePasseEqualeMotDePasseConfirmation(String Motdepasse, String MotdePasseConf) throws ParameterException {
		if(!Motdepasse.equals(MotdePasseConf)) {
			throw new ParameterException("Le mot de passe de confirmation est disff�rent du mot de passe");
		}
	}
	
	/**
	 * .
	 * @param motDePasse
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static String convertirMotdePasse(String motDePasse) throws NoSuchAlgorithmException {
		MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
		messageDigest.update(motDePasse.getBytes());
		
		byte byteData[] = messageDigest.digest();
		
		StringBuffer stringBuffer = new StringBuffer();
		
		for(int i =0; i < byteData.length;i++) {
			stringBuffer.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
		}
		
		return stringBuffer.toString();
		
	}
	
	public static void checkAncienMotdePasse(String ancienPassUtiliControl, String ancienPassUtilSet) throws FunctionnalException {
		if(!ancienPassUtiliControl.equals(ancienPassUtilSet)) {
			throw new FunctionnalException("L'ancien mot de passe est diff�rent de celui qui est renseigner");
		}
	}

}
