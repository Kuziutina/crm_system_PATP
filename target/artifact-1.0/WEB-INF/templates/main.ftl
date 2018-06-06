<#include "base.ftl"/>
<html>
<head>
    <@head/>
</head>
<body class="container">
<@menu/>
<h1 class="col-md-offset-2">
    Новости
</h1>

<div class="news col-md-8 col-md-offset-2">
    <#list news as n>
        <div class="news">
            <a href="news/${n.id}">${n.title}</a>
            </br>
            <p>${n.typeInfo}</p>
        </div>
    </#list>

</div>
</body>
</html>