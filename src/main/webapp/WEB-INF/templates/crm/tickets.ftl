<html>
<head>
    <meta charset="UTF-8" content="text/html">
<#--<link rel="stylesheet" href="/css/bootstrap.css">-->
<#--<link href="/css/magazine_site.css" rel="stylesheet">-->
<#--<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">-->
    <title>List</title>
<#--<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>-->
</head>
<body>
<div id="heading">
    <h1>Ticket</h1>
</div>
<div id="main-part">

    <#if error??>
        <p>${error}</p>
    </#if>

    <form action="/crm/tickets" method="post">
        <select name="status">
            <option selected>все</option>
            <option>отзыв</option>
            <option>новая</option>
            <option>в процессе</option>
            <option>закрыта</option>

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
            <th>${ticket.email!""}</th>
            <th>${ticket.name!""}</th>
            <th>${ticket.theme}</th>
            <th>${ticket.body}</th>
            <th>${ticket.ticketStatus.name}</th>
            <th>${ticket.dateApplication}</th>
            <th><button type="button" id="${ticket.id}" onclick="change()">Изменить</button> </th>
        </tr>
        </#list>
    </table>
</div>


</body>

</html>