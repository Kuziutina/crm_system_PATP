<#include "base.ftl"/>
<html>
<head>
    <@head/>
    <link rel="stylesheet" href="/stat/css/main.css">

</head>
<body class="container">
<@menu/>
<@header/>

<div id="main-part" class="col-md-10">
    <h1 class="page-title col-md-12">Личная страница</h1>
    <div class="col-md-5 image" >
        <#if viewUser.userImage?? >
            <img src="/userImage/load/${viewUser.userImage.id}" width="300" height="450">
        <#else >
            <img src="/stat/dizain_kuhni-foto-10.jpg" width="300" height="450">
        </#if>
        <div class="col-md-12 change-image-form">

        <form action="/profile/changeImage" method="post" enctype="multipart/form-data" id="avatar-form" modelAttribute="userImageForm">
            <div class="form-group">
                <input id="file" type="file" multiple="multiple" name="file" accept="image/*"/>
            </div>
            <button id="ok-image-button" class="btn btn-primary col-md-10" type="button" onclick="changeImage(${viewUser.id})">Отправить</button>
        </form>
        <button id="change-image-button" class="btn btn-primary col-md-10" type="button" onclick="showOkButton()">Изменить</button>
        </div>

        <div class="change-profile col-md-12">
        <#if viewUser.userData??>
            <a href="/profile/change" class="btn-primary btn col-md-10">Редактировать</a>
        </#if>
        </div>
        <div class="doing col-md-12">
            <a href="/profile/feedbacks" class="feedbacks btn btn-primary col-md-10">Отзывы</a>
            </br>
            <a href="/profile/tickets" class="tickets btn-primary btn col-md-10">Заказы</a>
        </div>
    </div>
    <div class="col-md-6 col-md-offset-1">
        <p class="user-name">${viewUser.login}</p>
        <#if viewUser.userData??>
            <p class="usual-text">${viewUser.userData.lastName} ${viewUser.userData.name} ${viewUser.userData.fatherName} </p>
        </#if>
        <#if viewUser.employee??>
            <p class="usual-text">${viewUser.employee.lastName} ${viewUser.employee.name} ${viewUser.employee.fatherName} </p>
            <p>Стаж работы: ${viewUser.employee.experience}</p>
            <p>Должность: ${viewUser.employee.position.name}</p>
        </#if>
    </div>

</div>

<script>
    hideOkButton();

    function hideOkButton() {
        $('#avatar-form').hide();
        $('#change-image-button').show();
    }


    function showOkButton() {
        $('#avatar-form').show();
        $('#change-image-button').hide();
    }

    function changeImage(user) {
        // var form = $('#avatar-form')[0];
        var data = new FormData();
        data.append("userId", user);
        data.append("imageUser", document.getElementById('file').files[0]);

        console.log(data.get("imageUser"));
        console.log(data.get("userId"));
        var xhr = new XMLHttpRequest();
        xhr.open("POST", "/profile/changeImage");
        xhr.send(data);
        setTimeout(function () {
            window.location.reload();
        }, 1000)

    }
</script>

</body>

</html>