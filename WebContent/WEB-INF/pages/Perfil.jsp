<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<script>
$(document).ready(function() {
    $(".my_table tr:nth-child(even)").addClass("even");
    $(".my_table tr:nth-child(odd)").addClass("odd");
});
</script>
    
<table class="my_table">
<!--   <thead> -->
<!--     <tr> -->
<!--       	<th style="height:20px"></th> -->
<!--       	<th></th> -->
<!--     </tr> -->
<!--   </thead> -->
  
  <tbody>
  
    <tr>
    <td><s:text name="user.id"/></td>
    <td><s:property value="id"/></td>
    </tr>
    
    <tr>
    <td><s:text name="user.alias"/></td>
    <td><s:property value="alias"/></td>
    </tr> 
       
    <s:if test="nombre!=null && !nombre.isEmpty()">
    	<tr>
		<td><s:text name="firstname"/></td>
		<td><s:property value="nombre"/></td>
		</tr>
	</s:if>
	
	<s:if test="apellido!=null && !apellido.isEmpty()">
		<tr>
		<td><s:text name="lastname"/></td>
		<td><s:property value="apellido"/></td>
		</tr>
	</s:if>
	
	<tr>
	<td><s:text name="email"/></td>
	<td><a href='mailto:<s:property value="email"/>'><s:property value="email"/></a></td>
	</tr>
	
	<s:if test="sexo!=null">
		<tr>
		<td><s:text name="sexo"/></td>
		<td><s:if test="%{sexo}">
		     				<s:text name="sexo.masculino"/> 
		     			</s:if> 
		     			<s:else> 
		     				<s:text name="sexo.femenino"/> 
		     			</s:else>
		</td>
		</tr>
	</s:if>
	
	<s:if test="nacimiento!=null">
		<tr>
		<td><s:text name="fecha.nacimiento"/></td>
		<td><s:property value="nacimiento"/></td>
		</tr>
	</s:if>
	
	<s:if test="pais!=null">
		<tr>
		<td><s:text name="pais"/></td>
		<td><s:property value="parentPais.nombre"/></td>
		</tr>
	</s:if>
	
	<s:if test="municipio!=null">
		<tr>
		<td><s:text name="region"/></td>
		<td><s:property value="parentMunicipio.parentProvincia.parentRegion.nombre"/></td>
		</tr>
		<tr>
		<td><s:text name="provincia"/></td>
		<td><s:property value="parentMunicipio.parentProvincia.nombre"/></td>
		</tr>
		<tr>
		<td><s:text name="municipio"/></td>
		<td><s:property value="parentMunicipio.nombre"/></td>
		</tr>
	</s:if>
	
	<s:if test="homepage!=null && !homepage.isEmpty()">
		<tr>
		<td><s:text name="homepage"/></td>
		<td><a href="<s:url value="%{homepage}"/>" target="_blank"><s:property value="homepage"/></a></td>
		</tr>
	</s:if>
	
	<tr>
	<td><s:text name="fecha.registro"/></td>
	<td><s:property value="fechaRegistro"/></td>
	</tr>
	
	</tbody>
    </table>

	
	
