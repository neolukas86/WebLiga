<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">
$("#resultEquipos").on('click','span a, th a', function(event) {
	$("#resultEquipos").load(this.href);
	event.preventDefault();
});
</script>

<s:if test="equipoPaginatedList != null">
		<display:table name="equipoPaginatedList" id="row" class="table" sort="external" partialList="true" 
							size="${equipoPaginatedList.fullListSize}" pagesize="${equipoPaginatedList.objectsPerPage}" 
							requestURI="${reqURI}">
<%-- 			<display:column property="id" titleKey="id" /> --%>
		    <display:column titleKey="nombre">
		    	<a href="<s:url action="VerEquipo"><s:param name="id" >${row.id}</s:param></s:url>">${row.nombre}</a>
		    </display:column>				
		</display:table>
</s:if>

<s:if test="equipoList != null">
	<s:if test="equipoList.size() > 10">
		<display:table name="equipoList" id="row" class="table" pagesize="10" 
							requestURI="${reqURI}">
<%-- 			<display:column property="id" titleKey="id" style="width:2%"/> --%>
		    <display:column titleKey="nombre" sortable="true" sortName="nombre">
			    <c:if test="${row.invitado == false}">
			     	<a href="<s:url action="VerEquipo"><s:param name="id" >${row.id}</s:param></s:url>">${row.nombre}</a>
				</c:if>
				<c:if test="${row.invitado == true}">
					<div style="color:grey"><c:out value="${row.nombre}"/></div>
				</c:if>
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
		<display:table name="equipoList" id="row" class="table" pagesize="10" 
							requestURI="${reqURI}">
<%-- 			<display:column property="id" titleKey="id" style="width:2%"/> --%>
		    <display:column titleKey="nombre" sortable="true" sortName="nombre">
			    <c:if test="${row.invitado == false}">
			     	<a href="<s:url action="VerEquipo"><s:param name="id" >${row.id}</s:param></s:url>">${row.nombre}</a>
				</c:if>
				<c:if test="${row.invitado == true}">
					<div style="color:grey"><c:out value="${row.nombre}"/></div>
				</c:if>
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
<s:if test="userEquipoList != null">
	<s:set var="rangoNORMALUSER" value="@com.proyecto.util.funciones.auxiliares.FuncionesAuxiliares@NORMALUSER" />
	<s:url var="rutaCambiarEquipo" value="/images/template/cambiarPerfiAEquipo.png" />
	<s:url var="rutaCambiarUsuario" value="/images/template/cambiarPerfiAUsuario.png" />
	
	<s:if test="userEquipoList.size() > 10">	
		<display:table name="userEquipoList" id="row" class="table" pagesize="10" 
							requestURI="${reqURI}">
<%-- 			<display:column titleKey="id" style="width:2%"> --%>
<%-- 				<c:out value="${row.id.parentEquipo.id}"/> --%>
<%-- 			</display:column> --%>
		    <display:column titleKey="nombre" sortable="true" sortName="nombre">
			    <c:if test="${row.id.parentEquipo.invitado == false}">
			     	<a href="<s:url action="VerEquipo"><s:param name="id" >${row.id.parentEquipo.id}</s:param>
			     		</s:url>">${row.id.parentEquipo.nombre}</a>
				</c:if>
				<c:if test="${row.id.parentEquipo.invitado == true}">
					<div style="color:grey"><c:out value="${row.id.parentEquipo.nombre}"/></div>
				</c:if>		    
		    </display:column>
		    
		    <c:set var="coleccionAct" value="${row.id.parentEquipo.actividades}" />
		    
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
		    
		    <c:set var="coleccionPl" value="${row.id.parentEquipo.plataformas}" />
		    
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
		    <display:column style="width:2%">
		    	<c:if test="${row.rango < rangoNORMALUSER}">		    		
		    		<s:set var="equipoId">${row.id.parentEquipo.id}</s:set>
		    		<s:set var="equipoNombre">${row.id.parentEquipo.nombre}</s:set>
		    		
		    		<s:if test="%{#session.equipo != equipoId}">		    		
			    		<s:form action="TomarPerfilEquipo">
			    			<s:hidden name="id" value="%{equipoId}"/>
			    			<s:hidden name="nombre" value="%{equipoNombre}"/>
			    			<s:submit type="image" title="%{getText('utilizar.perfil.equipo')}" src="%{rutaCambiarEquipo}"/>
			    		</s:form>
			    	</s:if>
			    	<s:else>
			    		<s:form action="TomarPerfilUsuario">
			    			<s:submit type="image" title="%{getText('utilizar.perfil.usuario')}" src="%{rutaCambiarUsuario}"/>
			    		</s:form>
			    	</s:else>
		    	</c:if>
		    </display:column>
		    <display:column style="width:55px">
		    	<c:if test="${row.id.parentEquipo.passwordProtected != null && 
		    	row.id.parentEquipo.passwordProtected== true}">
		    		<img src="<s:url value="/images/template/Lock-icon.png"/>" 
							title="<s:text name="password.protected"/>"  width="24" height="24"/>
		    	</c:if>
<%-- 		    	<s:else> --%>
<%-- 		    	</s:else> --%>
		    	<c:if test="${row.id.parentEquipo.regRequest != null && row.id.parentEquipo.regRequest == true}">
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
		<display:table name="userEquipoList" id="row" class="table" pagesize="10" 
							requestURI="${reqURI}">
<%-- 			<display:column titleKey="id" style="width:2%"> --%>
<%-- 				<c:out value="${row.id.parentEquipo.id}"/> --%>
<%-- 			</display:column> --%>
		    <display:column titleKey="nombre" sortable="true" sortName="nombre">
			    <c:if test="${row.id.parentEquipo.invitado == false}">
			     	<a href="<s:url action="VerEquipo"><s:param name="id" >${row.id.parentEquipo.id}</s:param>
			     		</s:url>">${row.id.parentEquipo.nombre}</a>
				</c:if>
				<c:if test="${row.id.parentEquipo.invitado == true}">
					<div style="color:grey"><c:out value="${row.id.parentEquipo.nombre}"/></div>
				</c:if>		    
		    </display:column>
		    
		    <c:set var="coleccionAct" value="${row.id.parentEquipo.actividades}" />
		    
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
		    
		    <c:set var="coleccionPl" value="${row.id.parentEquipo.plataformas}" />
		    
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
		    <display:column style="width:2%">
		    	<c:if test="${row.rango < rangoNORMALUSER}">		    		
		    		<s:set var="equipoId">${row.id.parentEquipo.id}</s:set>
		    		<s:set var="equipoNombre">${row.id.parentEquipo.nombre}</s:set>
		    		
		    		<s:if test="%{#session.equipo != equipoId}">		    		
			    		<s:form action="TomarPerfilEquipo">
			    			<s:hidden name="id" value="%{equipoId}"/>
			    			<s:hidden name="nombre" value="%{equipoNombre}"/>
			    			<s:submit type="image" title="%{getText('utilizar.perfil.equipo')}" src="%{rutaCambiarEquipo}"/>
			    		</s:form>
			    	</s:if>
			    	<s:else>
			    		<s:form action="TomarPerfilUsuario">
			    			<s:submit type="image" title="%{getText('utilizar.perfil.usuario')}" src="%{rutaCambiarUsuario}"/>
			    		</s:form>
			    	</s:else>
		    	</c:if>
		    </display:column>
		    <display:column style="width:55px">
		    	<c:if test="${row.id.parentEquipo.passwordProtected != null && 
		    	row.id.parentEquipo.passwordProtected== true}">
		    		<img src="<s:url value="/images/template/Lock-icon.png"/>" 
							title="<s:text name="password.protected"/>"  width="24" height="24"/>
		    	</c:if>
<%-- 		    	<s:else> --%>
<%-- 		    	</s:else> --%>
		    	<c:if test="${row.id.parentEquipo.regRequest != null && row.id.parentEquipo.regRequest == true}">
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