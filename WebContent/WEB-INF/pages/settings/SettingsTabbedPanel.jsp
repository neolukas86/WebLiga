<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>


<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<link href="<s:url value="/images/iconos/web/favicon.png" />" rel="shortcut icon"  />
<link href="<s:url value="/css/style.css" />" rel="stylesheet" type="text/css" />
<link href="<s:url value="%{getText('css.style.locale')}" />" rel="stylesheet" type="text/css" />
<link href="<s:url value="/css/displaytag.css" />" rel="stylesheet" type="text/css" />
<sj:head locale="%{#request.locale}" jqueryui="true" jquerytheme="redmond"/>
<!--[if lt IE 9]>
	<link href="<s:url value="/css/explorer8.css" />" rel="stylesheet" type="text/css" />
<![endif]-->

<title><s:text name="mi.perfil"/> - <s:text name="title" /></title>

</head>
 
<body>
<div class="main">
  <jsp:include page="/WEB-INF/pages/include/Header.jsp" />
  <div class="clr"></div>
  <div class="body">
    <div class="body_resize">
    	<jsp:include page="/WEB-INF/pages/include/Menu.jsp" />
	    <div class="menu_central">
	    <div class="menu_central_header degradado sombra rounded-corners">
			<div class="menu_central_header_miJugador_imagen">
				<h3><s:property value="#session.alias" /></h3>
			</div>
			</div>
			
			<s:url id="personalURL" action="GoModifyUserProperties"/>
			<s:url id="passwordURL" action="GoModifyPassword"/>
			<s:url id="webURL" action="GoModifyUserPropertiesWeb"/>
    
    		<sj:tabbedpanel id="mytabsSettings" selectedTab="0" cssClass="sombra" spinner="%{getText('cargando')}" 
    		cssStyle="margin-left:2px; margin-bottom:3px;">
      			<sj:tab id="tab1" href="%{personalURL}" key="personal.settings"/>
      			<sj:tab id="tab2" href="%{webURL}" key="web.settings"/>
      			<sj:tab id="tab3" href="%{passwordURL}" key="password"/>
    		</sj:tabbedpanel>
					
		</div>
	</div>
  </div>
 
  <div class="clr"></div>
  <jsp:include page="/WEB-INF/pages/include/Footer.jsp" />
</div>

</body>
</html>