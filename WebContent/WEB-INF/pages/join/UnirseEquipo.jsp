<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
		
	<s:if test="regRequest">
		<s:if test="!requestPendiente">
		<p>Sin Requests Pendientes!! </p>
			<s:if test="passwordProtected">
				<s:actionerror />
				<s:form action="JoinRequestEquipoPassword" cssClass="form">
					<fieldset>	
						<legend><s:text name="equipo.unirse"/></legend>
						<fieldset>
							<legend><s:text name="password" /></legend>					
							<s:hidden name="id" value="%{id}" />
							<s:password name="joinPassword" required="true" key="password" size="20" maxlength="20" />
						</fieldset>
					
						<s:submit align="center" key="request.join" cssClass="centrado" cssStyle="font-size:15px;"/>
					</fieldset>
				</s:form>
			</s:if>
			<s:else>
				<s:actionerror />
				<s:form action="JoinRequestEquipo" cssClass="form">
					<fieldset>	
						<legend><s:text name="equipo.unirse"/></legend>				
									
						<s:hidden name="id" value="%{id}" />
						<s:submit align="center" key="request.join" cssClass="centrado" cssStyle="font-size:15px;"/>
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
				<s:form action="JoinEquipoPassword" cssClass="form">
					<fieldset>	
						<legend><s:text name="equipo.unirse"/></legend>
						<fieldset>
							<legend><s:text name="password" /></legend>				
							
							<s:hidden name="id" value="%{id}" />
							<s:password name="joinPassword" required="true" key="password" size="20" maxlength="20" />
						</fieldset>
					
						<s:submit align="center" key="unirse" cssClass="centrado" cssStyle="font-size:15px;"/>
					</fieldset>
				</s:form>
			</s:if>
			<s:else>
				<s:actionerror />
				<s:form action="JoinEquipo" cssClass="form">
					<fieldset>	
						<legend><s:text name="equipo.unirse"/></legend>				
				
						<s:hidden name="id" value="%{id}" />
						<s:submit align="center" key="unirse" cssClass="centrado" cssStyle="font-size:15px;"/>
					</fieldset>
				</s:form>
			</s:else>
	</s:else>