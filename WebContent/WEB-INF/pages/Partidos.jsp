<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">
function ValidarResultado(form){
	if(form.puntosLocal.value == form.puntosVisitante.value){
		return false;
	}
	else{
		return true;	
	}
	
}
	
	
</script>
<s:if test="roltorneo == 'creador' || roltorneo == 'admin'">
	<s:if test="jornadaActual == 0">
		<s:form action="EmpezarTorneo" cssClass="form">
			<fieldset>
			<legend><s:text name="comenzar.torneo"/></legend>
				<s:hidden name="id" value="%{id}"/>
				<sj:submit  button="true" key="comenzar" 
		    		cssClass="centrado" cssStyle="font-size:15px;"/>			
			</fieldset>
		</s:form>
	</s:if>
	<s:elseif test="liga == true">
		<s:form action="PasarJornadas" cssClass="form">
			<fieldset>
			<legend><s:text name="pasar.siguientes.jornadas"/></legend>
				<s:hidden name="id" value="%{id}"/>
				<s:hidden name="auto" value="no"/>
				<sj:submit  button="true" key="pasar.jornadas" 
		    		cssClass="centrado" cssStyle="font-size:15px;"/>			
			</fieldset>
		</s:form>
	</s:elseif>
</s:if>
	
<s:if test="partidoIndList!=null">
	<div id="result" class="result ui-widget-content ui-corner-all">

	<display:table name="partidoIndList" id="row" class="table" pagesize="10" 
			requestURI="">
		<display:column titleKey="palabra.jornada" property="jornada" style="width:2%"/>
				    
		<display:column titleKey="local" >
			<c:if test="${row.parentLocal.invitado == false}">
			     <s:url var="perfilAction" action="GoPerfil">
			     	<s:param name="id" >${row.parentLocal.id}</s:param></s:url>
			     			
				<sj:a  
					href="%{perfilAction}"
					openDialog="myremotelinkdialog"
					button="false" >
						${row.parentLocal.alias}
					</sj:a>
				</c:if>
				<c:if test="${row.parentLocal.invitado == true}">
					<div style="color:grey"><c:out value="${row.parentLocal.alias}"/></div>
				</c:if>		    					    		
		</display:column>
		    		 
		<display:column titleKey="visitante" >
			<c:if test="${row.parentVisitante.invitado == false}">
			     <s:url var="perfilAction" action="GoPerfil">
			     	<s:param name="id" >${row.parentVisitante.id}</s:param></s:url>
			     			
				<sj:a  
					href="%{perfilAction}"
					openDialog="myremotelinkdialog"
					button="false" >
						${row.parentVisitante.alias}
				</sj:a>
			</c:if>
			<c:if test="${row.parentVisitante.invitado == true}">
				<div style="color:grey"><c:out value="${row.parentVisitante.alias}"/></div>
			</c:if>	
		</display:column>

		<display:column titleKey="resultado" >
			<s:set var="iduser" value="#session.id" />
		    			
			<c:if test="${row.puntosLocal != null && row.puntosVisitante != null}">
				<s:set var="puntosL">${row.puntosLocal}</s:set>
				<s:set var="puntosV">${row.puntosVisitante}</s:set>
			    					
				<s:textfield size="3" value="%{puntosL}" disabled="true" cssStyle="float:left"/>
				<s:textfield size="3" value="%{puntosV}" disabled="true" cssStyle="float:left"/>
			    				
				<c:if test="${row.confirmado == false && row.rechazado == false}">
			    				
					<c:set var="introductor" >${row.parentIntroductorResultado.id}</c:set>		    					
				    					
					<c:if test="${iduser != introductor}">
						<s:set var="partidoId">${row.id}</s:set>
						<s:set var="formularioConfirmar">Confirmar${row.id}</s:set>
						<s:set var="formularioRechazar">Rechazar${row.id}</s:set>				    												
					    															
					   	<table style="margin:0;padding:0"><tr><td>
					   	<s:form id="formularioConfirmar" name="formularioConfirmar" action="ConfirmarResultadoIndividual">
							<s:hidden name="id" value="%{partidoId}"/>
							<sj:submit button="true" buttonIcon="ui-icon-gear" indicator="indicator" key="confirmar" />				    					
						</s:form>
					   	</td>
					   	<td>
					   	<s:form id="formularioRechazar" name="formularioRechazar" action="RechazarResultadoIndividual">
							<s:hidden name="id" value="%{partidoId}"/>
							<sj:submit button="true" buttonIcon="ui-icon-gear" indicator="indicator" key="rechazar" />				    					
						</s:form>
					   	</td></tr></table>
					    					
					</c:if>
					
					<c:if test="${iduser == introductor}">
						<img src="<s:url value="/images/template/FAQ-icon.png"/>" 
							title="<s:text name="esperando.confirmacion"/>"  width="24" height="24"/>
					</c:if> 
				</c:if> 
				
				<c:if test="${row.confirmado == true}"> 
					<img src="<s:url value="/images/template/Ok-icon.png"/>" 
							title="<s:text name="confirmado"/>"  width="24" height="24"/>
				</c:if>
				
				<c:if test="${row.rechazado == true}"> 
					<img src="<s:url value="/images/template/Close-2-icon.png"/>" 
							title="<s:text name="rechazado"/>"  width="24" height="24"/>
				</c:if>
				
 		    </c:if> 
		    			
 		    <c:if test="${(row.puntosLocal == null) || (row.puntosVisitante == null)}"> 
 		    	<s:set var="partidoId">${row.id}</s:set> 
		    			
		    	<c:if test="${(iduser == row.parentLocal.id && !row.parentVisitante.invitado) ||
		    			(!row.parentLocal.invitado && iduser == row.parentVisitante.id)}">
		    					
		    		<s:set var="idEnviar">Enviar${row.id}</s:set>
		    							
			    	<s:if test="liga == false || parentActividad.empate == false">						    								
		 		    	<s:form id="%{idEnviar}" name="%{idEnviar}" action="IntroducirResultadoIndividual"
		 		    			onSubmit="return ValidarResultado(this);"> 
		 		    		<s:hidden name="id" value="%{partidoId}"/> 
		 		    		<s:hidden name="idtorneo" value="%{torneo.id}"/>
		 		    		<s:textfield name="puntosLocal" size="3" cssStyle="float:left"/>
		 		    		<s:textfield name="puntosVisitante" size="3" cssStyle="float:left"/>						
		 		    		<sj:submit key="enviar" button="true" buttonIcon="ui-icon-gear" cssStyle="float:left"/>	 		    				 
		 		    	</s:form>
	 		    	</s:if>
	 		    	<s:else>
	 		    		<s:form id="%{idEnviar}" name="%{idEnviar}" action="IntroducirResultadoIndividual"> 
		 		    		<s:hidden name="id" value="%{partidoId}"/> 
		 		    		<s:hidden name="idtorneo" value="%{torneo.id}"/>
		 		    		<s:textfield name="puntosLocal" size="3" cssStyle="float:left"/>
		 		    		<s:textfield name="puntosVisitante" size="3" cssStyle="float:left"/>						
		 		    		<sj:submit key="enviar" button="true" buttonIcon="ui-icon-gear" cssStyle="float:left"/>	 		    				 
		 		    	</s:form>
	 		    	</s:else>

 		    	</c:if>
 		    	
 		    	<c:if test="${row.parentLocal.invitado || row.parentVisitante.invitado}">
 		    				
 		    		<s:set var="idEnviarConfirmado">EnviarConfirmado${row.id}</s:set>
<%--  		    					<s:property value="%{idEnviarConfirmado}"/> --%>
					
					<s:if test="liga == false || parentActividad.empate == false">					
		 		    	<s:form id="%{idEnviarConfirmado}" name="%{idEnviarConfirmado}" action="IntroducirResultadoIndividualConfirmado"
		 		    			onSubmit="return ValidarResultado(this);"> 	 		    					
		 		    		<s:hidden name="id" value="%{partidoId}"/> 
		 		    		<s:hidden name="idtorneo" value="%{torneo.id}"/>
		 		    		<s:textfield name="puntosLocal" size="3" cssStyle="float:left"/>
		 		    		<s:textfield name="puntosVisitante" size="3" cssStyle="float:left"/>
		 		    		<sj:submit key="enviar" button="true" buttonIcon="ui-icon-gear" cssStyle="float:left"/> 
		 		    	</s:form>
		 		    </s:if>
		 		    <s:else>
		 		    	<s:form id="%{idEnviarConfirmado}" name="%{idEnviarConfirmado}" action="IntroducirResultadoIndividualConfirmado"> 	 		    					
		 		    		<s:hidden name="id" value="%{partidoId}"/> 
		 		    		<s:hidden name="idtorneo" value="%{torneo.id}"/>
		 		    		<s:textfield name="puntosLocal" size="3" cssStyle="float:left"/>
		 		    		<s:textfield name="puntosVisitante" size="3" cssStyle="float:left"/>
		 		    		<sj:submit key="enviar" button="true" buttonIcon="ui-icon-gear" cssStyle="float:left"/> 
		 		    	</s:form>		 		    
		 		    </s:else> 
	 		    </c:if>
		    				 				    				    				 
 		    </c:if> 

		</display:column>				 
	</display:table>
				
	</div>				
</s:if>
			
<s:if test="partidoEqList!=null">
	<div id="result" class="result ui-widget-content ui-corner-all">

	<display:table name="partidoEqList" id="row2" class="table" pagesize="10" 
			requestURI="">
				    
		<display:column titleKey="local" >
			<c:if test="${row2.parentLocal.invitado == false}">
			     <a href="<s:url action="VerEquipo"><s:param name="id" >${row2.parentLocal.id}</s:param>
								</s:url>">${row2.parentLocal.nombre}</a>
			</c:if>
			<c:if test="${row2.parentLocal.invitado == true}">
				<div style="color:grey"><c:out value="${row2.parentLocal.nombre}"/></div>
			</c:if>		    					    		
		</display:column>
		    		 
		<display:column titleKey="visitante" >
			<c:if test="${row2.parentVisitante.invitado == false}">
			     <a href="<s:url action="VerEquipo"><s:param name="id" >${row2.parentVisitante.id}</s:param>
								</s:url>">${row2.parentVisitante.nombre}</a>
			</c:if>
			<c:if test="${row2.parentVisitante.invitado == true}">
				<div style="color:grey"><c:out value="${row2.parentVisitante.nombre}"/></div>
			</c:if>
		</display:column>

		<display:column titleKey="resultado" >
			<s:set var="idequipo" value="#session.equipo" />
		    			
			<c:if test="${row2.puntosLocal != null && row2.puntosVisitante != null}">
				<s:set var="puntosL">${row2.puntosLocal}</s:set>
				<s:set var="puntosV">${row2.puntosVisitante}</s:set>
			    					
				<s:textfield size="3" value="%{puntosL}" disabled="true" cssStyle="float:left"/>
				<s:textfield size="3" value="%{puntosV}" disabled="true" cssStyle="float:left"/>
			    				
				<c:if test="${row2.confirmado == false && row2.rechazado == false}">
			    				
					<c:set var="introductor" >${row2.parentIntroductorResultado.id}</c:set>		    					
				    
<!-- La única vez que se cumple esta condición teniendo perfil de usuario es siendo al menos MOD por lo que es correcto 0 = 0 -->

					<c:if test="${idequipo != introductor}"> 
						<s:set var="partidoId">${row2.id}</s:set>
						<s:set var="formularioConfirmar">Confirmar${row2.id}</s:set>				    												
					    															
						<table style="margin:0;padding:0"><tr><td>
					   	<s:form id="formularioConfirmar" name="formularioConfirmar" action="ConfirmarResultadoEquipo">
							<s:hidden name="id" value="%{partidoId}"/>
							<sj:submit button="true" buttonIcon="ui-icon-gear" indicator="indicator" key="confirmar" />				    					
						</s:form>
					   	</td>
					   	<td>
					   	<s:form id="formularioRechazar" name="formularioRechazar" action="RechazarResultadoEquipo">
							<s:hidden name="id" value="%{partidoId}"/>
							<sj:submit button="true" buttonIcon="ui-icon-gear" indicator="indicator" key="rechazar" />				    					
						</s:form>
					   	</td></tr></table>
					    					
					</c:if> 
				<c:if test="${idequipo == introductor}">
						<img src="<s:url value="/images/template/FAQ-icon.png"/>" 
							title="<s:text name="esperando.confirmacion"/>"  width="24" height="24"/>
					</c:if> 
				</c:if> 
				
				<c:if test="${row2.confirmado == true}"> 
					<img src="<s:url value="/images/template/Ok-icon.png"/>" 
							title="<s:text name="confirmado"/>"  width="24" height="24"/>
				</c:if>
				
				<c:if test="${row2.rechazado == true}"> 
					<img src="<s:url value="/images/template/Close-2-icon.png"/>" 
							title="<s:text name="rechazado"/>"  width="24" height="24"/>
				</c:if>
				
 		    </c:if> 
		    			
 		    <c:if test="${(row2.puntosLocal == null) || (row2.puntosVisitante == null)}"> 
 		    	<s:set var="partidoId">${row2.id}</s:set> 
		    			
		    	<c:if test="${(idequipo == row2.parentLocal.id && !row2.parentVisitante.invitado) ||
		    			(!row2.parentLocal.invitado && idequipo == row2.parentVisitante.id)}">
		    					
		    		<s:set var="idEnviar">Enviar${row2.id}</s:set>
		    							    							    								
	 		    	<s:form id="%{idEnviar}" name="%{idEnviar}" action="IntroducirResultadoEquipo"> 
	 		    		<s:hidden name="id" value="%{partidoId}"/> 
	 		    		<s:hidden name="idtorneo" value="%{torneo.id}"/>
	 		    		<s:textfield name="puntosLocal" size="3" cssStyle="float:left"/>
	 		    		<s:textfield name="puntosVisitante" size="3" cssStyle="float:left"/>
	 		    		<sj:submit key="enviar"	button="true" buttonIcon="ui-icon-gear" cssStyle="float:left" /> 
	 		    	</s:form>
 		    	</c:if>
 		    	
 		    	<c:if test="${row2.parentLocal.invitado || row2.parentVisitante.invitado}">
 		    				
 		    		<s:set var="idEnviarConfirmado">EnviarConfirmado${row2.id}</s:set>
<%--  		    					<s:property value="%{idEnviarConfirmado}"/> --%>
	 		    	<s:form id="%{idEnviarConfirmado}" name="%{idEnviarConfirmado}" action="IntroducirResultadoEquipoConfirmado"> 	 		    					
	 		    		<s:hidden name="id" value="%{partidoId}"/> 
	 		    		<s:hidden name="idtorneo" value="%{torneo.id}"/>
	 		    		<s:textfield name="puntosLocal" size="3" cssStyle="float:left"/>
	 		    		<s:textfield name="puntosVisitante" size="3" cssStyle="float:left"/>
	 		    		<sj:submit key="enviar"	button="true" buttonIcon="ui-icon-gear" cssStyle="float:left" /> 
	 		    	</s:form> 
	 		    </c:if>
		    				 				    				    				 
 		    </c:if> 

		</display:column>				 
	</display:table>	
	</div>			
</s:if>

	<s:if test="roltorneo == 'creador' || roltorneo == 'admin'">
		
		<s:if test="partidosRechazadosIndividualList!=null && !partidosRechazadosIndividualList.isEmpty()">
<!-- 			<div id="resultRechazados" class="result ui-widget-content ui-corner-all"> -->
			<fieldset class="form">
			<legend class="form">Partidos Rechazados</legend>
				<display:table name="partidosRechazadosIndividualList" id="rowRechazadoInd" class="table" pagesize="10" 
						requestURI="">
					<display:column titleKey="palabra.jornada" property="jornada" style="width:2%"/>
							    
					<display:column titleKey="local" >
					    <c:out value="${rowRechazadoInd.parentLocal.alias}" />		    					    		
					</display:column>
					    		 
					<display:column titleKey="visitante" >
						<c:out value="${rowRechazadoInd.parentVisitante.alias}" />
					</display:column>
			
					<display:column titleKey="resultado" >
						<s:set var="partidoId">${rowRechazadoInd.id}</s:set>
						<s:set var="puntosL">${rowRechazadoInd.puntosLocal}</s:set>
						<s:set var="puntosV">${rowRechazadoInd.puntosVisitante}</s:set>
	 		    		<s:set var="idEnvConfirmado">EnvConfirmado${rowRechazadoInd.id}</s:set>
	<%--  		    					<s:property value="%{idEnviarConfirmado}"/> --%>
		 		    	<s:form id="%{idEnvConfirmado}" name="%{idEnvConfirmado}" action="IntroducirResultadoIndividualConfirmado"> 	 		    					
		 		    		<s:hidden name="id" value="%{partidoId}"/> 
		 		    		<s:hidden name="idtorneo" value="%{torneo.id}"/>
		 		    		<s:textfield name="puntosLocal" size="3" cssStyle="float:left" value="%{puntosL}"/>
		 		    		<s:textfield name="puntosVisitante" size="3" cssStyle="float:left" value="%{puntosV}"/>
		 		    		<sj:submit key="enviar"	button="true" buttonIcon="ui-icon-gear" cssStyle="float:left" /> 
		 		    	</s:form> 					
					</display:column>
				</display:table>
			</fieldset>			
<!-- 			</div> -->
		</s:if>
		
		<s:if test="partidosRechazadosEquiposList!=null && !partidosRechazadosEquiposList.isEmpty()">
<!-- 			<div id="resultRechazados" class="result ui-widget-content ui-corner-all"> -->
			<fieldset class="form">
			<legend class="form">Partidos Rechazados</legend>
				<display:table name="partidosRechazadosEquipoList" id="rowRechazadoEq" class="table" pagesize="10" 
						requestURI="">
					<display:column titleKey="jornada" property="jornada" style="width:2%"/>
							    
					<display:column titleKey="local" >
					    <c:out value="${rowRechazadoEq.parentLocal.nombre}" />		    					    		
					</display:column>
					    		 
					<display:column titleKey="visitante" >
						<c:out value="${rowRechazadoEq.parentVisitante.nombre}" />
					</display:column>
			
					<display:column titleKey="resultado" >
						<s:set var="partidoId">${rowRechazadoEq.id}</s:set>
						<s:set var="puntosL">${rowRechazadoEq.puntosLocal}</s:set>
						<s:set var="puntosV">${rowRechazadoEq.puntosVisitante}</s:set>
	 		    		<s:set var="idEnvConfirmado">EnvConfirmado${rowRechazadoEq.id}</s:set>
	<%--  		    					<s:property value="%{idEnviarConfirmado}"/> --%>
		 		    	<s:form id="%{idEnvConfirmado}" name="%{idEnvConfirmado}" action="IntroducirResultadoEquipoConfirmado"> 	 		    					
		 		    		<s:hidden name="id" value="%{partidoId}"/> 
		 		    		<s:hidden name="idtorneo" value="%{torneo.id}"/>
		 		    		<s:textfield name="puntosLocal" size="3" cssStyle="float:left" value="%{puntosL}"/>
		 		    		<s:textfield name="puntosVisitante" size="3" cssStyle="float:left" value="%{puntosV}"/>
		 		    		<sj:submit key="enviar"	button="true" buttonIcon="ui-icon-gear" cssStyle="float:left" /> 
		 		    	</s:form> 					
					</display:column>
				</display:table>
			</fieldset>			
<!-- 			</div> -->
		</s:if>
	</s:if>	