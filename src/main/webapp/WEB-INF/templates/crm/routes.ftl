<html>
<head>
    <#--<meta charset="UTF-8" content="text/html">-->
    <head>
        <link rel="stylesheet" type="text/css" href="/stat/bootstrap/bootstrap-3.3.2-dist/css/bootstrap.min.css"/>
        <script src="/stat/js/jquery.js"></script>
        <script src="/stat/bootstrap/bootstrap-3.3.2-dist/js/bootstrap.min.js"></script>
    </head>
    <title>List</title>
<#--<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>-->
</head>
<body>
<div id="heading">
    <h1>Car</h1>
</div>
<div id="main-part">

    <input id="routes-station-filter" type="text" placeholder="search" onchange="doFilter()">
    <#if error??>
        <p>${error}</p>
    </#if>

    <table>
        <tr>
            <th>Маршрут</th>
            <th>Тип</th>
            <th></th>
            <th></th>
            <th>Остановка</th>
            <th></th>
            <th></th>
        </tr>
    </table>
        <div id="routes-list">
            <table>
        <#list routes as route>
            <tr>
                <th>${route.name}</th>
                <th>${route.type.name}</th>
                <th><a href="/crm/routes/${route.id}/change">update</a> </th>
                <th><a href="/crm/routes/${route.id}/delete">delete</a> </th>
                <th></th>
                <th></th>
                <th></th>
            </tr>
            <#list route.stations as station>
            <tr>
                <th></th>
                <th></th>
                <th></th>
                <th></th>
                <th>${station.name}</th>
                <th><a href="/crm/routes/station/${station.id}/change">change</a> </th>
                <th><a href="/crm/routes/${route.id}/station/${station.id}/delete">delete</a> </th>
            </tr>
            </#list>
        <form action="/crm/routes/${route.id}/station/add" method="post" modelAttribute="addStation">
        <tr>
            <th></th>
            <th></th>
            <th></th>
            <th></th>
            <th><input type="text" name="name"></th>
            <th><input type="submit"></th>
            <th></th>
        </tr>
        </form>
        </#list>
        <form action="/crm/routes/add" method="post" modelAttribute="addRoute">
            <tr>
                <th><input type="text" name="name"></th>
                <th><input type="text" name="type"></th>
                <th><input type="submit" value="ok"></th>
                <th></th>
                <th></th>
                <th></th>
                <th></th>
            </tr>
        </form>
            </table>
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
            $('#routes-list').append('<tr>' +
'                <th>'+ route.name + '</th>' +
'                <th>' + route.type + '</th>' +
'                <th><a href="/crm/routes/' + route.id + '/change">update</a> </th>' +
'                <th><a href="/crm/routes/' + route.id + '/delete">delete</a> </th>' +
'                <th></th>' +
'                <th></th>' +
'                <th></th>' +
'            </tr>');
            var stations = route.stations;
            for (var j = 0; j < stations.length; j++) {
                $('#routes-list').append('<tr>' +
                '                <th></th>' +
                '                <th></th>' +
                '                <th></th>' +
                '                <th></th>' +
                '                <th>' + stations[j].name + '</th>' +
                '                <th><a href="/crm/routes/station/' + stations[j].id + '/change">change</a> </th>' +
                '                <th><a href="/crm/routes/' + route.id + '/station/' + stations[j].id + '/delete">delete</a> </th>' +
                '            </tr>\n');
            }
            $('#routes-list').append('<form action="/crm/routes/' + route.id + '/station/add" method="post" modelAttribute="addStation">' +
                    '        <tr>' +
                    '            <th></th>' +
                    '            <th></th>' +
                    '            <th></th>' +
                    '            <th></th>' +
                    '            <th><input type="text" name="name"></th>' +
                    '            <th><input type="submit"></th>' +
                    '            <th></th>' +
                    '        </tr>' +
                    '        </form>' +
                    '        <form action="/crm/routes/add" method="post" modelAttribute="addRoute">' +
                    '            <tr>' +
                    '                <th><input type="text" name="name"></th>' +
                    '                <th><input type="text" name="type"></th>' +
                    '                <th><input type="submit" value="ok"></th>' +
                    '                <th></th>' +
                    '                <th></th>' +
                    '                <th></th>' +
                    '                <th></th>' +
                    '            </tr>' +
                    '        </form>');

        }
    }
</script>


</body>

</html>