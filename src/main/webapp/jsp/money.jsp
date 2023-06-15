
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %><!DOCTYPE html>
<html  lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="icon" type="image/x-icon" href="../static/images/lgo.png"/>
    <link rel="stylesheet" href="../static/css/bootstrap.min.css">
    <link rel="stylesheet" href="../static/css/bootstrap.css">
    <link rel="stylesheet" href="../static/css/pay.css">
    <link rel="stylesheet" href="../static/css/layui.css">
    <script href="../static/js/bootstrap.min.js"></script>
    <script src="../static/layui/layui.js"></script>
    <script src="../static/js/http_cdn.bootcss.com_jquery_3.3.1_jquery.js"></script>
    <title>支付</title>
</head>
<body>
<div style="height: 100px;width: 100px;"><a href="../jsp/login.jsp" style="background-size: 100px;
 background-color: red">登录</a></div>
<div style="margin-top: 90px;"></div>
<div class="container">
    <div class="order-progress-bar">
        <div class="step first done">
            <span class="step-num">1</span>
            <div class="bar"></div>
            <span class="step-next">选择影片场次</span>
        </div>
        <div class="step done">
            <span class="step-num">2</span>
            <div class="bar"></div>
            <span class="step-next">选择座位</span>
        </div>
        <div class="step done">
            <span class="step-num">3</span>
            <div class="bar"></div>
            <span class="step-next">付款</span>
        </div>
        <div class="step last">
            <span class="step-num">4</span>
            <div class="bar"></div>
            <span class="step-next">影院取票观影</span>
        </div>
    </div>

    <div class="count-down-wrapper">
        <div class="count-down">
            <p class="time-left">
                请在<span class="minute"></span>分钟<span class="second"></span>秒内完成支付
            </p>
            <p class="tip">超时订单会自动取消，如遇支付问题，请联系管理员</p>
        </div>
    </div>

    <p class="warning">
        请仔细核对场次信息，出票后将<span class="attention">无法退票和改签</span>
    </p>

    <table class="order-table">
        <thead>
        <tr>
            <th>影片</th>
            <th>时间</th>
            <th>影院</th>
            <th>座位</th>
        </tr>
        </thead>
        <tbody>
        </tbody>
    </table>

    <div class="right">
        <div class="price-wrapper">
            <span>实际支付：</span>
            <span class="price"></span>
        </div>
        <div>
            <div class="pay-btn">
                确认支付 </div>
        </div>
    </div>
</div>
<script>

    var clientHeight = document.documentElement.clientHeight;
    var json = localStorage.getItem("orders");
    json = eval('(' + json + ')');
    var scheduleId = json.scheduleId;
    var payBtn = $(".pay-btn");
    window.onload = function () {
        initPay(); //支付
        timeDown(); //计时器
    }

    //初始化支付页面
    function initPay() {
        var orderTable = $(".order-table").find("tbody");
        var TempLength = json.TicketSeat.split(",");
        console.log(TempLength);
        var price = json.price;
        $.ajax({
            type: 'post',
            url: "http://localhost:8080/findScheduleById",
            dataType: 'json',
            data: {
                scheduleId: scheduleId
            },
            success: function (obj) {
                orderTable.append(
                    "<tr>" +
                    "<td class=\"movie-name\">" + obj.data.scheduleMovies.movieCnName + "</td>" +
                    "<td class=\"showtime\">" + obj.data.scheduleStartTime + "</td>" +
                    "<td class=\"cinema-name\">" + obj.data.scheduleHall.hallCinema.cinemaName + "</td>" +
                    "<td>" +
                    "<span class=\"hall\">" + obj.data.scheduleHall.hallName + "</span>" +
                    "<div class=\"seats\">" +
                    "<div class=\"choiceseat\" style=\"display: block;\">" +
                    "</div>" +
                    "</div>" +
                    "</td>" +
                    "<tr>"
                );
                console.log(obj.data.scheduleMovies.movieCnName)
                console.log(obj.data.scheduleStartTime)
                console.log(obj.data.scheduleHall.hallCinema.cinemaName)
                console.log(obj.data.scheduleHall.hallName)
                var choiceSeat = $(".choiceseat");
                for (var i = 0; i < TempLength.length; i++) {
                    choiceSeat.append("<span class=\"border\" style=\"font-size: 15px;\">" + TempLength[i] + "</span>");
                }
            }
        });

        var choiceSeat = $(".choiceseat");
        for (var i = 0; i < TempLength.length; i++) {
            choiceSeat.append("<span class=\"border\" style=\"font-size: 15px;\">" + TempLength[i] + "</span>");
        }
        $(".price").html(price);
    }

    //计时器
    function timeDown() {
        var timeMinute = $(".minute");
        var timeSecond = $(".second");
        var minute;
        var second;
        timeMinute.text(localStorage.minute);
        timeSecond.text(localStorage.second);
        setInterval(function () {
            if (second == 0 && minute == 0) {
                window.alert("支付时间已过，订单失效！");
                localStorage.clear();
            }
            if ((localStorage.second == "NaN") || (localStorage.second == 0 && localStorage.minute == 0)) {
                localStorage.minute = 14;
                localStorage.second = 59;
            }
            second = localStorage.second;
            minute = localStorage.minute;
            if (second == 0) {
                minute--;
                second = 60;
            }
            second--;
            timeMinute.text(minute);
            timeSecond.text(second);
            localStorage.second = second;
            localStorage.minute = minute;
        }, 1000);
    }

    //购买
    payBtn[0].onclick = function () {
        var position = json.TicketSeat.split(",");
        var price = json.price;
        $.ajax({
            type: "post",
            url: "http://localhost:8080/buyTickets",
            data: {
                scheduleId: scheduleId,
                position: position,
                price: price,
            },
            dataType: "json",
            success: function (obj) {
                $.ajax({
                    type: "post",
                    url: "http://localhost:8080/alipay",
                    data: {
                        price:price
                    },
                    success: function () {

                    },
                    error: function (){
                        alert("network error!!!")
                    }

                })
                localStorage.removeItem("orders");
                if (obj.code == 0) {
                    layui.use(['layer'], function () {
                        var layer = layui.layer;
                        layer.alert('购票成功！', {icon: 0, offset: clientHeight / 5},
                            function () {
                                layer.closeAll();
                                document.location.href = "../jsp/success.jsp";
                            }
                        );
                    });
                } else {
                    layui.use(['layer'], function () {
                        var layer = layui.layer;
                        layer.alert(obj.msg, {icon: 0, offset: clientHeight / 5},
                            function () {
                                layer.closeAll();
                            }
                        );
                    });
                }
            },
            error: function () {
                alert("network error");
            }
        });
    }

    //获取url参数
    function getUrlParams(name) { // 不传name返回所有值，否则返回对应值
        var url = window.location.search;
        if (url.indexOf('?') == 1) {
            return false;
        }
        url = url.substr(1);
        url = url.split('&');
        var name = name || '';
        var nameres;
        // 获取全部参数及其值
        for (var i = 0; i < url.length; i++) {
            var info = url[i].split('=');
            var obj = {};
            obj[info[0]] = decodeURI(info[1]);
            url[i] = obj;
        }
        // 如果传入一个参数名称，就匹配其值
        if (name) {
            for (var i = 0; i < url.length; i++) {
                for (const key in url[i]) {
                    if (key == name) {
                        nameres = url[i][key];
                    }
                }
            }
        } else {
            nameres = url;
        }
        // 返回结果
        return nameres;
    }

</script>

</body>
</html>