<#ftl encoding='UTF-8'>
<html>
<head>
    <link rel="stylesheet" href="/css/styles.css"  type="text/css">
    <#--<link rel="stylesheet" href="/css/bootstrap.min.css" type="text/css"/>-->
</head>
<body>
<#if error??>
    <div class="alert alert-danger">Wrong login or email!</div>
</#if>
<div class="form-style-2">
    <div class="form-style-2-heading">
        Please Sign Up!
    </div>
    <form method="post" action="/signUp" enctype="multipart/form-data">
        <label for="login">Login
            <input class="input-field" type="text" id="login" name="login">
        </label>
        <br>
        <label for="password">Password
            <input class="input-field" type="password" id="password" name="password">
        </label>
        <br>
        <label for="name">Name
            <input class="input-field" type="text" id="name" name="name">
        </label>
        <br>
        <label for="surname">Surname
            <input class="input-field" type="text" id="surname" name="surname">
        </label>
        <br>
        <label for="email">Email
            <input class="input-field" type="text" id="email" name="email">
        </label>
        <br>
        <input type="file" name="file" id="file">
        <br>
        <input type="submit" value="Register">
    </form>
    <label for="login">Already registered?
    <a id="login" class="button" href="/login">Login</a></label>
</div>
</body>
</html>