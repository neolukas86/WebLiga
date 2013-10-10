<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<script type="text/javascript">
$('#mytabsComunidad').tabs({
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
						<s:set name="tabSelected" value="2" />
				</s:elseif>
				<s:elseif test="subTab == 'settings'">
					<s:if test="regRequest">
						<s:set name="tabSelected" value="3" />
					</s:if>
					<s:else>
						<s:set name="tabSelected" value="2" />
					</s:else>
				</s:elseif>
				<s:elseif test="subTab == 'password'">
					<s:if test="regRequest">
						<s:set name="tabSelected" value="4" />
					</s:if>
					<s:else>
						<s:set name="tabSelected" value="3" />
					</s:else>
				</s:elseif>
				<s:elseif test="subTab == 'eliminar'">
					<s:if test="regRequest">
						<s:set name="tabSelected" value="5" />
					</s:if>
					<s:else>
						<s:set name="tabSelected" value="4" />
					</s:else>
				</s:elseif>			
			</s:if>
			
			<s:else>
				<s:set name="tabSelected" value="0" />
			</s:else>
			
<%-- 			Valor de subTab -- <s:property value="subTab"/> --%>
<%-- 			Valor de tab -- <s:property value="tab"/> --%>
			
			<s:url var="settings" action="GoSettingsComunidad"><s:param name="id" value="id" /></s:url>
			<s:url var="usuarios" action="GoUsuariosComunidad"><s:param name="id" value="id" /></s:url>
			<s:url var="equipos"  action="GoEquiposComunidad"><s:param name="id" value="id" /></s:url>
			<s:url var="requests" action="GoRequestsComunidad"><s:param name="id" value="id" /></s:url>
			<s:url var="password" action="GoPasswordComunidad"><s:param name="id" value="id" /></s:url>
			<s:url var="eliminar" action="GoEliminarComunidad"><s:param name="id" value="id" /></s:url>			
			    
    		<sj:tabbedpanel id="mytabsComunidadAdmin" selectedTab="%{tabSelected}" spinner="%{getText('cargando')}">
    			<s:if test="rolcomunidad == 'creador' || rolcomunidad == 'admin'  
										|| rolcomunidad == 'mod'">      				
      				<sj:tab id="tab1" href="%{usuarios}" key="palabra.usuarios"/>
      				<sj:tab id="tab6" href="%{equipos}" key="palabra.equipos"/>
      				<s:if test="regRequest">
      					<sj:tab id="tab2" href="%{requests}" key="requests"/>
      				</s:if>
       			</s:if> 
      			<s:if test="rolcomunidad == 'creador' || rolcomunidad == 'admin'">
      				<sj:tab id="tab3" href="%{settings}" key="settings"/>
      				<sj:tab id="tab4" href="%{password}" key="seguridad"/>
      				<sj:tab id="tab5" href="%{eliminar}" key="eliminar"/>
      			</s:if>
    		</sj:tabbedpanel>
