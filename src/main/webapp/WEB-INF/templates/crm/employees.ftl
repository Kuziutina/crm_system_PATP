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
    <h1>Работники</h1>
</div>
<div id="main-part">

    <#if error??>
        <p>${error}</p>
    </#if>

    <table>
        <tr>
            <th>Фамилия</th>
            <th>Имя</th>
            <th>Отчество</th>
            <th>Зарплата</th>
            <th>Стаж</th>
            <th>Маршрут</th>
            <th>Должность</th>
            <th></th>
            <th></th>
        </tr>
        <#list employees as employee>
        <tr>
            <th>${employee.lastName}</th>
            <th>${employee.name}</th>
            <th>${employee.fatherName}</th>
            <th>${employee.salary}</th>
            <th>${employee.experience}</th>
            <th><#if employee.route??>
                ${employee.route.name}
            </#if></th>
            <th>${employee.position.name}</th>
            <th><a href="/crm/employees/${employee.id}/change" >Изменить</a> </th>
            <th><a href="/crm/employees/${employee.id}/delete">Удалить</a> </th>
        </tr>
        </#list>
        <tr>
            <form name="add_emp" action="/crm/employees/add" method="post" modelAttribute="addEmployee">
                <th><input type="text" name="lastName" placeholder="lastName"></th>
                <th><input type="text" name="name" placeholder="name"></th>
                <th><input type="text" name="fatherName" placeholder="fatherName"></th>
                <th><input type="text" name="salary" placeholder="salary"></th>
                <th><input type="text" name="experience" placeholder="experience"></th>
                <th><input type="text" name="routeName" placeholder="route"></th>
                <th><input type="text" name="positionName" placeholder="position"></th>
                <th><input type="submit"></th>
            </form>
        </tr>
    </table>
</div>


</body>

</html>