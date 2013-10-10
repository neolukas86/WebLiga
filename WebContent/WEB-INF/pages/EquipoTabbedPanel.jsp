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
<!--[if lt IE 9]>
	<link href="<s:url value="/css/explorer8.css" />" rel="stylesheet" type="text/css" />
<![endif]-->

<title><s:property value="nombre"/> - <s:text name="palabra.equipo"/> - <s:text name="title" /></title>
<sj:head locale="%{#request.locale}" jqueryui="true" jquerytheme="redmond"/>
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
			<div class="menu_central_header_miEquipo_imagen">
				<h3><s:property value="nombre" /></h3>
			</div>
			</div>
<!-- 			</div> -->

			<s:if test="tab != null">
				<s:if test="tab == 'admin'">
					<s:set name="tabSelected" value="2" />
				</s:if>
				<s:elseif test="tab == 'join'">
					<s:set name="tabSelected" value="3" />
				</s:elseif>
			</s:if>
			
			<s:else>
				<s:set name="tabSelected" value="1" />
			</s:else>
			
			<s:url var="noticiasURL" action="GoNoticiasEquipo"><s:param name="id" value="id" /></s:url>
			<s:url var="equipoURL" action="EquipoDisplay"><s:param name="id" value="id" /></s:url>
    		<s:url var="adminURL" action="GoAdminEquipo"><s:param name="id" value="id" /></s:url>
    		<s:url var="abandonarURL" action="GoAbandonarEquipo"><s:param name="id" value="id" /></s:url>
    		<s:url var="unirseURL" action="GoUnirEquipo"><s:param name="id" value="id" /></s:url>
    
    		<sj:tabbedpanel id="mytabsEquipo" selectedTab="1" spinner="%{getText('cargando')}" 
    				cssClass="sombra" cssStyle="margin-left:2px; margin-bottom:3px;">
    			<sj:tab id="tab1" href="%{noticiasURL}" key="palabra.noticias"/>
      			<sj:tab id="tab2" href="%{equipoURL}" key="info"/>
      			
				<s:if test="rolequipo == 'creador' || rolequipo == 'mod' || rolequipo == 'normaluser'">
			    	<s:if test="rolequipo == 'creador' || rolequipo == 'mod'">
			    		<sj:tab id="tab3" href="%{adminURL}" key="admin"/>
			    	</s:if> 
			    	<sj:tab id="tab4" href="%{abandonarURL}" key="abandonar"/>     		
			    </s:if>
				<s:elseif test="rolequipo != 'baneado'">
					<sj:tab id="tab5" href="%{unirseURL}" key="unirse"/>
				</s:elseif>
    		</sj:tabbedpanel>
					
		</div>
	</div>
  </div>
 
  <div class="clr"></div>
  <jsp:include page="/WEB-INF/pages/include/Footer.jsp" />
</div>

</body>
</html>