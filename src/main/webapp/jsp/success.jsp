<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link href="../static/css/bootstrap.min.css" rel="stylesheet">
    <link href="../static/css/bootstrap.css" rel="stylesheet">
    <script src="../static/js/jquery-3.3.1.min.js"></script>
    <script src="../static/js/bootstrap.min.js"></script>
    <link rel="icon" type="image/x-icon" href="../static/images/lgo.png"/>
    <link rel="stylesheet" type="text/css" href="../static/css/success.css">
    <script src="../static/layui/layui.js" charset="utf-8"></script>
    <link rel="stylesheet" href="../static/layui/css/layui.css" media="all">
    <title>支付成功</title>
</head>
<body>
<div style="margin-top: 90px;"></div>

<!-- 主体 -->
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
        <div class="step last done">
            <span class="step-num">4</span>
            <div class="bar"></div>
            <span class="step-next">影院取票观影</span>
        </div>
    </div>
    <div class="main-paystatus">
        <img src="../static/images/success.jpg"/>
        <p class="statustext">已完成支付</p>
    </div>
    <div class="right">
        <p class="warning">
            该页面关闭后可以在<span class="attention">个人订单</span>页面查看订单信息
        </p>
        <div>
            <div class="pay-btn" onclick="returnCenter()">返回个人订单</div>
        </div>
    </div>
</div>
<script>
    var clientHeight = document.documentElement.clientHeight;

    window.onload = function(){
    }

    function returnCenter(){
        window.location.href = "center.jsp"
    }

</script>

</body>
</html>