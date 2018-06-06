<html>
<body>
<div id="heading">
    <h1>Страничка редактирование</h1>
</div>
<div id="main-part">
    <form action="/user/${user.id}/change" method="post"  modelAttribute="changeUser">
        <input type="text" name="login">
        <input type="submit">
    </form>
</div>
</body>

</html>