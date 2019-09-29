<#ftl encoding='UTF-8'>
<#include "header.ftl">
<html>
<head>
    <link rel="stylesheet" href="/css/styles.css" type="text/css">
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/util.css">
    <meta charset="utf-8">
</head>
<body class="bg-light">
<#if user??>
<br>
<div class="container">


    <div class="row justify-content-center after-header m-b-20">
        <form method="post" class="col-sm-8 card card-body" enctype="multipart/form-data">
            <#if errors??>
                <#list errors as error>
            <h6 class="alert alert-danger">${error}</h6>
            <br>
                </#list>
            </#if>
            <div class="form-group">
                <label for="name">Name</label>
                <input type="text" class="form-control" id="name" name="name" value="${user.name}">
            </div>
            <div class="form-group">
                <label for="surname">Surname</label>
                <input type="text" class="form-control" id="surname" name="surname" value="${user.surname}">
            </div>
            <div class="form-group">
                <label for="login">Login</label>
                <input type="text" class="form-control" id="login" name="login" value="${user.login}">
            </div>
            <div class="form-group">
                <label for="new-password">New Password</label>
                <input type="password" class="form-control" id="new-password" name="newPassword">
            </div>
            <div class="form-group">
                <label for="old-password">Old Password</label>
                <input type="password" class="form-control" id="old-password" name="oldPassword">
            </div>
            <div class="form-group">
                <label for="email">Email address</label>
                <input type="email" class="form-control" name="email" id="email" aria-describedby="emailHelp"
                       value="${user.email}">
            </div>
            <div class="form-group">
                <label for="city">City</label>
                <input type="text" class="form-control" id="city" name="city"
                       value="<#if user.city??>${user.city}</#if>">
            </div>
            <div class="form-group">
                <label for="dateOfBirth">Date of birth</label>
                <input class="form-control" type="date" id="dateOfBirth" name="dateOfBirth"
                       value="<#if user.dateOfBirth??>${user.dateOfBirth}</#if>">
            </div>

            <div class="form-group">
                <label for="gender">Gender</label>
                <select class="form-control" id="gender" name="gender">
                    <option value=""
                        <#if !user.gender??>
                        selected
                        </#if>>not selected
                    </option>
                    <option value="male"
                        <#if user.gender?? && user.gender == "male">
                        selected
                        </#if>>male
                    </option>
                    <option value="female"
                        <#if user.gender?? && user.gender == "female">
                        selected
                        </#if>>female
                    </option>
                </select>
            </div>

            <div class="form-group">
                <label for="file">Avatar</label>
                <input type="file" class="form-control-file" id="file" name="file" aria-describedby="fileHelp">
            </div>
            <div class="form-group text-center">


                <button type="submit" class="btn btn-outline-warning col-sm-3">Save changes</button>
            </div>
        </form>
<#else>
        <div class="text-center after-header" style="margin: auto">
            <h1>No such user</h1>
        </div>
</#if>
</div>
</div>
<script type="application/javascript" src="/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="/js/bootstrap.min.js"></script>
<script type="application/javascript" src="/js/profile.js"></script>
</body>

</html>