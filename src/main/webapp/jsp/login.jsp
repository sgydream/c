
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %><!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>登录</title>
    <script src="../static/js/jquery-3.3.1.min.js"></script>
</head>
<body>
<form>
    <div>
        <label for="userName">用户名:</label>
        <input type="text" id="userName" name="userName" required>
    </div>
    <div>
        <label for="userPassword">密码:</label>
        <input type="text" id="userPassword" name="userPassword" required>
    </div>
    <div>
        <input type="submit" onclick=" return login()" value="提交">
    </div>
    <script>
        function login() {
            var userName = document.getElementById("userName").value;
            var userPassword = document.getElementById("userPassword").value;
            $.ajax({
                type: 'post',
                url: 'http://localhost:8080/login',
                data: {
                    userName : userName,
                    userPassword : userPassword,
                },
                success:function () {
                    alert("成功")
                }

            })
        }

    </script>
</form>
</body>
</html>