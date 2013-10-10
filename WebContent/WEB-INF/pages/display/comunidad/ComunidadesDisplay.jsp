<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">
$("#resultComunidades").on('click','span a, th a', function(event) {
	$("#resultComunidades").load(this.href);
	event.preventDefault();
});
</script>



<s:if test="comunidadPaginatedList != null">
		<display:table name="comunidadPaginatedList" id="row" class="table" sort="external" partialList="true" 
							size="${comunidadPaginatedList.fullListSize}" pagesize="${comunidadPaginatedList.objectsPerPage}" 
							requestURI="${reqURI}">
			<display:column property="id" titleKey="id" />
		    <display:column titleKey="nombre">
		    	<s:a action="VerComunidad"><s:param name="id" >${row.id}</s:param>${row.nombre}</s:a>
		    </display:column>
<%-- 			<display:column property="usuariosMax" titleKey="usuarios.maximos" /> --%>
<%-- 			<display:column property="torneosMax" titleKey="torneos.maximos" /> --%>
<%-- 			<display:column property="torneosActivosMax" titleKey="torneos.activos.maximos" /> --%>
<%-- 			<display:column property="torneosEnRegistroMax" titleKey="torneos.registro.maximos" /> --%>
			<display:column property="fechaCreacion" titleKey="fecha.creacion" />
			
		</display:table>
</s:if>

<s:if test="comunidadList != null">

	<s:if test="comunidadList.size() > 10">
		<display:table name="comunidadList" id="row" class="table" pagesize="10" 
							requestURI="${reqURI}">
			<display:column property="id" titleKey="id" style="width:2%"/>
		    <display:column titleKey="nombre" sortable="true" sortName="nombre">
		    	<a href="<s:url action="VerComunidad"><s:param name="id" >${row.id}</s:param></s:url>">${row.nombre}</a>
		    </display:column>				
		    
		    <c:set var="coleccionAct" value="${row.actividades}" />
		    
		    <display:column titleKey="palabra.actividades" style="width:160px">
		    	<c:forEach  var="var" items="${coleccionAct}" end="4">
		    		<s:set name="rutaIMG">/images/iconos/actividades/25${var.rutaimagen}</s:set>
		    		
			    	<s:if test="#request.locale.language.equals('es')">
						<img src="<s:url value="%{rutaIMG}"/>" 
							title="<c:out value="${var.nombre}"/>" width="25" height="25"/>
					</s:if>
					<s:elseif test="#request.locale.language.equals('en')">
						<s:if test="nombre_EN!=null">
							<img src="<s:url value="%{rutaIMG}"/>" 
								title="<s:property value="nombre_EN"/>" width="25" height="25"/>
						</s:if>
						<s:else>
							<img src="<s:url value="%{rutaIMG}"/>" 
								title="<s:property value="nombre"/>" width="25" height="25"/>
						</s:else>
					</s:elseif>
					<s:elseif test="#request.locale.language.equals('ca')">
						<s:if test="nombre_CA!=null">
							<img src="<s:url value="%{rutaIMG}"/>" 
							title="<s:property value="nombre_CA"/>"  width="25" height="25"/>
						</s:if>
						<s:else>
							<img src="<s:url value="%{rutaIMG}"/>" 
								title="<s:property value="nombre"/>" width="25" height="25"/>
						</s:else>
					</s:elseif>
		    	</c:forEach>
		    	<c:if test="${coleccionAct.size() > 5}">
		    		(<c:out value="${coleccionAct.size()}"/>)
		    	</c:if>
		    </display:column>
		    
		    <c:set var="coleccionPl" value="${row.plataformas}" />
		    
		    <display:column titleKey="palabra.plataformas" style="width:160px">
		    	<c:forEach  var="var" items="${coleccionPl}" end="4">
		    		<s:set name="rutaIMG">/images/iconos/plataformas/25${var.rutaimagen}</s:set>
		    		
			    	<s:if test="#request.locale.language.equals('es')">
						<img src="<s:url value="%{rutaIMG}"/>" 
							title="<c:out value="${var.nombre}"/>" width="25" height="25"/>
					</s:if>
					<s:elseif test="#request.locale.language.equals('en')">
						<s:if test="nombre_EN!=null">
							<img src="<s:url value="%{rutaIMG}"/>" 
								title="<s:property value="nombre_EN"/>" width="25" height="25"/>
						</s:if>
						<s:else>
							<img src="<s:url value="%{rutaIMG}"/>" 
								title="<s:property value="nombre"/>" width="25" height="25"/>
						</s:else>
					</s:elseif>
					<s:elseif test="#request.locale.language.equals('ca')">
						<s:if test="nombre_CA!=null">
							<img src="<s:url value="%{rutaIMG}"/>" 
							title="<s:property value="nombre_CA"/>"  width="25" height="25"/>
						</s:if>
						<s:else>
							<img src="<s:url value="%{rutaIMG}"/>" 
								title="<s:property value="nombre"/>" width="25" height="25"/>
						</s:else>
					</s:elseif>
		    	</c:forEach>
		    	<c:if test="${coleccionPl.size() > 5}">
		    		(<c:out value="${coleccionPl.size()}"/>)
		    	</c:if>		    
		    </display:column>
		    <display:column  style="width:55px">
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
<%-- 		    	<s:else> --%>
<%-- 		    	</s:else> --%>
		    </display:column>		    
		</display:table>
		</s:if>
		
		<s:else>
		<div class="solo_pagebanner">
		
		<display:table name="comunidadList" id="row" class="table" pagesize="10" 
							requestURI="${reqURI}">
			<display:column property="id" titleKey="id" style="width:2%"/>
		    <display:column titleKey="nombre" sortable="true" sortName="nombre">
		    	<a href="<s:url action="VerComunidad"><s:param name="id" >${row.id}</s:param></s:url>">${row.nombre}</a>
		    </display:column>				
		    
		    <c:set var="coleccionAct" value="${row.actividades}" />
		    
		    <display:column titleKey="palabra.actividades" style="width:160px">
		    	<c:forEach  var="var" items="${coleccionAct}" end="4">
		    		<s:set name="rutaIMG">/images/iconos/actividades/25${var.rutaimagen}</s:set>
		    		
			    	<s:if test="#request.locale.language.equals('es')">
						<img src="<s:url value="%{rutaIMG}"/>" 
							title="<c:out value="${var.nombre}"/>" width="25" height="25"/>
					</s:if>
					<s:elseif test="#request.locale.language.equals('en')">
						<s:if test="nombre_EN!=null">
							<img src="<s:url value="%{rutaIMG}"/>" 
								title="<s:property value="nombre_EN"/>" width="25" height="25"/>
						</s:if>
						<s:else>
							<img src="<s:url value="%{rutaIMG}"/>" 
								title="<s:property value="nombre"/>" width="25" height="25"/>
						</s:else>
					</s:elseif>
					<s:elseif test="#request.locale.language.equals('ca')">
						<s:if test="nombre_CA!=null">
							<img src="<s:url value="%{rutaIMG}"/>" 
							title="<s:property value="nombre_CA"/>"  width="25" height="25"/>
						</s:if>
						<s:else>
							<img src="<s:url value="%{rutaIMG}"/>" 
								title="<s:property value="nombre"/>" width="25" height="25"/>
						</s:else>
					</s:elseif>
		    	</c:forEach>
		    	<c:if test="${coleccionAct.size() > 5}">
		    		(<c:out value="${coleccionAct.size()}"/>)
		    	</c:if>
		    </display:column>
		    
		    <c:set var="coleccionPl" value="${row.plataformas}" />
		    
		    <display:column titleKey="palabra.plataformas" style="width:160px">
		    	<c:forEach  var="var" items="${coleccionPl}" end="4">
		    		<s:set name="rutaIMG">/images/iconos/plataformas/25${var.rutaimagen}</s:set>
		    		
			    	<s:if test="#request.locale.language.equals('es')">
						<img src="<s:url value="%{rutaIMG}"/>" 
							title="<c:out value="${var.nombre}"/>" width="25" height="25"/>
					</s:if>
					<s:elseif test="#request.locale.language.equals('en')">
						<s:if test="nombre_EN!=null">
							<img src="<s:url value="%{rutaIMG}"/>" 
								title="<s:property value="nombre_EN"/>" width="25" height="25"/>
						</s:if>
						<s:else>
							<img src="<s:url value="%{rutaIMG}"/>" 
								title="<s:property value="nombre"/>" width="25" height="25"/>
						</s:else>
					</s:elseif>
					<s:elseif test="#request.locale.language.equals('ca')">
						<s:if test="nombre_CA!=null">
							<img src="<s:url value="%{rutaIMG}"/>" 
							title="<s:property value="nombre_CA"/>"  width="25" height="25"/>
						</s:if>
						<s:else>
							<img src="<s:url value="%{rutaIMG}"/>" 
								title="<s:property value="nombre"/>" width="25" height="25"/>
						</s:else>
					</s:elseif>
		    	</c:forEach>
		    	<c:if test="${coleccionPl.size() > 5}">
		    		(<c:out value="${coleccionPl.size()}"/>)
		    	</c:if>		    
		    </display:column>
		    <display:column  style="width:55px">
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
<%-- 		    	<s:else> --%>
<%-- 		    	</s:else> --%>
		    </display:column>		    
		</display:table>	
		
		</div>	
		</s:else>
</s:if>

<s:if test="userComunidadList != null">

	<s:if test="userComunidadList.size() > 10">
	
		<display:table name="userComunidadList" id="row2" class="table" pagesize="10" 
							requestURI="${reqURI}">
			<display:column titleKey="id" style="width:2%">
				<c:out value="${row2.id.parentComunidad.id}"/>
			</display:column>
		    <display:column titleKey="nombre" sortable="true" sortName="nombre">
		    	<s:a action="VerComunidad"><s:param name="id" >${row2.id.parentComunidad.id}</s:param>${row2.id.parentComunidad.nombre}</s:a>
		    </display:column>
		    
		    <c:set var="coleccionAct" value="${row2.id.parentComunidad.actividades}" />
		    
		    <display:column titleKey="palabra.actividades" style="width:160px">
		    	<c:forEach  var="var" items="${coleccionAct}" end="4">
		    		<s:set name="rutaIMG">/images/iconos/actividades/25${var.rutaimagen}</s:set>
		    		
			    	<s:if test="#request.locale.language.equals('es')">
						<img src="<s:url value="%{rutaIMG}"/>" 
							title="<c:out value="${var.nombre}"/>" width="25" height="25"/>
					</s:if>
					<s:elseif test="#request.locale.language.equals('en')">
						<s:if test="nombre_EN!=null">
							<img src="<s:url value="%{rutaIMG}"/>" 
								title="<s:property value="nombre_EN"/>" width="25" height="25"/>
						</s:if>
						<s:else>
							<img src="<s:url value="%{rutaIMG}"/>" 
								title="<s:property value="nombre"/>" width="25" height="25"/>
						</s:else>
					</s:elseif>
					<s:elseif test="#request.locale.language.equals('ca')">
						<s:if test="nombre_CA!=null">
							<img src="<s:url value="%{rutaIMG}"/>" 
							title="<s:property value="nombre_CA"/>"  width="25" height="25"/>
						</s:if>
						<s:else>
							<img src="<s:url value="%{rutaIMG}"/>" 
								title="<s:property value="nombre"/>" width="25" height="25"/>
						</s:else>
					</s:elseif>
		    	</c:forEach>
		    	<c:if test="${coleccionAct.size() > 5}">
		    		(<c:out value="${coleccionAct.size()}"/>)
		    	</c:if>
		    </display:column>
		    
		    <c:set var="coleccionPl" value="${row2.id.parentComunidad.plataformas}" />
		    
		    <display:column titleKey="palabra.plataformas" style="width:160px">
		    	<c:forEach  var="var" items="${coleccionPl}" end="4">
		    		<s:set name="rutaIMG">/images/iconos/plataformas/25${var.rutaimagen}</s:set>
		    		
			    	<s:if test="#request.locale.language.equals('es')">
						<img src="<s:url value="%{rutaIMG}"/>" 
							title="<c:out value="${var.nombre}"/>" width="25" height="25"/>
					</s:if>
					<s:elseif test="#request.locale.language.equals('en')">
						<s:if test="nombre_EN!=null">
							<img src="<s:url value="%{rutaIMG}"/>" 
								title="<s:property value="nombre_EN"/>" width="25" height="25"/>
						</s:if>
						<s:else>
							<img src="<s:url value="%{rutaIMG}"/>" 
								title="<s:property value="nombre"/>" width="25" height="25"/>
						</s:else>
					</s:elseif>
					<s:elseif test="#request.locale.language.equals('ca')">
						<s:if test="nombre_CA!=null">
							<img src="<s:url value="%{rutaIMG}"/>" 
							title="<s:property value="nombre_CA"/>"  width="25" height="25"/>
						</s:if>
						<s:else>
							<img src="<s:url value="%{rutaIMG}"/>" 
								title="<s:property value="nombre"/>" width="25" height="25"/>
						</s:else>
					</s:elseif>
		    	</c:forEach>
		    	<c:if test="${coleccionPl.size() > 5}">
		    		(<c:out value="${coleccionPl.size()}"/>)
		    	</c:if>
		    </display:column>		    
		    <display:column style="width:55px">
		    	<c:if test="${row2.id.parentComunidad.passwordProtected != null && 
		    	row2.id.parentComunidad.passwordProtected== true}">
		    		<img src="<s:url value="/images/template/Lock-icon.png"/>" 
							title="<s:text name="password.protected"/>"  width="24" height="24"/>
		    	</c:if>
<%-- 		    	<s:else> --%>
<%-- 		    	</s:else> --%>
		    	<c:if test="${row2.id.parentComunidad.regRequest != null && row2.id.parentComunidad.regRequest == true}">
		    		<img src="<s:url value="/images/template/Like-icon.png"/>" 
							title="<s:text name="confirmacion.registro"/>"  width="24" height="24"/>
		    	</c:if>
<%-- 		    	<s:else> --%>
<%-- 		    	</s:else> --%>
		    </display:column>		    
<%-- 			<display:column property="usuariosMax" titleKey="usuarios.maximos" /> --%>
<%-- 			<display:column property="torneosMax" titleKey="torneos.maximos" /> --%>
<%-- 			<display:column property="torneosActivosMax" titleKey="torneos.activos.maximos" /> --%>
<%-- 			<display:column property="torneosEnRegistroMax" titleKey="torneos.registro.maximos" /> --%>
<%-- 			<display:column property="fechaCreacion" titleKey="fecha.creacion" sortable="true" sortName="fechaCreacion"/> --%>
			
		</display:table>
		
	</s:if>
	
	<s:else>
	<div class="solo_pagebanner">
		<display:table name="userComunidadList" id="row2" class="table" pagesize="10" 
							requestURI="${reqURI}">
			<display:column titleKey="id" style="width:2%">
				<c:out value="${row2.id.parentComunidad.id}"/>
			</display:column>
		    <display:column titleKey="nombre" sortable="true" sortName="nombre">
		    	<s:a action="VerComunidad"><s:param name="id" >${row2.id.parentComunidad.id}</s:param>${row2.id.parentComunidad.nombre}</s:a>
		    </display:column>
		    
		    <c:set var="coleccionAct" value="${row2.id.parentComunidad.actividades}" />
		    
		    <display:column titleKey="palabra.actividades" style="width:160px">
		    	<c:forEach  var="var" items="${coleccionAct}" end="4">
		    		<s:set name="rutaIMG">/images/iconos/actividades/25${var.rutaimagen}</s:set>
		    		
			    	<s:if test="#request.locale.language.equals('es')">
						<img src="<s:url value="%{rutaIMG}"/>" 
							title="<c:out value="${var.nombre}"/>" width="25" height="25"/>
					</s:if>
					<s:elseif test="#request.locale.language.equals('en')">
						<s:if test="nombre_EN!=null">
							<img src="<s:url value="%{rutaIMG}"/>" 
								title="<s:property value="nombre_EN"/>" width="25" height="25"/>
						</s:if>
						<s:else>
							<img src="<s:url value="%{rutaIMG}"/>" 
								title="<s:property value="nombre"/>" width="25" height="25"/>
						</s:else>
					</s:elseif>
					<s:elseif test="#request.locale.language.equals('ca')">
						<s:if test="nombre_CA!=null">
							<img src="<s:url value="%{rutaIMG}"/>" 
							title="<s:property value="nombre_CA"/>"  width="25" height="25"/>
						</s:if>
						<s:else>
							<img src="<s:url value="%{rutaIMG}"/>" 
								title="<s:property value="nombre"/>" width="25" height="25"/>
						</s:else>
					</s:elseif>
		    	</c:forEach>
		    	<c:if test="${coleccionAct.size() > 5}">
		    		(<c:out value="${coleccionAct.size()}"/>)
		    	</c:if>
		    </display:column>
		    
		    <c:set var="coleccionPl" value="${row2.id.parentComunidad.plataformas}" />
		    
		    <display:column titleKey="palabra.plataformas" style="width:160px">
		    	<c:forEach  var="var" items="${coleccionPl}" end="4">
		    		<s:set name="rutaIMG">/images/iconos/plataformas/25${var.rutaimagen}</s:set>
		    		
			    	<s:if test="#request.locale.language.equals('es')">
						<img src="<s:url value="%{rutaIMG}"/>" 
							title="<c:out value="${var.nombre}"/>" width="25" height="25"/>
					</s:if>
					<s:elseif test="#request.locale.language.equals('en')">
						<s:if test="nombre_EN!=null">
							<img src="<s:url value="%{rutaIMG}"/>" 
								title="<s:property value="nombre_EN"/>" width="25" height="25"/>
						</s:if>
						<s:else>
							<img src="<s:url value="%{rutaIMG}"/>" 
								title="<s:property value="nombre"/>" width="25" height="25"/>
						</s:else>
					</s:elseif>
					<s:elseif test="#request.locale.language.equals('ca')">
						<s:if test="nombre_CA!=null">
							<img src="<s:url value="%{rutaIMG}"/>" 
							title="<s:property value="nombre_CA"/>"  width="25" height="25"/>
						</s:if>
						<s:else>
							<img src="<s:url value="%{rutaIMG}"/>" 
								title="<s:property value="nombre"/>" width="25" height="25"/>
						</s:else>
					</s:elseif>
		    	</c:forEach>
		    	<c:if test="${coleccionPl.size() > 5}">
		    		(<c:out value="${coleccionPl.size()}"/>)
		    	</c:if>
		    </display:column>		    
		    <display:column style="width:55px">
		    	<c:if test="${row2.id.parentComunidad.passwordProtected != null && 
		    	row2.id.parentComunidad.passwordProtected== true}">
		    		<img src="<s:url value="/images/template/Lock-icon.png"/>" 
							title="<s:text name="password.protected"/>"  width="24" height="24"/>
		    	</c:if>
<%-- 		    	<s:else> --%>
<%-- 		    	</s:else> --%>
		    	<c:if test="${row2.id.parentComunidad.regRequest != null && row2.id.parentComunidad.regRequest == true}">
		    		<img src="<s:url value="/images/template/Like-icon.png"/>" 
							title="<s:text name="confirmacion.registro"/>"  width="24" height="24"/>
		    	</c:if>
<%-- 		    	<s:else> --%>
<%-- 		    	</s:else> --%>
		    </display:column>		    
<%-- 			<display:column property="usuariosMax" titleKey="usuarios.maximos" /> --%>
<%-- 			<display:column property="torneosMax" titleKey="torneos.maximos" /> --%>
<%-- 			<display:column property="torneosActivosMax" titleKey="torneos.activos.maximos" /> --%>
<%-- 			<display:column property="torneosEnRegistroMax" titleKey="torneos.registro.maximos" /> --%>
<%-- 			<display:column property="fechaCreacion" titleKey="fecha.creacion" sortable="true" sortName="fechaCreacion"/> --%>
			
		</display:table>	
	</div>		
	</s:else>
</s:if>
		
<s:if test="equipoComunidadList != null">
	<s:if test="equipoComunidadList.size() > 10">
	
		<display:table name="equipoComunidadList" id="row3" class="table" pagesize="10" 
							requestURI="${reqURI}">
			<display:column titleKey="id" style="width:2%">
				<c:out value="${row3.id.parentComunidad.id}"/>
			</display:column>
		    <display:column titleKey="nombre" sortable="true" sortName="nombre">
		    	<s:a action="VerComunidad"><s:param name="id" >${row3.id.parentComunidad.id}</s:param>${row3.id.parentComunidad.nombre}</s:a>
		    </display:column>
		    
		    <c:set var="coleccionAct" value="${row3.id.parentComunidad.actividades}" />
		    
		    <display:column titleKey="palabra.actividades" style="width:160px">
		    	<c:forEach  var="var" items="${coleccionAct}" end="4">
		    		<s:set name="rutaIMG">/images/iconos/actividades/25${var.rutaimagen}</s:set>
		    		
			    	<s:if test="#request.locale.language.equals('es')">
						<img src="<s:url value="%{rutaIMG}"/>" 
							title="<c:out value="${var.nombre}"/>" width="25" height="25"/>
					</s:if>
					<s:elseif test="#request.locale.language.equals('en')">
						<s:if test="nombre_EN!=null">
							<img src="<s:url value="%{rutaIMG}"/>" 
								title="<s:property value="nombre_EN"/>" width="25" height="25"/>
						</s:if>
						<s:else>
							<img src="<s:url value="%{rutaIMG}"/>" 
								title="<s:property value="nombre"/>" width="25" height="25"/>
						</s:else>
					</s:elseif>
					<s:elseif test="#request.locale.language.equals('ca')">
						<s:if test="nombre_CA!=null">
							<img src="<s:url value="%{rutaIMG}"/>" 
							title="<s:property value="nombre_CA"/>"  width="25" height="25"/>
						</s:if>
						<s:else>
							<img src="<s:url value="%{rutaIMG}"/>" 
								title="<s:property value="nombre"/>" width="25" height="25"/>
						</s:else>
					</s:elseif>
		    	</c:forEach>
		    	<c:if test="${coleccionAct.size() > 5}">
		    		(<c:out value="${coleccionAct.size()}"/>)
		    	</c:if>
		    </display:column>
		    
		    <c:set var="coleccionPl" value="${row3.id.parentComunidad.plataformas}" />
		    
		    <display:column titleKey="palabra.plataformas" style="width:160px">
		    	<c:forEach  var="var" items="${coleccionPl}" end="4">
		    		<s:set name="rutaIMG">/images/iconos/plataformas/25${var.rutaimagen}</s:set>
		    		
			    	<s:if test="#request.locale.language.equals('es')">
						<img src="<s:url value="%{rutaIMG}"/>" 
							title="<c:out value="${var.nombre}"/>" width="25" height="25"/>
					</s:if>
					<s:elseif test="#request.locale.language.equals('en')">
						<s:if test="nombre_EN!=null">
							<img src="<s:url value="%{rutaIMG}"/>" 
								title="<s:property value="nombre_EN"/>" width="25" height="25"/>
						</s:if>
						<s:else>
							<img src="<s:url value="%{rutaIMG}"/>" 
								title="<s:property value="nombre"/>" width="25" height="25"/>
						</s:else>
					</s:elseif>
					<s:elseif test="#request.locale.language.equals('ca')">
						<s:if test="nombre_CA!=null">
							<img src="<s:url value="%{rutaIMG}"/>" 
							title="<s:property value="nombre_CA"/>"  width="25" height="25"/>
						</s:if>
						<s:else>
							<img src="<s:url value="%{rutaIMG}"/>" 
								title="<s:property value="nombre"/>" width="25" height="25"/>
						</s:else>
					</s:elseif>
		    	</c:forEach>
		    	<c:if test="${coleccionPl.size() > 5}">
		    		(<c:out value="${coleccionPl.size()}"/>)
		    	</c:if>
		    </display:column>
		    <display:column style="width:55px">
		    	<c:if test="${row2.id.parentComunidad.passwordProtected != null && 
		    	row2.id.parentComunidad.passwordProtected== true}">
		    		<img src="<s:url value="/images/template/Lock-icon.png"/>" 
							title="<s:text name="password.protected"/>"  width="24" height="24"/>
		    	</c:if>
<%-- 		    	<s:else> --%>
<%-- 		    	</s:else> --%>
		    	<c:if test="${row2.id.parentComunidad.regRequest != null && row2.id.parentComunidad.regRequest == true}">
		    		<img src="<s:url value="/images/template/Like-icon.png"/>" 
							title="<s:text name="confirmacion.registro"/>"  width="24" height="24"/>
		    	</c:if>
<%-- 		    	<s:else> --%>
<%-- 		    	</s:else> --%>
		    </display:column>		    
<%-- 			<display:column property="usuariosMax" titleKey="usuarios.maximos" /> --%>
<%-- 			<display:column property="torneosMax" titleKey="torneos.maximos" /> --%>
<%-- 			<display:column property="torneosActivosMax" titleKey="torneos.activos.maximos" /> --%>
<%-- 			<display:column property="torneosEnRegistroMax" titleKey="torneos.registro.maximos" /> --%>
<%-- 			<display:column property="fechaCreacion" titleKey="fecha.creacion" sortable="true" sortName="fechaCreacion"/> --%>
			
		</display:table>	
	</s:if>
	
	<s:else>
	<div class="solo_pagebanner">
		<display:table name="equipoComunidadList" id="row3" class="table" pagesize="10" 
							requestURI="${reqURI}">
			<display:column titleKey="id" style="width:2%">
				<c:out value="${row3.id.parentComunidad.id}"/>
			</display:column>
		    <display:column titleKey="nombre" sortable="true" sortName="nombre">
		    	<s:a action="VerComunidad"><s:param name="id" >${row3.id.parentComunidad.id}</s:param>${row3.id.parentComunidad.nombre}</s:a>
		    </display:column>
		    
		    <c:set var="coleccionAct" value="${row3.id.parentComunidad.actividades}" />
		    
		    <display:column titleKey="palabra.actividades" style="width:160px">
		    	<c:forEach  var="var" items="${coleccionAct}" end="4">
		    		<s:set name="rutaIMG">/images/iconos/actividades/25${var.rutaimagen}</s:set>
		    		
			    	<s:if test="#request.locale.language.equals('es')">
						<img src="<s:url value="%{rutaIMG}"/>" 
							title="<c:out value="${var.nombre}"/>" width="25" height="25"/>
					</s:if>
					<s:elseif test="#request.locale.language.equals('en')">
						<s:if test="nombre_EN!=null">
							<img src="<s:url value="%{rutaIMG}"/>" 
								title="<s:property value="nombre_EN"/>" width="25" height="25"/>
						</s:if>
						<s:else>
							<img src="<s:url value="%{rutaIMG}"/>" 
								title="<s:property value="nombre"/>" width="25" height="25"/>
						</s:else>
					</s:elseif>
					<s:elseif test="#request.locale.language.equals('ca')">
						<s:if test="nombre_CA!=null">
							<img src="<s:url value="%{rutaIMG}"/>" 
							title="<s:property value="nombre_CA"/>"  width="25" height="25"/>
						</s:if>
						<s:else>
							<img src="<s:url value="%{rutaIMG}"/>" 
								title="<s:property value="nombre"/>" width="25" height="25"/>
						</s:else>
					</s:elseif>
		    	</c:forEach>
		    	<c:if test="${coleccionAct.size() > 5}">
		    		(<c:out value="${coleccionAct.size()}"/>)
		    	</c:if>
		    </display:column>
		    
		    <c:set var="coleccionPl" value="${row3.id.parentComunidad.plataformas}" />
		    
		    <display:column titleKey="palabra.plataformas" style="width:160px">
		    	<c:forEach  var="var" items="${coleccionPl}" end="4">
		    		<s:set name="rutaIMG">/images/iconos/plataformas/25${var.rutaimagen}</s:set>
		    		
			    	<s:if test="#request.locale.language.equals('es')">
						<img src="<s:url value="%{rutaIMG}"/>" 
							title="<c:out value="${var.nombre}"/>" width="25" height="25"/>
					</s:if>
					<s:elseif test="#request.locale.language.equals('en')">
						<s:if test="nombre_EN!=null">
							<img src="<s:url value="%{rutaIMG}"/>" 
								title="<s:property value="nombre_EN"/>" width="25" height="25"/>
						</s:if>
						<s:else>
							<img src="<s:url value="%{rutaIMG}"/>" 
								title="<s:property value="nombre"/>" width="25" height="25"/>
						</s:else>
					</s:elseif>
					<s:elseif test="#request.locale.language.equals('ca')">
						<s:if test="nombre_CA!=null">
							<img src="<s:url value="%{rutaIMG}"/>" 
							title="<s:property value="nombre_CA"/>"  width="25" height="25"/>
						</s:if>
						<s:else>
							<img src="<s:url value="%{rutaIMG}"/>" 
								title="<s:property value="nombre"/>" width="25" height="25"/>
						</s:else>
					</s:elseif>
		    	</c:forEach>
		    	<c:if test="${coleccionPl.size() > 5}">
		    		(<c:out value="${coleccionPl.size()}"/>)
		    	</c:if>
		    </display:column>
		    <display:column style="width:55px">
		    	<c:if test="${row2.id.parentComunidad.passwordProtected != null && 
		    	row2.id.parentComunidad.passwordProtected== true}">
		    		<img src="<s:url value="/images/template/Lock-icon.png"/>" 
							title="<s:text name="password.protected"/>"  width="24" height="24"/>
		    	</c:if>
<%-- 		    	<s:else> --%>
<%-- 		    	</s:else> --%>
		    	<c:if test="${row2.id.parentComunidad.regRequest != null && row2.id.parentComunidad.regRequest == true}">
		    		<img src="<s:url value="/images/template/Like-icon.png"/>" 
							title="<s:text name="confirmacion.registro"/>"  width="24" height="24"/>
		    	</c:if>
<%-- 		    	<s:else> --%>
<%-- 		    	</s:else> --%>
		    </display:column>		    
<%-- 			<display:column property="usuariosMax" titleKey="usuarios.maximos" /> --%>
<%-- 			<display:column property="torneosMax" titleKey="torneos.maximos" /> --%>
<%-- 			<display:column property="torneosActivosMax" titleKey="torneos.activos.maximos" /> --%>
<%-- 			<display:column property="torneosEnRegistroMax" titleKey="torneos.registro.maximos" /> --%>
<%-- 			<display:column property="fechaCreacion" titleKey="fecha.creacion" sortable="true" sortName="fechaCreacion"/> --%>
			
		</display:table>	
	</div>
	</s:else>	
</s:if>