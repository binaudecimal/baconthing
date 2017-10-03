<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>.::Update User Profile::.</title>
    </head>
    <body>
        <h1>Update User</h1>
        
        <div class='update-user-profile'>
            <form action="${pageContext.request.contextPath}/user?action=updateUser">
                <label for='uname'>Username: </label>
                <input type='text' name='username' value='${requestScope.user.username}' id="uname"/> <br>

                <label for='pword'>Password: </label>
                <input type='password' name='password'id="pword"/> <br>

                <label for='cpword'>Confirm Password: </label>
                <input type='password' name='cpassword' id="cpword"/> <br>

                <label for='fname'>First Name: </label>
                <input type='text' name='firstname' value='${requestScope.user.firstname}' id="fname"/> <br>

                <label for='lname'>Last Name: </label>
                <input type='text' name='lastname' value='${requestScope.user.lastname}' id="lname"/> <br>

                <label for='utype'>User Type: </label>
                <input type='text' name='usertype' value='${requestScope.user.usertype}' id="utype"/> <br>

                <label for='id'>User ID: </label>
                <input type='text' name='id' value='${requestScope.user.id}' id="id"/> <br>

                <label for='act'>Active: </label>
                <input type='checkbox' name='active' value='${requestScope.user.active}' id="act"/> <br>

                <input type="submit"/>
            </form>
            <br>    
            ${errorMessage}
        </div>
    </body>
</html>
