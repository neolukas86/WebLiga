<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">
$('#mytabsEquipo').tabs({
    load: function(event, ui) {
        $(ui.panel).delegate('span a, th a', 'click', function(event) {
            $(ui.panel).load(this.href);
            event.preventDefault();
        });
    }
});
</script>

<s:url var="rutaCambiarEquipo" value="/images/template/cambiarPerfiAEquipo.png" />

			<display:table name="equipo" id="row" class="table">
				<display:column property="id" titleKey="id" style="width:10%"/>
				<display:column property="tag" titleKey="tag" style="width:10%"/>
			    <display:column property="nombre" titleKey="nombre" />
				<display:column style="width:2%">
					<s:form action="TomarPerfilEquipo">
					    <s:hidden name="id" value="%{equipo.id}"/>
					    <s:hidden name="nombre" value="%{equipo.nombre}"/>
					    <s:submit type="image" title="%{getText('utilizar.perfil.equipo')}" 
					    	src="%{rutaCambiarEquipo}"/>
					</s:form>			    
				</display:column>
			</display:table>
			

			    		
			<h2><s:text name="palabra.actividades"/></h2>
			
			<div class="box">

			<s:iterator value="actividadList">
<%-- 				<s:property value="nombre"/> --%>
				<s:if test="#request.locale.language.equals('es')">
					<img src="<s:url value="%{'/images/iconos/actividades/50'+rutaimagen}"/>" 
						title="<s:property value="nombre"/>" width="50" height="50"/>
				</s:if>
				<s:elseif test="#request.locale.language.equals('en')">
					<s:if test="nombre_EN!=null">
						<img src="<s:url value="%{'/images/iconos/actividades/50'+rutaimagen}"/>" 
							title="<s:property value="nombre_EN"/>" width="50" height="50"/>
					</s:if>
					<s:else>
						<img src="<s:url value="%{'/images/iconos/actividades/50'+rutaimagen}"/>" 
							title="<s:property value="nombre"/>" width="50" height="50"/>
					</s:else>
				</s:elseif>
				<s:elseif test="#request.locale.language.equals('ca')">
					<s:if test="nombre_CA!=null">
						<img src="<s:url value="%{'/images/iconos/actividades/50'+rutaimagen}"/>" 
						title="<s:property value="nombre_CA"/>"  width="50" height="50"/>
					</s:if>
					<s:else>
						<img src="<s:url value="%{'/images/iconos/actividades/50'+rutaimagen}"/>" 
							title="<s:property value="nombre"/>" width="50" height="50"/>
					</s:else>
				</s:elseif>
			</s:iterator>
			
			</div>
			
			<s:if test="plataformas != null && !plataformas.isEmpty()">
			
				<h2><s:text name="palabra.plataformas"/></h2>
			
				<div class="box">
				<s:iterator value="plataformaList">
					<img src="<s:url value="%{'/images/iconos/plataformas/50'+rutaimagen}"/>" 
						title="<s:property value="nombre"/>" width="50" height="50"/>
				</s:iterator>			
				
				</div>
			</s:if>
			
			
			<h2><s:text name="palabra.usuarios" /></h2>
			
			<div id="resultJugadores" class="result ui-widget-content ui-corner-all">
					<s:set name="reqURI">EquipoDisplay</s:set>
					<jsp:include page="/WEB-INF/pages/display/jugador/UsuariosDisplay.jsp"/>
			</div>
