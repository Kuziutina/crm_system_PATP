<html>
<body>
<h1>Hello</h1>

<h1>
    Новости
</h1>

<div class="news">
    <#list news as n>
        <p>${n.info}</p>
        <p>${n.date}</p>
    </#list>

</div>
</body>
</html>