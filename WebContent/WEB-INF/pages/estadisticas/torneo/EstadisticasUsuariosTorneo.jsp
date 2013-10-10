<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>



<s:form id="formularioUsuario" action="VerEstadisticasUsuarioTorneo" method="post" cssClass="form">
	<s:hidden name="id" value="%{id}"/>
	<s:select name="participanteTorneo" key="user.alias" list="listaParticipante" />
	<s:select name="estadistica" key="estadisticas" list="listaEstadisticas" />
</s:form>

<sj:a
	id="ajaxformlinkUsuario"
	formIds="formularioUsuario"
	targets="resultUser"
	indicator="indicator"
	button="true"
	buttonIcon="ui-icon-gear"
>
<s:text name="mostrar"/>
</sj:a>
	
	
	
<div id="resultUser" class="result ui-widget-content ui-corner-all">
</div>
