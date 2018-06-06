<html>
<body>
<div id="heading">
    <h1>Страничка</h1>
</div>
<div id="main-part">
    <p>${viewUser.login}</p>
    <#if viewUser.employee??>
        <p>staj: ${viewUser.employee.experience}</p>
    </#if>
    <#if user??>
    <#if viewUser.id != user.id>
        <p>Написать сообщение</p>
    <#else>
        <p>Отзывы</p>
        <p>Заказы</p>
        <a href="/user/${user.id}/change">Редактировать</a>
    </#if>
    </#if>
</div>
</body>

</html>