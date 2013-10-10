<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>


<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<link href="<s:url value="/images/iconos/web/favicon.png" />" rel="shortcut icon"  />
<link href="<s:url value="/css/style.css" />" rel="stylesheet" type="text/css" />
<link href="<s:url value="/css/displaytag.css" />" rel="stylesheet" type="text/css" />
<!--[if lt IE 9]>
	<link href="<s:url value="/css/explorer8.css" />" rel="stylesheet" type="text/css" />
<![endif]--> 
<sj:head locale="%{#request.locale}" jqueryui="true" jquerytheme="redmond"/>
<title><s:text name="buscar.jugador"/> - <s:text name="title" /></title>

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
	    <div class="menu_central_header_buscarJugador_imagen">
			<h3><s:text name="buscar.jugador"/></h3>
		</div>
		</div>
	    
	    <div class="menu_central_body sombra rounded-corners">
	    

		<s:form id="formulario" action="SearchUser" method="post" cssClass="form">
		<fieldset>
		<legend><s:text name="buscar.jugador"/></legend>
		    <s:textfield name="alias" key="user.alias" size="20" maxlength="35" />
		    
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
		    <td><sj:datepicker name="nacimiento" key="fecha.nacimiento" firstDay="1" maxDate="0y0m0d" changeYear="true" changeMonth="true"
					yearRange="-100:+100" displayFormat="dd-MM-yy" /></td>
			</tr><tr>
			<td>
			<s:url id="paislist" action="listarPaises" />
		    <sj:select name="pais" href="%{paislist}" key="palabra.pais" list="listaPaises" listKey="id" listValue="nombre"
		    	onChangeTopics="reloadregionlist,reloadprovincialist,reloadmunicipiolist" onCompleteTopics="reloadregionlist,reloadprovincialist,reloadmunicipiolist" />
		    </td>
		    <td>
		    <s:url id="regionlist" action="listarRegionesByPais" />	
		    <sj:select name="region" href="%{regionlist}" key="palabra.region" list="listaRegiones" formIds="formulario" listKey="id" listValue="nombre"
		    	reloadTopics="reloadregionlist" deferredLoading="true" onChangeTopics="reloadprovincialist,reloadmunicipiolist" onCompleteTopics="reloadprovincialist,reloadmunicipiolist" />
		    </td>
		    </tr><tr>
		    <td>
		    <s:url id="provincialist" action="listarProvinciasByRegion" />	
		    <sj:select name="provincia" href="%{provincialist}" key="palabra.provincia" list="listaProvincias" formIds="formulario" listKey="id" listValue="nombre"
		    	reloadTopics="reloadprovincialist" deferredLoading="true" onChangeTopics="reloadmunicipiolist" onCompleteTopics="reloadmunicipiolist" />
		    </td>
		    <td>	
		    <s:url id="municipiolist" action="listarMunicipiosByProvinciaNull" />
		    <sj:select name="municipio" href="%{municipiolist}" key="palabra.municipio" list="listaMunicipios" formIds="formulario" listKey="id" listValue="nombre"
		    	reloadTopics="reloadmunicipiolist" deferredLoading="true" />
		    </td>
		    </tr>
		    </table>
			</fieldset>

			</fieldset>		    	
		</s:form>


<sj:a
	id="ajaxformlinkBuscadorJugador"
	formIds="formulario"
	targets="resultJugadores"
	indicator="indicator"
	button="true"
	buttonIcon="ui-icon-gear"
	cssStyle="margin-left:10px;"
>
<s:text name="buscar"/>
</sj:a>



<div id="resultJugadores" class="result ui-widget-content ui-corner-all" style="margin:0px 10px; padding-bottom:10px">
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