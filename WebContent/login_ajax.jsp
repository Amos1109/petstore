<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<html>
<head>
    <jsp:include page="head.jsp"></jsp:include>
    <title>用户登录</title>
    <link href="styles/login.css" rel="stylesheet">
</head>
<body>

<jsp:include page="menu.jsp"></jsp:include>
<div class="container">
    <div class="row">
        <div class="col-3 col-md">
            <form class="form-signin">
                <div class="text-center">
                    <img width="72" height="72" class="mb-4" alt="" src="images/logo.png">
                </div>

                <!-- <h1 class="h3 mb-3 font-weight-normal">请登录</h1> -->
                <label class="sr-only" for="inputUserName">用户名</label>
                <input class="form-control" id="inputUserName" autofocus="" required="" type="text" placeholder="用户名" name="inputUserName">
                <label class="sr-only" for="inputPassword">密码</label>
                <input class="form-control" id="inputPassword" required="" type="password" placeholder="密码" name="inputPassword">
                <div class="checkbox mb-3">
                    <label>
                        <input type="checkbox" value="remember-me"> 记住我
                    </label>
                </div>
                <button class="btn btn-lg btn-success btn-block" type="button" id="login">登录</button>
            </form>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp"></jsp:include>

<script type="text/javascript">
    $(function() {
        $("#login").click(function(){
            var inputUserName=$("#inputUserName").val();
            var inputPassword=$("#inputPassword").val();
            $.ajax({
                url:'login',
                dataType:"JSON",
                type:'post',
                data:{
                    'inputUserName':inputUserName,
                    'inputPassword':inputPassword
                },
                success:function(data){
                    if(data.success==true){
                        window.location.href = 'index';
                    }else{
                        toastr.error(data.msg);
                    }

                }
            });
        });

    });
</script>
</body>
</html>
