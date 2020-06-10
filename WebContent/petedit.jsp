<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="layui/css/layui.css"  media="all">
</head>
<body>
<br/>
<div style="width: 400px;height: auto">
<form class="layui-form" action="" lay-filter="petedit">
    <div class="layui-form-item">
        <label class="layui-form-label">宠物名称：</label>
        <div class="layui-input-block">
            <input type="text" name="name" lay-verify="title" autocomplete="off" placeholder="请输入标题" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">宠物特点：</label>
        <div class="layui-input-block">
            <input type="text" name="tag" lay-verify="title" autocomplete="off" placeholder="请输入标题" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">宠物描述：</label>
        <div class="layui-input-block">
            <textarea placeholder="请输入内容" class="layui-textarea" name="description"></textarea>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">宠物种类：</label>
        <div class="layui-input-block">
            <select name="cid" lay-filter="cid">
                <option value="1" selected="">猫</option>
                <option value="2">狗</option>
                <option value="3">鸟</option>
                <option value="4">鱼</option>
            </select>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">宠物价格：</label>
        <div class="layui-input-inline">
            <input type="text" name="price" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">宠物库存：</label>
        <div class="layui-input-inline">
            <input type="text" name="stock" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">是否上架：</label>
        <div class="layui-input-block">
            <input type="checkbox" name="is_offsale"  checked="" lay-skin="switch" value="0" lay-text="YES|NO">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button type="submit" class="layui-btn" lay-submit="" lay-filter="demo1">提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>
</div>
<script src="https://cdn.bootcss.com/jquery/3.4.1/jquery.min.js"></script>
<script src="layui/layui.js" charset="utf-8"></script>
<script>
    layui.use('form', function(){
        var form = layui.form;

        form.val("petedit", {
            "name": "${pet.name}"
            ,"tag": "${pet.tag}"
            ,"description": "${pet.description}"
            ,"cid": "${pet.c_id}"
            ,"price": "${pet.price}"
            ,"stock": "${pet.stock}"
            ,"is_offsale": "${pet.offsale=="true"?"1":"0"}"
        });

        form.render(); //更新全部
    });


</script>
</body>
</html>
