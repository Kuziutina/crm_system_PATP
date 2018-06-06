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
    <h1>Driver Licenses</h1>
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
        <tr>
            <form name="change_driver_licenses" action="/crm/driver_licenses/${id}/update" method="post" modelAttribute="changeDriverLicenses">
                <#if lastDriverLicense?? >
                    <th><input type="text" name="lastName" placeholder="last name" value="${lastDriverLicense.lastName}"></th>
                    <th><input type="text" name="name" placeholder="name" value="${lastDriverLicense.name}"></th>
                    <th><input type="text" name="fatherName" placeholder="father name" value="${lastDriverLicense.fatherName}"></th>
                    <th><input type="text" name="category" placeholder="category" value="${lastDriverLicense.category}"></th>
                    <th><input type="text" name="info" placeholder="info" value="${lastDriverLicense.info}"></th>
                    <th><input type="date" name="dateApplication" placeholder="date application" value="${lastDriverLicense.dateApplicationString}"></th>
                    <th><input type="submit"></th>
                <#else>
                    <th><input type="text" name="lastName" placeholder="last name" value="${driverLicense.employee.lastName}"></th>
                    <th><input type="text" name="name" placeholder="name" value="${driverLicense.employee.name}"></th>
                    <th><input type="text" name="fatherName" placeholder="father name" value="${driverLicense.employee.fatherName}"></th>
                    <th><input type="text" name="category" placeholder="category" value="${driverLicense.category}"></th>
                    <th><input type="text" name="info" placeholder="info" value="${driverLicense.info}"></th>
                    <th><input type="date" name="dateApplication" placeholder="date application" value="${driverLicense.dateApplicationString}"></th>
                    <th><input type="submit"></th>
                </#if>
            </form>
        </tr>
    </table>
</div>


</body>

</html>


