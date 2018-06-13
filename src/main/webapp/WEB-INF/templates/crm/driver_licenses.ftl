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
    <h1>Водительские права</h1>
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
            <th>Категория</th>
            <th>Информация</th>
            <th>Дата выдачи</th>
            <th></th>
            <th></th>
        </tr>
        <#list driver_licenses as driver_license>
        <tr>
            <th>${driver_license.employee.lastName}</th>
            <th>${driver_license.employee.name}</th>
            <th>${driver_license.employee.fatherName}</th>
            <th>${driver_license.category}</th>
            <th>${driver_license.info}</th>
            <th>${driver_license.dateApplicationString}</th>
            <th><a href="/crm/driver_licenses/${driver_license.id}/change">Изменить</a> </th>
            <th><a href="/crm/driver_licenses/${driver_license.id}/delete">Удалить</a> </th>
        </tr>
        </#list>
        <tr>
            <form name="add_driver_licenses" action="/crm/driver_licenses/add" method="post" modelAttribute="addDriverLicenses">
                <#if lastDriverLicense?? >
                    <th><input type="text" name="lastName" placeholder="last name" value="${lastDriverLicense.lastName}"></th>
                    <th><input type="text" name="name" placeholder="name" value="${lastDriverLicense.name}"></th>
                    <th><input type="text" name="fatherName" placeholder="father name" value="${lastDriverLicense.fatherName}"></th>
                    <th><input type="text" name="category" placeholder="category" value="${lastDriverLicense.category}"></th>
                    <th><input type="text" name="info" placeholder="info" value="${lastDriverLicense.info}"></th>
                    <th><input type="date" name="dateApplication" placeholder="date application"></th>
                    <th><input type="submit"></th>
                <#else>
                    <th><input type="text" name="lastName" placeholder="last name"></th>
                    <th><input type="text" name="name" placeholder="name"></th>
                    <th><input type="text" name="fatherName" placeholder="father name"></th>
                    <th><input type="text" name="category" placeholder="category"></th>
                    <th><input type="text" name="info" placeholder="info"></th>
                    <th><input type="date" name="dateApplication" placeholder="date application"></th>
                    <th><input type="submit"></th>
                </#if>
            </form>
        </tr>
    </table>
</div>


</body>

</html>


