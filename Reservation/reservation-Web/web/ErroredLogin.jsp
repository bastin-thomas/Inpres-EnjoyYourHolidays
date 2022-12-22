<%-- 
    Document   : Login
    Created on : 20 déc. 2022, 19:15:46
    Author     : Arkios
--%>

<%@page import="Entities.*"%>
<% 
    Voyageurs user = (Voyageurs) request.getSession().getAttribute("user");
    if(user != null && user.getNumeroClient() != -1) response.sendRedirect(request.getContextPath() + "/Store");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous"/>
        <link href="./styles/Login.css" rel="stylesheet"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login-Reservation</title>
    </head>
    
    <body>        
        <div class="container">
            <h2>Login </h2> 
            
            <label class ="errored" >Mauvais Login ou Mot de Passe</label>
            
            <form action="Login" class ="form-group" method="POST" >
                
                <label for="login "> Login :  </label> 
                <input type="text" class="form-control" name="login" required  >
                <label for="passWord "> Mot de Passe :  </label> 
                <input type="password" class="form-control" name="passWord" required>
                <br>
                <input type="submit" class="btn btn-primary" value="envoyer" >
                   
            </form>
        </div>
    </body>
</html>

