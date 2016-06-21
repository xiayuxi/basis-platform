<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
</head>
<body>
	<c:forEach items="${users}" var="item">
	id:${item.id}<br />
	name:${item.name}<br />
	</c:forEach>
</body>
</html>
