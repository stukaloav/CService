<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<table>
  <thead>
    <tr>
      <th>id</th>
      <th>firstName</th>
      <th>lastName</th>
      <th>birthDate</th>
    </tr>
    <tbody>
      <c:forEach items="${contacts}" var="contact">
        <tr>
          <td>${contact.id}</td>
          <td>${contact.firstName}</td>
          <td>${contact.lastName}</td>
          <td>${contact.birthDate}</td>
        </tr>
      </c:forEach>
    </tbody>
  </thead>
</table>
</body>
</html>
