<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<html>
<head>
    <jsp:include page="head.jsp"></jsp:include>
    <title>余额查询</title>
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
        <li class="sidebar-list-item"><a href="personal.jsp" class="sidebar-link text-muted"><i class="o-home-1 mr-3 text-gray"></i><span>个人信息</span></a></li>
        <li class="sidebar-list-item"><a href="order" class="sidebar-link text-muted"><i class="o-survey-1 mr-3 text-gray"></i><span>我的订单</span></a></li>
        <li class="sidebar-list-item"><a href="tables.html" class="sidebar-link text-muted"><i class="o-diploma-1 mr-3 text-gray"></i><span>超级会员</span></a></li>
        <li class="sidebar-list-item"><a href="forms.html" class="sidebar-link text-muted"><i class="o-document-1 mr-3 text-gray"></i><span>我的收藏</span></a></li>
        <li class="sidebar-list-item">
            <a href="#" data-toggle="collapse" data-target="#pages" aria-expanded="true" aria-controls="pages" class="sidebar-link text-muted active"><i class="o-database-1 mr-3 text-gray"></i><span>我的钱包</span></a>
            <div id="pages" class="collapse show">
                <ul class="sidebar-menu list-unstyled border-left border-primary border-thick">
                    <li class="sidebar-list-item"><a href="balance.jsp" class="sidebar-link text-muted pl-lg-5 active">余额查询</a></li>
                    <li class="sidebar-list-item"><a href="recharge.jsp" class="sidebar-link text-muted pl-lg-5">账户充值</a></li>
                    <li class="sidebar-list-item"><a href="#" class="sidebar-link text-muted pl-lg-5">绑定银行卡</a></li>
                </ul>
            </div>
        </li>

    </ul>
    <div class="text-gray-400 text-uppercase px-3 px-lg-4 py-4 font-weight-bold small headings-font-family">EXTRAS</div>
    <ul class="sidebar-menu list-unstyled">
        <li class="sidebar-list-item"><a href="login.html" class="sidebar-link text-muted"><i class="o-exit-1 mr-3 text-gray"></i><span>安全退出</span></a></li>
        <li class="sidebar-list-item"><a href="modifypwd.jsp" class="sidebar-link text-muted"><i class="o-imac-screen-1 mr-3 text-gray"></i><span>修改密码</span></a></li>
        <li class="sidebar-list-item"><a href="#" class="sidebar-link text-muted"><i class="o-wireframe-1 mr-3 text-gray"></i><span>发现更多</span></a></li>
    </ul>
</div>

<div class="card">
    <div class="card-header">
        <h2 class="h6 text-uppercase mb-0">余额查询</h2>
    </div>
    <div class="card-body">
        <div>
            <img src="http://pic2.zhimg.com/50/v2-be54dda1c19aaeab4f90bcb699057d03_hd.jpg" style="max-width: 3rem" class="rounded-circle mx-3 my-2 my-lg-0" style="display: inline-block">
            <h6 class="mb-0" style="display: inline-block">AmosHong</h6>
        </div>
        <form style="width: 100%;max-width:300px;margin-top: 20px">
            <div class="form-group">
                <label class="form-control-label text-uppercase">Email</label>
                <input type="email" value="${user.email}" class="form-control" name="email" readonly>
            </div>
            <div class="form-group">
                <label class="form-control-label text-uppercase">余额</label>
                <input type="email" value="${user.balance}" class="form-control" name="balance" readonly>
            </div>
            <div class="form-group">
                <button type="button"  onclick="location.href='recharge.jsp'" class="btn btn-primary" style="display:block;margin:0 auto">去充值</button>
            </div>
        </form>
        <div>
            <img src="">
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>

