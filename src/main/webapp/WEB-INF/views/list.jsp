<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
</head>
<body>
<h1>Listing People</h1>
<c:forEach items="${people}" var="person">
	<a href="edit?id=${person.id}"> ${person.id} - ${person.firstName} ${person.lastName}</a>
	<c:if test="${not empty person.employers}">
		(
		<c:forEach items="${person.employers}" var="employer">
			<a href="../org/${employer.id}">${employer.name}</a>
		</c:forEach>
		)
	</c:if>
	<br />
</c:forEach>
<a href="edit"> Add Person</a>
</body>
</html>
