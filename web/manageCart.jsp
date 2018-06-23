<%@ page import="com.ijse.onlineshoppingsys.controller.ManageCartController" %>
<%@ page import="com.ijse.onlineshoppingsys.dto.CartItemDTO" %>
<%@ page import="java.util.Collection" %>
<%--
  Created by IntelliJ IDEA.
  User: Missaka Iddamalgoda
  Date: 2018-06-20
  Time: 10:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@include file="header.jsp" %>
<!-- page content -->
<div class="right_col" role="main">
    <div class="">
        <div class="page-title">
            <div class="title_left">
                <h3>Your Cart</h3>
            </div>

            <div class="title_right">
                <div class="col-md-5 col-sm-5 col-xs-12 form-group pull-right top_search">
                    <div class="input-group">
                        <input type="text" class="form-control" placeholder="Search for...">
                        <span class="input-group-btn">
                      <button class="btn btn-default" type="button">Go!</button>
                    </span>
                    </div>
                </div>
            </div>
        </div>

        <div class="clearfix"></div>

        <div class="row">
            <div class="col-md-12 col-sm-12 col-xs-12">
                <div class="x_panel">
                    <div class="x_title">
                        <h2>Manage your cart here</h2>
                        <ul class="nav navbar-right panel_toolbox">
                            <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                            </li>
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                                   aria-expanded="false"><i class="fa fa-wrench"></i></a>
                                <ul class="dropdown-menu" role="menu">
                                    <li><a href="#">Settings 1</a>
                                    </li>
                                    <li><a href="#">Settings 2</a>
                                    </li>
                                </ul>
                            </li>
                            <li><a class="close-link"><i class="fa fa-close"></i></a>
                            </li>
                        </ul>
                        <div class="clearfix"></div>
                    </div>
                    <div class="x_content">

                        <div class="table-responsive">
                            <table class="table table-striped jambo_table">
                                <thead>
                                <tr class="headings">
                                    <th>
                                        <input type="checkbox" id="check-all" class="flat">
                                    </th>
                                    <th class="column-title">Item ID</th>
                                    <th class="column-title">Description</th>
                                    <th class="column-title">Qty in cart</th>
                                    <th class="column-title">Unit Price</th>
                                    <th class="column-title">Total</th>
                                    <th class="column-title no-link last"><span class="no br">Action</span>
                                    </th>
                                    <%--<th class="bulk-actions" colspan="7">
                                        <a class="antoo" style="color:#fff; font-weight:500;">Bulk Actions ( <span class="action-cnt"> </span> ) <i class="fa fa-chevron-down"></i></a>
                                    </th>--%>
                                </tr>
                                </thead>

                                <tbody>
                                <%
                                    ManageCartController controller = new ManageCartController();
                                    Collection<CartItemDTO> itemList = controller.getItemsInCart(session);
                                    int i = 0;
                                    if (itemList != null) {
                                        for (CartItemDTO itm : itemList) {
                                %>
                                <tr class="<%=i++%2==0?"even":"odd"%> pointer">
                                    <td class="a-center ">
                                        <input type="checkbox" class="flat" name="customer_ids"
                                               value="<%=itm.getId()%>">
                                    </td>
                                    <td class=""><%=itm.getId()%>
                                    </td>
                                    <td class=""><%=itm.getName()%>
                                    </td>
                                    <td class=""><%=itm.getQty()%>
                                    </td>
                                    <td class=""><%=itm.getUnitPrice()%>
                                    </td>
                                    <td class=""><%=itm.getTotalAmount()%>
                                    </td>
                                    <td class="last">
                                        <form action="/manageCartController" method="post">
                                            <input type="hidden" value="<%=itm.getId()%>" name="item_id">
                                            <a class="remove"><i class="fa fa-cart-arrow-down"></i><span>Remove from Cart</span></a>
                                        </form>
                                    </td>
                                </tr>
                                <%
                                    }
                                } else {
                                %>
                                <tr class="even pointer">
                                    <td class="a-center " colspan="7">
                                        No items in cart
                                    </td>
                                </tr>
                                <%
                                    }
                                %>
                                </tbody>
                            </table>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- /page content -->

<%--<script>
    $(".remove").click(function (event) {
        var form=$(".remove").parent();
        form.submit();
    });
</script>--%>

<%
    String script = "    $(\".remove\").click(function (event) {\n" +
            "        var form=$(\".remove\").parent();\n" +
            "        form.submit();\n" +
            "    });";
    request.setAttribute("additionalScript", script);
%>

<%@include file="footer.jsp" %>
