<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>电影详情</title>
    <link rel="icon" type="image/x-icon" href="../static/images/lgo.png"/>
    <link rel="stylesheet" href="../static/css/bootstrap.min.css">
    <link rel="stylesheet" href="../static/css/bootstrap.css">
    <link rel="stylesheet" href="../static/css/main.css">
    <link rel="stylesheet" href="../static/css/buyTickets.css">
    <link rel="stylesheet" type="text/css" href="../static/css/movieDetail.css">
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
        <div class="celeInfo-left">
            <div class="avatar-shadow">
                <!-- 图片 -->
            </div>
        </div>

        <div class="celeInfo-right clearfix">
            <div class="movie-brief-container">
                <!-- 上 -->
            </div>
            <div class="action-buyBtn">
                <div class="action clearfix" data-val="{movieid:42964}">
                    <a class="wish " data-wish="false" onclick="wantSee()">
                        <div>
                            <i class="icon wish-icon"></i>
                            <span class="wish-msg" data-act="wish-click">想看</span>
                        </div>
                    </a>
                    <a class="score-btn " data-bid="b_rxxpcgwd" onclick="giveScore()">
                        <div>
                            <i class="icon score-btn-icon"></i>
                            <span class="score-btn-msg" data-act="comment-open-click">评分</span>
                        </div>
                    </a>
                </div>
            </div>

            <div class="movie-stats-container">
                <div class="movie-index">
                    <p class="movie-index-title">用户评分</p>
                    <div class="movie-index-content score normal-score">
                            <span class="index-left info-num ">
                                <!-- 评分 -->
                            </span>
                        <div class="index-right">
                            <div class="star-wrapper">
                                <div id="MovieScore"></div>
                            </div>
                            <span class="score-num">
                                    <!-- 评分数 -->
                                </span>
                        </div>
                    </div>
                </div>

                <div class="movie-index">
                    <p class="movie-index-title">累计票房</p>
                    <div class="movie-index-content box stonefont-num">
                        <!-- 票房数 -->
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>
<div style="margin-top: 50px;"></div>
<div class="main">
    <div class="main-inner main-detail">
        <div class="main-content">
            <div class="tab-container">
                <div class="tab-title-container clearfix">
                    <div class="tab-title active" data-act="tab-desc-click">介绍</div>
                </div>

                <div class="tab-content-container">
                    <div class="tab-desc tab-content active" data-val="{tabtype:'desc'}">
                        <!-- 剧情简介 -->
                        <div class="module introduction">
                            <div class="mod-title">
                                <h3>剧情简介</h3>
                            </div>
                        </div>
                        <!-- 演职人员 -->
                        <div class="module staringPeople">
                            <div class="mod-title">
                                <h3>演职人员</h3>
                            </div>
                            <div class="mod-content">
                                <div class="celebrity-container clearfix">
                                </div>
                            </div>
                        </div>
                        <!-- 评论 -->
                        <div class="module">
                            <div class="mod-title">
                                <h3>热门短评</h3>
                            </div>

                            <div class="mod-content">
                                <div class="comment-list-container" data-hot="10">
                                    <ul>
                                    </ul>
                                </div>
                                <a class="comment-entry" data-act="comment-no-content-click" onclick="writeComment()">写短评</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    var clientHeight = document.documentElement.clientHeight;
    var movieId;
    var ScoreHtml;

    window.onload = function () {
        initHtml(); //HTML
        initBanner(); //巨幕
        initActor(); //演职人员
        initComment(); //评论
    }

    //初始化HTML
    function initHtml() {
        ScoreHtml =
            "<div style=\"text-align:center; margin:30px 0;\">" +
            "<div id=\"GiveScore\"></div>" +
            "<p style=\"color:#888;\">点击星星进行评分</p>" +
            "</div>"
        ;
        UpdateCommentHtml =
            "<h3 class=\"commenttitle\">评论信息</h3>" +
            "<div class=\"layui-form-item\">" +
            "<label class=\"layui-form-label usernametext\">用户帐号</label>" +
            "<div class=\"layui-input-block username\">" +
            "<input id=\"user_name\" type=\"text\" name=\"title\" lay-verify=\"title\" disabled=\"disabled\" style=\"cursor:not-allowed;\" autocomplete=\"off\" placeholder=\"username\" class=\"layui-input\">" +
            "</div>" +
            "</div>" +
            "<div class=\"layui-form-item\">" +
            "<label class=\"layui-form-label commenttimetext\">评论时间</label>" +
            "<div class=\"layui-input-block commenttime\">" +
            "<input id=\"comment_time\" type=\"text\" name=\"title\" lay-verify=\"title\" disabled=\"disabled\" style=\"cursor:not-allowed;\" autocomplete=\"off\" placeholder=\"commenttime\" class=\"layui-input\">" +
            "</div>" +
            "</div>" +
            "<div class=\"layui-form-item\">" +
            "<label class=\"layui-form-label commentcontenttext\">评论内容</label>" +
            "<div class=\"layui-input-block commentcontent\">" +
            "<textarea id=\"comment_content\" style=\"height:120px;\" placeholder=\"请输入评论内容\" autocomplete=\"off\" class=\"layui-textarea\" name=\"desc\" class=\"layui-input\"></textarea>" +
            "</div>" +
            "</div>"
        ;
        WriteCommentHtml =
            "<h3 class=\"commenttitle\">评论信息</h3>" +
            "<div class=\"layui-form-item\">" +
            "<label class=\"layui-form-label commentcontenttext\">评论内容</label>" +
            "<div class=\"layui-input-block commentcontent\">" +
            "<textarea id=\"comment_content_write\" style=\"height:150px;\" placeholder=\"请输入评论内容\" autocomplete=\"off\" class=\"layui-textarea\" name=\"desc\" class=\"layui-input\"></textarea>" +
            "</div>" +
            "</div>"
        ;
    }

    //初始化巨幕
    function initBanner() {
        movieId = getUrlParams('movieId');
        var avatar = $(".avatar-shadow");
        var movieBriefContainer = $(".movie-brief-container");
        var infoNum = $(".info-num");
        var scoreNum = $(".score-num");
        var stonefontNum = $(".stonefont-num");
        var actionBuyBtn = $(".action-buyBtn");
        var Introduction = $(".introduction");
        var StonefontTemp;

        $.ajax({
            type: 'post',
            url: "http://localhost:8080/findMovieById",
            dataType: 'json',
            data: {
                movieId: movieId
            },
            success: function (obj) {
                StonefontTemp = obj.data.movieBoxOffice;
                StonefontTemp += "万";
                avatar.append("<img class=\"avatar\" src=\"" + obj.data.moviePicture + "\" alt=\"\">");
                movieBriefContainer.append(
                    "<h3 class=\"name\">" + obj.data.movieCnName + "</h3>" +
                    "<div class=\"ename ellipsis\">" + obj.data.movieEgName + "</div>" +
                    "<ul>" +
                    "<li class=\"ellipsis\">" + obj.data.movieType + "</li>" +
                    "<li class=\"ellipsis\">" + obj.data.movieHour + " / " + obj.data.movieCountry + "</li>" +
                    "<li class=\"ellipsis\">" + obj.data.movieReleaseDate + "</li>" +
                    "<ul>");
                infoNum.append("<span class=\"stonefont\">" + obj.data.movieScore + "</span>");
                scoreNum.append("<span class=\"stonefont\">" + obj.data.movieCommentCount + "</span>人评分");
                stonefontNum.append("<span class=\"stonefont\">" + StonefontTemp + "</span>");
                actionBuyBtn.append("<div class=\"moviedetail\"></div>");
                Introduction.append(
                    "<div class=\"mod-content\">" +
                    "<span class=\"dra\" style=\"font-size:16px;\">" + obj.data.movieDetail + "</span>" +
                    "</div>"
                );
                layui.use('rate', function () {
                    var rate = layui.rate;
                    rate.render({
                        elem: '#MovieScore'
                        , value: (obj.data.movieScore / 2)
                        , half: true
                        , readonly: true
                    })
                });
            }
        });
    }

    //评分
    function giveScore() {
        layui.use(['rate', 'layer'], function () {
            var layer = layui.layer;
            var rate = layui.rate;
            layer.open({
                type: 1
                , title: "电影评分"
                , closeBtn: false
                , area: '400px;'
                , shade: 0.8
                , offset: clientHeight / 5
                , id: 'LAY_layuipro' //设定一个id，防止重复弹出
                , btn: ['确认评分', '取消']
                , yes: function () {
                    layer.alert('感谢你的评价,祝你生活愉快！', {icon: 0, offset: clientHeight / 5},
                        function () {
                            layer.closeAll();
                            location.reload();
                        }
                    );
                }
                , btnAlign: 'c'
                , moveType: 0 //拖拽模式，0或者1
                , content: ScoreHtml
                , success: function (layero) {
                    rate.render({
                        elem: '#GiveScore'
                        , value: 4.5
                        , half: true
                        , text: true
                        , setText: function (value) {
                            this.span.text(value * 2 + "分");
                        }
                    })
                }
            });
        });
    }

    //想看
    function wantSee() {
        layui.use(['rate', 'layer'], function () {
            var layer = layui.layer;
            var rate = layui.rate;
            layer.alert('感谢你的支持！', {icon: 0, offset: clientHeight / 5},
                function () {
                    layer.closeAll();
                    location.reload();
                }
            );
        });
    }

    //初始化演职人员
    function initActor() {
        var celebrityContainer = $(".celebrity-container");
        var PosName;
        var StrActor;
        var StrRole;
        var PictureUrl;
        $.ajax({
            type: 'post',
            url: "http://localhost:8080/findMovieById",
            dataType: 'json',
            data: {
                movieId: movieId
            },
            success: function (obj) {
                console.log(obj);
                PictureUrl = "../static/images/actor/" + obj.data.movieCnName + "/director/" + obj.data.movieDirector + ".jpg";
                // 导演
                console.log(PictureUrl)
                celebrityContainer.prepend(
                    "<div class=\"celebrity-group\">" +
                    "<div class=\"celebrity-type\">" +
                    "导演" +
                    "</div>" +
                    "<ul class=\"celebrity-list clearfix\">" +
                    "<li class=\"celebrity\">" +
                    "<a class=\"portrait\">" +
                    "<img class=\"default-img\" alt=\"\" src=\"" + PictureUrl + "\">" +
                    "</a>" +
                    "<div>" +
                    "<a style=\"text-decoration: none;\" class=\"name\">" + obj.data.movieDirector + "</a>" +
                    "</div>" +
                    "</li>" +
                    "</ul>" +
                    "</div>" +
                    "<div style=\"width:40px; height:20px; float:left;\"></div>"
                );
                StrActor = obj.data.movieActor.split(',');
                for (var i = 0; i < StrActor.length; i++) {
                    StrRole = StrActor[i].split(':');
                    PictureUrl = "../static/images/actor/" + obj.data.movieCnName + "/actor/" + StrRole[0] + ".jpg";
                    // 演员
                    console.log(PictureUrl)
                    PosName = "&nbsp";
                    if (i == 0) {
                        PosName = "演职人员";
                    }
                    celebrityContainer.append(
                        "<div class=\"celebrity-group\">" +
                        "<div class=\"celebrity-type\">" +
                        PosName +
                        "</div>" +
                        "<ul class=\"celebrity-list clearfix\">" +
                        "<li class=\"celebrity\">" +
                        "<a class=\"portrait\">" +
                        "<img class=\"default-img\" alt=\"\" src=\"" + PictureUrl + "\">" +
                        "</a>" +
                        "<div>" +
                        "<a style=\"text-decoration: none;\" class=\"name\">" + StrRole[0] + "&nbsp饰&nbsp" + StrRole[1] + "</a>" +
                        "</div>" +
                        "</li>" +
                        "</ul>" +
                        "</div>"
                    );
                }
            }
        });


    }

    //写评论
    function writeComment() {
        layui.use(['laypage', 'layer', 'table'], function () {
            var laypage = layui.laypage;
            var layer = layui.layer;
            var table = layui.table
            //写评论
            layer.open({
                type: 1
                , title: "编写评论" //不显示标题栏
                , closeBtn: false
                , area: '430px;'
                , shade: 0.8
                , offset: clientHeight / 20
                , id: 'LAY_layuipro' //设定一个id，防止重复弹出
                , btn: ['确认评价', '取消']
                , yes: function () {
                    var commentContent = $('#comment_content_write').val();
                    if (commentContent == "") {
                        layer.alert('评论内容不能空，评论失败！', {icon: 0, offset: clientHeight / 5},
                            function () {
                                layer.close(layer.index);
                            }
                        );
                    } else {
                        if (commentContent.length > 150) {
                            layer.alert('字数超过150个，评论失败！', {icon: 0, offset: clientHeight / 5},
                                function () {
                                    layer.close(layer.index);
                                }
                            );
                        } else {
                            console.log(movieId);
                            console.log(commentContent);
                            $.ajax({
                                type: 'post',
                                url: "http://localhost:8080/addCommentByUser",
                                dataType: 'json',
                                data: {
                                    movieId: movieId,
                                    commentContent: commentContent
                                },
                                success: function (obj) {
                                    if (obj.code == 0) {
                                        layer.alert('评价成功！', {icon: 0, offset: clientHeight / 5},
                                            function () {
                                                layer.closeAll();
                                                location.reload();
                                            }
                                        );
                                    } else {
                                        layer.alert(obj.msg, {icon: 0, offset: clientHeight / 5},
                                            function () {
                                                layer.closeAll();
                                            }
                                        );
                                    }
                                }
                            });

                        }
                    }
                }
                , btnAlign: 'c movie-last'
                , moveType: 0 //拖拽模式，0或者1
                , content: WriteCommentHtml

            });
        });
    }

    //删除评论
    function deleteConfirm(commentId) {
        layui.use(['layer'], function () {
            var layer = layui.layer;
            layer.alert('确定要删除该条评论信息吗？', {icon: 0, offset: clientHeight / 5}
                , function () {
                    $.ajax({
                        type: 'post',
                        url: "http://localhost:8080/deleteComemnt",
                        dataType: 'json',
                        data: {
                            commentId: commentId,
                        },
                        success: function (obj) {
                            if (obj.code == 0) {
                                layer.alert(obj.msg, {icon: 0, offset: clientHeight / 5},
                                    function () {
                                        layer.closeAll();
                                        location.reload();
                                    }
                                );
                            } else {
                                layer.alert(obj.msg, {icon: 0, offset: clientHeight / 5},
                                    function () {
                                        layer.closeAll();
                                    }
                                );
                            }
                        }
                    });
                }
            );
        });
    }

    //修改评论
    function updateConfirm(commentId, userName, commentContent, commentTime) {
        layui.use(['laypage', 'layer', 'table'], function () {
            var laypage = layui.laypage;
            var layer = layui.layer;
            var table = layui.table
            //修改评论
            layer.open({
                type: 1
                , title: "修改评论" //不显示标题栏
                , closeBtn: false
                , area: '430px;'
                , shade: 0.8
                , offset: clientHeight / 20
                , id: 'LAY_layuipro' //设定一个id，防止重复弹出
                , btn: ['确认修改', '取消']
                , yes: function () {
                    var comment_content = $('#comment_content').val();
                    if (comment_content == "") {
                        layer.alert('评论内容不能空，修改失败！', {icon: 0, offset: clientHeight / 5},
                            function () {
                                layer.close(layer.index);
                            }
                        );
                    } else {
                        console.log(comment_content.length);
                        if (comment_content.length > 150) {
                            layer.alert('字数超过150个，评论失败！', {icon: 0, offset: clientHeight / 5},
                                function () {
                                    layer.close(layer.index);
                                }
                            );
                        } else {
                            $.ajax({
                                type: 'post',
                                url: "http://localhost:8080/updateComment",
                                dataType: 'json',
                                data: {
                                    commentId: commentId,
                                    commentContent: commentContent
                                },
                                success: function (obj) {
                                    if (obj.code == 0) {
                                        layer.alert(obj.msg, {icon: 0, offset: clientHeight / 5},
                                            function () {
                                                layer.closeAll();
                                                location.reload();
                                            }
                                        );
                                    } else {
                                        layer.alert(obj.msg, {icon: 0, offset: clientHeight / 5},
                                            function () {
                                                layer.closeAll();
                                            }
                                        );
                                    }
                                },
                                error: function () {
                                    alert("network error!");
                                }
                            });
                        }
                    }
                }
                , btnAlign: 'c movie-last'
                , moveType: 0 //拖拽模式，0或者1
                , content: UpdateCommentHtml
                , success: function (layero) {
                    $('#user_name').val(userName);
                    $('#comment_time').val(commentTime);
                    $('#comment_content').val(commentContent);
                }
            });
        });
    }

    function initComment() {
        var user = localStorage.getItem("userJson");
        var commentListContainer = $(".comment-list-container").find("ul");
        if (user == null) {
            $.ajax({
                type: 'post',
                url: "http://localhost:8080/findMovieById",
                dataType: 'json',
                data: {
                    movieId: movieId
                },
                success: function (obj) {
                    console.log(obj);
                    for (var i = 0; i < obj.data.commentList.length; i++) {
                        commentListContainer.append(
                            "<li class=\"comment-container\">" +
                            "<div class=\"portrait-container\">" +
                            "<div class=\"portrait\">" +
                            "<img src=\"" + obj.data.commentList[i].commentUserss.userHead + "\" alt=\"\">" +
                            "</div>" +
                            "<i class=\"level-4-icon\"></i>" +
                            "</div>" +
                            "<div class=\"main2\">" +
                            "<div class=\"main2-header clearfix\">" +
                            "<div class=\"user\">" +
                            "<span class=\"name\">" + obj.data.commentList[i].commentUserss.userName + "</span>	" +
                            "<span class=\"tag\">购</span>" +
                            "</div>" +
                            "<div class=\"time\" title=\"2018-11-16 12:06:10\">" +
                            "<span title=\"2018-11-16 12:06:10\">" + obj.data.commentList[i].commentTime + "</span>" +
                            "</div>" +
                            "<div class=\"approve\" data-id=\"1044884745\">" +
                            "</div>" +
                            "</div>" +
                            "<div class=\"comment-content\"> " +
                            obj.data.commentList[i].commentContent +
                            "</div>" +
                            "</div>" +
                            "</ul>"
                        );
                    }
                }
            });
        } else {
            user = eval('(' + user + ')');
            var userRole = user.userRole;  //身份验证
            var userName = user.userName;
            var html;
            $.ajax({
                type: 'post',
                url: "http://localhost:8080/findMovieById",
                dataType: 'json',
                data: {
                    movieId: movieId
                },
                success: function (obj) {
                    console.log(obj);
                    for (var i = 0; i < obj.data.commentList.length; i++) {
                        if ((userRole == 1) && (user_name == obj.data.commentList[i].commentUser.userName)) {
                            html = "<div class=\"updateBtn\" onclick='updateConfirm(\"" + obj.data.commentList[i].commentId + "\",\"" + obj.data.commentList[i].commentUser.userName + "\",\"" + obj.data.commentList[i].commentContent + "\",\"" + obj.data.commentList[i].commentTime + "\")'>修改</div>" +
                                "<div class=\"deleteCom\" onclick='deleteConfirm(\"" + obj.data.commentList[i].commentId + "\")'>删除</div>";
                        } else if (userRole == 1) {
                            html = "<div class=\"deleteCom\" onclick='deleteConfirm(\"" + obj.data.commentList[i].commentId + "\")'>删除</div>";
                        } else if ((userName == obj.data.commentList[i].commentUser.userName) && (userRole != 1)) {
                            html = "<div class=\"updateBtn\" onclick='updateConfirm(\"" + obj.data.commentList[i].commentId + "\",\"" + obj.data.commentList[i].commentUser.userName + "\",\"" + obj.data.commentList[i].commentContent + "\",\"" + obj.data.commentList[i].commentTime + "\")'>修改</div>";
                        } else {
                            html = "";
                        }
                        commentListContainer.append(
                            "<li class=\"comment-container\">" +
                            "<div class=\"portrait-container\">" +
                            "<div class=\"portrait\">" +
                            "<img src=\"" + obj.data.commentList[i].commentUser.userHeadImg + "\" alt=\"\">" +
                            "</div>" +
                            "<i class=\"level-4-icon\"></i>" +
                            "</div>" +
                            "<div class=\"main2\">" +
                            "<div class=\"main2-header clearfix\">" +
                            "<div class=\"user\">" +
                            "<span class=\"name\">" + obj.data.commentList[i].commentUser.userName + "</span>	" +
                            "<span class=\"tag\">购</span>" +
                            "</div>" +
                            "<div class=\"time\" title=\"2018-11-16 12:06:10\">" +
                            "<span title=\"2018-11-16 12:06:10\">" + obj.data.commentList[i].commentTime + "</span>" +
                            "</div>" +
                            "<div class=\"approve\" data-id=\"1044884745\">" +
                            html +
                            "</div>" +
                            "</div>" +
                            "<div class=\"comment-content\"> " +
                            obj.data.commentList[i].commentContent +
                            "</div>" +
                            "</div>" +
                            "</ul>"
                        );
                    }
                }
            });
        }
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