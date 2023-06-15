<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title> 选电影</title>
    <link rel="icon" type="image/x-icon" href="../static/images/lgo.png"/>
    <link rel="stylesheet" href="../static/css/bootstrap.min.css">
    <link rel="stylesheet" href="../static/css/main.css">
    <link rel="stylesheet" href="../static/css/main2.css">
    <link rel="stylesheet" href="../static/css/bootstrap.css">
    <link rel="stylesheet" href="../static/css/bootstrap.css">
    <script src="../static/js/jquery-3.3.1.min.js"></script>
    <script src="../static/js/http_cdn.bootcss.com_jquery_3.3.1_jquery.js"></script>
    <script src="../static/js/bootstrap.min.js"></script>
    <script src="../static/layui/layui.js"></script>
</head>
<body>
<div style="margin-top: 90px;"></div>

<div class="main">

    <div class="main-inner main-page">
        <div class="movie-grid">

            <div class="panel-header">
                    <span class="panel-more">
                        <a href="#" class="textcolor_red" data-act="all-playingMovie-click">
                            <span>全部</span>
                        </a>
                        <span class="panel-arrow panel-arrow-red"></span>
                    </span>
                <span class="panel-title hot-title">
                    </span>
            </div>
            <div class="panel-content">
                <ul class="movie-list movie-hot">
                </ul>
            </div>
            <div class="panel">
                    <span class="panel-more">
                        <a href="#" class="textcolor_red" data-act="all-upcomingMovie-click">
                            <span>全部</span>
                        </a>
                        <span class="panel-arrow panel-arrow-blue"></span>
                    </span>
                <span class="panel-title on-title">
                    </span>
            </div>
            <div class="panel-content">
                <ul class="movie-list movie-on">
                </ul>
            </div>
        </div>

    </div>
</div>


<script>
    var clientHeight = document.documentElement.clientHeight;

    window.onload = function () {
        initHostMovie(); //初始化正在热映和即将上映电影
    }

    //初始化正在热映和即将上映电影
    function initHostMovie() {
        var MoiveLiHot = $(".movie-hot");
        var MoiveLiOn = $(".movie-on");
        var htmlHot, htmlOn;
        var ListLength;
        var notice, sale;
        var HotNum = $(".hot-title");
        var OnNum = $(".on-title");
        var TempName;

        $.ajax({
            type: 'post',
            url: "http://localhost:8080/findAllMovies",
            dataType: 'json',
            data: {},
            success: function (obj) {
                console.log(obj);
                HotNum.append("<span class=\"textcolor_red\">正在热映（" + obj.data.length + "部）</span>");
                if (obj.data.length < 8) {
                    ListLength = obj.data.length;
                } else {
                    ListLength = 8;
                }
                for (var i = 0; i < ListLength; i++) {
                    htmlHot =
                        "<li>" +
                        "<div class=\"movie-item\">" +
                        "<a href=\"javascript:void(0)\" target=\"_blank\" data-act=\"playingMovie-click\" data-val=\"" + obj.data[i].movieId + "\">" +
                        "<div class=\"movie-poster\" style=\"cursor:default;\">" +
                        "<img id=\"moive_picture\" src=\"" + obj.data[i].moviePicture + "\">" +
                        "<div class=\"movie-overlay movie-overlay-bg\">" +
                        "<div class=\"movie-info\">" +
                        "<div class=\"movie-score\"><i id=\"moive_score\" class=\"integer\">" + obj.data[i].movieScore + "</i></div>" +
                        "<div class=\"movie-title movie-title-padding\" title=\"" + obj.data[i].movieCnName + "\">" + obj.data[i].movieCnName + "</div>" +
                        "</div>" +
                        "</div>" +
                        "</div>" +
                        "</a>" +
                        "<div class=\"movie-detail movie-detail-strong movie-sale\">" +
                        "<a href=\"../jsp/yinyuan.jsp?movieId=" + obj.data[i].movieId + "\" class=\"active\" target=\"_blank\" data-act=\"salePlayingMovie-click\" data-val=\"{movieid:42964}\">购 票</a>" +
                        "</div>" +
                        "</div>" +
                        "</li>";
                    MoiveLiHot.append(htmlHot);
                }


                OnNum.append("<span class=\"textcolor_blue\">即将上映（" + obj.data1.length + "部）</span>");
                if (obj.data1.length < 8) {
                    ListLength = obj.data1.length;
                } else {
                    ListLength = 8;
                }
                for (var i = 0; i < ListLength; i++) {
                    var random = Math.floor(Math.random(1, 10) * 50000);
                    htmlOn =
                        "<li>" +
                        "<div class=\"movie-item\">" +
                        "<a href=\"javascript:void(0)\" target=\"_blank\" data-act=\"playingMovie-click\" data-val=\"" + obj.data1[i].movieId + "\">" +
                        "<div class=\"movie-poster\" style=\"cursor:default;\">" +
                        "<img id=\"moive_picture\" src=\"" + obj.data1[i].moviePicture + "\">" +
                        "<div class=\"movie-overlay movie-overlay-bg\">" +
                        "<div class=\"movie-info\">" +
                        "<div class=\"movie-score\"><i id=\"moive_score\" class=\"integer\">" + obj.data1[i].movieScore + "</i></div>" +
                        "<div class=\"movie-title movie-title-padding\" title=\"" + obj.data1[i].movieCnName + "\">" + obj.data1[i].movieCnName + "</div>" +
                        "</div>" +
                        "</div>" +
                        "</div>" +
                        "</a>" +
                        "<div class=\"movie-detail movie-wish\"><span class=\"stonefont\">" + random + "</span>人想看</div>" +
                        "<div class=\"movie-detail movie-detail-strong movie-presale\">" +
                        "<a id=\"movie-notice\" class=\"movie-presale-sep movie-notice\">预告片</a>" +
                        "<a id=\"movie-sale\" class=\"movie-presale-sep movie-sale\">预 售</a>" +
                        "</div>" +
                        "</div>" +
                        "</li>";
                    MoiveLiOn.append(htmlOn);
                }
                notice = $(".movie-on").find(".movie-notice");
                sale = $(".movie-on").find(".movie-sale");
                for (var i = 0; i < ListLength; i++) {
                    notice[i].index = i;
                    notice[i].onclick = function () {
                        layer.alert('‘' + obj.data1[this.index].movieCnName + '’电影暂未有预告片！', {
                            icon: 0,
                            offset: clientHeight / 7
                        });
                    }
                    sale[i].index = i;
                    sale[i].onclick = function () {
                        layer.alert('预售‘' + obj.data1[this.index].movieCnName + '’电影成功！', {
                            icon: 0,
                            offset: clientHeight / 7
                        });
                    }
                }
            }
        });
    }

    //初始化总体票房


</script>

</body>
</html>