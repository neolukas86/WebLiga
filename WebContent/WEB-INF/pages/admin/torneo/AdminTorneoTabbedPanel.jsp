<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<script type="text/javascript">
$('#mytabsTorneo').tabs({
    load: function(event, ui) {
        $(ui.panel).undelegate('span a, th a', 'click');
    }
});

</script>

			<s:if test="!subTab.isEmpty()">
				<s:if test="subTab == 'usuarios'">
					<s:set name="tabSelected" value="0" />
				</s:if>
				<s:elseif test="subTab == 'equipos'">
					<s:set name="tabSelected" value="1" />					
				</s:elseif>
				<s:elseif test="subTab == 'requests'">
					<s:if test="porEquipos == true">
						<s:set name="tabSelected" value="2" />
					</s:if>
					<s:else>
						<s:set name="tabSelected" value="1" />
					</s:else>
				</s:elseif>
				<s:elseif test="subTab == 'sortear'">
					<s:if test="porEquipos == true">
						<s:set name="tabSelected" value="3" />
					</s:if>
					<s:else>
						<s:set name="tabSelected" value="2" />
					</s:else>	
				</s:elseif>
				<s:elseif test="subTab == 'settings'">
					<s:if test="porEquipos == true">
						<s:if test="sorteado == 0">
							<s:set name="tabSelected" value="4" />							
						</s:if>
						<s:else>
							<s:set name="tabSelected" value="3" />
						</s:else>	
					</s:if>
					<s:else>
						<s:set name="tabSelected" value="2" />
					</s:else>
				</s:elseif>
				<s:elseif test="subTab == 'password'">
					<s:if test="porEquipos == true">
						<s:set name="tabSelected" value="5" />							
					</s:if>
					<s:else>
						<s:set name="tabSelected" value="4" />
					</s:else>
				</s:elseif>
				<s:elseif test="subTab == 'eliminar'">
					<s:if test="porEquipos == true">
						<s:if test="sorteado == 0">
							<s:set name="tabSelected" value="6" />							
						</s:if>
						<s:else>
							<s:set name="tabSelected" value="5" />
						</s:else>	
					</s:if>
					<s:else>
						<s:set name="tabSelected" value="4" />
					</s:else>
				</s:elseif>			
			</s:if>
			
			<s:else>
				<s:set name="tabSelected" value="0" />
			</s:else>
				
			<s:url var="settingsURL" action="GoSettingsTorneo"><s:param name="id" value="id" /></s:url>
			<s:url var="usuariosURL" action="GoUsuariosTorneo"><s:param name="id" value="id" /></s:url>
			<s:url var="equiposURL" action="GoEquiposTorneo"><s:param name="id" value="id" /></s:url>
			<s:url var="sortearURL" action="GoSortearTorneo"><s:param name="id" value="id" /></s:url>
			<s:url var="requestsURL" action="GoRequestsTorneo"><s:param name="id" value="id" /></s:url>
			<s:url var="eliminarURL" action="GoEliminarTorneo"><s:param name="id" value="id" /></s:url>
			<s:url var="passwordURL" action="GoPasswordTorneo"><s:param name="id" value="id" /></s:url>
    
    		<sj:tabbedpanel id="mytabsTorneoAdmin" selectedTab="%{tabSelected}" spinner="%{getText('cargando')}" >
		    	<s:if test="roltorneo == 'creador' || roltorneo == 'admin' 
								|| roltorneo == 'mod'"> 														
					<sj:tab id="tab1" href="%{usuariosURL}" key="palabra.usuarios"/>
					<s:if test="porEquipos == true">
						<sj:tab id="tab12" href="%{equiposURL}" key="palabra.equipos"/>
					</s:if>
					<sj:tab id="tab2" href="%{requestsURL}" key="requests"/>
					<s:if test="sorteado == 0">
						<sj:tab id="tab3" href="%{sortearURL}" key="sortear"/>
					</s:if>														
				</s:if>
				<s:if test="roltorneo == 'creador' || roltorneo == 'admin'">
					<sj:tab id="tab4" href="%{settingsURL}" key="settings"/>
					<s:if test="sorteado == 0"> 
						<sj:tab id="tab5" href="%{passwordURL}" key="seguridad"/>
					</s:if>
					<sj:tab id="tab6" href="%{eliminarURL}" key="eliminar"/>
				</s:if>
    		</sj:tabbedpanel>
