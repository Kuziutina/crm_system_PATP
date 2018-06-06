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
            <th>type</th>
            <th></th>
            <th></th>
        </tr>

        <tr>
            <#if lastRoute??>
                <form name="change_route" action="/crm/routes/${id}/update" method="post" modelAttribute="changeRoute">
                    <th><input type="text" name="name" placeholder="name" value="${lastRoute.name}"></th>
                    <th><input type="text" name="type" placeholder="type" value="${lastRoute.type}"></th>
                    <th><input type="submit"></th>
                </form>
            <#else >
                <form name="change_route" action="/crm/routes/${id}/update" method="post" modelAttribute="changeRoute">
                    <th><input type="text" name="name" placeholder="name" value="${route.name}"></th>
                    <th><input type="text" name="type" placeholder="type" value="${route.type.name}"></th>
                    <th><input type="submit"></th>
                </form>
            </#if>
        </tr>
    </table>
</div>


</body>

</html>