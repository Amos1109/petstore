<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
  <head>
   <jsp:include page="head.jsp"></jsp:include>
   <title>PetStore · 宠物详情</title>
    <link href="styles/detail.css" rel="stylesheet">
  </head>
  <body>
    <jsp:include page="menu.jsp"></jsp:include>
    <div class="container">
        <div class="row" data-id="${pet.id }" id="shopDetail">
            <div class="col-md-12">
              <div class="row no-gutters border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
                <div class="col-auto d-none d-lg-block">
                    <img src="petImg/${pet.img }" width="300" heght="400" class="mb-2">
                </div>
                <div class="col p-4 d-flex flex-column position-static">
                    <h3 class="d-inline-block mb-2 text-dark">${pet.name }</h3>
                    
                    <div class="mb-2 text-muted"><span class="pet-tag">${pet.tag }</span></div>
                    
                    <p class="card-text">${pet.description }</p>
                    <p>￥<span id="pet-price">${pet.price }</span></p>
                    <p>库存 (<span id="pet-stock">${pet.stock }</span>)</p>
                    <p>选择数量：<input type="number" id="number" value="1" step="1" min="1" ></p>
                    <nav>
                        <button class="btn btn-warning" type="button" id="addToCart">加入购物车</button>
                        <button class="btn btn-warning" type="button">返回列表</button>
                    </nav>   
                </div>
              </div>
            </div>
          </div>
    </div>
    <jsp:include page="footer.jsp"></jsp:include>
    
    			<script type="text/javascript">



$(function() {
	$("#number").change(function(){
		var number=parseInt($("#number").val());
		var stock=parseInt($("#pet-stock").text());
		if(stock<number){
			toastr.warning("库存不足！")
			$("#number").val(stock)
		}
	});
	
	$("#addToCart").click(function(){
		var number=$("#number").val();
		var id=$("#shopDetail").data("id");
		$.ajax({
			url:'cart',
			dataType:"JSON",
			type:'post',
			data:{
				'type':'add',
				'id':id,
				'quantity':number
			},
			success:function(data){
				if(data.success==true){
					 $("#totalCount").html(data.msg2);
					 toastr.success("成功添加了"+number+"件商品！");
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