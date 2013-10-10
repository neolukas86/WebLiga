<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">
$('#mytabsTorneo').tabs({
    load: function(event, ui) {
        $(ui.panel).delegate('span a, th a', 'click', function(event) {
            $(ui.panel).load(this.href);
            event.preventDefault();
        });
    }
});
</script>



			<display:table name="torneo" id="row" class="table">
<%-- 				<display:column property="id" titleKey="id" /> --%>
			    <display:column property="nombre" titleKey="nombre" />
<%-- 				<display:column titleKey="actividad" > --%>
<%-- 			    	<c:out value="${row.parentActividad.nombre}" /> --%>
<%-- 			    </display:column> --%>
				<display:column titleKey="tipo.campeonato">
					<c:if test="${row.liga == true}">
						<s:text name="liga"/>
					</c:if>
					<c:if test="${row.liga == false}">
						<s:text name="copa"/>
					</c:if>
				</display:column>
			    <display:column property="fechaComienzo" titleKey="fecha.comienzo"/>
			    <display:column property="fechaFinal" titleKey="fecha.final"/>
			    <display:column titleKey="online">
			    	<c:if test="${row.online == false}">
			    		<s:text name="no"/>
			    	</c:if>
			    	<c:if test="${row.online == true}">
			    		<s:text name="yes"/>
			    	</c:if>
			    </display:column>
<%-- 			    <display:column property="fechaCreacion" titleKey="fecha.creacion" /> --%>

				<s:set var="rutaimagenActividad">${row.parentActividad.rutaimagen}</s:set>
				<s:set var="actividadNombre">${row.parentActividad.nombre}</s:set>
				
				<c:if test="${row.parentPlataforma != null}">
					<s:set var="rutaimagenPlataforma">${row.parentPlataforma.rutaimagen}</s:set>
					<s:set var="plataformaNombre">${row.parentPlataforma.nombre}</s:set>
				</c:if>
			</display:table>
			

			<h2><s:text name="palabra.actividad"/></h2>
				<div class="box">
				<img src="<s:url value="%{'/images/iconos/actividades/50'}%{rutaimagenActividad}"/>" 
					title="<s:property value="actividadNombre"/>" width="50" height="50"/>
					<p><s:property value="actividadNombre" /></p>
				</div>

			

			<s:if test="parentPlataforma != null">
									
				<h2><s:text name="palabra.plataforma"/></h2>
				<div class="box">	
					<img src="<s:url value="%{'/images/iconos/plataformas/50'}%{rutaimagenPlataforma}"/>" 
						title="<s:property value="plataformaNombre"/>" width="50" height="50"/>
					<p><s:property value="plataformaNombre" /></p>
				</div>
				
			</s:if>

			
			
								
			<s:if test="porEquipos">
				<h2><s:text name="palabra.equipos" /></h2>
				<div id="resultEquipos" class="result ui-widget-content ui-corner-all">
					<jsp:include page="/WEB-INF/pages/display/equipo/EquiposDisplay.jsp"/>
				</div>			
				
			</s:if>
			

				<h2><s:text name="palabra.usuarios" /></h2>
				<div id="resultJugadores" class="result ui-widget-content ui-corner-all">
					<s:set name="reqURI">TorneoDisplay</s:set>
					<jsp:include page="/WEB-INF/pages/display/jugador/UsuariosDisplay.jsp"/>
				</div>
				
