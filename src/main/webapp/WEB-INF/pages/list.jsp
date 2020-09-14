<%--
  Created by IntelliJ IDEA.
  User: walker
  Date: 2020/9/14
  Time: 16:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>地址列表</title>
</head>
<body>

    <table>
        <tr>
            <th>addressId</th>
            <th>customerId</th>
            <th>cnee</th>
            <th>phone</th>
            <th>address</th>
        </tr>a
        <c:forEach items="${address}" var="a">
            <tr>
                <td>${a.addressId}</td>
                <td>${a.customerId}</td>
                <td>${a.cnee}</td>
                <td>${a.phone}</td>
                <td>${a.address}</td>
            </tr>
        </c:forEach>
    </table>

</body>
</html>

