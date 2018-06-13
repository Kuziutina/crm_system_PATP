<#macro head>
    <link href="https://fonts.googleapis.com/css?family=Lato" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Pacifico" rel="stylesheet">
    <link rel="stylesheet" href="/stat/css/menu.css">
    <link rel="stylesheet" type="text/css" href="/stat/bootstrap/bootstrap-3.3.2-dist/css/bootstrap.min.css"/>
    <script src="/stat/js/jquery.js"></script>
    <script src="/stat/bootstrap/bootstrap-3.3.2-dist/js/bootstrap.min.js"></script>
</#macro>

<#macro menu>
    <div class="navigation-menu">
        <input id="hamburger" class="hamburger" type="checkbox"/>
        <label class="hamburger" for="hamburger">
            <i></i>
            <text>
                <close>close</close>
                <open>menu</open>
            </text>
        </label>
        <section class="drawer-list">
            <ul>
                <li><a href="/main">Главная</a></li>
                <li><a href="/feedback/create">Оставить отзыв</a></li>
                <li><a href="/ticket/create">Оставить заявку</a></li>
                <#if user??>

                    <li><a href="/profile">Личный кабинет</a></li>
                    <#if user.role == "MANAGER_ROLE">
                        <li><a href="/profile/messages">Сообщения</a></li>
                        <li><a href="/crm">CRM</a></li>
                        <li><a href="/employees">Работники</a> </li>
                    </#if>
                    <#if user.role == "EMPLOYEE_ROLE">
                        <li><a href="/profile/messages">Сообщения</a></li>
                        <li><a href="/employees">Работники</a> </li>
                    </#if>
                    <li><a href="/logout">Выход</a></li>
                <#else>
                    <li><a href="/login">Вход</a></li>
                    <li><a href="/registration">Регистрация</a></li>
                </#if>
            </ul>
        </section>
    </div>
</#macro>

<#macro header>
    <div class="header">
        <div class="title">
            <a href="/main" class="logo">
                <img src="">
            </a>
            <div class="col-md-4 col-md-offset-4 title">
                <h1>ПАССАЖИРСКОЕ АВТОТРАНСПОРТНОЕ ПРЕДПРИЯТИЕ №2</h1>
            </div>
        </div>
    </div>
</#macro>