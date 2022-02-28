package com.epsi.fr.gosecuri.fichiers;


// voir ce github pour l'améliorer
//https://github.com/xmlet/HtmlFlow
public class PageHtml {
	
	private String head = "head";
	private String titrePage = "GOsecuri";
	private String linkCss = "https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css";
	private String body = "Hello world";
	private String heading = "heading";
	private String titreCorps = "Title: ";
	private String description = "description";
	private String priority = "priority";
	
	public PageHtml() {

	}

	public String toString() {
		
		StringBuffer maquette = new StringBuffer();
		
		maquette.append("<!DOCTYPE html>");
		maquette.append("<html lang=\"en\" >");
		maquette.append("<head>");
		maquette.append("<meta charset=\"UTF-8\">");
//		maquette.append("<link rel=\"stylesheet\" href=\"https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&display=swap\"/>");
//		maquette.append("<link rel=\"icon\" type=\"image/png\" href=\"Images/Logo.png\" />");
		maquette.append("<title>"+titrePage+"</title>");
		maquette.append("</head>");
		maquette.append(body);
		maquette.append("</html>");
//		maquette.append("<link rel=\"stylesheet\" href=\"CSS.css\">");
		
		return maquette.toString();
	}
	
	public void bodyAcceuil() {
		this.body = "<body>\r\n"
				+ "    <div class=\"background\">\r\n"
				+ "        <div class=\"shape\" ></div>\r\n"
				+ "        <div class=\"shape\"></div>\r\n"
				+ "    </div>\r\n"
				+ "    <div class=\"container-list\">\r\n"
				+ "        <div class=\"Logo\"><img src=\"Images/Logo.png\" /></div>\r\n"
				+ "        <ul class=\"agents-list\">\r\n"
				+ "            <a href=\"agent.html\"><li>Alexander Foley</li></a>\r\n"
				+ "            <a href=\"agent.html\"><li>Boris Vian</li></a>\r\n"
				+ "            <a href=\"agent.html\"><li>Corinne Berthier</li></a>\r\n"
				+ "            <a href=\"agent.html\"><li>...</li></a>\r\n"
				+ "        </ul>\r\n"
				+ "    </div>\r\n"
				+ "</body>";
	}

}
