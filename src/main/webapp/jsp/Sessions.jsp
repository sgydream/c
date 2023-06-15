
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %><!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>选场次</title>
    <link rel="icon" type="image/x-icon" href="../static/images/lgo.png"/>
    <link rel="stylesheet" href="../static/css/bootstrap.min.css">
    <link rel="stylesheet" href="../static/css/bootstrap.css">
    <link rel="stylesheet" href="../static/css/main.css">
    <link rel="stylesheet" href="../static/css/buyTickets.css">
    <link rel="stylesheet" href="../static/css/movieDetail.css">
    <link rel="stylesheet" href="../static/css/selectSeat.css">
    <link rel="stylesheet" href="../static/layui/css/layui.css">
    <script src="../static/js/jquery-3.3.1.min.js"></script>
    <script src="../static/js/bootstrap.min.js"></script>
    <script src="../static/layui/layui.js"></script>
</head>
<body>
<div style="margin-top: 80px;"></div>
<div class="banner2">
    <div class="wrapper clearfix">
        <div class="cinema-left">
            <div class="avatar-shadow">

            </div>
        </div>

        <div class="cinema-main clearfix">
            <div class="cinema-brief-container">
                <div class="telphone">电话：0335-2661222</div>
                <div class="features-group">
                    <div class="group-title">影院服务</div>

                    <div class="feature">
                        <span class="tag ">3D眼镜</span>
                        <p class="desc text-ellipsis" title="免押金">免押金</p>
                    </div>
                    <div class="feature">
                        <span class="tag ">可停车</span>
                        <p class="desc text-ellipsis" title="可停车">停车场可凭电影票在影城票台领取3小时内免停权益</p>
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>
<div style="margin-top: 50px;"></div>
<div class="main">
    <div class="main-inner main-bodyz">
        <div class="movie-list-container" data-cinemaid="8330">
            <!-- 电影海报 -->
            <div class="movie-list">
                <!--<span class="pointer" style="left: 395px;"></span>-->
            </div>
            <span class="scroll-prev scroll-btn"></span>
            <span class="scroll-next scroll-btn"></span>
        </div>

        <div class="show-list active" data-index="0">
            <!-- 电影信息 -->
            <div class="movie-info">
            </div>
            <!-- 观影时间 -->
            <div class="show-date">
                <span>观影时间 :</span>
            </div>
            <!-- 场次列表 -->
            <div class="plist-container active">
                <table class="plist">
                    <thead>
                    <tr>
                        <th>放映时间</th>
                        <th>语言版本</th>
                        <th>放映厅</th>
                        <th>售价（元）</th>
                        <th>选座购票</th>
                    </tr>
                    </thead>
                    <tbody>

                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<div style="margin-top: 80px;"></div>

<script>
    window.onload = function(){
        initBanner(); //初始化巨幕
    }

    function initBanner(){
        movieId = getUrlParams('movieId');
        cinemaId = getUrlParams('cinemaId');
        var cinemaBriefContainer = $(".cinema-brief-container");
        var avatarShadow = $(".avatar-shadow");
        $.ajax({
            type:'post',
            url: "http://localhost:8080/findScheduleByCinemaAndMovie",
            dataType:'json',
            data: {
                movieId: movieId,
                cinemaId: cinemaId
            },
            success:function (obj) {
                console.log(obj);
                cinemaBriefContainer.prepend(
                    "<h3 class=\"name text-ellipsis\">" + obj.data[0].scheduleHall.hallCinema.cinemaName + "</h3>" +
                    "<div class=\"address text-ellipsis\">" + obj.data[0].scheduleHall.hallCinema.cinemaAddress + "</div>"
                );
                avatarShadow.append(
                    "<img class=\"avatar\" src=\"../static/images/cinemas/" + obj.data[0].scheduleHall.hallCinema.cinemaName + ".jpg\">" +
                    "<div class=\"avatar-num\">查看全部21张图片</div>"
                );
                initMovie(obj); //初始化电影信息
                initSchedule(obj); //初始化场次信息
            }

        });
    }

    function initMovie(obj){
        // console.log(obj);
        var Data = new Date();
        var Month = Data.getMonth() + 1;
        var Day = Data.getDate();
        var movieList = $(".movie-list");
        var movieInfo = $(".movie-info");
        var showDate = $(".show-date");
        for(var i=0;i<5;i++){
            movieList.append(
                "<div class=\"movie active\" data-index=\"0\">" +
                "<img src=\"../static/images/stills/" + obj.data[0].scheduleMovies.movieCnName + "/" + (i+1) + ".jpg\" alt=\"\">" +
                "</div>"
            );
            console.log(obj.data[0].scheduleMovies.movieCnName)
        }

        movieInfo.append(
            "<div>" +
            "<h3 class=\"movie-name\">"+ obj.data[0].scheduleMovies.movieCnName +"</h3>" +
            "<span class=\"score sc\">"+ obj.data[0].scheduleMovies.movieScore +"</span>" +
            "</div>" +
            "<div class=\"movie-desc\">" +
            "<div>" +
            "<span class=\"key\">时长 : </span>" +
            "<span class=\"value\">"+ obj.data[0].scheduleMovies.movieHour +"</span>" +
            "</div>" +
            "<div>" +
            "<span class=\"key\">类型 :</span>" +
            "<span class=\"value\">"+ obj.data[0].scheduleMovies.movieType +"</span>" +
            "</div>" +
            "<div>" +
            "<span class=\"key\">导演 :</span>" +
            "<span class=\"value\">"+ obj.data[0].scheduleMovies.movieDirector+"</span>" +
            "</div>" +
            "</div>"
        )
        showDate.append("<span class=\"date-item active\" data-index=\"${status.index}\">今天" + Month + "月" + (Day+i) + "</span>  ");
    }

    function initSchedule(obj){
        var plist = $(".plist").find("tbody");

        for(var i = 0;i < obj.data.length;i++){
            plist.append(
                "<tr class=\"\">" +
                "<td> <span class=\"begin-time\">"+ obj.data[i].scheduleStartTime +"</span> <br> </td>" +
                "<td> <span class=\"lang\">" + obj.data[i].scheduleMovies.movieCountry +"</span> </td>" +
                "<td> <span class=\"hall\">" + obj.data[i].scheduleHall.hallName + "</span> </td>" +
                "<td> <span class=\"sell-price\"> <span class=\"stonefont\">" + obj.data[i].schedulePrice + "</span> </span> </td>" +
                "<td> <a href=\"../jsp/location.jsp?scheduleId="+ obj.data[i].scheduleId +"\" class=\"buy-btn normal\">选座购票</a> </td>" +
                "</tr>"
            );
        }
    }

    //获取url参数
    function getUrlParams(name) { // 不传name返回所有值，否则返回对应值
        var url = window.location.search;
        if (url.indexOf('?') == 1) { return false; }
        url = url.substr(1);
        url = url.split('&');
        var name = name || '';
        var nameres;
        for(var i=0;i<url.length;i++) {
            var info = url[i].split('=');
            var obj = {};
            obj[info[0]] = decodeURI(info[1]);
            url[i] = obj;
        }
        if (name) {
            for(var i=0;i<url.length;i++) {
                for (const key in url[i]) {
                    if (key == name) {
                        nameres = url[i][key];
                    }
                }
            }
        } else {
            nameres = url;
        }
        return nameres;
    }

</script>

</body>
</html>