<#include "base.ftl">

<html>
<head>
    <@head/>
    <link rel="stylesheet" href="/stat/css/main.css">

</head>
<body class="container">
<@header/>
<@menu/>
<div id="heading" class="col-md-10 col-md-offset-2">
    <h1>Оставить отзыв</h1>
</div>
<div id="main-part" class="col-md-8 col-md-offset-2">
    <div id="result">

    </div>
    <#--<form action="/feedback/add" method="post">-->
        <div class="form-label-group validation">
            <label for="name">Ваше имя или логин</label></br>
            <#if user??>
                <input class="form-control" id="name" required type="text" value="${user.login}">
            <#else>
                <input class="form-control" id="name" required type="text" >
            </#if>
            <span class="validity"></span>
        </div>
        <div class="form-label-group validation">
            <label for="theme">Заголовок (также может быть указан номер маршрута)</label></br>
            <input class="form-control" id="theme" required type="text">
            <span class="validity"></span>
        </div>
        <div class="form-label-group validation">
            <label for="body">Ваш отзыв</label></br>
            <textarea class="form-control" required id="body"></textarea>
            <span class="validity"></span>
        </div>



        <button class="btn btn-primary" onclick="send_feedback()" type="button">Send</button>
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
        feedback["userId"] = <#if user??>${user.id}<#else> 0 </#if>;
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







</html>