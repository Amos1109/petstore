<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="head.jsp"></jsp:include>
<title>我的购物车</title>
<link href="styles/cart.css" rel="stylesheet">
</head>
<body>
<jsp:include page="menu.jsp"></jsp:include>
 <div class="container">
		<div class="card">
			<div class="card-header">购物车</div>
			<div class="card-body">   
				<table class="table panel-body ">
                <thead>
                    <tr>
						<td><input type="checkbox" name="" value="">全选</td>
						<td></td>
						<td>商品名称</td>
						<td>单价</td>
						<td>数量</td>
						<td>小计</td>
						<td>操作</td>
                	</tr>
                </thead>
                <tbody>
                <c:forEach var="item" items="${cart.cartItems.values() }">
                    <tr class="shop" data-id="${item.id }">
                        <td>
                            <input type="checkbox" name="" value="" checked="checked">  
                        </td>
                        <td><img src="petImg/${item.img }" width="80" ></td>
                        <td>${item.name}</td>
                        <td>
                           <span class="p-price"> ¥ ${item.price }</span>
                        </td>
                        <td>
                                <input type="number" step="1" min="1" class="form-control quantity" name="quantity" value="${item.quantity }">
                        </td>
                        <td>
                        	<span class="subTotal">${item.subTotal }</span>
                        </td>
                        <td class="delete" style="cursor: pointer;">X</td>
                    </tr>
         </c:forEach>
                </tbody>
				</table>
			</div> 
			<div class="card-footer">            
				<div class="pull-left" >总计: ¥ <span id="totalMoney">${cart.totalMoney }</span></div>
				<button class="btn btn-warning  pull-right">立即结算</button>
			</div>
		  </div>
    </div>
<jsp:include page="footer.jsp"></jsp:include>

 <script type="text/javascript">



$(function() {
	
	$(".delete").click(function(){
		 var id=$(this).parents(".shop").data("id");
		 var $del=$(this);
		$.ajax({
			url:'cart',
			dataType:"JSON",
			type:'post',
			data:{
				'type':'remove',
				'id':id,
			},
			success:function(data){
				if(data.success==true){
  	    		  $del.parents(".shop").fadeOut("slow",function(){
 	        		 $(this).remove();
 	        		 });
					 $("#totalCount").html(data.msg2);
					 $("#totalMoney").html(data.msg3);
					toastr.success("成功删除了1种商品！");
					
				}else{
					toastr.error(data.msg);
				}
				
			}
		});
	});
	
	
	 $("input[type='number']").change(function () {
		 var id=$(this).parents(".shop").data("id");
		 var quantity=$(this).val();
		 var $mod=$(this);
		 $.ajax({
				url:'cart',
				dataType:"JSON",
				type:'post',
				data:{
					'type':'modify',
					'id':id,
					'quantity':quantity
				},
				success:function(data){
					if(data.success==true){
						$mod.parents(".shop")
						.find(".subTotal").html(data.msg);
						 $("#totalCount").html(data.msg2);
						 $("#totalMoney").html(data.msg3);
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