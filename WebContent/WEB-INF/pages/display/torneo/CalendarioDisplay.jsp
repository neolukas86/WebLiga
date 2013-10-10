<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="nada">
		<s:if test="partidoIndList!= null">
				<display:table name="partidoIndList" id="rowInd" class="table nada" pagesize="32" 
							requestURI="">
							
				<display:setProperty name="paging.banner.some_items_found" value=""/>
				<display:setProperty name="paging.banner.one_item_found" value=""/>
				<display:setProperty name="paging.banner.all_items_found" value=""/>
				<display:setProperty name="paging.banner.first" value=""/>
				<display:setProperty name="paging.banner.page.selected" value=""/>						
<%-- 				    <display:column titleKey="jornada" property="jornada" /> --%>
				    <display:column titleKey="local" style="width:42%">
				    	<c:if test="${rowInd.parentLocal.invitado == false}">
			     				<s:url var="perfilAction" action="GoPerfil">
			     					<s:param name="id" >${rowInd.parentLocal.id}</s:param></s:url>
			     			
						     	<sj:a  
									href="%{perfilAction}"
									openDialog="myremotelinkdialog"
									button="false" >
										${rowInd.parentLocal.alias}
								</sj:a>
						</c:if>
						<c:if test="${rowInd.parentLocal.invitado == true}">
							<div style="color:grey; float:left">
								<c:out value="${rowInd.parentLocal.alias}"/>
							</div>
						</c:if>
						
						<div style="float:right">
						<c:if test="${rowInd.confirmado == true}">
							<c:if test="${rowInd.parentGanador == rowInd.parentLocal}">
								<img src="<s:url value="/images/template/Stock-Index-Up-icon.png"/>" 
											title="<s:text name='ganador'/>" width="16" height="16"/>
							</c:if>
							<c:if test="${rowInd.parentGanador == rowInd.parentVisitante}">
								<img src="<s:url value="/images/template/Stock-Index-Down-icon.png"/>" 
											title="<s:text name='perdedor'/>" width="16" height="16"/>
							</c:if>
							<c:if test="${rowInd.parentGanador != rowInd.parentLocal && 
									rowInd.parentGanador != rowInd.parentVisitante}">
								<img src="<s:url value="/images/template/equals.png"/>" 
											title="<s:text name='empate'/>" width="16" height="16"/>
							</c:if>
						</c:if>
						</div>
												
		    		</display:column>
		    		<display:column titleKey="resultado" style="width:72px" class="guion_imagen">
		    			<c:if test="${rowInd.confirmado == true}">    					    				
			    			<div style="float:left;padding-left:5px; width:20px" >
								<b><c:out value="${rowInd.puntosLocal}"/></b>
							</div>
						
							<div style="float:right;padding-left:5px; width:20px">
								<b><c:out value="${rowInd.puntosVisitante}" /></b>
							</div>
						</c:if>	

		    		</display:column>
				    <display:column titleKey="visitante">
				    	<div style="float:left; margin-right:10px">
							<c:if test="${rowInd.confirmado == true}">
								<c:if test="${rowInd.parentGanador == rowInd.parentVisitante}">
									<img src="<s:url value="/images/template/Stock-Index-Up-icon.png"/>" 
												title="<s:text name='ganador'/>" width="16" height="16"/>
								</c:if>
								<c:if test="${rowInd.parentGanador == rowInd.parentLocal}">
									<img src="<s:url value="/images/template/Stock-Index-Down-icon.png"/>" 
												title="<s:text name='perdedor'/>" width="16" height="16"/>
								</c:if>
								<c:if test="${rowInd.parentGanador != rowInd.parentLocal &&
										rowInd.parentGanador != rowInd.parentVisitante}">
									<img src="<s:url value="/images/template/equals.png"/>" 
												title="<s:text name='empate'/>" width="16" height="16"/>
								</c:if>
							</c:if>
						</div>
										    
				    	<c:if test="${rowInd.parentVisitante.invitado == false}">
			     			<s:url var="perfilAction" action="GoPerfil">
			     				<s:param name="id" >${rowInd.parentVisitante.id}</s:param></s:url>
			     			
						     <sj:a  
								href="%{perfilAction}"
								openDialog="myremotelinkdialog"
								button="false" >
									${rowInd.parentVisitante.alias}
							</sj:a>
						</c:if>
						<c:if test="${rowInd.parentVisitante.invitado == true}">
							<div style="color:grey">
								<c:out value="${rowInd.parentVisitante.alias}"/>
							</div>
						</c:if>				    
		    		</display:column>				
				</display:table>
				
				<s:if test="estado == 3">
				<div style="margin-left:40%; margin-right:30%">
					<display:table name="campeon" class="table" style="width:40%; margin-left:auto;margin-right:auto">
						<display:column style="width:2%">
							<img src="<s:url value="/images/template/trophy-icon16.png"/>" 
									title="<s:text name='campeon' />" width="16" height="16"/>
						</display:column>
						<display:column titleKey="campeon">
							<s:if test="campeon.invitado == true">
								<div style="color:grey">
									<s:property value="campeon.alias"/>
								</div>
							</s:if>
							<s:else>
				     				<s:url var="perfilAction" action="GoPerfil">
				     					<s:param name="id" ><s:property value="campeon.id"/></s:param></s:url>
				     			
							     	<sj:a  
 										href="%{perfilAction}" 
 										openDialog="myremotelinkdialog" 
 										button="false" > 
 											<s:property value="campeon.alias"/> 
 									</sj:a> 
 							</s:else> 
 						</display:column> 
 					</display:table> 
				</div>
 				</s:if> 
			</s:if>
			
		<s:if test="partidoEqList!= null">
				<display:table name="partidoEqList" id="rowEq" class="table" pagesize="32" 
							requestURI="">
<%-- 				    <display:column titleKey="jornada" property="jornada" /> --%>
				    <display:column titleKey="local" style="width:42%">
				    	<c:if test="${rowEq.parentLocal.invitado == false}">
							<a href="<s:url action="VerEquipo"><s:param name="id" >${rowEq.parentLocal.id}</s:param>
								</s:url>">${rowEq.parentLocal.nombre}</a>
						</c:if>
						<c:if test="${rowEq.parentLocal.invitado == true}">
							<div style="color:grey">
								<c:out value="${rowEq.parentLocal.nombre}"/>
							</div>
						</c:if>
						
						<div style="float:right">
						<c:if test="${rowEq.confirmado == true}">
							<c:if test="${rowEq.parentGanador == rowEq.parentLocal}">
								<img src="<s:url value="/images/template/Stock-Index-Up-icon.png"/>" 
											title="<s:text name='ganador'/>" width="16" height="16"/>
							</c:if>
							<c:if test="${rowEq.parentGanador == rowEq.parentVisitante}">
								<img src="<s:url value="/images/template/Stock-Index-Down-icon.png"/>" 
											title="<s:text name='perdedor'/>" width="16" height="16"/>
							</c:if>
							<c:if test="${rowEq.parentGanador != rowEq.parentLocal && 
									rowEq.parentGanador != rowEq.parentVisitante}">
								<img src="<s:url value="/images/template/equals.png"/>" 
											title="<s:text name='empate'/>" width="16" height="16"/>
							</c:if>
						</c:if>
						</div>
												
		    		</display:column>
		    		<display:column titleKey="resultado" style="width:72px" class="guion_imagen">
		    			<c:if test="${rowEq.confirmado == true}">		    					    						    					    				
			    			<div style="float:left;padding-left:5px; width:20px" >
								<b><c:out value="${rowEq.puntosLocal}"/></b>
							</div>
						
							<div style="float:right;padding-left:5px; width:20px">
								<b><c:out value="${rowEq.puntosVisitante}" /></b>
							</div>
						</c:if>	

		    		</display:column>
				    <display:column titleKey="visitante">
				    	<div style="float:left; margin-right:10px">
							<c:if test="${rowEq.confirmado == true}">
								<c:if test="${rowEq.parentGanador == rowEq.parentVisitante}">
									<img src="<s:url value="/images/template/Stock-Index-Up-icon.png"/>" 
												title="<s:text name='ganador'/>" width="16" height="16"/>
								</c:if>
								<c:if test="${rowEq.parentGanador == rowEq.parentLocal}">
									<img src="<s:url value="/images/template/Stock-Index-Down-icon.png"/>" 
												title="<s:text name='perdedor'/>" width="16" height="16"/>
								</c:if>
								<c:if test="${rowEq.parentGanador != rowEq.parentLocal &&
										rowEq.parentGanador != rowEq.parentVisitante}">
									<img src="<s:url value="/images/template/equals.png"/>" 
												title="<s:text name='empate'/>" width="16" height="16"/>
								</c:if>
							</c:if>
						</div>				    
				    	<c:if test="${rowEq.parentVisitante.invitado == false}">
							<a href="<s:url action="VerEquipo"><s:param name="id" >${rowEq.parentVisitante.id}</s:param>
								</s:url>">${rowEq.parentVisitante.nombre}</a>
						</c:if>
						<c:if test="${rowEq.parentVisitante.invitado == true}">
							<div style="color:grey">
								<c:out value="${rowEq.parentVisitante.nombre}"/>
							</div>
						</c:if>				    
		    		</display:column>
		    	</display:table>
		    		
		    		
		    		
				<s:if test="estado == 3">
				<div style="margin-left:40%; margin-right:30%">
					<display:table name="equipo" class="table" style="width:40%; margin-left:auto;margin-right:auto">
						<display:column style="width:2%">
							<img src="<s:url value="/images/template/trophy-icon16.png"/>" 
									title="<s:text name='campeon' />" width="16" height="16"/>
						</display:column>
						<display:column titleKey="campeon">
							<s:if test="equipo.invitado == true">
								<div style="color:grey">
									<s:property value="equipo.nombre"/>
								</div>
							</s:if>
							<s:else>
				     			<a href="<s:url action="VerEquipo"><s:param name="id" value="equipo.id"/></s:url>" >
				     				<s:property value="equipo.nombre"/></a>
 							</s:else> 
 						</display:column> 
 					</display:table> 
				</div>
 				</s:if> 						    		
		    						
				
			</s:if>
</div>			