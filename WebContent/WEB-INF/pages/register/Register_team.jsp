<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

<link href="<s:url value="/images/iconos/web/favicon.png" />" rel="shortcut icon"  />
<link href="<s:url value="/css/style.css" />" rel="stylesheet" type="text/css" />
<!--[if lt IE 9]>
	<link href="<s:url value="/css/explorer8.css" />" rel="stylesheet" type="text/css" />
<![endif]-->
<sj:head jqueryui="true" jquerytheme="redmond" defaultIndicator="myDefaultIndicator"/>
<title><s:text name="register.equipo"/> - <s:text name="title" /></title>
</head>

<script type="text/javascript" src="<s:url value="/javascript/mostrarOcultar.js" />" ></script>
 
<body>

<div class="main">
  <jsp:include page="/WEB-INF/pages/include/Header.jsp" />
 

  <div class="clr"></div>
  <div class="body">
    <div class="body_resize">
    	<jsp:include page="/WEB-INF/pages/include/Menu.jsp" />
	    <div class="menu_central">
	    	<div class="menu_central_header degradado sombra rounded-corners">
			<div class="menu_central_header_registroEquipo_imagen">
				<h3><s:text name="register.equipo"/></h3>
			</div>
			</div>
			<div class="menu_central_body sombra rounded-corners">
			
	<s:fielderror theme="jquery"/>
	<s:actionerror theme="jquery"/>

			<s:form action="CrearEquipo" cssClass="form">
				<fieldset>	
				<legend><s:text name="register.equipo" /></legend>		
			    <s:textfield name="nombre" key="nombre" size="20" maxlength="100" required="true"/>
			    <s:textfield name="tag" key="palabra.tag" size="20" maxlength="10" />
			    <s:textfield name="homepage" key="homepage" size="20" maxlength="255" />
			    
			
			    <fieldset>
			    
			    <legend><s:text name="palabra.deportes" /></legend>
			    
			    <sj:checkboxlist  id="checkboxbuttonset1" list="listaDeportes" name="deportes" />
			    
			    </fieldset>			  
			    	
			    <fieldset>	
			    
			    <legend><s:text name="palabra.videojuegos" /></legend>
			     
				<sj:checkboxlist id="checkboxbuttonset2" list="listaJuegos" name="juegos"/>
				</fieldset>
			    
			    <fieldset>
			    
			    <legend><s:text name="palabra.cartas" /></legend>
			     
				<sj:checkboxlist id="checkboxbuttonset3" list="listaCartas" name="cartas"/>
				</fieldset>
			    
			    <fieldset>
			    
			    <legend><s:text name="palabra.juegos.mesa" /></legend>
			     
				<sj:checkboxlist id="checkboxbuttonset4" list="listaJuegosdemesa" name="juegosdemesa"/>
				</fieldset>
				
				<fieldset>
			   	<legend><s:text name="palabra.plataformas" /></legend>
			    	
			    <sj:checkboxlist  id="checkbuttonset4" list="listaPlataformas" name="plataformasdejuegos" />
			    </fieldset>
				
				<fieldset>	
				<legend><s:text name="seguridad" /></legend>
				<table>
				<tr>
				<td style="padding-right:15px;"><s:select key="password.protected" name="passwordProtected" list="listaBoolean" onchange="mostrar_ocultar_elemento_boolean(this,'joinPassword')"/></td>
	    		<td><s:password id="joinPassword" name="joinPassword" key="password" size="20" maxlength="20" disabled="true" /></td>
	    		</tr><tr>
				<td><s:select name="regRequest" key="confirmacion.registro" list="listaBoolean" /></td>
				</tr>				
				</table>
				</fieldset>
								
			    <sj:submit button="true" indicator="indicator" key="register.equipo"  
			    cssClass="centrado" cssStyle="font-size:15px;"/>
			    
			    </fieldset>
			</s:form>
	</div>
		</div>
	</div>
  </div>
  <div class="clr"></div>
  <jsp:include page="/WEB-INF/pages/include/Footer.jsp" />
</div>

</body>
</html>	