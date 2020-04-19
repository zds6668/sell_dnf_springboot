<html>
<#include "../common/head.ftl">
<body>
<div id="wrapper" class="toggled">
    <#include "../common/nav.ftl">
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <form role="form" method="post" action="/seller/category/save">
                        <div class=" form-group">
                            <label>类名</label><input name="categoryName" type="text" class="form-control" value="${(productCategory.categoryName)!''}" />
                        </div>
                        <div class="form-group">
                            <label>type</label><input name="categoryType" type="number" class="form-control" value="${(productCategory.categoryType)!''}" />
                        </div>
                        <div class="form-group" hidden>
                            <input hidden name="categoryId" type="number" class="form-control" value="${(productCategory.categoryId)!''}" />
                        </div>
                        <button type="submit" class="btn btn-default">提交修改</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
