<#ftl encoding='UTF-8'>
<html lang="en">
<head>
    <title>Sign Up</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css">

    <link rel="stylesheet" type="text/css" href="/css/util.css">
    <link rel="stylesheet" type="text/css" href="/css/main.css">
    <link rel="stylesheet" type="text/css" href="/fonts/iconic/css/material-design-iconic-font.min.css">
    <link rel="stylesheet" type="text/css" href="/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
</head>

<body>


<div class="limiter">
    <div class="container-login100" style="background-image: url('/images/back.jpg')">
        <div class="wrap-login100">
            <form class="login100-form validate-form" method="post" action="/signUp" enctype="multipart/form-data">
					<span class="login100-form-title p-b-26">
						Join Us
					</span>
                <span class="login100-form-title p-b-48">
						<i class="zmdi zmdi-accounts-add"></i>
					</span>
                <div>
                    <#if errors??>
    <#list errors as error>
            <h6 class="alert alert-danger">${error}</h6>
            <br>
    </#list>
                    </#if>
                </div>
                <div class="wrap-input100">
                    <input class="input100" type="text" name="login" id="login" data-validate="Invalid login">
                    <span class="focus-input100" data-placeholder="Login"></span>
                </div>
                <div class="wrap-input100">
                    <input class="input100" type="text" name="name" id="name">
                    <span class="focus-input100" data-placeholder="Name"></span>
                </div>
                <div class="wrap-input100">
                    <input class="input100" type="text" name="surname" id="surname">
                    <span class="focus-input100" data-placeholder="Surname"></span>
                </div>

                <div class="wrap-input100 validate-input" data-validate="Valid email is a@b.c">
                    <input class="input100" type="text" name="email" id="email">
                    <span class="focus-input100" data-placeholder="Email"></span>
                </div>

                <div class="wrap-input100 validate-input" data-validate="Invalid password">
						<span class="btn-show-pass">
							<i class="zmdi zmdi-eye"></i>
						</span>
                    <input class="input100" type="password" name="password" id="password">
                    <span class="focus-input100" data-placeholder="Password"></span>
                </div>

                <div class="wrap-checkbox">
                    <input type="file" name="file" id="file">
                </div>

                <div class="container-login100-form-btn">
                    <div class="wrap-login100-form-btn">
                        <div class="login100-form-bgbtn"></div>
                        <button class="login100-form-btn" type="submit">
                            Sign Up
                        </button>
                    </div>
                </div>

                <div class="text-center p-t-50">
						<span class="txt1">
							Already have an account?
						</span>

                    <a class="txt2" href="/login">
                        Sign In
                    </a>
                </div>
            </form>
        </div>
    </div>
</div>

<script src="/js/jquery-3.2.1.min.js"></script>
<script src="/js/main.js"></script>
<script>
    $(window).scroll(function () {
        $("body").css("background-position","50% " + ($(this).scrollTop() / 2) + "px");
    });
</script>
</body>
</html>