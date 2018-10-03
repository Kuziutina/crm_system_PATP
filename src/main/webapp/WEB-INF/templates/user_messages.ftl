<#include "base.ftl"/>
<html>
<head>

    <#--<link rel="stylesheet" href="/stat/css/message.css">-->
    <@head/>
        <link rel="stylesheet" href="/stat/css/ticket.css">
</head>
<body class="container">
<@header/>
<@menu/>
<h1 class="col-md-offset-2 col-md-10">
    Сообщения
</h1>
<div class="row">
    <div class="col-md-2 col-md-offset-2">
        <#list recipients as recipient>
            <div class="">
                <button class="recipient-item btn btn-primary col-md-12" type="button" onclick="load_messages(${recipient.id})">
                    <#if recipient.employee??>${recipient.employee.lastName}<#else>${recipient.login}</#if></button>
            </div>
        </#list>
    </div>

    <div class="messages col-md-6" id="messages">
    </div>

    <div class="input-message col-md-6 col-md-offset-4" id="input-message"></div>
</div>





</body>
<script>

    function FormatTime(time, prefix = "") {
        var date = Date.parse(time); // returns NaN if it can't parse
        return Number.isNaN(date) ? "" : prefix + date.toLocaleDateString();
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
                console.log(message["date"]);

                $("#messages").append("<div class='item'>" +
                        "<p class='sender'> ${user.login} </p></br>" +
                        "<p class='message-body'> " + message["body"] + "</p></br>" +
                        "<p class='date'>" + FormatTime(message["date"]) + "</p></div>");
                $("#message-text").val("");
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

    function load_messages (recipient_id) {
        console.log(recipient_id);
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/profile/messages",
            data: JSON.stringify(recipient_id),
            dataType: "json",
            success: function (messages) {
                console.log(messages);
                console.log("no");
                $("#messages").html("");
                $("#input-message").html("");
                for (var i = 0; i < messages.length; i++) {
                    console.log(messages[i].body);

                    $("#messages").append("<div class='item'>" +
                            "<p class='sender'>" + messages[i].sender_name + "</p></br>" +
                            "<p class='message-body'> " + messages[i].body + "</p></br>" +
                            "<p class='date'>" + messages[i].date + "</p></div>");
                }

                $("#input-message").append("<div class='send-message'>" +
                        "    <form id='new-message'>" +
                        "        <textarea class='col-md-12' id='message-text' name='body' placeholder='Сообщение'></textarea>" +
                        "        <button class='btn' type='button' id='send-message-button' onclick='send_message(" + recipient_id + ")'>Отправить</button>" +
                        "    </form>" +
                        "</div>");
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
</html>