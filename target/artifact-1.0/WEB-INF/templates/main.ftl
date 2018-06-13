<#include "base.ftl"/>
<html>
<head>

    <@head/>
    <link rel="stylesheet" href="/stat/css/main.css">
</head>
<@header/>
<body class="container">
<@menu/>
<h1 class="col-md-10 col-md-offset-2">
    Новости
</h1>

<div class="all-news col-md-8 col-md-offset-2">
    <#list news as n>
        <div class="news">
            <p class="news-name">${n.title}</p>
            </br>
            <p class="news-body">${n.typeInfo}</p>
        </div>
    </#list>

</div>
<script>
    (function(){
        var widget_id = 896884;
        _shcp =[{widget_id : widget_id}];
        var lang =(navigator.language || navigator.systemLanguage
                || navigator.userLanguage ||"en")
                .substr(0,2).toLowerCase();
        var url ="widget.siteheart.com/widget/sh/"+ widget_id +"/"+ lang +"/widget.js";
        var hcc = document.createElement("script");
        hcc.type ="text/javascript";
        hcc.async =true;
        hcc.src =("https:"== document.location.protocol ?"https":"http")
                +"://"+ url;
        var s = document.getElementsByTagName("script")[0];
        s.parentNode.insertBefore(hcc, s.nextSibling);
    })();
</script>
</body>
</html>