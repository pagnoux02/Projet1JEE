package fr.Enchere.util;

public abstract class GestionDAO {
	
	public static boolean recupBoolean(String admin) {
		return "0".equals(admin) ? true : false;
	}
	
	public static byte recupBit(Boolean bool){
		return true ? 0 : 1;
	}

}
