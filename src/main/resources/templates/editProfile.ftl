<#ftl encoding='UTF-8'>
<html>
<head>
    <link rel="stylesheet" href="/css/styles.css" type="text/css">
    <meta charset="utf-8">
</head>
<body>
<#if user??>
<form method="post" class="form-style-2" enctype="multipart/form-data">
    <div class="form-style-2-heading">
        Edit your profile
    </div>
     <#if errors??>
         <#list errors as error>
            <span>${error}</span>
            <br>
         </#list>
     </#if>
    <label for="name">Name:
        <input class="input-field" type="text" id="name" name="name" value="${user.name}">
    </label>
    <br>

    <label for="surname">Surname:
        <input class="input-field" type="text" id="surname" name="surname" value="${user.surname}">
    </label>
    <br>

    <label for="login">Login:
        <input class="input-field" type="text" name="login" id="login" value="${user.login}">
    </label>
    <br>

    <label for="new-password">New Password:
        <input class="input-field" type="password" name="newPassword" id="new-password">
    </label>
    <br>

    <label for="old-password">Old Password:
        <input class="input-field" type="password" name="oldPassword" id="old-password">
    </label>
    <br>

    <label for="email">Email:
        <input class="input-field" type="text" name="email" id="email" value="${user.email}">
    </label>
    <br>

    <label for="gender">Gender:
        <select class="select-field" id="gender" name="gender">
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
    </label>
    <br>

    <label for="city">City:
        <input class="input-field" type="text" name="city" id="city" value="<#if user.city??>${user.city}</#if>">
    </label>
    <br>

    <label for="dateOfBirth">Date of birth:
        <input class="input-field" type="text" name="dateOfBirth" id="dateOfBirth" value="<#if user.dateOfBirth??>${user.dateOfBirth}</#if>">
    </label>
    <br>

    <label for="file">Avatar:
    <input type="file" name="file" id="file">
    </label>
    <br>

    <input type="submit" value="Save">

</form>

<#else>
<div class="form-style-2-heading">
    No such user
</div>
</#if>
<script type="application/javascript" src="/js/jquery-1.9.1.js"></script>
<script type="application/javascript" src="/js/profile.js"></script>
</body>
</html>