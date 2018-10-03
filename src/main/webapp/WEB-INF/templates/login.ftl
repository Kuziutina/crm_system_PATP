<html>
<head>
    <link rel="stylesheet" type="text/css" href="/stat/bootstrap/bootstrap-3.3.2-dist/css/bootstrap.min.css"/>
    <script src="/stat/js/jquery.js"></script>
    <script src="/stat/bootstrap/bootstrap-3.3.2-dist/js/bootstrap.min.js"></script>
</head>
<body>
<h1 class="col-md-offset-4">Вход</h1>
<form class="form-signin col-md-offset-4 col-md-3" method="post" action="/login">
    <#if error??>
        ${error}
    </#if>
    <div class="form-label-group validation">
        <label for="inputLogin">Логин</label>
        <input type="text" name="username" id="inputLogin" class="form-control" required autofocus>
    </div>

    <div class="form-label-group validation">
        <label for="inputPassword">Пароль</label>
        <input type="password" pattern="(?=^.{6,}$)(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$"
               name="password" id="inputPassword" class="form-control" required>
    </div>

    <div class="form-label-group validation">
        <label>
            <input type="checkbox" name="remember-me" checked> Запомнить меня
        </label>
    </div>
    <button type="submit" class="btn btn-primary btn-block">Войти</button>
    <a href="/registration">
        Зарегистрироваться
    </a>
</form>
</body>
</html>