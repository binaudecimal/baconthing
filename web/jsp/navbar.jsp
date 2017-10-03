<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>.::Navigation Bar::.</title>
    </head>
    <body>
        <div class='navbar'>
        <table>
            <tr>
                <td colspan="3"><a href='${pageContext.request.contextPath}/jsp/home.jsp'>Home</a></td>
                <td colspan="1"><a href='${pageContext.request.contextPath}/user?action=getUserProfile'>User</a></td>
                <td colspan="3">${sessionScope.user.firstname}</td>
                <td colspan="3">${sessionScope.user.usertype}</td>
                <td colspan="3"><a href='${pageContext.request.contextPath}/login?action=logout'>Logout</a></td>
            </tr>
        </table>
        </div>
    </body>
</html>
