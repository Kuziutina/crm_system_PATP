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
    <h1>Машины</h1>
</div>
<div id="main-part">

    <#if error??>
        <p>${error}</p>
    </#if>

    <table>
        <tr>
            <th>Номер</th>
            <th>маршрут</th>
            <th>Статус</th>
            <th>Последняя проверка</th>
            <th></th>
            <th></th>
        </tr>
        <#list cars as car>
        <tr>
            <th>${car.number}</th>
            <th>${car.route.name}</th>
            <th>${car.carStatus.name}</th>
            <th>${car.getLastCheckUpString()}</th>
            <th><a href="/crm/cars/${car.id}/change">Изменить</a></th>
            <th><a href="/crm/cars/${car.id}/delete">Удалить</a> </th>
        </tr>
        </#list>
        <tr>
            <form name="add_car" action="/crm/cars/add" method="post" modelAttribute="addCar">
                <th><input type="text" name="number" placeholder="number"></th>
                <th><input type="text" name="routeName" placeholder="route"></th>
                <th><input type="text" name="carStatusName" placeholder="carStatus"></th>
                <th><input type="date" name="lastCheckUp" placeholder="last Check Up"></th>
                <th><input type="submit"></th>
            </form>
        </tr>
    </table>
</div>


</body>

</html>