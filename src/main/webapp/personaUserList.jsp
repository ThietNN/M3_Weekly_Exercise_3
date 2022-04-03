<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 03/04/2022
  Time: 5:59 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Persona User List</title>
</head>
<body>
<center>
    <h1>Persona User</h1>
    <h2>
        <a href="/p5?action=createPersonaUser">Add New User</a>
    </h2>
</center>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>List of Persona User</h2></caption>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Gender</th>
            <th>Persona ID</th>
        </tr>
        <c:forEach var="personaUser" items="${personaUserList}">
            <tr>
                <td><c:out value="${personaUser.id}"/></td>
                <td><c:out value="${personaUser.name}"/></td>
                <td><c:out value="${personaUser.gender}"/></td>
                <td><c:out value="${personaUser.personaID}"/></td>
                <td>
                    <a href="/p5?action=editPersonaUser&id=${personaUser.id}">Edit</a>
                    <a href="/p5?action=deletePersonaUser&id=${personaUser.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
