<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
	
	<s:if test="regRequest">
		<s:if test="!requestPendiente">
<!-- 		<p>Sin Requests Pendientes!! </p> -->
			<s:if test="passwordProtected">
				<s:actionerror theme="jquery" />
				<s:form action="JoinRequestComunidadPassword" cssClass="form">
					<fieldset>	
						<legend><s:text name="comunidad.unirse"/></legend>
						<fieldset>
							<legend><s:text name="password" /></legend>				
								
							<s:hidden name="id" value="%{id}" />
							<s:password name="joinPassword" required="true" key="password" size="20" maxlength="20" />
						</fieldset>				
							    
						<sj:submit key="request.join" button="true" cssClass="centrado" cssStyle="font-size:15px;"/>
					</fieldset>
				</s:form>
			</s:if>
			<s:else>
				<s:actionerror theme="jquery"/>
				<s:form action="JoinRequestComunidad" cssClass="form">
					<fieldset>	
						<legend><s:text name="comunidad.unirse"/></legend>				
					
						<s:hidden name="id" value="%{id}" />
						<sj:submit key="request.join" button="true" cssClass="centrado" cssStyle="font-size:15px;"/>
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
				<s:actionerror theme="jquery"/>
				<s:form action="JoinComunidadPassword" cssClass="form">
					<fieldset>	
						<legend><s:text name="comunidad.unirse"/></legend>
						<fieldset>
							<legend><s:text name="password" /></legend>
							
							<s:hidden name="id" value="%{id}" />
							<s:password name="joinPassword" required="true" key="password" size="20" maxlength="20" />
						</fieldset>
						
						<sj:submit key="unirse" button="true" cssClass="centrado" cssStyle="font-size:15px;"/>
					</fieldset>
				</s:form>
			</s:if>
			<s:else>
				<s:actionerror theme="jquery"/>
				<s:form action="JoinComunidad" cssClass="form">
					<fieldset>	
						<legend><s:text name="comunidad.unirse"/></legend>
						<s:hidden name="id" value="%{id}" />
						<sj:submit key="unirse" button="true" cssStyle="font-size:15px;"/>
					</fieldset>
				</s:form>
			</s:else>
	</s:else>