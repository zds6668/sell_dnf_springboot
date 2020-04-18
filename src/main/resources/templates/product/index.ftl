<html>
<#include "../common/head.ftl">
<body>
<div id="wrapper" class="toggled">
    <#include "../common/nav.ftl">
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <form role="form" method="post" action="/seller/product/save">
                        <div class=" form-group" hidden>
                            <input name="productId" type="text" value="${(productInfo.productId)!''}" />
                        </div>
                        <div class=" form-group">
                            <label>商品名</label><input name="productName" type="text" class="form-control" value="${(productInfo.productName)!''}" />
                        </div>
                        <div class="form-group">
                            <label>价格</label><input name="productPrice" type="text" class="form-control" value="${(productInfo.productPrice)!''}" />
                        </div>
                        <div class="form-group">
                            <label>库存</label><input name="productStock" type="number" class="form-control" value="${(productInfo.productStock)!''}" />
                        </div>
                        <div class="form-group">
                            <label>简介</label><input name="productDescription" type="text" class="form-control" value="${(productInfo.productDescription)!''}" />
                        </div>
                        <div class="form-group">
                            <label>图片</label><img width="400" height="250" src="${(productInfo.productIcon)!''}" alt=""><input name="productIcon" type="text" class="form-control" value="${(productInfo.productIcon)!''}" />
                        </div>
                        <div class="form-group">
                            <label>分类</label>
                            <select name="categoryType" class="form-control">
                                <#list productCategoryList as category>
                                    <option value="${category.categoryType}"
                                        <#if (productInfo.categoryType)?? && productInfo.categoryType == category.categoryType>
                                            selected
                                        </#if>
                                    >${category.categoryName}
                                    </option>
                                </#list>
                            </select>
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
