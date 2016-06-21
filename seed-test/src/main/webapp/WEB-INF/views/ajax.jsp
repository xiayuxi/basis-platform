<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page session="false"%>
<html>
<head>
<title>ajax起始页</title>
</head>
<body>
<input type="button" value="submit ajax json" id="bn" onclick="submitJson();"/>
<input type="button" value="submit ajax html" id="bn" onclick="submitHtml();"/>
</body>
<script type="text/javascript" src="../resources/base/jquery-1.8.0.min.js"></script>
<script type="text/javascript" >
function submitJson(){
	$.ajax({
		async : false,
		url : "submitJson",
		type : 'GET',
		dataType : 'json',
		cache : false,
		success : function(json) {
			 alert(json);
		},
		error : function(e) {
			return false;
		}
	});
}
function submitHtml(){
	$.ajax({
		async : false,
		url : "submitHtml",
		type : 'GET',
		dataType : 'json',
		cache : false,
		success : function(json) {
			 alert(json);
		},
		error : function(e) {
			return false;
		}
	});
}
</script>
</html>
