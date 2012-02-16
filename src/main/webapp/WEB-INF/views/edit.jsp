<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Editing Person ${person.id} - ${person.firstName}  ${person.lastName} 
</h1>
<form:form commandName="person" style="padding:8px">
    ID - ${person.id}<br/>
    <p>
        First Name<br/>
        <form:input path="firstName"/>
    </p>
    <p>
        Last Name<br/>
        <form:input path="lastName"/>
    </p>
    <p>
        Organization<br/>
        <%-- <form:select path="employer" items="${organizations}" itemLabel="name" itemValue="id" /> --%>
        <spring:bind path="employer" >
        	<c:forEach items="${organizations}" var="org">
        		<input type="radio" id="${status.expression}-${org.id}" name="${status.expression}" value="${org.id}" ${org.id == status.value ? 'checked="true"' : ''}/>
        		<label for="${status.expression}-${org.id}"> ${org.name} </label>
        	</c:forEach>
        </spring:bind>
    </p>
    
    <input type="submit" value="Save"/>
</form:form>
</body>
</html>
