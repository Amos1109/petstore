<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
        <div>
            <form action="index" class="form-inline"  id="petForm"  method="post">
                <div class="form-group">
                    <select name="cid" class="form-control" id="cid" style="width:80px">
                        <option value="0" >全部</option>
                        <option value="1" >猫</option>
                        <option value="2">狗</option>
                        <option value="3">鸟</option>
                        <option value="4">鱼</option>
                    </select>
                <input type="text" class="form-control" name="name" id="name" style="width:120px" value="${name }">
                &nbsp;&nbsp;
                <input type="submit" class="btn btn-primary btn-sm" value="查询">
                </div>
            </form>
            </div>
        <c:if test="${fn:length(result.dataList) eq 0 }">
            <span>查询的结果不存在。</span>
        </c:if>
        <!-- 后台返回结果不为空 -->
        <c:if test="${fn:length(result.dataList) gt 0 }">
        <div class="card-deck mb-3 text-center">
        <c:forEach var="pet" items="${result.dataList }">
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
            <div id="pagerArea" unselectable="on" onselectstart="return false;">
            </div>
        </c:if>
    </div>

    <jsp:include page="footer.jsp"></jsp:include>
    <script type="text/javascript">


        $(function() {

            $("#pagerArea").cypager({pg_size: ${result.pageSize}, pg_nav_count: 7, pg_total_count: ${result.totalRecord},pg_cur_count:${result.currentPage}, pg_call_fun: function(count) {
                    $("#petForm").attr("action", "index?pageNum=" + count);
                    $("#petForm").submit();
                }
            });


            // 设置默认种类
            $("#cid").val("${cid}");

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