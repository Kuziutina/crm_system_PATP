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
    <a href="/crm/cars">Back</a>

    <table>
        <tr>
            <th>Номер</th>
            <th>маршрут</th>
            <th>Статус</th>
            <th>Последняя проверка</th>
            <th></th>
            <th></th>
        </tr>

        <tr>
            <#if lastCar??>
                <form name="change_car" action="/crm/cars/${id}/update" method="post" modelAttribute="changeCar">
                    <th><input type="text" name="number" placeholder="number" value="${lastCar.number}"></th>
                    <th><input type="text" name="routeName" placeholder="route" value="${lastCar.routeName}"></th>
                    <th><input type="text" name="carStatusName" placeholder="carStatus" value="${lastCar.carStatusName}"></th>
                    <th><input type="date" name="lastCheckUp" placeholder="last Check Up" value="${lastCar.lastCheckUpString}"></th>
                    <th><input type="submit"></th>
                </form>
            <#else >
                <form name="change_car" action="/crm/cars/${id}/update" method="post" modelAttribute="changeCar">
                    <th><input type="text" name="number" placeholder="number" value="${car.number}"></th>
                    <th><input type="text" name="routeName" placeholder="route" value="${car.route.name}"></th>
                    <th><input type="text" name="carStatusName" placeholder="carStatus" value="${car.carStatus.name}"></th>
                    <th><input type="date" name="lastCheckUp" placeholder="last Check Up" value="${car.lastCheckUpString}"></th>
                    <th><input type="submit"></th>
                </form>
            </#if>
        </tr>
    </table>
</div>


</body>

</html>