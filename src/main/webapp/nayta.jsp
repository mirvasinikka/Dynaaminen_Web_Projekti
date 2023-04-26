<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Calendar" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tiedot</title>
</head>
<body>
<%
String eNimi = request.getParameter("eNimi");
String sNimi = request.getParameter("sNimi");
String sposti = request.getParameter("sposti");
String puh = request.getParameter("puh");
String vuosistr = request.getParameter("vuosi");

int vuosi = Integer.parseInt(vuosistr);
int year = Calendar.getInstance().get(Calendar.YEAR);
int ika = year - vuosi;

if (ika < 18){
	out.print("Alaikäinen");
	out.print("<meta http-equiv='Refresh' content='5;url=kysy.jsp'>");
} else {
out.print("Nimi: " + eNimi + " " + sNimi + "<br>"
		+ "Sähköposti: " + sposti + "<br>"
		+ "Puhelin: " + puh + "<br>"
		+ "Ikä: " + ika);
}

%>
</body>
</html>