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

<title>Admin - <s:text name="palabra.usuarios" /></title>
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
					<h3><s:text name="palabra.usuarios"/></h3>
				</div>

				<h2><s:text name="palabra.usuarios"/></h2>

				 
<s:if test="userList != null">
	<s:if test="userList.size() > 10">
		<display:table name="userList" id="row" class="table" pagesize="10" requestURI=""> 
			<display:column property="id" titleKey="id" style="width:2%"/>
		     <display:column titleKey="user.alias" sortable="true" sortName="alias">
			     	<s:url var="perfilAction" action="GoPerfil"><s:param name="id" >${row.id}</s:param></s:url>
			     			
			     	<sj:a  
						href="%{perfilAction}"
						openDialog="myremotelinkdialog"
						button="false" >
							${row.alias}
					</sj:a>
		     </display:column>
		     <display:column titleKey="palabra.pais" sortable="true" sortName="pais"> 
		    	<s:if test="#request.locale.language == 'es'">		    	
    				<c:out value="${row.parentPais.nombre}" />
    			</s:if>
    			<s:elseif test="#request.locale.language == 'en'">
    				<c:if test="${row.parentPais.nombre_EN != null}" >		    	
    					<c:out value="${row.parentPais.nombre_EN}" />
    				</c:if>
    				<c:if test="${row.parentPais.nombre_EN == null}">
    					<c:out value="${row.parentPais.nombre}" />
    				</c:if>
    			</s:elseif>
    			<s:elseif test="#request.locale.language == 'ca'">
    				<c:if test="${row.parentPais.nombre_CA != null}" >		    	
    					<c:out value="${row.parentPais.nombre_CA}" />
    				</c:if>
    				<c:if test="${row.parentPais.nombre_CA == null}">
    					<c:out value="${row.parentPais.nombre}" />
    				</c:if>
    			</s:elseif> 
		     </display:column>
		     <display:column titleKey="sexo" sortable="true" sortName="sexo"> 
		     	<c:if test="${row.sexo == false}">
		     		<s:text name="sexo.femenino"/> 
		     	</c:if> 
		     	<c:if test="${row.sexo == true}"> 
		     		<s:text name="sexo.masculino"/> 
		     	</c:if>
		     </display:column>    
		
		  <display:column property="fechaRegistro" titleKey="fecha.registro" sortable="true" sortName="fechaCreacion"/>
		
		  <display:column style="width:36px">
						<s:set var="EstUsuarios">EstaUs${row.id}</s:set>
						<s:set var="ExpulsarUsuario">ExpulsarUs${row.id}</s:set>
						
						<s:url id="URLUsuarios" action="EstadisticasUsuario">
							<s:param name="id">${row.id}</s:param>
						</s:url>					
						
						<sj:a id="ajaxLink%{EstUsuarios}" 
							href="%{URLUsuarios}" 
							targets="resultUsuario" 
						>
						<img src="<s:url value="/images/template/chart-icon.png" />" 
							title="<s:text name="estadisticas"/>" width="16" height="16"/>
						</sj:a>
						
						<s:url id="URLExpulsarUsuarios" action="ExpulsarUsuario">
							<s:param name="id">${row.id}</s:param>
						</s:url>					
						
						<sj:a id="ajaxLink%{ExpulsarUsuario}" 
							href="%{URLExpulsarUsuarios}"  
						>
						<img src="<s:url value="/images/template/kick-icon.png" />" 
							title="<s:text name="expulsar"/>" width="16" height="16"/>
						</sj:a>						
											
			</display:column>		
		 </display:table>
	</s:if>
	
	<s:else>
	<div class="solo_pagebanner">
		<display:table name="userList" id="row" class="table" pagesize="10" requestURI=""> 
			<display:column property="id" titleKey="id" style="width:2%"/>
		     <display:column titleKey="user.alias" sortable="true" sortName="alias">
			     	<s:url var="perfilAction" action="GoPerfil"><s:param name="id" >${row.id}</s:param></s:url>
			     			
			     	<sj:a  
						href="%{perfilAction}"
						openDialog="myremotelinkdialog"
						button="false" >
							${row.alias}
					</sj:a>
		     </display:column>
		     <display:column titleKey="palabra.pais" sortable="true" sortName="pais"> 
		    	<s:if test="#request.locale.language == 'es'">		    	
    				<c:out value="${row.parentPais.nombre}" />
    			</s:if>
    			<s:elseif test="#request.locale.language == 'en'">
    				<c:if test="${row.parentPais.nombre_EN != null}" >		    	
    					<c:out value="${row.parentPais.nombre_EN}" />
    				</c:if>
    				<c:if test="${row.parentPais.nombre_EN == null}">
    					<c:out value="${row.parentPais.nombre}" />
    				</c:if>
    			</s:elseif>
    			<s:elseif test="#request.locale.language == 'ca'">
    				<c:if test="${row.parentPais.nombre_CA != null}" >		    	
    					<c:out value="${row.parentPais.nombre_CA}" />
    				</c:if>
    				<c:if test="${row.parentPais.nombre_CA == null}">
    					<c:out value="${row.parentPais.nombre}" />
    				</c:if>
    			</s:elseif> 
		     </display:column>
		     <display:column titleKey="sexo" sortable="true" sortName="sexo"> 
		     	<c:if test="${row.sexo == false}">
		     		<s:text name="sexo.femenino"/> 
		     	</c:if> 
		     	<c:if test="${row.sexo == true}"> 
		     		<s:text name="sexo.masculino"/> 
		     	</c:if>
		     </display:column>    
		
		  <display:column property="fechaRegistro" titleKey="fecha.registro" sortable="true" sortName="fechaCreacion"/>
		  
		  <display:column style="width:36px">
						<s:set var="EstUsuarios">EstaUs${row.id}</s:set>
						<s:set var="ExpulsarUsuario">ExpulsarUs${row.id}</s:set>
						
						<s:url id="URLUsuarios" action="EstadisticasUsuario">
							<s:param name="id">${row.id}</s:param>
						</s:url>					
						
						<sj:a id="ajaxLink%{EstUsuarios}" 
							href="%{URLUsuarios}" 
							targets="resultUsuario" 
						>
						<img src="<s:url value="/images/template/chart-icon.png" />" 
							title="<s:text name="estadisticas"/>" width="16" height="16"/>
						</sj:a>
						
						<s:url id="URLExpulsarUsuarios" action="ExpulsarUsuario">
							<s:param name="id">${row.id}</s:param>
						</s:url>					
						
						<sj:a id="ajaxLink%{ExpulsarUsuario}" 
							href="%{URLExpulsarUsuarios}"  
						>
						<img src="<s:url value="/images/template/kick-icon.png" />" 
							title="<s:text name="expulsar"/>" width="16" height="16"/>
						</sj:a>						
											
			</display:column>
		
		 </display:table>	
	</div>
	</s:else>
</s:if>
				
			
				
				<div id="resultUsuario" class="result ui-widget-content ui-corner-all" style="padding:5px; margin:10px"></div>					    	
			       	
	          	<div class="clr"></div>
	          	</div>
			</div>
			
	    </div>
  </div>
  </div>
  <div class="clr"></div>
  <jsp:include page="/WEB-INF/pages/include/Footer.jsp" />
</div>

    <sj:dialog 
    	id="myremotelinkdialog" 
    	autoOpen="false"  
    	title="%{getText('perfil.usuario')}"
    	buttons="{'Cerrar':function() { $('#myremotelinkdialog').dialog('close'); }}"
    	width="500"
    	 
    	
    />
    
</body>
</html>