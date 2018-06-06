<html>
<head>
    <meta charset="UTF-8" content="text/html">
    <#--<link rel="stylesheet" href="/css/bootstrap.css">-->
    <#--<link href="/css/magazine_site.css" rel="stylesheet">-->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <#--<title>MagazineSite</title>-->
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
</head>
<body>
<div id="heading">
    <h1>Страничка feedback</h1>
</div>
<div id="main-part">
    <div id="result">

    </div>
    <#--<form action="/feedback/add" method="post">-->
        <#if user??>
            <input id="name" type="text" value="${user.login}">
        <#else>
            <input id="name" type="text" >
        </#if>
        <input id="theme" type="text">
        <textarea id="body"></textarea>

        <button onclick="send_feedback()" type="button">Send</button>
    <#--</form>-->
</div>
</body>

<script>
    var send_feedback = function () {
        console.log("i start send")
        var feedback = {};
        feedback["name"] = $("#name").val();
        feedback["theme"] = $("#theme").val();
        feedback["body"] = $("#body").val();
        // console.log(text + "  " + recipient);
        if (feedback["body"] != "" && feedback["name"] != "" ){
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/feedback/add",
                data: JSON.stringify(feedback),
                dataType: "json",
                success: function (result) {
                    console.log(result.errors.length);
                    // var has = result.has;
                    console.log("i'm here");
                    if (result.errors.length != 0) {
                        //TODO add exception notification
                        console.log("suc")
                    }
                    else {
                        console.log("no");
                        $("#result").append("<div class='result'>" +
                                "               <p>Ваш отзыв был успешно отправлен</p>" +
                                "             </div>")
                    }

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
            $("#theme").val("");
            $("#body").val("");
            console.log("end");
        }
        else {

            console.log("else");
        }

    }
</script>
<footer>
    <#--<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js"></script>-->
    <#--<script src="/js/bootstrap.js"></script>-->
    <#--<script src="/js/horizontal_tab.js"></script>-->
    <#--<script src="/js/module_wind.js"></script>-->
</footer>






</html>