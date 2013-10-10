<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<link href="<s:url value="/images/iconos/web/favicon.png" />" rel="shortcut icon"  />
<link href="<s:url value="/css/style.css" />" rel="stylesheet" type="text/css" />
<link href="<s:url value="/css/displaytag.css" />" rel="stylesheet" type="text/css" />
<!--[if lt IE 9]>
	<link href="<s:url value="/css/explorer8.css" />" rel="stylesheet" type="text/css" />
<![endif]-->

<title><s:text name="mis.comunidades"/> - <s:text name="title" /></title>
</head>
 
<body>
<%-- <h2><s:text name="subtitle.list.comunidades" /></h2> --%>
<div class="main">
  <jsp:include page="/WEB-INF/pages/include/Header.jsp" />

  <div class="clr"></div>
  <div class="body">
    <div class="body_resize">
    	<jsp:include page="/WEB-INF/pages/include/Menu.jsp" />
	    <div class="menu_central">
	    <div class="menu_central_header degradado sombra rounded-corners">
	    <div class="menu_central_header_comunidad_imagen">
				<h3><s:text name="mis.comunidades"/></h3>
			</div>
		</div>
	    <div class="menu_central_body sombra rounded-corners">
	    
			<jsp:include page="/WEB-INF/pages/display/comunidad/ComunidadesDisplayPropias.jsp" />

		</div>
		</div>
	</div>
  </div>
 
  <div class="clr"></div>
  <jsp:include page="/WEB-INF/pages/include/Footer.jsp" />
</div>

</body>
</html>