<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<link rel="shortcut icon" href="<s:url value="/images/iconos/web/favicon.png" />" >
<link href="<s:url value="/css/style.css" />" rel="stylesheet" type="text/css" />
<sj:head locale="%{#request.locale}" jqueryui="true" jquerytheme="redmond"/>
<!--[if lt IE 9]>
	<link href="<s:url value="/css/explorer8.css" />" rel="stylesheet" type="text/css" />
<![endif]-->

<title><s:text name="mis.torneos"/> - <s:text name="title" /></title>
</head>
 
<body>
<div class="main">
	<jsp:include page="/WEB-INF/pages/include/HeaderSinMenu.jsp" />
	
	<div class="body">
   <div class="body_resize">
   			<div class="menu_central_header degradado sombra rounded-corners" style="margin-top:5px;">
   			<div class="menu_central_header_interrogacion_imagen">
				<h3><s:text name="recuperar.datos"/></h3>
			</div>
			</div>
	<div  class="menu_central_body sombra rounded-corners">
		
		<s:fielderror theme="jquery"/>
		<s:actionerror theme="jquery"/>
		<s:form action="RecuperarDatos" method="post" cssClass="form">
		<s:hidden name="recoverAttempt" value="%{'1'}" />
		<fieldset>
		<legend><s:text name="recuperar.datos"/></legend>
		    
		<table>
		<tr>
		<td><s:textfield name="email" key="email" size="20" maxlength="50" required="true"/></td>
		</tr>
		</table>
		</fieldset>
		<sj:submit button="true" indicator="indicator"  key="enviar" cssStyle="margin-left:auto; margin-right:auto; display:block; font-size:15px;"/>
		</s:form>

	</div>
	</div>
	</div>

<div class="clr"></div>
  <jsp:include page="/WEB-INF/pages/include/Footer.jsp" />
</div>

</body>
</html>