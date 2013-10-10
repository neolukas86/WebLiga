<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

	<s:url var="estadisticasTorneoURL" action="GoEstadisticasTorneo"><s:param name="id" value="id" /></s:url>
	<s:url var="estadisticasUsuariosURL" action="GoEstadisticasUsuariosTorneo"><s:param name="id" value="id" /></s:url>
	<s:url var="estadisticasEquiposURL" action="GoEstadisticasEquiposTorneo"><s:param name="id" value="id" /></s:url>
    
    <sj:tabbedpanel id="mytabsEstadisticasTorneo" selectedTab="0" spinner="%{getText('cargando')}">
      	<sj:tab id="tab1" href="%{estadisticasTorneoURL}" key="palabra.torneo"/>
      	<s:if test="porEquipos">
      		<sj:tab id="tab2" href="%{estadisticasEquiposURL}" key="palabra.equipos"/>
      	</s:if>
      	<s:else>
      		<sj:tab id="tab3" href="%{estadisticasUsuariosURL}" key="palabra.usuarios"/>
      	</s:else>
      				
    </sj:tabbedpanel>