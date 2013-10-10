<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Error 500 - Error Interno del Servidor</title>
</head>
<body>

<div class="main">
  <jsp:include page="/WEB-INF/pages/include/Header.jsp" />
  <div class="clr"></div>
  <div class="body">
    <div class="body_resize">
    	<jsp:include page="/WEB-INF/pages/include/Menu.jsp" />
	    <div class="menu_central">
<!-- 	    <div class="menu_central_body"> -->
			<img src="<s:url value="/images/error/simpson500.png" />" width="316" height="315" border="0" alt="imagen" />
<!-- 		</div> -->
		</div>
	</div>
  </div>
	<div class="clr"></div>
    <jsp:include page="/WEB-INF/pages/include/Footer.jsp" />
</div>
</body>
</html>