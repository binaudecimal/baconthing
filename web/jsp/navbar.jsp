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
                <td>${sessionScope.user.firstname}</td>
                <td><a href='#'>Home</a></td>
                <td><a href='${pageContext.request.contextPath}/login?action=logout'>Logout</a></td>
            </tr>
        </table>
        </div>
    </body>
</html>
