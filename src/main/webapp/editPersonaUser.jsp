<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 03/04/2022
  Time: 6:27 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Edit Persona User Info</title>
</head>
<body>
<center>
    <h1>User Management</h1>
    <h2>
        <a href="p5">Persona User List</a>
    </h2>
</center>
<div align="center">
    <form method="post">
        <table border="1" cellpadding="5">
            <caption>
                <h2>
                    Edit Persona User
                </h2>
            </caption>
            <c:if test="${personaUser != null}">
                <input type="hidden" name="id" value="<c:out value='${personaUser.id}' />"/>
            </c:if>
            <tr>
                <th>Persona User Name:</th>
                <td>
                    <input type="text" name="name" size="45"
                           value="<c:out value='${personaUser.name}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Persona User Gender:</th>
                <td>
                    <input type="text" name="gender" size="45"
                           value="<c:out value='${personaUser.gender}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Persona ID:</th>
                <td>
                    <input type="text" name="personaId" size="15"
                           value="<c:out value='${personaUser.personaID}' />"
                    />
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Save"/>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
