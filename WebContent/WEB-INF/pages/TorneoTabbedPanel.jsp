<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>


<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<link href="<s:url value="/images/iconos/web/favicon.png" />" rel="shortcut icon"  />
<link href="<s:url value="/css/style.css" />" rel="stylesheet" type="text/css" />
<link href="<s:url value="%{getText('css.style.locale')}" />" rel="stylesheet" type="text/css" />
<link href="<s:url value="/css/displaytag.css" />" rel="stylesheet" type="text/css" />
<!--[if lt IE 9]>
	<link href="<s:url value="/css/explorer8.css" />" rel="stylesheet" type="text/css" />
<![endif]--> 

<title><s:property value="nombre"/> - <s:text name="palabra.torneo"/> - <s:text name="title" /></title>
<sj:head locale="%{#request.locale}" jquerytheme="redmond"/>
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
			<div class="menu_central_header_miTorneo_imagen">
				<s:set var="rutaimagenActividad">${torneo.parentActividad.rutaimagen}</s:set>
				<s:set var="actividadNombre">${torneo.parentActividad.nombre}</s:set>
				
				<h3><s:property value="nombre" /></h3>
				<img src="<s:url value="%{'/images/iconos/actividades/25'}%{rutaimagenActividad}"/>" 
					title="<s:property value="actividadNombre"/>" width="25" height="25" 
					style="float:right;margin-top:-32px;margin-right:10px"/>
				<s:a action="VerComunidad"><s:param name="id" >${torneo.parentComunidad.id}</s:param>
					<img src="<s:url value="%{getText('ruta.imagen.volver.comunidad')}"/>" 
						width="124" height="45" style="float:right;margin-top:-45px;margin-right:50px"/>
				</s:a>
				
			</div>
			</div>
			
			
			<s:if test="!tab.isEmpty()">
				<s:if test="tab == 'admin'">
					<s:set name="tabSelected" value="2" />
				</s:if>
				<s:elseif test="tab == 'calendario'">
					<s:if test="estado != 3 && (roltorneo == 'creador' || roltorneo == 'admin' || roltorneo == 'mod')">
						<s:set name="tabSelected" value="3" />
					</s:if>
					<s:else>
						<s:set name="tabSelected" value="2" />
					</s:else>
				</s:elseif>
				<s:elseif test="tab == 'partidos'">
					<s:if test="estado != 3 && (roltorneo == 'creador' || roltorneo == 'admin' || roltorneo == 'mod')">
						<s:set name="tabSelected" value="4" />
					</s:if>
					<s:else>
						<s:set name="tabSelected" value="3" />
					</s:else>
				</s:elseif>
				<s:elseif test="tab == 'clasificacion'">
					<s:if test="estado != 3 && (roltorneo == 'creador' || roltorneo == 'admin' || roltorneo == 'mod')">
						<s:set name="tabSelected" value="5" />
					</s:if>
					<s:else>
						<s:set name="tabSelected" value="4" />
					</s:else>
				</s:elseif>
			</s:if>
			
			<s:else>
				<s:set name="tabSelected" value="1" />
			</s:else>
			
			<s:url var="noticiasURL" action="GoNoticiasTorneo"><s:param name="id" value="id" /></s:url>
			<s:url var="torneoURL" action="TorneoDisplay"><s:param name="id" value="id" /></s:url>
			<s:url var="calendarioURL" action="GoCalendario"><s:param name="id" value="id" /></s:url>
			<s:url var="partidosURL" action="GoPartidos"><s:param name="id" value="id" /></s:url>
			<s:url var="clasificacionURL" action="GoClasificacion"><s:param name="id" value="id" /></s:url>
    		<s:url var="adminURL" action="GoAdminTorneo"><s:param name="id" value="id" /></s:url>
    		<s:url var="abandonarURL" action="GoAbandonarTorneo"><s:param name="id" value="id" /></s:url>
    		<s:url var="unirseURL" action="GoJoinTorneo"><s:param name="id" value="id" /></s:url>
    		<s:url var="estadisticasURL" action="GoEstadisticasTorneoPanel"><s:param name="id" value="id" /></s:url>
    		
    		
    
    		<sj:tabbedpanel id="mytabsTorneo" selectedTab="%{tabSelected}" spinner="%{getText('cargando')}" 
    				cssClass="sombra" cssStyle="margin-left:2px; margin-bottom:3px;">
      			<sj:tab id="tab1" href="%{noticiasURL}" key="palabra.noticias"/>
      			<sj:tab id="tab2" href="%{torneoURL}" key="info"/>

<%-- 				<s:if test="%{estado == FINALIZADO}"> --%>
<!-- 					FINALIZADO -->
<%-- 				</s:if> --%>
			    <s:if test="estado != 3 && (roltorneo == 'creador' || roltorneo == 'admin' || roltorneo == 'mod')">
			    		<sj:tab id="tab6" href="%{adminURL}" key="admin" />
			    </s:if>
			    
      			<s:if test="sorteado">
					<sj:tab id="tab3" href="%{calendarioURL}" key="calendario" />
					<s:if test="estado != 3 && (roltorneo == 'creador' || roltorneo == 'admin' 
			          		|| roltorneo == 'mod' || roltorneo == 'normaluser')">
						<sj:tab id="tab4" href="%{partidosURL}" key="partidos" />
					</s:if>
					<s:if test="liga == true">
						<sj:tab id="tab5" href="%{clasificacionURL}" key="clasificacion" />
					</s:if>
				</s:if>
				
<!-- 				Si está en juego o finalizado -->
				<s:if test="estado == 2 || estado == 3"> 
					<sj:tab id="tab9" href="%{estadisticasURL}" key="estadisticas" />
				</s:if>
				
				<s:if test="estado != 3 && (roltorneo == 'creador' || roltorneo == 'admin' 
			          		|| roltorneo == 'mod' || roltorneo == 'normaluser')">			    	 
			    	<sj:tab id="tab7" href="%{abandonarURL}" key="abandonar" />     		
			    </s:if>
				<s:elseif test="estado != 3 && roltorneo!= 'baneado' && joinedComunidad">
					<sj:tab id="tab8" href="%{unirseURL}" key="unirse" />
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