<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

<link rel="shortcut icon" href="<s:url value="/images/iconos/web/favicon.png" />" >
<link href="<s:url value="/css/style.css" />" rel="stylesheet" type="text/css" />
<!--[if lt IE 9]>
	<link href="<s:url value="/css/explorer8.css" />" rel="stylesheet" type="text/css" />
<![endif]-->

<sj:head jqueryui="true" jquerytheme="redmond"/>


<title><s:text name="title" /></title>
</head>


<script type="text/javascript" src="<s:url value="/javascript/jqFancyTransitions.1.8.js" />" ></script>
<script type="text/javascript">
$(document).ready( function(){
	$('#slideshowHolder').jqFancyTransitions({ effect: 'wave',width: 600, height: 365,delay: 4000});
});
</script>
 
<body>
<div class="main">
	<jsp:include page="/WEB-INF/pages/include/HeaderSinMenu.jsp" />

<div class="body">
   <div class="body_resize">

	<div id="ftHolder">	
	   <div id="slideshowHolder">
	   		<img src="<s:url value="%{getText('ruta.imagen.slider.comunidad')}" />" />
	   		<img src="<s:url value="%{getText('ruta.imagen.slider.torneo')}" />"  />
	   		<img src="<s:url value="%{getText('ruta.imagen.slider.deporte')}" />"  />
	   		<img src="<s:url value="%{getText('ruta.imagen.slider.videojuego')}" />"  />
	   		<img src="<s:url value="%{getText('ruta.imagen.slider.cartas')}" />"  />
	   </div>
   </div>
   <div id="loginSlider" >
	   <s:actionerror/>    
		<s:form id="formulario" action="Login" method="post" cssClass="form">
			<s:hidden name="loginAttempt" value="%{'1'}" />
			<s:textfield id="alias" name="alias"  key="user.alias" size="20" maxlength="30" />
	    	<s:password id="password" name="password" key="password" size="20" maxlength="10" />
	    	<sj:submit button="true" key="login" />
		</s:form>
	
		
		<s:a action="GoRecordatorioUser"><s:param name="recoverAttempt" value="%{'1'}" /><s:text name="recordatorio.password" /></s:a>
		<br><br>
		<s:text name="unregistered" /><s:a action="GoRegisterUser"><s:param name="registerAttempt" value="%{'1'}" /><s:text name="registrese" /></s:a>
		
		
   </div>

</div>

</div>

<div class="clr"></div>
  <jsp:include page="/WEB-INF/pages/include/Footer.jsp" />
</div>


</body>
</html>