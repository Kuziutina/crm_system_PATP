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
    <h1>Журналы</h1>
</div>
<div id="main-part">

    <table>
        <#list items as item>
            <tr>
                <th>${item.number}</th>
                <th>${item.route.name}</th>
            </tr>
        </#list>
    </table>
    <a href="/cars/create"
</div>
</body>

</html>