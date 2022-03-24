package com.epsi.fr.gosecuri.models.personnes.materiels;

import java.lang.reflect.Method;

public class Materiel {
	private String nom;
	private String type;

	public Materiel(String nom, String type) {
		this.nom = nom;
		this.type = type;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/**
	 * A améliorer
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		try {
			Class c = Class.forName(this.getClass().getName());
			Method m[] = c.getDeclaredMethods();
			Object oo;

			for (int i = 0; i < m.length; i++)
				if (m[i].getName().startsWith("get")) {
					oo = m[i].invoke(this, null);
					sb.append(m[i].getName().substring(3) + " : "
							+ String.valueOf(oo) + "\n");
				}
		} catch (Throwable e) {
			System.err.println(e);
		}
		return sb.toString();
	}
	
	/**Gives a string representation of the fields of the object
	 * @param obj the object
	 * @return
	 */
//	private static String objectToString(Object obj) {
//	    StringBuilder sb = new StringBuilder();
//	    try {
//	        Class c = Class.forName(obj.getClass().getName());
//	        Method m[] = c.getDeclaredMethods();
//
//	        Object oo;
//	        for (int i = 0; i < m.length; i++)
//	            if (m[i].getName().startsWith("get")) {
//	                oo = m[i].invoke(obj, null);
//	                sb.append(m[i].getName().substring(3) + "="
//	                        + String.valueOf(oo) + "\n");
//	            }
//	    } catch (Throwable e) {
//	        System.err.println(e);
//	    }
//	    return sb.toString();
//	}


}
