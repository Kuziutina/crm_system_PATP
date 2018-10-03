<#include "base.ftl"/>
<html>
<head>
    <@head/>
</head>
<body>
<@header/>
<@menu/>

<div id="main-part" class="col-md-5 col-md-offset-4">
    <h1 class="col-md-12">Редактирование данных</h1>

    <form action="/profile/change" name="userForm" method="post" class="form col-md-8" modelAttribute="changeUser">

        <div class="form-label-group">
            <label for="name">Имя</label>
            <input type="text" name="name" id="name" class="form-control"
                   placeholder="Имя" required autofocus>
        </div>

        <div class="form-label-group">
            <label for="lastName">Фамилия</label>
            <input type="text"
                   name="lastName" id="lastName" class="form-control" placeholder="Фамилия">
        </div>

        <div class="form-label-group">
            <label for="fatherName">Отчество</label>
            <input type="text"
                   name="fatherName" id="fatherName" class="form-control"
                   placeholder="Отчество">
        </div>

        <div class="form-label-group">
            <label for="email">Почта</label>
            <input type="email"
                   name="email" id="email" class="form-control"
                   placeholder="email">
        </div>

        <button class="btn btn-primary btn-block" type="submit">Изменить</button>
    </form>

</div>
</body>

</html>