<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>.::User Page::.</title>
    </head>
    <body>
        <h1>User Page</h1>
        <div margin='5%' border='3%' padding='12%' width='80%' class='person-info'>
            Username:   ${sessionScope.user.username} </br>
            Password:   ${sessionScope.user.password}</br>
            First Name: ${sessionScope.user.firstname}</br>
            Last Name:  ${sessionScope.user.lastname}</br>
            User Type:  ${sessionScope.user.usertype}</br>
            User ID:    ${sessionScope.user.id}<br>
            Active:     ${sessionScope.user.active}<br>
            
            <br>
            <br>
            <a href='${pageContext.request.contextPath}/user?action=updateUserProfile&u=${sessionScope.user.username}'><H2>Update User Information</H2></a>
        </div>
        
        <div class='user-list'>
            <!--Put users here -->
            
        </div>
    </body>
</html>
