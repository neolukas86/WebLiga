<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<link href="<s:url value="/images/iconos/web/favicon.png" />" rel="shortcut icon"  />
<link href="<s:url value="/css/style.css" />" rel="stylesheet" type="text/css" />
<link href="<s:url value="/css/displaytag.css" />" rel="stylesheet" type="text/css" />
<!--[if lt IE 9]>
	<link href="<s:url value="/css/explorer8.css" />" rel="stylesheet" type="text/css" />
<![endif]-->

<title>Admin - <s:text name="palabra.equipos" /></title>
<sj:head locale="%{#request.locale}" jquerytheme="redmond"/>
</head>

<body>

<div class="main">
  <jsp:include page="/WEB-INF/pages/include/admin/HeaderAdmin.jsp" />
  <div class="clr"></div>
  <div class="body">
    <div class="body_resize">
    	<jsp:include page="/WEB-INF/pages/include/admin/MenuAdmin.jsp" />
	    <div class="menu_central">
	    	<div  class="menu_central_header degradado sombra rounded-corners">
				<h3><s:text name="zona.administracion"/></h3>
			</div>
	    	<div class="menu_central_body sombra rounded-corners" >
	    	<div style="padding:5px">
	    		<div class="menu_central_subheader sombra rounded-corners centrado" style="width:700px; margin-bottom:20px">
					<h3><s:text name="palabra.equipos"/></h3>
				</div>

				<h2><s:text name="palabra.equipos"/></h2>

				 
<s:if test="equipoList != null">
	<s:if test="equipoList.size() > 10">
		<display:table name="equipoList" id="row" class="table" pagesize="10" requestURI=""> 
			<display:column property="id" titleKey="id" style="width:2%"/>
		     <display:column titleKey="nombre" sortable="true" sortName="nombre" property="nombre" />
		
		  <display:column style="width:36px">
						<s:set var="EstEquipos">EstaEq${row.id}</s:set>
						<s:set var="EliminarEquipo">EliminarEq${row.id}</s:set>
						
						<s:url id="URLEquipos" action="EstadisticasEquipo">
							<s:param name="id">${row.id}</s:param>
						</s:url>					
						
						<sj:a id="ajaxLink%{EstEquipo}" 
							href="%{URLEquipos}" 
							targets="resultEquipo" 
						>
						<img src="<s:url value="/images/template/chart-icon.png" />" 
							title="<s:text name="estadisticas"/>" width="16" height="16"/>
						</sj:a>
						
						<s:url id="URLEliminarEquipos" action="EliminarEquipo">
							<s:param name="id">${row.id}</s:param>
						</s:url>					
						
						<sj:a id="ajaxLink%{EliminarEquipo}" 
							href="%{URLEliminarEquipos}"  
						>
						<img src="<s:url value="/images/template/kick-icon.png" />" 
							title="<s:text name="expulsar"/>" width="16" height="16"/>
						</sj:a>						
											
			</display:column>		
		 </display:table>
	</s:if>
	
	<s:else>
	<div class="solo_pagebanner">
		<display:table name="equipoList" id="row" class="table" pagesize="10" requestURI=""> 
			<display:column property="id" titleKey="id" style="width:2%"/>
		     <display:column titleKey="nombre" sortable="true" sortName="nombre" property="nombre" />
		
		  <display:column style="width:36px">
						<s:set var="EstEquipos">EstaEq${row.id}</s:set>
						<s:set var="EliminarEquipo">EliminarEq${row.id}</s:set>
						
						<s:url id="URLEquipos" action="EstadisticasEquipo">
							<s:param name="id">${row.id}</s:param>
						</s:url>					
						
						<sj:a id="ajaxLink%{EstEquipos}" 
							href="%{URLEquipos}" 
							targets="resultEquipo" 
						>
						<img src="<s:url value="/images/template/chart-icon.png" />" 
							title="<s:text name="estadisticas"/>" width="16" height="16"/>
						</sj:a>
						
						<s:url id="URLEliminarEquipos" action="EliminarEquipo">
							<s:param name="id">${row.id}</s:param>
						</s:url>					
						
						<sj:a id="ajaxLink%{EliminarEquipo}" 
							href="%{URLEliminarEquipos}"  
						>
						<img src="<s:url value="/images/template/kick-icon.png" />" 
							title="<s:text name="expulsar"/>" width="16" height="16"/>
						</sj:a>						
											
			</display:column>		
		 </display:table>	
	</div>
	</s:else>
</s:if>
				
			
				
				<div id="resultEquipo" class="result ui-widget-content ui-corner-all" style="padding:5px; margin:10px"></div>					    	
			       	
	          	<div class="clr"></div>
	          	</div>
			</div>
			
	    </div>
  </div>
  </div>
  <div class="clr"></div>
  <jsp:include page="/WEB-INF/pages/include/Footer.jsp" />
</div>
    
</body>
</html>