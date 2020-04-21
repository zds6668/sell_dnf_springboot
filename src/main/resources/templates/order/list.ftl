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
                                <th>订单id</th>
                                <th>姓名</th>
                                <th>手机号</th>
                                <th>地址</th>
                                <th>金额</th>
                                <th>订单状态</th>
                                <th>支付状态</th>
                                <th>创建时间</th>
                                <th colspan="2">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <#list orderDTOPage.content as orderDTO>
                                <tr>
                                    <td>${orderDTO.orderId}</td>
                                    <td>${orderDTO.buyerName}</td>
                                    <td>${orderDTO.buyerPhone}</td>
                                    <td>${orderDTO.buyerAddress}</td>
                                    <td>${orderDTO.orderAmount}</td>
                                    <td>${orderDTO.getOrderStatusEnum().msg}</td>
                                    <td>${orderDTO.getPayStatusEnum().msg}</td>
                                    <td>${orderDTO.createTime}</td>
                                    <td><a href="/seller/order/detail?orderId=${orderDTO.orderId}">详情</a></td>
                                    <td>
                                        <#if orderDTO.getOrderStatusEnum().status == 0>
                                            <a href="/seller/order/cancel?orderId=${orderDTO.orderId}">取消</a>
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
                                    <a href="/seller/order/list?page=#{currentPage-1}&size=#{currentSize}">Prev</a>
                                </li>
                            </#if>
                            <#list 1..orderDTOPage.getTotalPages() as index>
                                <#if currentPage == index>
                                    <li class="disabled"><a href="#">#{index}</a></li>
                                <#else >
                                    <li><a href="/seller/order/list?page=#{index}&size=#{currentSize}">#{index}</a></li>
                                </#if>
                            </#list>
                            <#if currentPage gte orderDTOPage.getTotalPages()>
                                <li class="disabled">
                                    <a href="#">Next</a>
                                </li>
                            <#else >
                                <li>
                                    <a href="/seller/order/list?page=#{currentPage+1}&size=#{currentSize}">Next</a>
                                </li>
                            </#if>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script>
        var websocket = null;
        if ('WebSocket' in window) {
            websocket = new WebSocket('ws://127.0.0.1:8080/webSocket');
        } else {
            alert('该浏览器不支持websocket');
        }
        websocket.onopen = function (event) {
            console.log('建立连接');
        }
        websocket.onclose = function (event) {
            console.log('关闭连接');
        }
        websocket.onmessage = function (event) {
            console.log('收到消息');
        }
        websocket.onerror = function () {
            alert('发生错误');
        }
        websocket.onbeforeunload = function () {
            websocket.close();
        }
    </script>
    </body>
</html>

<#--<h1></h1><#list orderDTOPage.content as orderDTO>-->
<#--    ${orderDTO.orderId}<br>-->
<#--</#list>-->