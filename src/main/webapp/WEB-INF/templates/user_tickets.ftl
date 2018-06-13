<#include "base.ftl"/>
<html>
<head>

    <@head/>
    <link rel="stylesheet" href="/stat/css/ticket.css">
</head>
<body class="container">
<@header/>
<@menu/>
<h1 class="col-md-offset-2 col-md-8">
    Заявки
</h1>

<div class="items col-md-8 col-md-offset-2">
    <#list tickets  as feedback>
        <div class="item feedback">
            <p class="item-title feedback-title">${feedback.name}</p>
            </br>
            <p class="item-status feedback-status">${feedback.ticketStatus.name}</p>
            </br>
            <p class="item-theme feedback-theme">${feedback.theme}</p>
            </br>
            <p class="item-body feedback-body">${feedback.body}</p>
            </br>
            <a href='/ticket/${feedback.id}/load' class="btn btn-default" role="button" download><span class="glyphicon glyphicon-download"></span></a>
        </div>
    </#list>

</div>
</body>
</html>