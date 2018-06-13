<#include "base.ftl"/>
<html>
<head>

    <@head/>
    <link rel="stylesheet" href="/stat/css/ticket.css">
</head>
<body class="container">
<@header/>
<@menu/>
<h1 class="col-md-offset-2 col-md-10">
    Отзывы
</h1>

<div class="items col-md-8 col-md-offset-2">
    <#list feedbacks as feedback>
        <div class="feedback item">
            <p class="item-title feedback-title">${feedback.name}</p>
            </br>
            <p class="item-theme feedback-theme">${feedback.theme!""}</p>
            </br>
            <p class="item-body feedback-body">${feedback.body!""}</p>
            </br>
        </div>
    </#list>

</div>
</body>
</html>