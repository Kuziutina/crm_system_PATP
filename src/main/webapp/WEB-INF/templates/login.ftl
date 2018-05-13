<html>
<head></head>
<body>
<h1>Login</h1>
<form class="form-signin" method="post" action="/login">
    <#if error??>
        ${error}
    </#if>
    <div>
        <input type="text" name="username" id="inputLogin" required autofocus>
        <label for="inputLogin">Логин</label>
    </div>

    <div>
        <input type="password" pattern="(?=^.{6,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$"
               name="password" id="inputPassword" required>
        <label for="inputPassword">Пароль</label>
    </div>

    <div class="checkbox mb-3">
        <label>
            <input type="checkbox" name="remember-me-param" checked> Remember me
        </label>
    </div>
    <button type="submit">Войти</button>
    <a href="/registration">
        Зарегистрироваться
    </a>
</form>
</body>
</html>