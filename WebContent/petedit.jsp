<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<head>
    <title>宠物信息详细</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="layui/css/layui.css"  media="all">
    <link href="//cdn.bootcss.com/toastr.js/latest/css/toastr.min.css" rel="stylesheet">
</head>
<body>
<br/>
<div style="width: 400px;height: auto">
<form class="layui-form" lay-filter="petedit">
    <input type="hidden" name="id">
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
            <button type="button" class="layui-btn" lay-submit="" lay-filter="demo1" id="submit">提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>
</div>
<script src="https://cdn.bootcss.com/jquery/3.4.1/jquery.min.js"></script>
<script src="//cdn.bootcss.com/toastr.js/latest/js/toastr.min.js"></script>
<script src="layui/layui.js" charset="utf-8"></script>
<script>
    toastr.options = { // toastr配置
        "closeButton": true, //是否显示关闭按钮
        "debug": false, //是否使用debug模式
        "progressBar": true, //是否显示进度条，当为false时候不显示；当为true时候，显示进度条，当进度条缩短到0时候，消息通知弹窗消失
        "positionClass": "toast-top-center",//显示的动画时间
        "showDuration": "200", //显示的动画时间
        "hideDuration": "500", //消失的动画时间
    }

    layui.use('form', function(){
        var form = layui.form;

        form.val("petedit", {
            "id":"${pet.id}"
            ,"name": "${pet.name}"
            ,"tag": "${pet.tag}"
            ,"description": "${pet.description}"
            ,"cid": "${pet.c_id}"
            ,"price": "${pet.price}"
            ,"stock": "${pet.stock}"
            ,"is_offsale": "${pet.offsale=="false"?"1":""}"
        });

        form.render(); //更新全部
    });

    $(function() {

    $("#submit").click(function () {
        console.info("111");
        var id=$("input[name='id']").val();
        var name=$("input[name='name']").val();
        var tag=$("input[name='tag']").val();
        var description=$("textarea[name='description']").val();
        var cid=$("select[name='cid']").val();
        var price=$("input[name='price']").val();
        var stock=$("input[name='stock']").val();
        $.ajax({
            url:'updatepet',
            dataType:"JSON",
            type:'post',
            data:{
                'id':id,
                'name':name,
                'tag':tag,
                'description':description,
                'cid':cid,
                'price':price,
                'stock':stock
            },
            success:function(data){
                if(data.success==true){
                   toastr.success(data.msg);
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
