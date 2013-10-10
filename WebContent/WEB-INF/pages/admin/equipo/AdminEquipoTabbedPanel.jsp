<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<script type="text/javascript">
$('#mytabsEquipo').tabs({
    load: function(event, ui) {
        $(ui.panel).undelegate('span a, th a', 'click');
    }
});

</script>

			<s:if test="!subTab.isEmpty()">
				<s:if test="subTab == 'usuarios'">
					<s:set name="tabSelected" value="0" />
				</s:if>
				<s:elseif test="subTab == 'requests'">
						<s:set name="tabSelected" value="1" />
				</s:elseif>
				<s:elseif test="subTab == 'settings'">
					<s:if test="regRequest">
						<s:set name="tabSelected" value="2" />
					</s:if>
					<s:else>
						<s:set name="tabSelected" value="1" />
					</s:else>
				</s:elseif>
				<s:elseif test="subTab == 'password'">
					<s:if test="regRequest">
						<s:set name="tabSelected" value="3" />
					</s:if>
					<s:else>
						<s:set name="tabSelected" value="2" />
					</s:else>
				</s:elseif>		
			</s:if>
			
			<s:else>
				<s:set name="tabSelected" value="0" />
			</s:else>

			<s:url var="settingsURL" action="GoSettingsEquipo"><s:param name="id" value="id" /></s:url>
			<s:url var="usuariosURL" action="GoAdminUsuariosEquipo"><s:param name="id" value="id" /></s:url>
			<s:url var="requestsURL" action="GoRequestsEquipo"><s:param name="id" value="id" /></s:url>			
			<s:url var="passwordURL" action="GoPasswordEquipo"><s:param name="id" value="id" /></s:url>
    
    		<sj:tabbedpanel id="mytabsEquipoAdmin" selectedTab="0" spinner="%{getText('cargando')}">
		    	<s:if test="rolequipo == 'creador' || rolequipo == 'admin' || rolequipo == 'mod'"> 														
					<sj:tab id="tab1" href="%{usuariosURL}" key="palabra.usuarios"/>
					<s:if test="regRequest">
						<sj:tab id="tab2" href="%{requestsURL}" key="requests"/>
					</s:if>									
				</s:if>
				<s:if test="rolequipo == 'creador' || rolequipo == 'admin'">
					<sj:tab id="tab3" href="%{settingsURL}" key="settings"/> 
					<sj:tab id="tab4" href="%{passwordURL}" key="seguridad"/>
				</s:if>
    		</sj:tabbedpanel>

