<html>
    <#include "../common/head.ftl">
    <body>
    <div id="wrapper" class="toggled">
        <#include "../common/nav.ftl">
        <div id="page-content-wrapper">
            <div class="container-fluid">
                <div class="row clearfix">
                    <div class="col-md-12 column">
                        <table class="table table-condensed">
                            <thead>
                            <tr>
                                <th>商品id</th>
                                <th>商品名</th>
                                <th>图片</th>
                                <th>单价</th>
                                <th>库存</th>
                                <th>描述</th>
                                <th>类目</th>
                                <th>创建时间</th>
                                <th>修改时间</th>
                                <th colspan="2">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <#list ProductInfoPage.content as productInfo>
                                <tr>
                                    <td>${productInfo.productId}</td>
                                    <td>${productInfo.productName}</td>
                                    <td><img height="100" width="200" src="${productInfo.productIcon}"></td>
                                    <td>${productInfo.productPrice}</td>
                                    <td>${productInfo.productStock}</td>
                                    <td>${productInfo.getProductDescription()}</td>
                                    <td>${productInfo.categoryType}</td>
                                    <td>${productInfo.createTime}</td>
                                    <td>${productInfo.updateTime}</td>
                                    <td><a href="/seller/product/index?productId=${productInfo.productId}">修改</a></td>
                                    <td>
                                        <#if productInfo.getProductStatusEnum().getStatus() == 0>
                                            <a href="/seller/product/offsale?productId=${productInfo.productId}">下架</a>
                                        <#else>
                                            <a href="/seller/product/onsale?productId=${productInfo.productId}">上架</a>
                                        </#if>
                                    </td>
                                </tr>
                            </#list>
                            </tbody>
                        </table>
                    </div>
                    <#--分页-->
                    <div class="col-md-12 column">
                        <ul class="pagination pull-right">
                            <#if currentPage lte 1>
                                <li class="disabled">
                                    <a href="#">Prev</a>
                                </li>
                            <#else >
                                <li>
                                    <a href="/seller/product/list?page=#{currentPage-1}&size=#{currentSize}">Prev</a>
                                </li>
                            </#if>
                            <#list 1..ProductInfoPage.getTotalPages() as index>
                                <#if currentPage == index>
                                    <li class="disabled"><a href="#">#{index}</a></li>
                                <#else >
                                    <li><a href="/seller/product/list?page=#{index}&size=#{currentSize}">#{index}</a></li>
                                </#if>
                            </#list>
                            <#if currentPage gte ProductInfoPage.getTotalPages()>
                                <li class="disabled">
                                    <a href="#">Next</a>
                                </li>
                            <#else >
                                <li>
                                    <a href="/seller/product/list?page=#{currentPage+1}&size=#{currentSize}">Next</a>
                                </li>
                            </#if>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </body>
</html>
