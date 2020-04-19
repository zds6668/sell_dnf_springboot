<html>
<#include "../common/head.ftl">
    <body><div class="container">
        <div class="row clearfix">
            <div class="col-md-12 column">
                <#if status == 12>
                    <div class="alert alert-dismissable alert-danger">
                        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                        <h4>
                            ${msg}
                        </h4> <strong>${msg}</strong> Best check yo self, you're not looking too good. <a href=${url} class="alert-link">即将跳转</a>
                    </div>
                <#else >
                    <div class="alert alert-dismissable alert-success">
                        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                        <h4>
                            ${msg}
                        </h4> <strong>${msg}</strong> 操作成功，等一哈. <a href=${url} class="alert-link">即将跳转</a>
                    </div>
                </#if>
            </div>
        </div>
    </div></body>
<script>setTimeout('location.href="${url}"', 1000);</script>
</html>