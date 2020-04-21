<html>
<#include "../common/head.ftl">
<body>
<div id="wrapper" class="toggled">
    <#include "../common/nav.ftl">
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <form role="form" method="post" action="/seller/login">
                        <div class=" form-group">
                            <label for="exampleInputUsername1">用户名</label>
                            <input name="username" type="text"/>
                        </div>
                        <div class=" form-group">
                            <label for="exampleInputPassword1">密 码</label>
                            <input name="password" type="text"/>
                        </div>
                        <button type="submit" class="btn btn-default">登录</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
