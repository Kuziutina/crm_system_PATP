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

        <tr>
            <#if lastEmployee??>
            <form name="change_emp" action="/crm/employees/${id}/update" method="post" modelAttribute="changeEmployee">
                <th><input type="text" name="lastName" placeholder="lastName" value="${lastEmployee.lastName!""}"></th>
                <th><input type="text" name="name" placeholder="name" value="${lastEmployee.name!""}"></th>
                <th><input type="text" name="fatherName" placeholder="fatherName" value="${lastEmployee.fatherName!""}"></th>
                <th><input type="text" name="salary" placeholder="salary" value="${lastEmployee.salary!""}"></th>
                <th><input type="text" name="experience" placeholder="experience" value="${lastEmployee.experience!""}"></th>
                <th><input type="text" name="routeName" placeholder="route" value="${lastEmployee.routeName!""}"></th>
                <th><input type="text" name="positionName" placeholder="position" value="${lastEmployee.positionName!""}"></th>
                <th><input type="submit"></th>
            </form>
            <#else >
            <form name="change_emp" action="/crm/employees/${employee.id}/update" method="post" modelAttribute="changeEmployee">
                <th><input type="text" name="lastName" placeholder="lastName" value="${employee.lastName}"></th>
                <th><input type="text" name="name" placeholder="name" value="${employee.name}"></th>
                <th><input type="text" name="fatherName" placeholder="fatherName" value="${employee.fatherName!""}"></th>
                <th><input type="text" name="salary" placeholder="salary" value="${employee.salary}"></th>
                <th><input type="text" name="experience" placeholder="experience" value="${employee.experience}"></th>

                <th><input type="text" name="routeName" placeholder="route" value="<#if employee.route??>${employee.route.name}</#if>"</th>
                <th><input type="text" name="positionName" placeholder="position" value="${employee.position.name}"></th>
                <th><input type="submit"></th>
            </form>
            </#if>
        </tr>
    </table>
</div>


</body>

</html>