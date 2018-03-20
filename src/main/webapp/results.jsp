<%@include file="head.jsp"%>

<body>
<div class="container-fluid">
<h3>${message}</h3><br/>
<table id="customers">
    <tr><th>User ID</th><th>First Name</th><th>Last Name</th><th>Age</th><th>Password</th><th>Order/s</th><th>Role/s</th></tr>
    <c:forEach var="user" items="${users}">
        <tr>
            <td>${user.userName}</td>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
            <td>${user.age}</td>
            <td>${user.userPassword}</td>

            <td>
                <c:forEach var="order" items="${user.orders}">
                    ${order.id} ${order.description}<br/>
                </c:forEach>
            </td>

            <td>
                <c:forEach var="role" items="${user.roles}">
                    ${role.id} ${role.roleName}<br/>
                </c:forEach>
            </td>
        </tr>
    </c:forEach>
</table>
<br/>

    <a href="index.jsp"><b>Back</b></a>
</div>
</html>
