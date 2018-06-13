<#include "../base.ftl"/>
<html>
<head>
    <@head/>
    <title>List</title>
<#--<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>-->
</head>
<body>
<@header/>
<@menu/>
<div id="heading">
    <h1>Заявки/Отзывы</h1>
</div>
<div id="main-part">

    <div id="error">
    <#if error??>
        <p>${error}</p>
    </#if>
    </div>

    <form action="/crm/tickets/param" method="get">
        <select name="status">
            <option selected>Все</option>
            <option>Отзыв</option>
            <option>Новая</option>
            <option>В процессе</option>
            <option>Закрыта</option>

        </select>
        <button type="submit">Поиск</button>
    </form>
    <table>
        <tr>
            <th>Почта</th>
            <th>Имя</th>
            <th>Тема/номер маршрута</th>
            <th>Тело/описание</th>
            <th>Статус</th>
            <th>Дата подачи</th>
            <th></th>
        </tr>
        <#list tickets as ticket>
        <tr>
            <th id="email-${ticket.id}">${ticket.email!""}</th>
            <th>${ticket.name!""}</th>
            <th>${ticket.theme!""}</th>
            <th>${ticket.body!""}</th>
            <th id="status-${ticket.id}">${ticket.ticketStatus.name}</th>
            <th>${ticket.dateApplication}</th>
            <th id="change-button-${ticket.id}"><button type="button" id="${ticket.id}" onclick="change(${ticket.id})"
            <#if ticket.ticketStatus.name == "Отзыв">
                disabled
            </#if>
            >Изменить</button> </th>
        </tr>
        </#list>
    </table>

</div>


</body>
<script>

    function send(ticket_id) {
        var ticketUpdate = {};
        ticketUpdate["id"] = ticket_id;
        ticketUpdate["status"] = $("#status-select-" + ticket_id).val();
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/crm/ticket/change",
            data: JSON.stringify(ticketUpdate),
            dataType: "json",
            success: function () {
                $("#status-" + ticket_id).html(ticketUpdate["status"]);
                $("#change-button-" + ticket_id).html("<button type='button' onclick='change(" + ticket_id + ")'>Изменить</button>");
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

    function change(ticket_id) {
        $("#status-" + ticket_id).html("" +
                "<select id='status-select-" + ticket_id + "'>" +
                "   <option selected>Новая</option>" +
                "   <option>В процессе</option>" +
                "   <option>Закрыта</option>" +
                "</select>");
        $("#change-button-" + ticket_id).html("<button type='button' onclick='send(" + ticket_id + ")'>Сохранить</button>");

    }
</script>

</html>