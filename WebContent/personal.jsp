<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="head.jsp"></jsp:include>
    <title>个人中心</title>
    <!-- Font Awesome CSS-->
    <link href="//netdna.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <!-- theme stylesheet-->
    <link rel="stylesheet" href="styles/personal.css" id="theme-stylesheet">
    <!-- orion icons-->
    <link rel="stylesheet" href="css/orionicons.css">
    <!-- Font Awesome CSS-->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
</head>
<body>

<jsp:include page="menu.jsp"></jsp:include>
<div id="sidebar" class="sidebar py-3">
    <div class="text-gray-400 text-uppercase px-3 px-lg-4 py-4 font-weight-bold small headings-font-family">USER CENTER</div>
    <ul class="sidebar-menu list-unstyled">
        <li class="sidebar-list-item"><a href="index.html" class="sidebar-link text-muted active"><i class="o-home-1 mr-3 text-gray"></i><span>个人信息</span></a></li>
        <li class="sidebar-list-item"><a href="charts.html" class="sidebar-link text-muted"><i class="o-survey-1 mr-3 text-gray"></i><span>我的订单</span></a></li>
        <li class="sidebar-list-item"><a href="tables.html" class="sidebar-link text-muted"><i class="o-diploma-1 mr-3 text-gray"></i><span>超级会员</span></a></li>
        <li class="sidebar-list-item"><a href="forms.html" class="sidebar-link text-muted"><i class="o-document-1 mr-3 text-gray"></i><span>我的收藏</span></a></li>
        <li class="sidebar-list-item"><a href="#" data-toggle="collapse" data-target="#pages" aria-expanded="false" aria-controls="pages" class="sidebar-link text-muted"><i class="o-database-1 mr-3 text-gray"></i><span>我的钱包</span></a>

            <div id="pages" class="collapse">
                <ul class="sidebar-menu list-unstyled border-left border-primary border-thick">
                    <li class="sidebar-list-item"><a href="#" class="sidebar-link text-muted pl-lg-5">余额查询</a></li>
                    <li class="sidebar-list-item"><a href="#" class="sidebar-link text-muted pl-lg-5">账户充值</a></li>
                    <li class="sidebar-list-item"><a href="#" class="sidebar-link text-muted pl-lg-5">绑定银行卡</a></li>
                </ul>
            </div>
        </li>

    </ul>
    <div class="text-gray-400 text-uppercase px-3 px-lg-4 py-4 font-weight-bold small headings-font-family">EXTRAS</div>
    <ul class="sidebar-menu list-unstyled">
        <li class="sidebar-list-item"><a href="login.html" class="sidebar-link text-muted"><i class="o-exit-1 mr-3 text-gray"></i><span>安全退出</span></a></li>
        <li class="sidebar-list-item"><a href="#" class="sidebar-link text-muted"><i class="o-imac-screen-1 mr-3 text-gray"></i><span>修改密码</span></a></li>
        <li class="sidebar-list-item"><a href="#" class="sidebar-link text-muted"><i class="o-wireframe-1 mr-3 text-gray"></i><span>发现更多</span></a></li>
    </ul>
</div>
<div class="card">
    <div class="card-header">
        <h2 class="h6 text-uppercase mb-0">个人信息</h2>
    </div>
    <div class="card-body">
        <div>
        <img src="http://pic2.zhimg.com/50/v2-be54dda1c19aaeab4f90bcb699057d03_hd.jpg" style="max-width: 3rem" class="rounded-circle mx-3 my-2 my-lg-0" style="display: inline-block">
        <h6 class="mb-0" style="display: inline-block">Amos Hong</h6>
        </div>
        <p class="text-gray">对不起，这里我还没写。</p>
    </div>
</div>
<jsp:include page="footer.jsp"></jsp:include>
<!-- JavaScript files-->
<script src="js/jquery.min.js"></script>

<script src="js/bootstrap.min.js"></script>

</body>
</html>
