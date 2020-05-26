<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom shadow-sm">
        <img src="images/logo.png" width="64" height="64" class="mb-2"">
        <h5 class="my-0 mr-md-auto font-weight-normal">宠物商店</h5>
        <nav class="my-2 my-md-0 mr-md-3">
            <a class="p-2 text-dark" href="/petstore/index">首页</a>
            <a class="p-2 text-dark" href="${user==null?"login.jsp":"personal.jsp"}">${user==null?"登录":user.name}</a>
            <a class="p-2 text-dark" href="/petstore/cart">购物车(<span id="totalCount"><c:out value="${cart.totalCount }" default="0" escapeXml = "false"></c:out></span>)</a>
            <a class="p-2 text-dark" href="#">联系客服</a>
        </nav>
        <a class="btn btn-outline-primary" href="#">登录</a>
    </div>