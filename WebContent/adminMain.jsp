<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>商店后台管理 - Layui</title>

    <script src="https://cdn.bootcss.com/jquery/3.4.1/jquery.min.js"></script>
    <link rel="stylesheet" href="layui/css/layui.css">
    <link rel="stylesheet" href="layui/css/modules/layer/default/layer.css">
    <link href="//cdn.bootcss.com/toastr.js/latest/css/toastr.min.css" rel="stylesheet">
    <script src="layui/layui.js"></script>
    <script src="layui/lay/modules/layer.js"></script>
    <script src="//cdn.bootcss.com/toastr.js/latest/js/toastr.min.js"></script>
    <script type="text/javascript">


        $(document).ready(
            function(){
                $("dd>a").click(function (e) {
                    e.preventDefault();
                    $("#iframeMain").attr("src",$(this).attr("href"));
                });
            }
        );

        layui.use('element', function(){
            var element = layui.element;
        });

        function openAddPetDialog() {
            layer.open({
                title:'编辑宠物信息',
                type: 2,
                area: ['500px', '600px'],
                content: 'petedit.jsp',
            });
        }

        function logout(){
            layer.confirm('您确定要退出吗？', {
                btn: ['确定','取消']
            },function(r){
                if(r){
                    window.location.href="logout"
                }
            });
        }

        function openPasswordModifyDialog(){
            layer.confirm('<form id="fm" method="post"><table cellspacing="15px" style="margin-top:20px;margin-left:10px"><tr><td>请输入原密码：</td><td><input id="oldPassword" name="oldPassword" type="password" /></td></tr><tr><td>请输入新密码：</td><td><input id="newPassword" name="password" type="password" /></td></tr><tr><td>请确认新密码：</td><td><input id="newPassword2" name="password2" type="password" /></td></tr></table></form>',{
                btn: ['确定',"关闭"],
                title:'修改密码',
                type: 1,
                skin: 'layui-layer-rim', //加上边框
                area: ['350px', '200px'], //宽高
            },function(r){
                if(r){
                    var email="${user.email}";
                    var oldPassword=$("input[name='oldPassword']").val();
                    var password=$("input[name='password']").val();
                    var password2=$("input[name='password2']").val();
                    if(password!=password2){
                        toastr.error("两次密码不一致！");
                        resetValue();
                        return false;
                    }
                    if(password!=""&&password!=null&&password==password2){
                        $.ajax({
                            url:'modifypwd',
                            dataType:"JSON",
                            type:'post',
                            data:{
                                'oldPassword':oldPassword,
                                'email':email,
                                'password':password
                            },
                            success:function(data){
                                if(data.success==true){
                                    toastr.success("密码修改成功，下次登录生效！")

                                }else{
                                    toastr.error(data.msg);
                                    resetValue();
                                }

                            }
                        });
                    }else{
                        toastr.error("新密码不能为空！");
                        return false;
                    }
                }
            });
        }

        function resetValue(){
            $("#oldPassword").val("");
            $("#newPassword").val("");
            $("#newPassword2").val("");
        }
    </script>
</head>
<body class="layui-layout-body">

<input type="hidden" name="email" value="${user.email}">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">PetStore后台管理</div>
        <ul class="layui-nav layui-layout-left">
            <li class="layui-nav-item"><a href="">后台首页</a></li>
            <li class="layui-nav-item"><a href="index">商店首页</a></li>
        </ul>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="http://121.43.102.125:8080/images/18164308.jpg" class="layui-nav-img">
                    Admin
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="modifyInfo.jsp">基本资料</a></dd>
                    <dd><a onclick="openPasswordModifyDialog()">修改密码</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item"><a onclick="logout()" style="cursor: pointer;">安全退出</a></li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree"  lay-filter="test">
                <li class="layui-nav-item">
                    <a class="" href="javascript:;">商店管理</a>
                    <dl class="layui-nav-child">
                        <dd><a onclick="openAddPetDialog()" style="cursor: pointer">新增宠物</a></dd>
                        <dd><a href="petlist.jsp">宠物管理</a></dd>
                        <dd><a href="#">类别管理</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">用户管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="modifyInfo.jsp">用户列表</a></dd>
                        <dd><a href="javascript:;">新增用户</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a class="" href="javascript:;">系统管理</a>
                    <dl class="layui-nav-child">
                        <dd><a onclick="refreshSystem()" style="cursor: pointer;">刷新系统缓存</a></dd>
                        <dd><a onclick="openPasswordModifyDialog()" style="cursor: pointer;">修改密码</a></dd>
                        <dd><a onclick="logout()" style="cursor: pointer;">安全退出</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item"><a href="">云市场</a></li>
                <li class="layui-nav-item"><a href="">开发文档</a></li>
            </ul>
        </div>
    </div>


    <div class="layui-body">
        <!-- 内容主体区域 -->
        <iframe id="iframeMain" src="welcome.jsp" style="width: 100%"; height="100%";></iframe>
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        © <a href="http://Amospace.fun" target="_blank" title="XzcBlogTemplate">PetStore</a> - 本商店后台管理采用layui进行开发。
    </div>
</div>


</body>
</html>