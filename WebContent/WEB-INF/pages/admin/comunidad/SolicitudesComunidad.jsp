<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


	<s:if test="userList.isEmpty() && equipoList.isEmpty()">
		Actualmente no hay ninguna solicitud
	</s:if>

	<s:if test="!userList.isEmpty()">
		<div class="form">
		<fieldset>
		<legend><s:text name="palabra.usuarios" /></legend>
			<s:url var="rutaImgAceptarUsuario" value="/images/template/Person-Male-Light-icon16-add.png" />
			<s:url var="rutaImgDenegarUsuario" value="/images/template/Person-Male-Light-icon16-remove.png" />
			
			<display:table name="userList" id="row" class="table" pagesize="10" 
					requestURI="GoRequestsComunidad">
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
			    
				    <s:form action="AceptarRequestUsuarioComunidad" method="post">
			     		<s:hidden name="id" value="%{id}"/>
			     		<s:hidden name="iduser" value="%{userId}"/>
			     		<s:submit type="image" title="%{getText('aceptar')}" src="%{rutaImgAceptarUsuario}" cssClass="centrado"/>
			     	</s:form>
			    </display:column>
			    <display:column titleKey="rechazar">
				    <s:form action="DenegarRequestUsuarioComunidad" method="post">
			     		<s:hidden name="id" value="%{id}"/>
			     		<s:hidden name="iduser" value="%{userId}"/>
			     		<s:submit type="image" title="%{getText('rechazar')}" src="%{rutaImgDenegarUsuario}" cssClass="centrado"/>
			     	</s:form>
			    </display:column>	
		 </display:table>
		 </fieldset>
		 </div>
		 
		 <sj:dialog 
	    	id="myremotelinkdialog" 
	    	autoOpen="false"  
	    	title="Perfil de Usuario"
	    />
	    
	  </s:if>
	  
	  <s:if test="!equipoList.isEmpty()">
	  	<div class="form">
		<fieldset>
		<legend><s:text name="palabra.equipos" /></legend>
	  	<s:url var="rutaImgAceptarEquipo" value="/images/template/Group-Meeting-Light-icon16-add.png" />
		<s:url var="rutaImgDenegarEquipo" value="/images/template/Group-Meeting-Light-icon16-remove.png" />
		
		 <display:table name="equipoList" id="row2" class="table" pagesize="10" 
					requestURI="GoRequestsComunidad">
<%-- 			    <display:column property="id" titleKey="id" /> --%>
		     	<display:column property="tag" titleKey="tag"/>
				<display:column titleKey="nombre" sortable="true" sortName="nombre">
		    		<a href="<s:url action="VerEquipo"><s:param name="id" >${row2.id}</s:param></s:url>">${row2.nombre}</a>
		    	</display:column>				
		    
		    	<c:set var="coleccionActEq" value="${row2.actividades}" />
		    
		    	<display:column titleKey="palabra.actividades" style="width:160px">
		    		<c:forEach  var="var" items="${coleccionActEq}" end="4">
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
			    	<c:if test="${coleccionActEq.size() > 5}">
			    		(<c:out value="${coleccionActEq.size()}"/>)
			    	</c:if>
			    </display:column>
		    
			    <c:set var="coleccionPlEq" value="${row2.plataformas}" />
			    
			    <display:column titleKey="palabra.plataformas" style="width:160px">
			    	<c:forEach  var="var" items="${coleccionPlEq}" end="4">
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
			    	<c:if test="${coleccionPlEq.size() > 5}">
			    		(<c:out value="${coleccionPlEq.size()}"/>)
			    	</c:if>		    
			    </display:column>
			    
			    <display:column titleKey="aceptar">
			    <s:set var="equipoId">${row2.id}</s:set>
			    
				    <s:form action="AceptarRequestEquipoComunidad" method="post">
			     		<s:hidden name="id" value="%{id}"/>
			     		<s:hidden name="idequipo" value="%{equipoId}"/>
			     		<s:submit type="image" title="%{getText('aceptar')}" src="%{rutaImgAceptarEquipo}" cssClass="centrado"/>
			     	</s:form>
			    </display:column>
			    <display:column titleKey="rechazar">
				    <s:form action="DenegarRequestEquipoComunidad" method="post">
			     		<s:hidden name="id" value="%{id}"/>
			     		<s:hidden name="idequipo" value="%{equipoId}"/>
			     		<s:submit type="image" title="%{getText('rechazar')}" src="%{rutaImgDenegarEquipo}" cssClass="centrado"/>
			     	</s:form>
			    </display:column>	
		 </display:table> 
		 </fieldset>
		 </div>
     </s:if>		  
		     	