<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">
$("#resultTorneos").on('click','span a, th a', function(event) {
	$("#resultTorneos").load(this.href);
	event.preventDefault();
});

$('#mytabsComunidad').tabs({
    load: function(event, ui) {
        $(ui.panel).delegate('span a, th a', 'click', function(event) {
            $(ui.panel).load(this.href);
            event.preventDefault();
        });
    }
});

</script>

<s:if test="torneoPaginatedList != null">
	
		<display:table name="torneoPaginatedList" id="row" class="table" sort="external" partialList="true" 
							size="${torneoPaginatedList.fullListSize}" pagesize="${torneoPaginatedList.objectsPerPage}" 
							requestURI="${reqURI}">
<%-- 			<display:column property="id" titleKey="id"/> --%>
		    <display:column titleKey="nombre">
		    	<a href="<s:url action="VerTorneo"><s:param name="id" >${row.id}</s:param></s:url>">${row.nombre}</a>
		    </display:column>
		    <display:column titleKey="palabra.actividad">
		    	<s:set var="ruta">${row.parentActividad.rutaimagen}</s:set>
		    	<s:set var="rutaPlataforma">${row.parentPlataforma.rutaimagen}</s:set>

		    	<s:if test="#request.locale.language == 'es'">
		    		<img src="<s:url value="%{'/images/iconos/actividades/25'}%{ruta}" />" 
							title="${row.parentActividad.nombre}" width="25" height="25"/>		    	
    				<c:out value="${row.parentActividad.nombre}" />
					
    			</s:if>
    			<s:elseif test="#request.locale.language == 'en'">
    				<c:if test="${row.parentActividad.nombre_EN != null}" >	
    					<img src="<s:url value="%{'/images/iconos/actividades/25'}%{ruta}"/>" 
							title="${row.parentActividad.nombre_EN}" width="25" height="25"/>
    					<c:out value="${row.parentActividad.nombre_EN}" />
    				</c:if>
    				<c:if test="${row.parentActividad.nombre_EN == null}">
    					<img src="<s:url value="%{'/images/iconos/actividades/25'}%{ruta}"/>" 
							title="${row.parentActividad.nombre}" width="25" height="25"/>
    					<c:out value="${row.parentActividad.nombre}" />
    				</c:if>
    			</s:elseif>
    			<s:elseif test="#request.locale.language == 'ca'">
    				<c:if test="${row.parentActividad.nombre_CA != null}" >
    					<img src="<s:url value="%{'/images/iconos/actividades/25'}%{ruta}"/>" 
							title="${row.parentActividad.nombre_CA}" width="25" height="25"/>		    	
    					<c:out value="${row.parentActividad.nombre_CA}" />
    				</c:if>
    				<c:if test="${row.parentActividad.nombre_CA == null}">
    					<img src="<s:url value="%{'/images/iconos/actividades/25'}%{ruta}"/>" 
    						title="${row.parentActividad.nombre}" width="25" height="25"/>
    					<c:out value="${row.parentActividad.nombre}" />
    				</c:if>
				</s:elseif>
    		</display:column>
    		<display:column titleKey="palabra.plataforma">
    			<img src="<s:url value="%{'/images/iconos/plataformas/25'}%{rutaPlataforma}" />" 
							title="${row.parentPlataforma.nombre}" width="25" height="25"/>		    	
    				<c:out value="${row.parentPlataforma.nombre}" />
    		</display:column>
		    <display:column property="fechaComienzo" titleKey="comienzo"/>
		    <display:column property="fechaFinal" titleKey="final"/>
		    <display:column titleKey="online">
		    	<c:if test="${row.online == false}">
		    		<s:text name="no"/>
		    	</c:if>
		    	<c:if test="${row.online == true}">
		    		<s:text name="yes"/>
		    	</c:if>
		    </display:column>
		    		    <display:column titleKey="rondas">
		    	<c:if test="${row.rondas == 1}">
		    		<s:text name="rondas.ida"/>
		    	</c:if>
		    	<c:if test="${row.rondas == 2}">
		    		<s:text name="rondas.ida.vuelta"/>
		    	</c:if>
		    	<c:if test="${row.rondas > 2}">
		    		<c:out value="${row.rondas}"/>
		    	</c:if>
		    </display:column>
		    
		    
<%-- 			<display:column property="fechaCreacion" titleKey="fecha.creacion" />				 --%>
		</display:table>
</s:if>

<s:if test="torneoList != null">
	<s:if test="torneoList.size() > 10">
		<display:table name="torneoList" id="row" class="table"  pagesize="10"
							requestURI="${reqURI}">
<%-- 			<display:column property="id" titleKey="id" /> --%>
		    <display:column titleKey="nombre" sortable="true" sortName="nombre" class="tamFijo">
		    	<a href="<s:url action="VerTorneo"><s:param name="id" >${row.id}</s:param></s:url>">${row.nombre}</a>
		    </display:column>
		    <display:column titleKey="palabra.actividad" sortable="true" sortName="actividad">
				<s:set var="ruta">${row.parentActividad.rutaimagen}</s:set>				

		    	<s:if test="#request.locale.language == 'es'">
		    		<img src="<s:url value="%{'/images/iconos/actividades/25'}%{ruta}" />" 
							title="${row.parentActividad.nombre}" width="25" height="25"/>		    	
    				<c:out value="${row.parentActividad.nombre}" />
					
    			</s:if>
    			<s:elseif test="#request.locale.language == 'en'">
    				<c:if test="${row.parentActividad.nombre_EN != null}" >	
    					<img src="<s:url value="%{'/images/iconos/actividades/25'}%{ruta}"/>" 
							title="${row.parentActividad.nombre_EN}" width="25" height="25"/>
    					<c:out value="${row.parentActividad.nombre_EN}" />
    				</c:if>
    				<c:if test="${row.parentActividad.nombre_EN == null}">
    					<img src="<s:url value="%{'/images/iconos/actividades/25'}%{ruta}"/>" 
							title="${row.parentActividad.nombre}" width="25" height="25"/>
    					<c:out value="${row.parentActividad.nombre}" />
    				</c:if>
    			</s:elseif>
    			<s:elseif test="#request.locale.language == 'ca'">
    				<c:if test="${row.parentActividad.nombre_CA != null}" >
    					<img src="<s:url value="%{'/images/iconos/actividades/25'}%{ruta}"/>" 
							title="${row.parentActividad.nombre_CA}" width="25" height="25"/>		    	
    					<c:out value="${row.parentActividad.nombre_CA}" />
    				</c:if>
    				<c:if test="${row.parentActividad.nombre_CA == null}">
    					<img src="<s:url value="%{'/images/iconos/actividades/25'}%{ruta}"/>" 
    						title="${row.parentActividad.nombre}" width="25" height="25"/>
    					<c:out value="${row.parentActividad.nombre}" />
    				</c:if>
				</s:elseif>
    		</display:column>
    		<display:column titleKey="palabra.plataforma" sortable="true" sortName="plataforma">
    			<c:if test="${row.parentPlataforma != null}">
    				<s:set var="rutaPlataforma">${row.parentPlataforma.rutaimagen}</s:set>
    			<img src="<s:url value="%{'/images/iconos/plataformas/25'}%{rutaPlataforma}" />" 
							title="${row.parentPlataforma.nombre}" width="25" height="25"/>		    	
    				<c:out value="${row.parentPlataforma.nombre}" />
    			</c:if>
    		</display:column>
		    <display:column property="fechaComienzo" titleKey="comienzo" sortable="true" sortName="fechaComienzo"/>
		    <display:column property="fechaFinal" titleKey="final" sortable="true" sortName="fechaFinal"/>
		    <display:column titleKey="online" sortable="true" sortName="online">
		    	<c:if test="${row.online == false}">
		    		<s:text name="no"/>
		    	</c:if>
		    	<c:if test="${row.online == true}">
		    		<s:text name="yes"/>
		    	</c:if>
		    </display:column>
		    <display:column titleKey="rondas" sortable="true" sortName="rondas">
		    	<c:if test="${row.rondas == 1}">
		    		<s:text name="rondas.ida"/>
		    	</c:if>
		    	<c:if test="${row.rondas == 2}">
		    		<s:text name="rondas.ida.vuelta"/>
		    	</c:if>
		    	<c:if test="${row.rondas > 2}">
		    		<c:out value="${row.rondas}"/>
		    	</c:if>
		    </display:column>
		    <display:column titleKey="modo" sortable="true" sortName="tipoParticipante">
		    	<c:if test="${row.porEquipos == true}">
		    		<s:text name="palabra.equipos"/>
		    	</c:if>
		    	<c:if test="${row.porEquipos == false}">
		    		<s:text name="individual"/>
		    	</c:if>
		    </display:column>
		   	<display:column titleKey="modo" sortable="true" sortName="tipoCampeonato">
		    	<c:if test="${row.liga == true}">
		    		<s:text name="liga"/>
		    	</c:if>
		    	<c:if test="${row.liga == false}">
		    		<s:text name="copa"/>
		    	</c:if>
		    </display:column>
		    <display:column style="width:80px">
		    	<c:if test="${row.passwordProtected != null && row.passwordProtected== true}">
		    		<img src="<s:url value="/images/template/Lock-icon.png"/>" 
							title="<s:text name="password.protected"/>"  width="24" height="24"/>
		    	</c:if>
<%-- 		    	<s:else> --%>
<%-- 		    	</s:else> --%>
		    	<c:if test="${row.regRequest != null && row.regRequest == true}">
		    		<img src="<s:url value="/images/template/Like-icon.png"/>" 
							title="<s:text name="confirmacion.registro"/>"  width="24" height="24"/>
		    	</c:if>
		    	<c:if test="${row.estado == 0}">
		    		<img src="<s:url value="/images/template/Actions-view-calendar-tasks-close-icon.png"/>" 
							title="<s:text name="registro.cerrado"/>"  width="24" height="24"/>
		    	</c:if>
		    	<c:if test="${row.estado == 1}">
		    		<img src="<s:url value="/images/template/Actions-view-calendar-tasks-icon.png"/>" 
							title="<s:text name="registro.abierto"/>"  width="24" height="24"/>
		    	</c:if>		    	
		    	<c:if test="${row.estado == 2}">
		    		<img src="<s:url value="/images/template/Games-icon.png"/>" 
							title="<s:text name="enjuego"/>"  width="24" height="24"/>
		    	</c:if>
		    	<c:if test="${row.estado == 3}">
		    		<img src="<s:url value="/images/template/checkered-flag-icon.png"/>" 
							title="<s:text name="finalizado"/>"  width="24" height="24"/>
		    	</c:if>		    	
<%-- 		    	<s:else> --%>
<%-- 		    	</s:else> --%>
		    </display:column>
<%-- 			<display:column property="fechaCreacion" titleKey="fecha.creacion" sortable="true" sortName="fechaCreacion" />				 --%>
		</display:table>
	</s:if>
	
	<s:else>
	<div class="solo_pagebanner">
		<display:table name="torneoList" id="row" class="table"  pagesize="10"
							requestURI="${reqURI}">
<%-- 			<display:column property="id" titleKey="id" /> --%>
		    <display:column titleKey="nombre" sortable="true" sortName="nombre" class="tamFijo">
		    	<a href="<s:url action="VerTorneo"><s:param name="id" >${row.id}</s:param></s:url>">${row.nombre}</a>
		    </display:column>
		    <display:column titleKey="palabra.actividad" sortable="true" sortName="actividad">
				<s:set var="ruta">${row.parentActividad.rutaimagen}</s:set>				

		    	<s:if test="#request.locale.language == 'es'">
		    		<img src="<s:url value="%{'/images/iconos/actividades/25'}%{ruta}" />" 
							title="${row.parentActividad.nombre}" width="25" height="25"/>		    	
    				<c:out value="${row.parentActividad.nombre}" />
					
    			</s:if>
    			<s:elseif test="#request.locale.language == 'en'">
    				<c:if test="${row.parentActividad.nombre_EN != null}" >	
    					<img src="<s:url value="%{'/images/iconos/actividades/25'}%{ruta}"/>" 
							title="${row.parentActividad.nombre_EN}" width="25" height="25"/>
    					<c:out value="${row.parentActividad.nombre_EN}" />
    				</c:if>
    				<c:if test="${row.parentActividad.nombre_EN == null}">
    					<img src="<s:url value="%{'/images/iconos/actividades/25'}%{ruta}"/>" 
							title="${row.parentActividad.nombre}" width="25" height="25"/>
    					<c:out value="${row.parentActividad.nombre}" />
    				</c:if>
    			</s:elseif>
    			<s:elseif test="#request.locale.language == 'ca'">
    				<c:if test="${row.parentActividad.nombre_CA != null}" >
    					<img src="<s:url value="%{'/images/iconos/actividades/25'}%{ruta}"/>" 
							title="${row.parentActividad.nombre_CA}" width="25" height="25"/>		    	
    					<c:out value="${row.parentActividad.nombre_CA}" />
    				</c:if>
    				<c:if test="${row.parentActividad.nombre_CA == null}">
    					<img src="<s:url value="%{'/images/iconos/actividades/25'}%{ruta}"/>" 
    						title="${row.parentActividad.nombre}" width="25" height="25"/>
    					<c:out value="${row.parentActividad.nombre}" />
    				</c:if>
				</s:elseif>
    		</display:column>
    		<display:column titleKey="palabra.plataforma" sortable="true" sortName="plataforma">
    			<c:if test="${row.parentPlataforma != null}">
    				<s:set var="rutaPlataforma">${row.parentPlataforma.rutaimagen}</s:set>
    			<img src="<s:url value="%{'/images/iconos/plataformas/25'}%{rutaPlataforma}" />" 
							title="${row.parentPlataforma.nombre}" width="25" height="25"/>		    	
    				<c:out value="${row.parentPlataforma.nombre}" />
    			</c:if>
    		</display:column>
		    <display:column property="fechaComienzo" titleKey="comienzo" sortable="true" sortName="fechaComienzo"/>
		    <display:column property="fechaFinal" titleKey="final" sortable="true" sortName="fechaFinal"/>
		    <display:column titleKey="online" sortable="true" sortName="online">
		    	<c:if test="${row.online == false}">
		    		<s:text name="no"/>
		    	</c:if>
		    	<c:if test="${row.online == true}">
		    		<s:text name="yes"/>
		    	</c:if>
		    </display:column>
		    <display:column titleKey="rondas" sortable="true" sortName="rondas">
		    	<c:if test="${row.rondas == 1}">
		    		<s:text name="rondas.ida"/>
		    	</c:if>
		    	<c:if test="${row.rondas == 2}">
		    		<s:text name="rondas.ida.vuelta"/>
		    	</c:if>
		    	<c:if test="${row.rondas > 2}">
		    		<c:out value="${row.rondas}"/>
		    	</c:if>
		    </display:column>
		    <display:column title="Modo" sortable="true" sortName="tipoParticipante">
		    	<c:if test="${row.porEquipos == true}">
		    		<s:text name="palabra.equipos"/>
		    	</c:if>
		    	<c:if test="${row.porEquipos == false}">
		    		<s:text name="individual"/>
		    	</c:if>
		    </display:column>
		   	<display:column title="Modo" sortable="true" sortName="tipoCampeonato">
		    	<c:if test="${row.liga == true}">
		    		<s:text name="liga"/>
		    	</c:if>
		    	<c:if test="${row.liga == false}">
		    		<s:text name="copa"/>
		    	</c:if>
		    </display:column>
		    <display:column style="width:80px">
		    	<c:if test="${row.passwordProtected != null && row.passwordProtected== true}">
		    		<img src="<s:url value="/images/template/Lock-icon.png"/>" 
							title="<s:text name="password.protected"/>"  width="24" height="24"/>
		    	</c:if>
<%-- 		    	<s:else> --%>
<%-- 		    	</s:else> --%>
		    	<c:if test="${row.regRequest != null && row.regRequest == true}">
		    		<img src="<s:url value="/images/template/Like-icon.png"/>" 
							title="<s:text name="confirmacion.registro"/>"  width="24" height="24"/>
		    	</c:if>
		    	<c:if test="${row.estado == 0}">
		    		<img src="<s:url value="/images/template/Actions-view-calendar-tasks-close-icon.png"/>" 
							title="<s:text name="registro.cerrado"/>"  width="24" height="24"/>
		    	</c:if>
		    	<c:if test="${row.estado == 1}">
		    		<img src="<s:url value="/images/template/Actions-view-calendar-tasks-icon.png"/>" 
							title="<s:text name="registro.abierto"/>"  width="24" height="24"/>
		    	</c:if>		    	
		    	<c:if test="${row.estado == 2}">
		    		<img src="<s:url value="/images/template/Games-icon.png"/>" 
							title="<s:text name="enjuego"/>"  width="24" height="24"/>
		    	</c:if>
		    	<c:if test="${row.estado == 3}">
		    		<img src="<s:url value="/images/template/checkered-flag-icon.png"/>" 
							title="<s:text name="finalizado"/>"  width="24" height="24"/>
		    	</c:if>		    	
<%-- 		    	<s:else> --%>
<%-- 		    	</s:else> --%>
		    </display:column>
<%-- 			<display:column property="fechaCreacion" titleKey="fecha.creacion" sortable="true" sortName="fechaCreacion" />				 --%>
		</display:table>	
	</div>	
	</s:else>
</s:if>
