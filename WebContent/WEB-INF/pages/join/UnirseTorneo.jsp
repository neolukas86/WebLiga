<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

	<s:if test="joinedComunidad">
<!-- 		El único caso donde no habrá join será cuando estes con el perfil de equipo y el torneo sea individual -->
		<s:if test="estado == 1 && !(!porEquipos && #session.equipo != 0)">	
			<s:if test="regRequest">
				<s:if test="!requestPendiente">
					<p>Sin Requests Pendientes!! </p>
					<s:if test="passwordProtected">
						<s:actionerror />
						<s:form action="JoinRequestTorneoPassword" cssClass="form">
							<fieldset>
							<legend><s:text name="torneo.unirse"/></legend>
							<fieldset>
							<legend><s:text name="password"/></legend>
							<s:hidden name="id" value="%{id}" />
							<s:password name="joinPassword" required="true" key="password" size="20" maxlength="20" />
							</fieldset>
							<sj:submit button="true" align="center" key="request.join" cssClass="centrado" cssStyle="font-size:15px;"/>
							</fieldset>
						</s:form>
					</s:if>
					<s:else>
						<s:actionerror />
						<s:form action="JoinRequestTorneo" cssClass="form">
							<fieldset>
							<legend><s:text name="torneo.unirse"/></legend>
							<s:hidden name="id" value="%{id}" />
							<sj:submit button="true" align="center" key="request.join" cssClass="centrado" cssStyle="font-size:15px;"/>
							</fieldset>
						</s:form>
					</s:else>
				</s:if>
				<s:else>
					<p>Tiene un Request Pendiente!! </p>
				</s:else>
			</s:if>
			<s:else>
				<s:if test="passwordProtected">
					<s:actionerror />
					<s:form action="JoinTorneoPassword" cssClass="form">
						<fieldset>
						<legend><s:text name="torneo.unirse"/></legend>
						<fieldset>
						<legend><s:text name="password"/></legend>
						<s:hidden name="id" value="%{id}" />
						<s:password name="joinPassword" required="true" key="password" size="20" maxlength="20" />
						</fieldset>
						<sj:submit button="true" align="center" key="unirse" cssClass="centrado" cssStyle="font-size:15px;"/>
						</fieldset>
					</s:form>
				</s:if>
				<s:else>
					<s:actionerror />
					<s:form action="JoinTorneo" cssClass="form">
						<fieldset>
						<legend><s:text name="torneo.unirse"/></legend>
						<s:hidden name="id" value="%{id}" />
						<sj:submit button="true" align="center" key="unirse" cssClass="centrado" cssStyle="font-size:15px;"/>
						</fieldset>
					</s:form>
				</s:else>
			</s:else>
		</s:if>
		
		<s:if test="listaInvitados != null">
<!-- 			Si es por equipos -->
			<s:if test="porEquipos">
				<s:form action="RequestAsociarEquipo" cssClass="form">
					<fieldset>
					<legend><s:text name="asociar.equipo.invitado"/></legend>
					<fieldset>
					<legend><s:text name="invitados"/></legend>
					<s:hidden name="idtorneo" value="%{id}"/>
					<s:select name="id" list="listaInvitados" style="float:left"/>
					</fieldset>
					<sj:submit button="true" align="center" key="request.invitado" cssStyle="font-size:15px;float:left; margin-left:20px"/>
					
					</fieldset>	
				</s:form>
			</s:if>			
			<s:else>
				<s:form action="RequestAsociarUser" cssClass="form">
					<fieldset>
					<legend><s:text name="asociar.usuario.invitado"/></legend>
					<fieldset>
					<legend><s:text name="invitados"/></legend>					
					<s:hidden name="idtorneo" value="%{id}"/>
					<s:select name="id" list="listaInvitados" style="float:left"/>
					</fieldset>
					<sj:submit button="true" align="center" key="request.invitado" cssStyle="font-size:15px;float:left; margin-left:20px"/>
					
					</fieldset>	
				</s:form>
			</s:else>				
			

			
		</s:if>
		
		
	</s:if>

	<s:else>
		<p>No está registrado en la comunidad 
		<s:a action="VerComunidad"><s:param name="id" >${torneo.parentComunidad.id}</s:param>${torneo.parentComunidad.nombre}</s:a>
			, primero debe <s:a action="GoUnirComunidad"><s:param name="id" >${torneo.parentComunidad.id}</s:param>registrarse</s:a>.</p>
	</s:else>