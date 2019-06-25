<#ftl encoding='UTF-8'>
<html>
<head>
    <title>Login</title>
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
    <div class="container-login100">
        <div class="wrap-login100">
            <form method="post" action="/login" class="login100-form validate-form">
					<span class="login100-form-title p-b-26">
						Welcome
					</span>
                <span class="login100-form-title p-b-48">
						<i class="zmdi zmdi-accounts"></i>
					</span>
                <#if error??>
    <p class="alert alert-danger">Wrong login or password!</p>
                </#if>
                <div class="wrap-input100 validate-input" data-validate="Invalid login">

                    <input class="input100" type="text" name="login" id="login">
                    <span class="focus-input100" data-placeholder="Login"></span>
                </div>

                <div class="wrap-input100 validate-input" data-validate="Invalid password">
						<span class="btn-show-pass">
							<i class="zmdi zmdi-eye"></i>
						</span>
                    <input class="input100" type="password" name="password" id="password">
                    <span class="focus-input100" data-placeholder="Password"></span>
                </div>

                <div class="wrap-checkbox">
                    <input type="checkbox" name="remember-me" id="remember-me" class="input-checkbox100">
                    <label for="remember-me" class="label-checkbox100">Remember me</label>
                </div>

                <div class="container-login100-form-btn">
                    <div class="wrap-login100-form-btn">
                        <div class="login100-form-bgbtn"></div>
                        <button class="login100-form-btn" type="submit">
                            Login
                        </button>
                    </div>
                </div>

                <div class="text-center p-t-50">
						<span class="txt1">
							Don’t have an account?
						</span>

                    <a class="txt2" href="/signUp">
                        Sign Up
                    </a>
                </div>
            </form>
        </div>
    </div>
</div>

<script type="text/javascript" src="/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="/js/main.js"></script>
</body>
</html>