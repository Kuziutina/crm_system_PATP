<html>
<head>
    <meta charset="UTF-8" content="text/html">
<#--<link rel="stylesheet" href="/css/bootstrap.css">-->
<#--<link href="/css/magazine_site.css" rel="stylesheet">-->
<#--<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">-->
    <title>Cars</title>
<#--<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>-->
</head>
<body>
<div id="heading">
    <h1>Журналы</h1>
</div>
<div id="main-part">
    <form action="/">
        <input type="text" placeholder="number" name="number">
        <select>
            <option value="на ходу">На ходу</option>
            <option value="в ремонте">В ремонте</option>
        </select>
        <select>
            <#list drivers as item>
                <option>${item.name}</option>
            </#list>
            <option>водитель</option>
        </select>
        <select>
            <option>кондуктор</option>
        </select>
        <select>
            <option>маршрут</option>
        </select>
    </form>

  
    <a href="/cars/create"
</div>
</body>

</html>