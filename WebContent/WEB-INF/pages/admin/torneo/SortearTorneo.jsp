<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
	
<s:actionerror theme="jquery"/>
<s:fielderror theme="jquery"/>
		
<s:if test="liga == true">			
	<s:form name="formularioSorteo" action="SortearTorneo" method="post" cssClass="form">
		<fieldset>
		<legend><s:text name="sortear"/></legend>
		<table><tr>
		<td>
		<s:hidden name="id" value="%{id}"/>
		<s:hidden name="auto" value="no"/>
	<!-- 	empezar al sortear, jornadas jugables --> 
		<s:select name="empieza" list="listaBoolean" key="comenzar" value="true"/>
		</td><td>
		<s:select name="jornadasJugables" list="listaJornadasJugables" key="jornadas.jugables" value="1"/>
		</td>
		</tr><tr><td>
		<s:select name="entero" key="palabra.reglas" list="listaReglas" value="idregla"/>
		</td></tr>
		</table>
		<sj:submit  button="true" key="sortear" 
			    	cssClass="centrado" cssStyle="font-size:15px;"/>
		</fieldset>
	</s:form>
</s:if>

<s:else>
	<s:form name="formularioSorteoCopa" action="SortearTorneo" method="post" cssClass="form">
		<fieldset>
		<legend><s:text name="sortear"/></legend>
		<s:hidden name="id" value="%{id}"/>
		<s:hidden name="auto" value="no"/>
		<s:select name="empieza" list="listaBoolean" key="comenzar" value="true"/>
		<sj:submit  button="true" key="sortear" 
			    	cssClass="centrado" cssStyle="font-size:15px;"/>
		</fieldset>
	</s:form>
</s:else>
