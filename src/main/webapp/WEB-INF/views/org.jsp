<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Organization</title>
</head>
<body>
<h1>Listing Org Details for ${org.name}</h1>
<ul>
<c:forEach items="${org.employees}" var="person">
	<li><a href="../person/edit?id=${person.id}">
		${person.firstName} ${person.lastName}
	</a>
	</li>
</c:forEach>
</ul>

<a href="../person/list"> Back to People</a>
</body>
</html>
