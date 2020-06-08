<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
    <style>
        .clear {
            clear: both;
        }
    </style>
</head>
<body>

<jsp:include page="menu.jsp"></jsp:include>
<div id="sidebar" class="sidebar py-3">
    <div class="text-gray-400 text-uppercase px-3 px-lg-4 py-4 font-weight-bold small headings-font-family">USER CENTER</div>
    <ul class="sidebar-menu list-unstyled">
        <li class="sidebar-list-item"><a href="personal.jsp" class="sidebar-link text-muted"><i class="o-home-1 mr-3 text-gray"></i><span>个人信息</span></a></li>
        <li class="sidebar-list-item"><a href="order" class="sidebar-link text-muted active"><i class="o-survey-1 mr-3 text-gray"></i><span>我的订单</span></a></li>
        <li class="sidebar-list-item"><a href="tables.html" class="sidebar-link text-muted"><i class="o-diploma-1 mr-3 text-gray"></i><span>超级会员</span></a></li>
        <li class="sidebar-list-item"><a href="forms.html" class="sidebar-link text-muted"><i class="o-document-1 mr-3 text-gray"></i><span>我的收藏</span></a></li>
        <li class="sidebar-list-item">
            <a href="#" data-toggle="collapse" data-target="#pages" aria-expanded="true" aria-controls="pages" class="sidebar-link text-muted"><i class="o-database-1 mr-3 text-gray"></i><span>我的钱包</span></a>
            <div id="pages" class="collapse">
                <ul class="sidebar-menu list-unstyled border-left border-primary border-thick">
                    <li class="sidebar-list-item"><a href="balance.jsp" class="sidebar-link text-muted pl-lg-5">余额查询</a></li>
                    <li class="sidebar-list-item"><a href="recharge.jsp" class="sidebar-link text-muted pl-lg-5">账户充值</a></li>
                    <li class="sidebar-list-item"><a href="order" class="sidebar-link text-muted pl-lg-5">绑定银行卡</a></li>
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
<div style="width: 800px;float:left">
<c:forEach items="${orderList}" var="order">
    <br/>
<div class="card" style="height: auto">
    <div class="card-header">
        <h2 class="h6 text-uppercase mb-0">订单编号：${order.id}&nbsp;&nbsp;下单时间：${order.date}&nbsp;&nbsp;订单金额：${order.money}
             <button type="button" class="btn btn-outline-secondary btn-show" style="float: right" data-id="${order.id}">详情</button></h2>

    </div>
    <div class="card-body" style="height: auto;display: none">
        <table class="table panel-body ">
            <thead>
            <tr>
                <td></td>
                <td>商品名称</td>
                <td>单价</td>
                <td>数量</td>
                <td>小计</td>
            </tr>
            </thead>
            <tbody>

            </tbody>
        </table>

<%--        <div class="card-footer">--%>
<%--            <div class="pull-left" >总计: ¥ <span id="totalMoney">${orderTotal.totalMoney }</span></div>--%>
<%--            <a class="btn btn-warning  pull-right" href="order">立即支付</a>--%>
<%--        </div>--%>
    </div>
</div>
</c:forEach>
</div>

<div class="clear"></div>
<jsp:include page="footer.jsp"></jsp:include>
<script>
    $(function () {
        $(".btn-show").click(function () {
            $(this).parent().parent().next().toggle(600);
        });

        $(".btn-show").one("click",function(){
            var orderId=$(this).data("id");
            var tbody=$(this).parent().parent().next().find("tbody");
            $.ajax({
                url:'orderdetail',
                dataType:"JSON",
                type:'post',
                data:{
                    'id':orderId,
                },
                success:function(data){
                    if(data.success==true){
                        tbody.append(data.msg)

                    }else{
                        toastr.error(data.msg);
                    }
                }
            });
        }) ;

    })
</script>
</body>
</html>

