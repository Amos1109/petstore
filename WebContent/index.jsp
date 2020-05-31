<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
  <head>
   <jsp:include page="head.jsp"></jsp:include>
   <title>PetStore · Bootstrap</title>
<link href="styles/index.css" rel="stylesheet">
  </head>
  <body>
	<jsp:include page="menu.jsp"></jsp:include>
    <div class="container">
        <div class="card-deck mb-3 text-center">
        <c:forEach var="pet" items="${petlist }">
            <div class="card mb-4 shadow-sm shop" data-id="${pet.id }" >
                <div class="card-header">
                    <img src="petImg/${pet.img }" class="pet-pic">
                </div>
                <div class="card-body">
                    <h1 class="card-title pricing-card-title"><small class="text-muted">${pet.name }</small></h1>
                    <p class="pet-desc">${pet.description }</p>
                    <p><span class="pet-tag">${pet.tag }</span></p>
                    <p class="pet-price">￥${pet.price }</p>
                    <a href="detail?id=${pet.id }" class="btn btn-lg btn-block btn-outline-primary">查看详情</a>
                    <button class="btn btn-lg btn-block btn-outline-primary addToCart" type="button">加入购物车</button>
                </div>
            </div>
          </c:forEach>
        </div>
    </div>
    <jsp:include page="footer.jsp"></jsp:include>
    
    <script type="text/javascript">




    $(function() {
	$(".addToCart").click(function(){
		 var id=$(this).parents(".shop").data("id");
		$.ajax({
			url:'cart',
			dataType:"JSON",
			type:'post',
			data:{
				'type':'add',
				'id':id,
				'quantity':"1"
			},
			success:function(data){
				if(data.success==true){
					 $("#totalCount").html(data.msg2);
					toastr.success("成功添加了1件商品！");
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