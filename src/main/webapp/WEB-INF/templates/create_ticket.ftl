<#include "base.ftl"/>
<html>
<head>
    <@head/>
    <link rel="stylesheet" href="/stat/css/main.css">
</head>
<body class="container">

<@header/>
<@menu/>
<div id="main-part" class="col-md-8 col-md-offset-2">

    <div id="heading">
        <h1>Оставить заявку на заказной автобус</h1>
    </div>
    <form id="ticket-form" action="/feedback/add" method="post" class="form col-md-8">
        <div class="form-label-group">
            <label for="name">Фамилия Имя Отчество</label>
            <#if user??>
                <input id="name" type="text" name="name" class="form-control" required
                       placeholder="ФИО" value="${user.userData.lastName} ${user.userData.name} ${user.userData.fatherName}">
            <#else>
                 <input required id="name" type="text" name="name" class="form-control"
                        placeholder="ФИО" >
            </#if>
        </div>
        <div class="form-label-group">
            <label for="place">Куда вы хотите направиться?</label>
            <input type="text" name="theme" id="place" class="form-control"
                   placeholder="Место" required autofocus>
        </div>
        <div class="form-label-group">
            <label for="description">Подробности поездки</label>
            <textarea name="body" id="description" class="form-control"
                      placeholder="Подробности" autofocus></textarea>
        </div>

        <div class="form-label-group">
            <label for="date">Дата</label>
            <input type="date" name="date" id="date" class="form-control"
                   required autofocus>
        </div>
        <button class="btn-primary btn" onclick="send_feedback()" type="button">Отправить</button>
    </form>
    <div id="result"></div>
</div>
</body>

<script>
    var send_feedback = function () {
        console.log("i start send");
        var ticket = {};
        ticket["name"] = $("#name").val();
        ticket["theme"] = $("#place").val();
        ticket["body"] = $("#description").val();
        ticket["date"] = $("#date").val();
        ticket["userId"] =<#if user??> ${user.id}<#else >0 </#if>;
        // console.log(text + "  " + recipient);
        if (ticket["body"] != "" && ticket["name"] != "" ){
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/ticket/add",
                data: JSON.stringify(ticket),
                dataType: "json",
                success: function (result) {
                    console.log(result.errors.length);
                    console.log("i'm here");
                    if (result.errors.length != 0) {
                        //TODO add exception notification
                        console.log("suc")
                    }
                    else {
                        $("#ticket-form").hide();
                        console.log("no");
                        $("#result").append("<div class='result'>" +
                                "               <p>Ваша заявка была успешно отправлена, вы можете скачать экземпляр заявления ниже</p>" +
                                " <a href='/ticket/" + result.message + "/load' class=\"btn btn-default\" role=\"button\" download><span class=\"glyphicon glyphicon-download\"></span></a>" +
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