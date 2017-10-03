<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>.::Login Page::.</title>
    </head>
    <body>
        <H1>Login</H1>
    <class div='loginInput'>
        <form action="${pageContext.request.contextPath}/login" method='post' name='login'>
            <input type='hidden' name='action' value='login'/>
            <label for='uname'>Username :</label>
            <input type='text' name='username' id='uname'/>
            </br>
            <label for='pword'>Password :</label>
            <input type='password' name='password' id='pword'/>
            </br>
            <input type='submit' value="Submit"/>
        </form>
    </class>
        
    
    <div class='error'>
        <h3>${errorMessage}</h3>
    </div>
    </body>
</html>
