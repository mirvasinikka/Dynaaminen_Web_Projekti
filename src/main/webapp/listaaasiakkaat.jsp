<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>

table {
  font-family: arial, sans-serif;
  border-collapse: collapse;
  width: 100%;
}

td {
  border: 1px solid #dddddd;
  text-align: left;
  padding: 8px;
}

th {
border: 1px solid #dddddd;
  text-align: left;
  padding: 8px;
  background-color: #3399ff;
  color: #ffffff;
}

tr:nth-child(even) {
  background-color: #e6f2ff;
}
</style>
<meta charset="UTF-8">

<title>Asiakkaat</title>
</head>
<body>
<form action="listaaasiakkaat.jsp" method="get" id="my_form">
</form>

<table id="listaus">
	<thead>
<tr>
	
	<th colspan="2" style="text-align: right;">Hakusana:</th>
	<th><input type="text" name="haku" form="my_form"></th>
	<th><input type="submit" value="Hae" form="my_form"></th>
</tr>
		<tr>
			<th>Etunimi</th>
			<th>Sukunimi</th>
			<th>Puhelin</th>
			<th>Sposti</th>
		</tr>
	</thead>
	<tbody id="tbody">
	</tbody>
</table>
<span id="ilmo"></span>
<script>

function requestURLParam(sParam){
    var sPageURL = window.location.search.substring(1);
    var sURLVariables = sPageURL.split("&");
    for (var i = 0; i < sURLVariables.length; i++){
        var sParameterName = sURLVariables[i].split("=");
        if(sParameterName[0] == sParam){
            return sParameterName[1];
        }
    }
}
function haeAsiakkaat() {
	
	var hakuT = requestURLParam("haku");	
	
	let url = hakuT == null ? "asiakkaat" : "asiakkaat?haku=" + hakuT; 
	let requestOptions = {
        method: "GET",
        headers: { "Content-Type": "application/x-www-form-urlencoded" }       
    };    
    fetch(url, requestOptions)
    .then(response => response.json())
   	.then(response => printItems(response)) 
   	.catch(errorText => console.error("Fetch failed: " + errorText));
	console.log(hakuT + " OLEN TÄÄLLÄ " + url);
    
}


function printItems(respObjList){
	let htmlStr="";
	for(let item of respObjList){	
    	htmlStr+="<tr id='rivi_"+item.id+"'>";
    	htmlStr+="<td>"+item.etunimi+"</td>";
    	htmlStr+="<td>"+item.sukunimi+"</td>";
    	htmlStr+="<td>"+item.puhelin+"</td>";
    	htmlStr+="<td>"+item.sposti+"</td>";     	
    	htmlStr+="</tr>";    	
	}	
	document.getElementById("tbody").innerHTML = htmlStr;	
	
}

haeAsiakkaat();
</script>
</body>
</html>