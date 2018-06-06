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
    <h1>Employee</h1>
</div>
<div id="main-part">

    <#if error??>
        <p>${error}</p>
    </#if>
    <a href="/crm/routes">Back</a>

    <table>
        <tr>
            <th>name</th>
            <th></th>
        </tr>

        <tr>
            <#if lastStation??>
                <form name="change_station" action="/crm/routes/station/${id}/update" method="post" modelAttribute="changeStation">
                    <th><input type="text" name="name" placeholder="name" value="${lastStation.name}"></th>
                    <th><input type="submit" value="ok"></th>
                </form>
            <#else >
                <form name="change_station" action="/crm/routes/station/${id}/update" method="post" modelAttribute="changeStation">
                    <th><input type="text" name="name" placeholder="name" value="${station.name}"></th>
                    <th><input type="submit" value="ok"></th>
                </form>
            </#if>
        </tr>
    </table>
</div>


</body>

</html>