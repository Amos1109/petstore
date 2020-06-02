<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <jsp:include page="head.jsp"></jsp:include>
    <title>我的订单</title>
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
        <li class="sidebar-list-item"><a href="charts.html" class="sidebar-link text-muted"><i class="o-survey-1 mr-3 text-gray  active"></i><span>我的订单</span></a></li>
        <li class="sidebar-list-item"><a href="tables.html" class="sidebar-link text-muted"><i class="o-diploma-1 mr-3 text-gray"></i><span>超级会员</span></a></li>
        <li class="sidebar-list-item"><a href="forms.html" class="sidebar-link text-muted"><i class="o-document-1 mr-3 text-gray"></i><span>我的收藏</span></a></li>
        <li class="sidebar-list-item">
            <a href="#" data-toggle="collapse" data-target="#pages" aria-expanded="true" aria-controls="pages" class="sidebar-link text-muted"><i class="o-database-1 mr-3 text-gray"></i><span>我的钱包</span></a>
            <div id="pages" class="collapse show">
                <ul class="sidebar-menu list-unstyled border-left border-primary border-thick">
                    <li class="sidebar-list-item"><a href="balance.jsp" class="sidebar-link text-muted pl-lg-5">余额查询</a></li>
                    <li class="sidebar-list-item"><a href="recharge.jsp" class="sidebar-link text-muted pl-lg-5">账户充值</a></li>
                    <li class="sidebar-list-item"><a href="#" class="sidebar-link text-muted pl-lg-5">我的订单</a></li>
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
        <h2 class="h6 text-uppercase mb-0">我的订单</h2>
    </div>
    <div class="card-body">
        <table class="table panel-body ">
            <thead>
            <tr>
                <td></td>
                <td>商品名称</td>
                <td>单价</td>
                <td>数量</td>
                <td>小计</td>
                <td>操作</td>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="item" items="${orderList}">
                <tr class="shop">
                    <td><img src="petImg/${item.img }" width="80" ></td>
                    <td>${item.name}</td>
                    <td>
                        <span class="p-price"> ¥ ${item.price }</span>
                    </td>
                    <td>
                        <input type="number" step="1" min="1" class="form-control quantity" name="quantity" value="${item.quantity }">
                    </td>
                    <td>
                        <span class="subTotal">${item.price*item.quantity }</span>
                    </td>
                    <td class="delete" style="cursor: pointer;">X</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <div class="card-footer">
            <div class="pull-left" >总计: ¥ <span id="totalMoney">${orderTotal.totalMoney }</span></div>
            <a class="btn btn-warning  pull-right" href="order">立即支付</a>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"></jsp:include>
<!-- JavaScript files-->
<script src="js/jquery.min.js"></script>

<script src="js/bootstrap.min.js"></script>

<script type="text/javascript">

</script>
</body>
</html>

