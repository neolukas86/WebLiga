<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
	<s:url var="rutaImgAceptarUsuario" value="/images/template/Person-Male-Light-icon16-add.png" />
	<s:url var="rutaImgDenegarUsuario" value="/images/template/Person-Male-Light-icon16-remove.png" />
	<s:url var="rutaImgAceptarEquipo" value="/images/template/Group-Meeting-Light-icon16-add.png" />
	<s:url var="rutaImgDenegarEquipo" value="/images/template/Group-Meeting-Light-icon16-remove.png" />
	
	<s:if test="userList != null && !userList.isEmpty()">
		<div class="form">
		<fieldset>
			<legend >Solicitud de entrada de usuario</legend>			
			
			<display:table name="userList" id="row" class="table" pagesize="10" 
					requestURI="GoRequestsTorneo">
<%-- 			    <display:column property="id" titleKey="id" /> --%>
		     	<display:column titleKey="user.alias" >
				<s:url var="perfilAction" action="GoPerfil"><s:param name="id" >${row.id}</s:param></s:url>
				
				<sj:a  
					href="%{perfilAction}"
					openDialog="myremotelinkdialog"
					button="false" >
						${row.alias}
				</sj:a>
		     </display:column>
<%-- 			    <display:column property="nombre" titleKey="firstname"/> --%>
<%-- 			    <display:column property="apellido" titleKey="lastname"/> --%>
<%-- 			    <display:column property="email" titleKey="email" autolink="true"/> --%>
<%-- 			    <display:column property="nacimiento" titleKey="fecha.nacimiento"/> --%>
			    <display:column titleKey="palabra.pais">
			    	<c:out value="${row.parentPais.nombre}" />
			    </display:column>
			    <display:column titleKey="sexo">
			    	<c:if test="${row.sexo == false}">
			    		<s:text name="sexo.femenino"/>
			    	</c:if>
			    	<c:if test="${row.sexo == true}">
			    		<s:text name="sexo.masculino"/>
			    	</c:if>
			    </display:column>
			    <display:column property="nacimiento" titleKey="fecha.nacimiento"/>
			    <display:column property="fechaRegistro" titleKey="fecha.registro"/>
			    <display:column titleKey="aceptar">
			    <s:set var="userId">${row.id}</s:set>
			    
				    <s:form action="AceptarUserRequestTorneo" method="post">
			     		<s:hidden name="id" value="%{id}"/>
			     		<s:hidden name="iduser" value="%{userId}"/>
			     		<s:submit type="image" title="%{getText('aceptar')}" src="%{rutaImgAceptarUsuario}" cssClass="centrado"/>
			     	</s:form>
			    </display:column>
			    <display:column titleKey="rechazar">
				    <s:form action="DenegarUserRequestTorneo" method="post">
			     		<s:hidden name="id" value="%{id}"/>
			     		<s:hidden name="iduser" value="%{userId}"/>
			     		<s:submit type="image" title="%{getText('rechazar')}" src="%{rutaImgDenegarUsuario}" cssClass="centrado"/>
			     	</s:form>
			    </display:column>	
		 </display:table>
		 
		 </fieldset>
		 </div>
	</s:if>
		 
<!-- 		 Me tocará hacer dos actions aparte para que no altere el avanzar en una tabla a la otra -->
	<s:if test="userInvitadoList != null && !userInvitadoList.isEmpty()">
		<div class="form">
		<fieldset>
			<legend >Solicitud de asociación a usuario invitado</legend>		 
		 <display:table name="userInvitadoList" id="row2" class="table" pagesize="10" 
			requestURI="GoRequestsTorneo">
			<display:column titleKey="solicitante">
				<s:url var="perfilActionUsuario" action="GoPerfil"><s:param name="id" >${row2.solicitante.id}</s:param></s:url>
				
				<sj:a  
					href="%{perfilActionUsuario}"
					openDialog="myremotelinkdialog"
					button="false" >
						${row2.solicitante.alias}
				</sj:a>
<%-- 			    <c:out value="${row2.solicitante.alias}" /> --%>
			</display:column>
			<display:column titleKey="solicitado">
				<div style="color:grey"><c:out value="${row2.solicitado.alias}" /></div>
			</display:column>
			
			<s:set var="userSolicitanteId">${row2.solicitante.id}</s:set>
			<s:set var="userSolicitadoId">${row2.solicitado.id}</s:set>
			
			<display:column titleKey="aceptar" style="width:2%">
				<s:form action="AceptarRequestUserInvitado" method="post">
			     	<s:hidden name="id" value="%{userSolicitanteId}"/>
			     	<s:hidden name="entero" value="%{userSolicitadoId}"/>
			     	<s:hidden name="idtorneo" value="%{id}"/>
			     	<s:submit type="image" title="%{getText('aceptar')}" src="%{rutaImgAceptarUsuario}" cssClass="centrado"/>
			    </s:form>
			</display:column>
			<display:column titleKey="rechazar" style="width:2%">
				<s:form action="RechazarRequestUserInvitado" method="post">
			     	<s:hidden name="id" value="%{userSolicitanteId}"/>
			     	<s:hidden name="entero" value="%{userSolicitadoId}"/>
			     	<s:hidden name="idtorneo" value="%{id}"/>
			     	<s:submit type="image" title="%{getText('rechazar')}" src="%{rutaImgDenegarUsuario}" cssClass="centrado"/>
			     </s:form>
			</display:column>
		</display:table>
		</fieldset>
		</div>
	</s:if>
	
	<s:if test="equipoList != null && !equipoList.isEmpty()">
		<div class="form">
		<fieldset>
			<legend >Solicitud de entrada de equipo</legend>				
			
			<display:table name="equipoList" id="rowEq" class="table" pagesize="10" 
					requestURI="GoRequestsTorneo">
<%-- 			    <display:column property="id" titleKey="id" /> --%>
				<display:column titleKey="nombre" sortable="true" sortName="nombre">
		    		<a href="<s:url action="VerEquipo"><s:param name="id" >${rowEq.id}</s:param></s:url>">${rowEq.nombre}</a>
		    	</display:column>				
		    
		    	<c:set var="coleccionAct" value="${rowEq.actividades}" />
		    
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
		    
			    <c:set var="coleccionPl" value="${rowEq.plataformas}" />
			    
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

			    <display:column titleKey="aceptar">
			    <s:set var="equipoId">${rowEq.id}</s:set>
			    
				    <s:form action="AceptarEquipoRequestTorneo" method="post">
			     		<s:hidden name="id" value="%{id}"/>
			     		<s:hidden name="idequipo" value="%{equipoId}"/>
			     		<s:submit type="image" title="%{getText('aceptar')}" src="%{rutaImgAceptarEquipo}" cssClass="centrado"/>
			     	</s:form>
			    </display:column>
			    <display:column titleKey="rechazar">
				    <s:form action="DenegarEquipoRequestTorneo" method="post">
			     		<s:hidden name="id" value="%{id}"/>
			     		<s:hidden name="idequipo" value="%{equipoId}"/>
			     		<s:submit type="image" title="%{getText('rechazar')}" src="%{rutaImgDenegarEquipo}" cssClass="centrado"/>
			     	</s:form>
			    </display:column>	
		 </display:table>
		 </fieldset>
		 </div>
	</s:if>
		 
<!-- 		 Me tocará hacer dos actions aparte para que no altere el avanzar en una tabla a la otra -->
	<s:if test="equipoInvitadoList != null && !equipoInvitadoList.isEmpty()">
		<div class="form">
		<fieldset>
			<legend>Solicitud de asociación de equipo invitado</legend>		 
		 <display:table name="equipoInvitadoList" id="rowEq2" class="table" pagesize="10" 
			requestURI="GoRequestsTorneo">
			<display:column titleKey="solicitante">
		    	<a href="<s:url action="VerEquipo"><s:param name="id" >${rowEq2.id}</s:param></s:url>">${rowEq2.nombre}</a>
		    </display:column>
		    
		    <display:column titleKey="solicitado">
				<div style="color:grey"><c:out value="${rowEq2.solicitado.nombre}" /></div>
			</display:column>				
		    
		    <c:set var="coleccionAct2" value="${rowEq2.actividades}" />
		    
		    <display:column titleKey="palabra.actividades" style="width:160px">
		    	<c:forEach  var="var" items="${coleccionAct2}" end="4">
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
		    	<c:if test="${coleccionAct2.size() > 5}">
		    		(<c:out value="${coleccionAct2.size()}"/>)
		    	</c:if>
		    </display:column>
		    
		    <c:set var="coleccionPl2" value="${rowEq2.plataformas}" />
		    
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
		    	<c:if test="${coleccionPl2.size() > 5}">
		    		(<c:out value="${coleccionPl2.size()}"/>)
		    	</c:if>		    
		    </display:column>
			
			<s:set var="equipoSolicitanteId">${rowEq2.solicitante.id}</s:set>
			<s:set var="equipoSolicitadoId">${rowEq2.solicitado.id}</s:set>
			
			<display:column titleKey="aceptar" style="width:2%">
				<s:form action="AceptarRequestEquipoInvitado" method="post">
			     	<s:hidden name="id" value="%{equipoSolicitanteId}"/>
			     	<s:hidden name="entero" value="%{equipoSolicitadoId}"/>
			     	<s:hidden name="idtorneo" value="%{id}"/>
			     	<s:submit type="image" title="%{getText('aceptar')}" src="%{rutaImgAceptarEquipo}" cssClass="centrado"/>
			    </s:form>
			</display:column>
			<display:column titleKey="rechazar" style="width:2%">
				<s:form action="RechazarRequestEquipoInvitado" method="post">
			     	<s:hidden name="id" value="%{equipoSolicitanteId}"/>
			     	<s:hidden name="entero" value="%{equipoSolicitadoId}"/>
			     	<s:hidden name="idtorneo" value="%{id}"/>
			     	<s:submit type="image" title="%{getText('rechazar')}" src="%{rutaImgDenegarEquipo}" cssClass="centrado"/>
			     </s:form>
			</display:column>
		</display:table>
		</fieldset>
		</div>
	</s:if>
	
<%-- 	<s:if test="userList.isEmpty() && userInvitadoList.isEmpty()"> --%>
<!-- 		No hay, y si no hay que le vamos a hacer ! -->
<%-- 	</s:if>				 --%>
		 
    <sj:dialog 
    	id="myremotelinkdialog" 
    	autoOpen="false"  
    	title="Perfil de Usuario"
    />		  
		     	