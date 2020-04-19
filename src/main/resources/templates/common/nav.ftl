<nav class="navbar navbar-inverse navbar-fixed-top" id="sidebar-wrapper" role="navigation">
    <ul class="nav sidebar-nav">
        <li class="sidebar-brand">
            <a href="#">
                dof后台
            </a>
        </li>
        <li>
            <a href="/seller/order/list"><i class="fa fa-fw fa-list-alt"></i> 订单</a>
        </li>
        <li class="dropdown open" id="drop1">
            <a class="hover" data-toggle="dropdown" aria-expanded="false" onClick="doAct(this);"><i class="fa fa-fw fa-plus"></i> 商品 <span class="caret"></span></a>
            <ul class="dropdown-menu" role="menu">
                <li class="dropdown-header">操作</li>
                <li><a href="/seller/product/list">列表</a></li>
                <li><a href="/seller/product/index">新增</a></li>
            </ul>
        </li>
        <li class="dropdown open" id="drop2">
            <a class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false" onClick="doAct(this);"><i class="fa fa-fw fa-plus"></i> 类目 <span class="caret"></span></a>
            <ul class="dropdown-menu" role="menu">
                <li class="dropdown-header">操作</li>
                <li><a href="/seller/category/list">列表</a></li>
                <li><a href="/seller/category/index">新增</a></li>
            </ul>
        </li>

        <li>
            <a href="/seller/loginpage"><i class="fa fa-fw fa-list-alt"></i> 登录页</a>
        </li>
        <li>
            <a href="/seller/logout"><i class="fa fa-fw fa-list-alt"></i> 登出</a>
        </li>
    </ul>
</nav>
<script>
    function doAct(s){
        var c = s.parentElement.className;
        if(c.indexOf('open') > -1){
            s.parentElement.className = 'dropdown close'
        }else{
            s.parentElement.className = 'dropdown open'
        }
    }
</script>

