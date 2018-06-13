<#include "../base.ftl">
<html>
<head>
    <#--<meta charset="UTF-8" content="text/html">-->
    <head>
        <@head/>
    </head>
    <title>List</title>
<#--<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>-->
</head>
<body>
<@header/>
<@menu/>
<div class="container" id="heading">
    <div class="row">
        <h1 class="title col-md-12">Маршруты</h1>
    </div>
</div>
<div id="main-part" class="container">
    <div class="col-md-12">

        <input id="routes-station-filter" type="text" placeholder="search" onchange="doFilter()">
    </div>
    <#if error??>
        <p>${error}</p>
    </#if>

    <p class="col-md-1">Маршрут</p>
    <p class="col-md-1">Тип</p>
    <p class="col-md-1 col-md-offset-2">Остановка</p>

    <div id="routes-list">
        <#list routes as route>
            <div class="col-md-12 route-item">
                <p class="col-md-1">${route.name}</p>
                <p class="col-md-1">${route.type.name}</p>
                <div class="col-md-1 ref"><a href="/crm/routes/${route.id}/change">update</a> </div>
                <div class="col-md-1 ref"><a href="/crm/routes/${route.id}/delete">delete</a> </div>
            </div>

            <#list route.stations as station>
                <div class="col-md-12 station-item">
                    <p class="col-md-1 col-md-offset-4">${station.name}</p>
                    <div class="col-md-1 ref"><a href="/crm/routes/station/${station.id}/change">change</a> </div>
                    <div class="col-md-1 ref"><a href="/crm/routes/${route.id}/station/${station.id}/delete">delete</a> </div>
                </div>
            </#list>
            <div class="col-md-12 form-station">
                <form action="/crm/routes/${route.id}/station/add" method="post" modelAttribute="addStation">
                    <div class="col-md-1 col-md-offset-4"><input class="col-md-12" type="text" name="name"></div>
                    <div class="col-md-1"><input type="submit" value="Добавить"></div>
                </form>
            </div>
        </#list>
        <div class="col-md-12 form-route">
            <form action="/crm/routes/add" method="post" modelAttribute="addRoute">
                <div class="col-md-1"><input class="col-md-12" type="text" name="name"></div>
                <div class="col-md-1"><input class="col-md-12" type="text" name="type"></div>
                <div class="col-md-1"><input type="submit" value="Добавить"></div>
            </form>
        </div>
    </div>

</div>
<script>

    function doFilter() {
        var name = $('#routes-station-filter').val();
        console.log('kkkk');
        if (name.length === 0) {
            name = "all-stations";
        }
        $.ajax({
            url: '/crm/routes/station/filter/' + name,
            type: 'GET',
            dataType: 'json',
            success: function (data) {
                fillRoutes(data);
            },
            error: function (jqXHR, exception) {

                if (jqXHR.status === 0) {
                    alert('Not connect.\n Verify Network.');
                } else if (jqXHR.status == 404) {
                    alert('Requested page not found. [404]');
                } else if (jqXHR.status == 500) {
                    alert('Internal Server Error [500].');
                } else if (exception === 'parsererror') {
                    alert('Requested JSON parse failed.');
                } else if (exception === 'timeout') {
                    alert('Time out error.');
                } else if (exception === 'abort') {
                    alert('Ajax request aborted.');
                } else {
                    alert('Uncaught Error.\n' + jqXHR.responseText);
                }
            }
        });
    }

    function fillRoutes(data) {
        var routes = data;
        $('#routes-list').html('');
        for(var i = 0; i < routes.length; i++) {
            var route = routes[i];
            $('#routes-list').append(
'                <p class="col-md-1">'+ route.name + '</p>' +
'                <p class="col-md-1">' + route.type + '</p>' +
'                <p class="col-md-1"><a href="/crm/routes/' + route.id + '/change">update</a> </p>' +
'                <p class="col-md-1"><a href="/crm/routes/' + route.id + '/delete">delete</a> </p>' +
                    '<p class="col-md-6"></p> ' +
                    '</br>');
            var stations = route.stations;
            for (var j = 0; j < stations.length; j++) {
                $('#routes-list').append(
                '                <p class="col-md-1 col-md-offset-4">' + stations[j].name + '</p>' +
                '                <p class="col-md-1"><a href="/crm/routes/station/' + stations[j].id + '/change">change</a> </p>' +
                '                <p class="col-md-1"><a href="/crm/routes/' + route.id + '/station/' + stations[j].id + '/delete">delete</a> </p>' +
                        '<p class="col-md-3"></p> ' +
                '            </br>');
            }
            $('#routes-list').append('<form class="col-md-12" action="/crm/routes/' + route.id + '/station/add" method="post" modelAttribute="addStation">' +
                    '            <div class="col-md-1 col-md-offset-4"><input class="col-md-12" type="text" name="name"></div>' +
                    '            <div class="col-md-1"><input type="submit" value="Добавить"></div>' +
                    '<p class="col-md-4">  </p>' +
                    '        </form>' +
                    '</br>');

        }
        $('#routes-list').append('<form class="col-md-12" action="/crm/routes/add" method="post" modelAttribute="addRoute">' +
        '                <div class="col-md-1"><input class="col-md-12" type="text" name="name"></div>' +
        '                <div class="col-md-1"><input class="col-md-12" type="text" name="type"></div>' +
        '                <div class="col-md-1"><input class="col-md-12" type="submit" value="Добавить"></div>' +
        '                <p class="col-md-7 col-md"></p> '+
        '        </form>' +
        '</br>');
    }
</script>


</body>

</html>