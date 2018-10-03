<html>
<head>
    <link rel="stylesheet" type="text/css" href="/stat/bootstrap/bootstrap-3.3.2-dist/css/bootstrap.min.css"/>
    <script src="/stat/js/jquery.js"></script>
    <script src="/stat/bootstrap/bootstrap-3.3.2-dist/js/bootstrap.min.js"></script>
</head>
<body class="container">
<h1 class="col-md-offset-4">Регистрация</h1>
<#if error??>
        <p>${error}</p>
</#if>

<div class="col-md-offset-4">
<form action="/registration" name="userForm" method="post" class="form-signin col-md-4" modelAttribute="accountDto">

    <div class="form-label-group validation">
        <label for="regName">login</label>
        <input type="text" pattern="[A-Za-zА-Яа-яЁ]+" name="login" id="regName" class="form-control"
               placeholder="Логин" required autofocus>
        <span class="validity"></span>
    </div>

    <div class="form-label-group validation">
        <label for="regPassword">Пароль</label>
        <input type="password"
               pattern="(?=^.{6,}$)(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$"
               name="password" id="regPassword" class="form-control" placeholder="Пароль"
               required autofocus>
        <span class="validity"></span>
    </div>

    <div class="form-label-group validation">
        <label for="regPasswordRepeat">Повторите Пароль</label>
        <input type="password"
               pattern="(?=^.{6,}$)(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$"
               name="matchingPassword" id="regPasswordRepeat" class="form-control"
               placeholder="Повторите пароль" required autofocus>
        <span class="validity"></span>
    </div>

    <button class="btn btn-primary btn-block" type="submit">Зарегистрироваться</button>
</form>
</div>
</body>
</html>