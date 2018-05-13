<html>
<body>
<h1>form</h1>
<form action="/registration" name="userForm" method="post" class="form-signin">
    <input type="hidden" value="${failReg!''}" id="failReg">
    <#--<div class="form-label-group validation">-->
        <#--<input type="email" name="login" id="regEmail" class="form-control"-->
               <#--placeholder="Email address" required autofocus>-->
        <#--<span class="validity"></span>-->
        <#--<label for="regEmail">Email</label>-->
    <#--</div>-->

    <#if error??>
        <p>${error}</p>
    </#if>
    <div class="form-label-group validation">
        <input type="text" pattern="[A-Za-zА-Яа-ЯЁ]+" name="login" id="regName" class="form-control"
               placeholder="Full name" required autofocus>
        <span class="validity"></span>
        <label for="regName">login</label>
    </div>

    <div class="form-label-group validation">
        <input type="password"
               pattern="(?=^.{6,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$"
               name="password" id="regPassword" class="form-control" placeholder="Email address"
               required autofocus>
        <span class="validity"></span>
        <label for="regPassword">Пароль</label>
    </div>

    <div class="form-label-group validation">
        <input type="password"
               pattern="(?=^.{6,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$"
               name="matchingPassword" id="regPasswordRepeat" class="form-control"
               placeholder="Email address" required autofocus>
        <span class="validity"></span>
        <label for="regPasswordRepeat">Повторите Пароль</label>
    </div>

    <button class="btn btn-lg btn-primary btn-block" type="submit">Зарегистрироваться</button>
</form>

</body>
</html>