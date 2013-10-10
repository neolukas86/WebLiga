<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<script type="text/javascript">
$("#resultJugadores").on('click','span a, th a', function(event) {
	$("#resultJugadores").load(this.href);
	event.preventDefault();
});

</script>

<s:if test="userPaginatedList != null">
		<display:table name="userPaginatedList" id="row" class="table" sort="external" partialList="true" 
		 					size="${userPaginatedList.fullListSize}" pagesize="${userPaginatedList.objectsPerPage}" requestURI="${reqURI}"> 
<%-- 		    <display:column property="id" titleKey="id" />  --%>
		     <display:column titleKey="user.alias" >
			    <c:if test="${row.invitado == false}">
			     	<s:url var="perfilAction" action="GoPerfil"><s:param name="id" >${row.id}</s:param></s:url>
			     			
			     	<sj:a  
						href="%{perfilAction}"
						openDialog="myremotelinkdialog"
						button="false" >
							${row.alias}
					</sj:a>
				</c:if>
				<c:if test="${row.invitado == true}">
					<div style="color:grey"><c:out value="${row.alias}"/></div>
				</c:if>
		     </display:column>
<%-- 		     <display:column property="nombre" titleKey="firstname"/>  --%>
<%-- 		     <display:column property="apellido" titleKey="lastname"/> --%>
<%-- 		     <display:column property="email" titleKey="email"/> --%>
<%-- 		     <display:column property="nacimiento" titleKey="fecha.nacimiento"/>  --%>
		     <display:column titleKey="palabra.pais"> 
		    	<s:if test="#request.locale.language == 'es'">		    	
    				<c:out value="${row.parentPais.nombre}" />
    			</s:if>
    			<s:elseif test="#request.locale.language == 'en'">
    				<c:if test="${row.parentPais.nombre_EN != null}" >		    	
    					<c:out value="${row.parentPais.nombre_EN}" />
    				</c:if>
    				<c:if test="${row.parentPais.nombre_EN == null}">
    					<c:out value="${row.parentPais.nombre}" />
    				</c:if>
    			</s:elseif>
    			<s:elseif test="#request.locale.language == 'ca'">
    				<c:if test="${row.parentPais.nombre_CA != null}" >		    	
    					<c:out value="${row.parentPais.nombre_CA}" />
    				</c:if>
    				<c:if test="${row.parentPais.nombre_CA == null}">
    					<c:out value="${row.parentPais.nombre}" />
    				</c:if>
    			</s:elseif> 
		     </display:column>
		     <display:column titleKey="sexo"> 
		     	<c:if test="${row.sexo == false}">
		     		<s:text name="sexo.femenino"/> 
		     	</c:if> 
		     	<c:if test="${row.sexo == true}"> 
		     		<s:text name="sexo.masculino"/> 
		     	</c:if>
		     </display:column>    
		
		  <display:column property="fechaRegistro" titleKey="fecha.registro"/>
		
		 </display:table> 
</s:if>

<s:if test="userList != null">
	<s:if test="userList.size() > 10">
		<display:table name="userList" id="row" class="table" pagesize="10" requestURI="${reqURI}"> 
<%-- 		    <display:column property="id" titleKey="id" />  --%>
		     <display:column titleKey="user.alias" sortable="true" sortName="alias">
			    <c:if test="${row.invitado == false}">
			     	<s:url var="perfilAction" action="GoPerfil"><s:param name="id" >${row.id}</s:param></s:url>
			     			
			     	<sj:a  
						href="%{perfilAction}"
						openDialog="myremotelinkdialog"
						button="false" >
							${row.alias}
					</sj:a>
				</c:if>
				<c:if test="${row.invitado == true}">
					<div style="color:grey"><c:out value="${row.alias}"/></div>
				</c:if>
		     </display:column>
<%-- 		     <display:column property="nombre" titleKey="firstname"/>  --%>
<%-- 		     <display:column property="apellido" titleKey="lastname"/> --%>
<%-- 		     <display:column property="email" titleKey="email"/> --%>
<%-- 		     <display:column property="nacimiento" titleKey="fecha.nacimiento"/>  --%>
		     <display:column titleKey="palabra.pais" sortable="true" sortName="pais"> 
		    	<s:if test="#request.locale.language == 'es'">		    	
    				<c:out value="${row.parentPais.nombre}" />
    			</s:if>
    			<s:elseif test="#request.locale.language == 'en'">
    				<c:if test="${row.parentPais.nombre_EN != null}" >		    	
    					<c:out value="${row.parentPais.nombre_EN}" />
    				</c:if>
    				<c:if test="${row.parentPais.nombre_EN == null}">
    					<c:out value="${row.parentPais.nombre}" />
    				</c:if>
    			</s:elseif>
    			<s:elseif test="#request.locale.language == 'ca'">
    				<c:if test="${row.parentPais.nombre_CA != null}" >		    	
    					<c:out value="${row.parentPais.nombre_CA}" />
    				</c:if>
    				<c:if test="${row.parentPais.nombre_CA == null}">
    					<c:out value="${row.parentPais.nombre}" />
    				</c:if>
    			</s:elseif> 
		     </display:column>
		     <display:column titleKey="sexo" sortable="true" sortName="sexo"> 
		     	<c:if test="${row.sexo == false}">
		     		<s:text name="sexo.femenino"/> 
		     	</c:if> 
		     	<c:if test="${row.sexo == true}"> 
		     		<s:text name="sexo.masculino"/> 
		     	</c:if>
		     </display:column>    
		
		  <display:column property="fechaRegistro" titleKey="fecha.registro" sortable="true" sortName="fechaCreacion"/>
		
		 </display:table>
	</s:if>
	
	<s:else>
	<div class="solo_pagebanner">
		<display:table name="userList" id="row" class="table" pagesize="10" requestURI="${reqURI}"> 
<%-- 		    <display:column property="id" titleKey="id" />  --%>
		     <display:column titleKey="user.alias" sortable="true" sortName="alias">
			    <c:if test="${row.invitado == false}">
			     	<s:url var="perfilAction" action="GoPerfil"><s:param name="id" >${row.id}</s:param></s:url>
			     			
			     	<sj:a  
						href="%{perfilAction}"
						openDialog="myremotelinkdialog"
						button="false" >
							${row.alias}
					</sj:a>
				</c:if>
				<c:if test="${row.invitado == true}">
					<div style="color:grey"><c:out value="${row.alias}"/></div>
				</c:if>
		     </display:column>
<%-- 		     <display:column property="nombre" titleKey="firstname"/>  --%>
<%-- 		     <display:column property="apellido" titleKey="lastname"/> --%>
<%-- 		     <display:column property="email" titleKey="email"/> --%>
<%-- 		     <display:column property="nacimiento" titleKey="fecha.nacimiento"/>  --%>
		     <display:column titleKey="palabra.pais" sortable="true" sortName="pais"> 
		    	<s:if test="#request.locale.language == 'es'">		    	
    				<c:out value="${row.parentPais.nombre}" />
    			</s:if>
    			<s:elseif test="#request.locale.language == 'en'">
    				<c:if test="${row.parentPais.nombre_EN != null}" >		    	
    					<c:out value="${row.parentPais.nombre_EN}" />
    				</c:if>
    				<c:if test="${row.parentPais.nombre_EN == null}">
    					<c:out value="${row.parentPais.nombre}" />
    				</c:if>
    			</s:elseif>
    			<s:elseif test="#request.locale.language == 'ca'">
    				<c:if test="${row.parentPais.nombre_CA != null}" >		    	
    					<c:out value="${row.parentPais.nombre_CA}" />
    				</c:if>
    				<c:if test="${row.parentPais.nombre_CA == null}">
    					<c:out value="${row.parentPais.nombre}" />
    				</c:if>
    			</s:elseif> 
		     </display:column>
		     <display:column titleKey="sexo" sortable="true" sortName="sexo"> 
		     	<c:if test="${row.sexo == false}">
		     		<s:text name="sexo.femenino"/> 
		     	</c:if> 
		     	<c:if test="${row.sexo == true}"> 
		     		<s:text name="sexo.masculino"/> 
		     	</c:if>
		     </display:column>    
		
		  <display:column property="fechaRegistro" titleKey="fecha.registro" sortable="true" sortName="fechaCreacion"/>
		
		 </display:table>	
	</div>
	</s:else>
</s:if>

    <sj:dialog 
    	id="myremotelinkdialog" 
    	autoOpen="false"  
    	title="%{getText('perfil.usuario')}"
    	buttons="{'Cerrar':function() { $('#myremotelinkdialog').dialog('close'); }}"
    	width="500"
    	 
    	
    />