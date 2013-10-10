<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">
    var table = document.getElementById("clasi");
    if(table == null){
    	var table = document.getElementById("clasi2");
    }
    var tbody = table.getElementsByTagName("tbody")[0];
    var rows = tbody.getElementsByTagName("tr");
    // add event handlers so rows light up and are clickable
    
    rows[0].style.backgroundColor = "#ebfc8c";
    rows[rows.length -1].style.backgroundColor = "#faaa94";
</script>

	
			<s:if test="userTorneoList != null">
				<c:if test="${torneo.parentActividad.ptosVictoria != null}">
					<display:table name="userTorneoList" id="clasi" class="table" requestURI="">												
					    <display:column titleKey="palabra.posicion.abreviada" style="width:5%">

							<div style="float:left">
								<c:out value="${clasi.posicion}" />
							</div>
							
						    <c:if test="${estado == 3 && clasi_rowNum == 1}">

								<img src="<s:url value="/images/template/trophy-icon16.png"/>" 
									title="campeon" width="16" height="16"/>

							</c:if>
							
<%-- 							<c:if test="${estado != 3 || clasi_rowNum != 1}"> --%>
<%-- 								<c:out value="${clasi.posicion}" /> --%>
<%-- 							</c:if> --%>
							
					    	
					    </display:column>
					    <display:column titleKey="user.alias" >

			    			<c:if test="${clasi.id.parentUser.invitado == false}">
						     	<s:url var="perfilAction" action="GoPerfil"><s:param name="id" >${clasi.id.parentUser.id}</s:param></s:url>
						     			
						     	<sj:a  
 									href="%{perfilAction}" 
 									openDialog="myremotelinkdialog" 
 									button="false" > 
 										${clasi.id.parentUser.alias} 
 								</sj:a> 
 			    			</c:if> 
 			    			<c:if test="${clasi.id.parentUser.invitado == true}"> 
 			    				<div style="color:grey"><c:out value="${clasi.id.parentUser.alias}" /></div> 
 			    			</c:if> 
			    		</display:column>
				    	<display:column titleKey="palabra.puntos" style="width:2%">
				    		<b><c:out value="${clasi.puntos}" /></b>
				    	</display:column>
			    		<display:column titleKey="PJ" >
			    			<c:out value="${clasi.partidosGanados + clasi.partidosEmpatados + clasi.partidosPerdidos}" />
			    		</display:column>
					    <display:column titleKey="PG">
			    			<c:out value="${clasi.partidosGanados}" />
			    		</display:column>
				    	<display:column titleKey="PE" >
				    		<c:out value="${clasi.partidosEmpatados}" />
				    	</display:column>
			    		<display:column titleKey="PP" >
			    			<c:out value="${clasi.partidosPerdidos}" />
			    		</display:column>
			    		<display:column titleKey="TF" >
			    			<c:out value="${clasi.tantosFavor}" />
			    		</display:column>
			    		<display:column titleKey="TC" >
			    			<c:out value="${clasi.tantosContra}" />
			    		</display:column>
			    		<display:column titleKey="TT" >
			    			<c:out value="${clasi.tantosFavor - clasi.tantosContra}" />
			    		</display:column>				
					</display:table>
				</c:if>
				<c:if test="${torneo.parentActividad.ptosVictoria == null}">
					<display:table name="userTorneoList" id="clasi2" class="table" requestURI="">						
					    <display:column titleKey="palabra.posicion.abreviada" style="width:2%">
					    	<c:out value="${clasi2.posicion}" />
					    </display:column>
					    <display:column titleKey="user.alias" >
			    			<c:if test="${clasi2.id.parentUser.invitado == false}">
						     	<s:url var="perfilAction" action="GoPerfil"><s:param name="id" >${clasi2.id.parentUser.id}</s:param></s:url>
						     			
						     	<sj:a  
									href="%{perfilAction}"
									openDialog="myremotelinkdialog"
									button="false" >
										${clasi2.id.parentUser.alias}
								</sj:a>
			    			</c:if>
			    			<c:if test="${clasi2.id.parentUser.invitado == true}">
			    				<div style="color:grey"><c:out value="${clasi2.id.parentUser.alias}" /></div>
			    			</c:if>
			    		</display:column>
			    		<display:column titleKey="PG" >
			    			<b><c:out value="${clasi2.partidosGanados}" /></b>
			    		</display:column>
			    		<display:column titleKey="PP" >
			    			<c:out value="${clasi2.partidosPerdidos}" />
			    		</display:column>
			    		<display:column titleKey="PJ" >
			    			<c:out value="${clasi2.partidosGanados + clasi2.partidosPerdidos}" />
			    		</display:column>
			    		<display:column titleKey="TF" >
			    			<c:out value="${clasi2.tantosFavor}" />
			    		</display:column>
			    		<display:column titleKey="TC" >
			    			<c:out value="${clasi2.tantosContra}" />
			    		</display:column>
			    		<display:column titleKey="TT" >
			    			<c:out value="${clasi2.tantosFavor - clasi2.tantosContra}" />
			    		</display:column>				
					</display:table>
				</c:if>								
			</s:if>
			
			
			<s:if test="equipoTorneoList != null">
				<c:if test="${torneo.parentActividad.ptosVictoria != null}">
					<display:table name="equipoTorneoList" id="clasiEq" class="table" requestURI="">												
					    <display:column titleKey="palabra.posicion.abreviada" style="width:5%">

							<div style="float:left">
								<c:out value="${clasiEq.posicion}" />
							</div>
							
						    <c:if test="${estado == 3 && clasiEq_rowNum == 1}">

								<img src="<s:url value="/images/template/trophy-icon16.png"/>" 
									title="campeon" width="16" height="16"/>

							</c:if>
							
<%-- 							<c:if test="${estado != 3 || clasi_rowNum != 1}"> --%>
<%-- 								<c:out value="${clasi.posicion}" /> --%>
<%-- 							</c:if> --%>
							
					    	
					    </display:column>
					    <display:column titleKey="nombre" >
						    <c:if test="${clasiEq.id.parentEquipo.invitado == false}">
						     	<a href="<s:url action="VerEquipo"><s:param name="id" >${clasiEq.id.parentEquipo.id}</s:param>
						     		</s:url>">${clasiEq.id.parentEquipo.nombre}</a>
							</c:if>
							<c:if test="${clasiEq.id.parentEquipo.invitado == true}">
								<div style="color:grey"><c:out value="${clasiEq.id.parentEquipo.nombre}"/></div>
							</c:if>
			    		</display:column>
				    	<display:column titleKey="palabra.puntos" style="width:2%">
				    		<b><c:out value="${clasiEq.puntos}" /></b>
				    	</display:column>
			    		<display:column titleKey="PJ" >
			    			<c:out value="${clasiEq.partidosGanados + clasiEq.partidosEmpatados + clasiEq.partidosPerdidos}" />
			    		</display:column>
					    <display:column titleKey="PG">
			    			<c:out value="${clasiEq.partidosGanados}" />
			    		</display:column>
				    	<display:column titleKey="PE" >
				    		<c:out value="${clasiEq.partidosEmpatados}" />
				    	</display:column>
			    		<display:column titleKey="PP" >
			    			<c:out value="${clasiEq.partidosPerdidos}" />
			    		</display:column>
			    		<display:column titleKey="TF" >
			    			<c:out value="${clasiEq.tantosFavor}" />
			    		</display:column>
			    		<display:column titleKey="TC" >
			    			<c:out value="${clasiEq.tantosContra}" />
			    		</display:column>
			    		<display:column titleKey="TT" >
			    			<c:out value="${clasiEq.tantosFavor - clasiEq.tantosContra}" />
			    		</display:column>				
					</display:table>
				</c:if>
				<c:if test="${torneo.parentActividad.ptosVictoria == null}">
					<display:table name="equipoTorneoList" id="clasiEq2" class="table" requestURI="">						
					    <display:column titleKey="palabra.posicion.abreviada" style="width:2%">
					    	<c:out value="${clasiEq2.posicion}" />
					    </display:column>
					    <display:column titleKey="nombre" >
						    <c:if test="${clasiEq2.id.parentEquipo.invitado == false}">
						     	<a href="<s:url action="VerEquipo"><s:param name="id" >${clasiEq2.id.parentEquipo.id}</s:param>
						     		</s:url>">${clasiEq2.id.parentEquipo.nombre}</a>
							</c:if>
							<c:if test="${clasiEq2.id.parentEquipo.invitado == true}">
								<div style="color:grey"><c:out value="${clasiEq2.id.parentEquipo.nombre}"/></div>
							</c:if>
			    		</display:column>
			    		<display:column titleKey="PG" >
			    			<b><c:out value="${clasiEq2.partidosGanados}" /></b>
			    		</display:column>
			    		<display:column titleKey="PP" >
			    			<c:out value="${clasiEq2.partidosPerdidos}" />
			    		</display:column>
			    		<display:column titleKey="PJ" >
			    			<c:out value="${clasiEq2.partidosGanados + clasiEq2.partidosPerdidos}" />
			    		</display:column>
			    		<display:column titleKey="TF" >
			    			<c:out value="${clasiEq2.tantosFavor}" />
			    		</display:column>
			    		<display:column titleKey="TC" >
			    			<c:out value="${clasiEq2.tantosContra}" />
			    		</display:column>
			    		<display:column titleKey="TT" >
			    			<c:out value="${clasiEq2.tantosFavor - clasiEq2.tantosContra}" />
			    		</display:column>				
					</display:table>
				</c:if>								
			</s:if>			
    	