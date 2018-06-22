<%@ page import="java.util.List" %>
<%@ page import="com.ijse.onlineshoppingsys.dto.CustomerDTO" %>
<%@ page import="com.ijse.onlineshoppingsys.controller.ManageCustomerController" %>
<%@ page import="com.mysql.cj.xdevapi.JsonArray" %>
<%@ page import="com.google.gson.Gson" %><%--
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
                <h3>Manage your customers here</h3>
            </div>

            <%--
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
            --%>
        </div>

        <div class="clearfix"></div>

        <div class="row">
            <div class="col-md-12 col-sm-12 col-xs-12">
                <div class="x_panel">
                    <div class="x_title">
                        <h2>Registered Customers</h2>
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
                                    <th class="column-title">Customer ID</th>
                                    <th class="column-title">Name</th>
                                    <th class="column-title">NIC</th>
                                    <th class="column-title">Mobile</th>
                                    <th class="column-title no-link last"><span class="nobr">Action</span>
                                    </th>
                                    <%--<th class="bulk-actions" colspan="7">
                                        <a class="antoo" style="color:#fff; font-weight:500;">Bulk Actions ( <span class="action-cnt"> </span> ) <i class="fa fa-chevron-down"></i></a>
                                    </th>--%>
                                </tr>
                                </thead>

                                <tbody>
                                <%
                                    List<CustomerDTO> customerList = new ManageCustomerController().viewCustomers();
                                    for (int i = 0; i < customerList.size(); i++) {
                                        CustomerDTO cust = customerList.get(i);
                                %>
                                <tr class="<%=i%2==0?"even":"odd"%> pointer">
                                    <td class="a-center ">
                                        <input type="checkbox" class="flat" name="customer_ids"
                                               value="<%=cust.getCust_id()%>">
                                    </td>
                                    <td class=""><%=cust.getCust_id()%>
                                    </td>
                                    <td class=""><%=cust.getName()%>
                                    </td>
                                    <td class=""><%=cust.getNic()%>
                                    </td>
                                    <td class=""><%=cust.getMobile()%>
                                    </td>
                                    <td class="last">
                                        <a><input type="hidden" value="<%=i%>" class="edit"><i
                                                class="fa fa-user-plus"></i><span>Edit</span></a>
                                        <a><input type="hidden" value="<%=cust.getCust_id()%>" name="cust_id"
                                                  class="delete"><i class="fa fa-user-md"></i><span>Delete</span></a>
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

    <div class="row">
        <div class="col-md-12 col-sm-12 col-xs-12">
            <div class="x_panel">
                <div class="x_title">
                    <h2>Registered Customers</h2>
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
                    <form id="demo-form2" data-parsley-validate class="form-horizontal form-label-left" method="post"
                          action="/manageCustomerController">

                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="cust_id">Customer ID</label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input type="text" id="cust_id" disabled="disabled" name="cust_id"
                                       class="form-control col-md-7 col-xs-12">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="cust_name">Name<span
                                    class="required">*</span>
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input type="text" id="cust_name" required="required" name="cust_name"
                                       class="form-control col-md-7 col-xs-12">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="nic">NIC<span
                                    class="required">*</span>
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input type="text" id="nic" name="nic" required="required"
                                       class="form-control col-md-7 col-xs-12">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="mobile" class="control-label col-md-3 col-sm-3 col-xs-12">Mobile Number</label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input id="mobile" name="mobile" class="form-control col-md-7 col-xs-12" type="text">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="username" class="control-label col-md-3 col-sm-3 col-xs-12">Username</label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input id="username" name="username" class="form-control col-md-7 col-xs-12"
                                       type="text">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="password" class="control-label col-md-3 col-sm-3 col-xs-12">Password</label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input id="password" name="password" class="form-control col-md-7 col-xs-12"
                                       type="password">
                            </div>
                        </div>
                        <div class="ln_solid"></div>
                        <div class="form-group">
                            <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
                                <button class="btn btn-primary" type="reset" id="reset">Reset</button>
                                <button type="submit" class="btn btn-success" id=submit>Submit</button>
                            </div>
                        </div>

                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- /page content -->
<%--<script>
    var text='{"customers":}';
    var obj=JSON.parse(text);
    var isEditing=false;
    $(".edit").parent().click(function (event) {
        var index = $(this).children(".edit").val();
        $("#cust_id").val(obj.customers[index].cust_id);
        $("#cust_name").val(obj.customers[index].name);
        $("#nic").val(obj.customers[index].nic);
        $("#mobile").val(obj.customers[index].mobile);
        isEditing=true;
        event.preventDefault();
    });

    $(".delete").parent().click(function (event) {
        var cust_id = $(this).children(".delete").val();
        var form = $('<form></form>');

        form.attr("method", "post");
        form.attr("action", "/manageCustomerController");

        var fieldCustId = $('<input></input>');
        fieldCustId.attr("type", "hidden");
        fieldCustId.attr("name", "cust_id");
        fieldCustId.attr("value", cust_id);
        form.append(fieldCustId);

        var fieldAction= $('<input></input>');
        fieldAction.attr("type", "hidden");
        fieldAction.attr("name", "action");
        fieldAction.attr("value", "delete");
        form.append(fieldAction);

        // The form needs to be a part of the document in
        // order for us to be able to submit it.
        $(document.body).append(form);
        form.submit();
    });

    $("#submit").click(function (event) {
        var form=$("#demo-form2");
        if (isEditing){
            var fieldAction= $('<input></input>');
            fieldAction.attr("type", "hidden");
            fieldAction.attr("name", "action");
            fieldAction.attr("value", "edit");
            form.append(fieldAction);
            form.submit();
        }else{
            var fieldAction= $('<input></input>');
            fieldAction.attr("type", "hidden");
            fieldAction.attr("name", "action");
            fieldAction.attr("value", "new");
            form.append(fieldAction);
            form.submit();
        }
        event.preventDefault();
    });

    $("#reset").click(function (event) {
        isEditing=false;
    });

</script>--%>
<%
    String script = "var text='{\"customers\":" + new Gson().toJson(customerList) + "}';\n" +
            " var obj=JSON.parse(text);\n" +
            " var isEditing=false;" +
            " $(\".edit\").parent().click(function (event) {\n" +
            "        var index = $(this).children(\".edit\").val();\n" +
            "        isEditing=true;\n" +
            "        $(\"#cust_id\").val(obj.customers[index].cust_id);\n" +
            "        $(\"#cust_name\").val(obj.customers[index].name);\n" +
            "        $(\"#nic\").val(obj.customers[index].nic);\n" +
            "        $(\"#mobile\").val(obj.customers[index].mobile);" +
            " });\n";

    script += "$(\".delete\").parent().click(function (event) {\n" +
            "        var cust_id = $(this).children(\".delete\").val();\n" +
            "        var form = $('<form></form>');\n" +
            "\n" +
            "        form.attr(\"method\", \"post\");\n" +
            "        form.attr(\"action\", \"/manageCustomerController\");\n" +
            "\n" +
            "        var fieldCustId = $('<input></input>');\n" +
            "        fieldCustId.attr(\"type\", \"hidden\");\n" +
            "        fieldCustId.attr(\"name\", \"cust_id\");\n" +
            "        fieldCustId.attr(\"value\", cust_id);\n" +
            "        form.append(fieldCustId);\n" +
            "\n" +
            "        var fieldAction= $('<input></input>');\n" +
            "        fieldAction.attr(\"type\", \"hidden\");\n" +
            "        fieldAction.attr(\"name\", \"action\");\n" +
            "        fieldAction.attr(\"value\", \"delete\");\n" +
            "        form.append(fieldAction);\n" +
            "\n" +
            "        // The form needs to be a part of the document in\n" +
            "        // order for us to be able to submit it.\n" +
            "        $(document.body).append(form);\n" +
            "        form.submit();\n" +
            "    });\n";

    script += "$(\"#submit\").click(function (event) {\n" +
            "        var form=$(\"#demo-form2\");\n" +
            "        if (isEditing){\n" +
            "            var fieldAction= $('<input></input>');\n" +
            "            fieldAction.attr(\"type\", \"hidden\");\n" +
            "            fieldAction.attr(\"name\", \"action\");\n" +
            "            fieldAction.attr(\"value\", \"edit\");\n" +
            "            form.append(fieldAction);\n" +
            "            form.submit();\n" +
            "        }else{\n" +
            "            var fieldAction= $('<input></input>');\n" +
            "            fieldAction.attr(\"type\", \"hidden\");\n" +
            "            fieldAction.attr(\"name\", \"action\");\n" +
            "            fieldAction.attr(\"value\", \"new\");\n" +
            "            form.append(fieldAction);\n" +
            "            form.submit();\n" +
            "        }\n" +
            "    });\n" +
            "\n" +
            "    $(\"#reset\").click(function (event) {\n" +
            "        isEditing=false;\n" +
            "    });";

    request.setAttribute("additionalScript", script);
%>
<%@include file="footer.jsp" %>

