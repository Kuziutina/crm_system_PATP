<#include "base.ftl"/>
<html>
<head>
    <@head/>
    <link rel="stylesheet" href="/stat/css/main.css">

</head>
<body>
<@menu/>
<@header/>

<div id="main-part" class="col-md-8 col-md-offset-3">
    <h1 class="page-title col-md-12">Страница пользователя</h1>
    <div class="col-md-4 image" >
        <#if viewUser.userImage?? >
            <img src="/userImage/load/${viewUser.userImage.id}" width="300" height="450">
        <#else >
            <img src="/stat/dizain_kuhni-foto-10.jpg" width="300" height="450">
        </#if>
    </div>
    <div class="col-md-6">
        <p>${viewUser.login}</p>
        <#if viewUser.userData??>
            <p class="usual-text">${viewUser.userData.lastName} ${viewUser.userData.name} ${viewUser.userData.fatherName} </p>
        </#if>
        <#if viewUser.employee??>
            <p class="usual-text">${viewUser.employee.lastName} ${viewUser.employee.name} ${viewUser.employee.fatherName} </p>
            <p class="usual-text">Стаж работы: ${viewUser.employee.experience}</p>
            <div id="has-question" class="question">
                <button id="has-question-button" class="btn-primary btn" type="button" onclick="showOkButton()">Задать вопрос</button>
            </div>
            <div id="input-message">
                <form id="new-message">
                    <input type="text" id="message-text" name='body' placeholder='Сообщение'></br>
                    <button class="btn btn-primary" type="button" onclick="send_message(${viewUser.id})">Задать вопрос</button>
                </form>
            </div>
            <div id="question-form">
                <form action="/user/${viewUser.id}/message/add"
            </div>
        </#if>
    </div>

</div>

<script>

    hideOkButton();

    function hideOkButton() {
        $('#input-message').hide();
        $('#has-question').show();
    }


    function showOkButton() {
        $('#input-message').show();
        $('#has-question').hide();
    }

    function send_message(recipient_id) {
        var message = {};
        message["recipient_id"] = recipient_id;
        message["body"] = $("#message-text").val();
        message["sender_id"] = ${user.id};
        message["date"] = Date.now();
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/profile/messages/add",
            data: JSON.stringify(message),
            dataType: "json",
            success: function () {
                $("#message-notification").html("<p>Ваш вопрос был успешно задан! Наш сервис может гарантировать Вашу анонимность, ответ на вопрос можете ожидать на вкладке \"Сообщения\"</p>");
                $("#message-text").val("");
                hideOkButton();
            },
            error: function (jqXHR, exception) {

                if (jqXHR.status === 0) {
                    alert('Not connect.\n Verify Network.');
                } else if (jqXHR.status == 404) {
                    alert('Requested page not found. [404]');
                } else if (jqXHR.status == 500) {
                    alert('Internal Server Error [500].');
                } else if (exception === 'parsererror') {
                    alert('Requested JSON parse failed.');
                } else if (exception === 'timeout') {
                    alert('Time out error.');
                } else if (exception === 'abort') {
                    alert('Ajax request aborted.');
                } else {
                    alert('Uncaught Error.\n' + jqXHR.responseText);
                }
            }
        });
    }

</script>

</body>

</html>