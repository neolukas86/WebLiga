<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


			<s:url var="rutaImgDenegar" value="/images/template/remove-user-icon.png" />
			<s:url var="rutaImgAceptar" value="/images/template/add-user-icon.png" />
			
			<display:table name="userList" id="row" class="table" pagesize="10" 
					requestURI="GoRequestsEquipo">
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
			    <display:column titleKey="pais">
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
			    
				    <s:form action="AceptarRequestEquipo" method="post">
			     		<s:hidden name="id" value="%{id}"/>
			     		<s:hidden name="iduser" value="%{userId}"/>
			     		<s:submit type="image" title="%{getText('aceptar')}" src="%{rutaImgAceptar}" cssClass="centrado"/>
			     	</s:form>
			    </display:column>
			    <display:column titleKey="rechazar">
				    <s:form action="DenegarRequestEquipo" method="post">
			     		<s:hidden name="id" value="%{id}"/>
			     		<s:hidden name="iduser" value="%{userId}"/>
			     		<s:submit type="image" title="%{getText('rechazar')}" src="%{rutaImgDenegar}" cssClass="centrado"/>
			     	</s:form>
			    </display:column>	
		 </display:table>		
		 
    <sj:dialog 
    	id="myremotelinkdialog" 
    	autoOpen="false"  
    	title="Perfil de Usuario"
    />		  
		     	