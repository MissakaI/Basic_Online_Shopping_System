<%--
  Created by IntelliJ IDEA.
  User: Missaka Iddamalgoda
  Date: 2018-06-21
  Time: 10:20 PM
  To change this template use File | Settings | File Templates.
--%>

<!-- footer content -->
<footer>
    <div class="pull-right">
        Online Shopping System by <a href="#">Missaka Iddamalgoda</a>
    </div>
    <div class="clearfix"></div>
</footer>
<!-- /footer content -->
</div>
</div>

<!-- jQuery -->
<script src="vendors/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap -->
<script src="vendors/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- FastClick -->
<script src="vendors/fastclick/lib/fastclick.js"></script>
<!-- NProgress -->
<script src="vendors/nprogress/nprogress.js"></script>
<!-- iCheck -->
<script src="vendors/iCheck/icheck.min.js"></script>

<!-- Custom Theme Scripts -->
<script src="build/js/custom.min.js"></script>

<%
    String additionalScript = (String) request.getAttribute("additionalScript");
    if (additionalScript != null) {
        out.println("<script>");
        out.println(additionalScript);
        out.println("</script>");
    }
%>
</body>
</html>
