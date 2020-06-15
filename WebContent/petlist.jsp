<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>宠物列表</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="layui/css/layui.css"  media="all">
    <!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
</head>
<body>




<table class="layui-table" lay-data="{width: 892, height:330, url:'petlist', page:true, id:'idTest',limit:'10'}" lay-filter="demo">
    <thead>
    <tr>
        <th lay-data="{type:'checkbox', fixed: 'left'}"></th>
        <th lay-data="{field:'id', width:80, sort: true, fixed: true}">ID</th>
        <th lay-data="{field:'name', width:80}">宠物名</th>
        <th lay-data="{field:'tag', width:160, sort: true}">特点</th>
        <th lay-data="{field:'price', width:160}">价格</th>
        <th lay-data="{field:'stock', width:80, sort: true}">库存</th>
        <th lay-data="{fixed: 'right', width:178, align:'center', toolbar: '#barDemo'}"></th>
    </tr>
    </thead>
</table>

<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>

<script src="https://cdn.bootcss.com/jquery/3.4.1/jquery.min.js"></script>
<script src="layui/layui.js" charset="utf-8"></script>
<script src="//cdn.bootcss.com/toastr.js/latest/js/toastr.min.js"></script>
<link href="//cdn.bootcss.com/toastr.js/latest/css/toastr.min.css" rel="stylesheet">
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<script>
    layui.use('table', function(){
        var table = layui.table;
        //监听表格复选框选择
        table.on('checkbox(demo)', function(obj){
            console.log(obj)
        });
        //监听工具条
        table.on('tool(demo)', function(obj){
            var data = obj.data;
            if(obj.event === 'detail'){
                layer.open({
                    title:'宠物信息',
                    type: 2,
                    area: ['1100px', '700px'],
                    content: 'detail?id='+data.id,
                });
            } else if(obj.event === 'del'){
                layer.confirm('真的要删除吗？', function(index){
                    $.ajax({
                        url:'deletepet',
                        dataType:"JSON",
                        type:'post',
                        data:{
                            'id':data.id,
                        },
                        success:function(data){
                            if(data.success==true){
                                obj.del();
                                layer.close(index);
                                toastr.success("成功删除了1种商品！");

                            }else{
                                toastr.error("删除商品失败！");
                                layer.close(index);
                            }

                        }
                    });
                });
            } else if(obj.event === 'edit'){
                var id=data.id;
                layer.open({
                    title:'编辑宠物信息',
                    type: 2,
                    area: ['500px', '600px'],
                    content: 'petedit?id='+id,
                });
            }
        });


        $('.demoTable .layui-btn').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
    });
</script>

</body>
</html>