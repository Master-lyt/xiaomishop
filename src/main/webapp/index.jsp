<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>
<h2>Hello World!</h2>
    <!-- 转发到最终的首页src/main/webapp/web-inf/back/shop.jsp -->
    <c:redirect url="login"/>
<%--    <a href="${pageContext.request.contextPath}/xad/getaddress">查询所有地址</a>--%>
</body>
</html>