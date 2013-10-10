<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- <title>Error 404 - Página no encontrada</title> -->
<%-- <link href="<s:url value="/images/iconos/web/favicon.png" />" rel="shortcut icon"  /> --%>
<link href="<s:url value="/css/style.css" />" rel="stylesheet" type="text/css" />
<link href="<s:url value="%{getText('css.style.locale')}" />" rel="stylesheet" type="text/css" />
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
				<div class="menu_central_body_error sombra rounded-corners error500_imagen" style="height:420px">
<%-- 					<img src="<s:url value="/images/error/error404_ralph.png" />" width="500" height="423" border="0" alt="imagen" /> --%>
				</div>
			</div>
		</div>
	  </div>
		<div class="clr"></div>
	    <jsp:include page="/WEB-INF/pages/include/Footer.jsp" />


  		
</div>
</body>
</html>