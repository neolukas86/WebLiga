<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
<link rel="shortcut icon" href="<s:url value="/images/iconos/web/favicon.png" />" >
<link href="<s:url value="/css/style.css" />" rel="stylesheet" type="text/css" />
<!--[if lt IE 9]>
	<link href="<s:url value="/css/explorer8.css" />" rel="stylesheet" type="text/css" />
<![endif]-->
<sj:head locale="%{#request.locale}" jqueryui="true" jquerytheme="redmond"/>
<title><s:text name="register.usuario"/> - <s:text name="title" /></title>

</head>

<script type="text/javascript" src="<s:url value="/javascript/mostrarOcultar.js" />" ></script>
<script type="text/javascript">
function compararPasswords(){
	if(document.regUser.password == document.regUser.pw){
		alert('Cadenas Iguales');
    	document.regUser.submit();
	}
	else{
		alert('Cadenas Distintas');
		return 0;
	}
  };
</script>
 
<body>
<div class="main">
	<jsp:include page="/WEB-INF/pages/include/HeaderSinMenu.jsp" />
	
	<div class="body">
   <div class="body_resize">
   			<div class="menu_central_header degradado sombra rounded-corners" style="margin-top:5px;">
   			<div class="menu_central_header_registroJugador_imagen">
				<h3><s:text name="register.usuario"/></h3>
			</div>
			</div>
	<div  class="menu_central_body sombra rounded-corners">
		
		<s:fielderror theme="jquery"/>
		<s:actionerror theme="jquery"/>
		<s:form id="regUser" name="regUser" action="RegisterUser" method="post" cssClass="form">
		<fieldset>
		<legend><s:text name="register.usuario"/></legend>
		
			<s:hidden name="registerAttempt" value="%{'1'}" />
			<fieldset>
			<legend><s:text name="datos.usuario"/></legend>
			<table>
			<tr>
			<td><s:textfield name="alias" required="true" key="user.alias" size="20" maxlength="35" /></td>
		    </tr><tr>
		    <td><s:password  name="password" required="true" key="password" size="20" maxlength="20" /></td>
		    <td><s:password  name="pw" required="true" key="password.repetir" size="20" maxlength="20" /></td>
		    </tr><tr>
		    <td><s:select name="lenguaje" list="listaLenguajes"  key="idioma.defecto" required="true"/></td>
		    </tr>
		    </table>
		    </fieldset>
		    <fieldset>
		    <legend><s:text name="datos.personales"/></legend>
		    <table>
		    <tr>
		    <td><s:textfield name="nombre" key="firstname" size="20" maxlength="35" /></td>
		    <td><s:textfield name="apellido" key="lastname" size="20" maxlength="50" /></td>
		    </tr>
		    </table>
		    <table>
		    <tr>
		    <td><s:select name="genero" key="sexo" list="listaSexo"/></td>
		    </tr><tr>
		    <td><sj:datepicker name="nacimiento" key="fecha.nacimiento" firstDay="1" maxDate="0y0m0d" 
		    		changeYear="true" changeMonth="true" yearRange="-100:+100" displayFormat="dd-MM-yy" /></td>
			</tr><tr>
			<td>
			<s:url id="paislist" action="listarPaises" />
		    <sj:select name="pais" href="%{paislist}" key="palabra.pais" list="listaPaises" listKey="id" listValue="nombre"
		    	onChangeTopics="reloadprovincialist,reloadmunicipiolist" onCompleteTopics="reloadprovincialist,reloadmunicipiolist" />
		    </td>	
			<td>
		    <s:url id="provincialist" action="listarProvinciasByPais" />	
		    <sj:select name="provincia" href="%{provincialist}" key="palabra.provincia" list="listaProvincias" formIds="regUser" listKey="id" 
		    	listValue="nombre" reloadTopics="reloadprovincialist" deferredLoading="true" onChangeTopics="reloadmunicipiolist" 
		    	onCompleteTopics="reloadmunicipiolist" />
		    </td>
		    <td>	
		    <s:url id="municipiolist" action="listarMunicipiosByProvincia" />
		    <sj:select name="municipio" href="%{municipiolist}" key="palabra.municipio" list="listaMunicipios" formIds="regUser" listKey="id" 
		    	listValue="nombre" reloadTopics="reloadmunicipiolist" deferredLoading="true" />
		    </td>
		    </tr><tr>	
		    <td><s:textfield name="email" required="true" key="email" size="20" maxlength="50" /></td>
		    </tr><tr>
		    <td><s:textfield name="homepage" key="homepage" size="20" maxlength="255" value="http://"/></td>
		    </tr>
		    </table>
		  
			</fieldset>		    	
		    
		  <sj:submit id="sub" button="true" indicator="indicator"  key="register.usuario" 
		  	cssClass="centrado" cssStyle="font-size:15px;"
		  	/>  
		    
		    </fieldset>
		</s:form>
	</div>
	</div>
	</div>

<div class="clr"></div>
  <jsp:include page="/WEB-INF/pages/include/Footer.jsp" />
</div>

</body>
</html>