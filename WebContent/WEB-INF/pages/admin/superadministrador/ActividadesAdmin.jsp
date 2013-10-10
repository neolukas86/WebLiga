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

<title>Admin - <s:text name="palabra.actividades" /></title>
<sj:head locale="%{#request.locale}" jquerytheme="redmond"/>
</head>

<script type="text/javascript">
function toggleDiv(divId) {
   $("#"+divId).toggle();
}
</script>

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
					<h3><s:text name="palabra.actividades"/></h3>
				</div>

				<h2><a href="javascript:toggleDiv('deportes');"><s:text name="palabra.deportes"/></a></h2>
				
				<div id="deportes" style="display:none;">
				 
				<display:table name="listaDeportes" id="rowDeportes" class="table">
					<display:column property="id" titleKey="id" style="width:2%"/>
					<display:column property="nombre" titleKey="nombre.spanish"/>
					<display:column titleKey="nombre.ingles">
						<c:if test="${rowDeportes.nombre_EN != null }">
							<c:out value="${rowDeportes.nombre_EN}"/>
						</c:if>
						<c:if test="${rowDeportes.nombre_EN == null }">
							<c:out value="${rowDeportes.nombre}"/>
						</c:if>
					</display:column>
					<display:column titleKey="nombre.catalan">
						<c:if test="${rowDeportes.nombre_CA != null }">
							<c:out value="${rowDeportes.nombre_CA}"/>
						</c:if>
						<c:if test="${rowDeportes.nombre_CA == null }">
							<c:out value="${rowDeportes.nombre}"/>
						</c:if>
					</display:column>
					<display:column style="width:36px">
						<s:set var="EstDeportes">EstaDep${rowDeportes.id}</s:set>
						<s:set var="EliminarDeportes">EliminarDep${rowDeportes.id}</s:set>
						
						<s:url id="URLDeportes" action="EstadisticasActividad">
							<s:param name="id">${rowDeportes.id}</s:param>
						</s:url>					
						
						<sj:a id="ajaxLink%{EstDeportes}" 
							href="%{URLDeportes}" 
							targets="resultActividad" 
						>
						<img src="<s:url value="/images/template/chart-icon.png" />" 
							title="<s:text name="estadisticas"/>" width="16" height="16"/>
						</sj:a>
						
						<s:url id="URLEliminarDeportes" action="EliminarActividad">
							<s:param name="id">${rowDeportes.id}</s:param>
						</s:url>					
						
						<sj:a id="ajaxLink%{EliminarDeportes}" 
							href="%{URLEliminarDeportes}"  
						>
						<img src="<s:url value="/images/template/kick-icon.png" />" 
							title="<s:text name="expulsar"/>" width="16" height="16"/>
						</sj:a>						
											
					</display:column>
				</display:table>
				
				</div>
				
				<h2><a href="javascript:toggleDiv('mesa');"><s:text name="palabra.juegos.mesa"/></a></h2>
				
				<div id="mesa" style="display:none;">
				
				<display:table name="listaJuegosdemesa" id="rowMesa" class="table">
					<display:column property="id" titleKey="id" style="width:2%"/>
					<display:column property="nombre" titleKey="nombre.spanish"/>
					<display:column titleKey="nombre.ingles">
						<c:if test="${rowMesa.nombre_EN != null }">
							<c:out value="${rowMesa.nombre_EN}"/>
						</c:if>
						<c:if test="${rowMesa.nombre_EN == null }">
							<c:out value="${rowMesa.nombre}"/>
						</c:if>
					</display:column>
					<display:column titleKey="nombre.catalan">
						<c:if test="${rowMesa.nombre_CA != null }">
							<c:out value="${rowMesa.nombre_CA}"/>
						</c:if>
						<c:if test="${rowMesa.nombre_CA == null }">
							<c:out value="${rowMesa.nombre}"/>
						</c:if>
					</display:column>	
					<display:column style="width:36px">
						<s:set var="EstMesa">EstaMesa${rowMesa.id}</s:set>
						<s:set var="EliminarMesa">EliminarMesa${rowMesa.id}</s:set>
						
						<s:url id="URLMesa" action="EstadisticasActividad">
							<s:param name="id">${rowMesa.id}</s:param>
						</s:url>					
						
						<sj:a id="ajaxLink%{EstMesa}" 
							href="%{URLMesa}" 
							targets="resultActividad" 
						>
						<img src="<s:url value="/images/template/chart-icon.png" />" 
							title="<s:text name="estadisticas"/>" width="16" height="16"/>
						</sj:a>
						
						<s:url id="URLEliminarMesa" action="EliminarActividad">
							<s:param name="id">${rowMesa.id}</s:param>
						</s:url>					
						
						<sj:a id="ajaxLink%{EliminarMesa}" 
							href="%{URLEliminarMesa}"  
						>
						<img src="<s:url value="/images/template/kick-icon.png" />" 
							title="<s:text name="expulsar"/>" width="16" height="16"/>
						</sj:a>
														
					</display:column>									
				</display:table>				
				
				</div>
				
				
				
				
				<h2><a href="javascript:toggleDiv('cartas');"><s:text name="palabra.cartas"/></a></h2>
				
				<div id="cartas" style="display:none;">
				
				<display:table name="listaCartas" id="rowCartas" class="table">
					<display:column property="id" titleKey="id" style="width:2%"/>
					<display:column property="nombre" titleKey="nombre"/>
					<display:column style="width:36px">
						<s:set var="EstCartas">EstaCartas${rowCartas.id}</s:set>
						<s:set var="EliminarCartas">EliminarCartas${rowCartas.id}</s:set>
						
						<s:url id="URLEstCartas" action="EstadisticasActividad">
							<s:param name="id">${rowCartas.id}</s:param>
						</s:url>					
						
						<sj:a id="ajaxLink%{EstCartas}" 
							href="%{URLEstCartas}" 
							targets="resultActividad" 
						>
						<img src="<s:url value="/images/template/chart-icon.png" />" 
							title="<s:text name="estadisticas"/>" width="16" height="16"/>
						</sj:a>
						
						<s:url id="URLEliminarCartas" action="EliminarActividad">
							<s:param name="id">${rowCartas.id}</s:param>
						</s:url>					
						
						<sj:a id="ajaxLink%{EliminarCartas}" 
							href="%{URLEliminarCartas}"  
						>
						<img src="<s:url value="/images/template/kick-icon.png" />" 
							title="<s:text name="expulsar"/>" width="16" height="16"/>
						</sj:a>
														
					</display:column>					
				</display:table>
				
				</div>
				
				
				<h2><a href="javascript:toggleDiv('juegos');"><s:text name="palabra.videojuegos"/></a></h2>
				
				<div id="juegos" style="display:none;">
				
				<display:table name="listaJuegos" id="rowJuegos" class="table">
					<display:column property="id" titleKey="id" style="width:2%"/>
					<display:column property="nombre" titleKey="nombre"/>
					<display:column style="width:36px">
						<s:set var="EstJuegos">EstaJuegos${rowJuegos.id}</s:set>
						<s:set var="EliminarJuegos">EliminarJuegos${rowJuegos.id}</s:set>
						
						<s:url id="URLEstJuegos" action="EstadisticasActividad">
							<s:param name="id">${rowJuegos.id}</s:param>
						</s:url>					
						
						<sj:a id="ajaxLink%{EstJuegos}" 
							href="%{URLEstJuegos}" 
							targets="resultActividad" 
						>
						<img src="<s:url value="/images/template/chart-icon.png" />" 
							title="<s:text name="estadisticas"/>" width="16" height="16"/>
						</sj:a>
						
						<s:url id="URL%{EliminarJuegos}" action="EliminarActividad">
							<s:param name="id">${rowJuegos.id}</s:param>
						</s:url>					
						
						<sj:a id="ajaxLink%{EliminarJuegos}" 
							href="URL%{EliminarJuegos}"  
						>
						<img src="<s:url value="/images/template/kick-icon.png" />" 
							title="<s:text name="expulsar"/>" width="16" height="16"/>
						</sj:a>
														
					</display:column>					
				</display:table>
				
				</div>
				
				<div id="resultActividad" class="result ui-widget-content ui-corner-all" style="padding:5px; margin:10px"></div>					    	
			       	
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