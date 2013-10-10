<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sjc" uri="/struts-jquery-chart-tags"%>

<!-- 	<div style="margin-bottom:10px"> -->
<!-- 	<div style="float:left"> -->
    	<h2 style="padding-left:15px"><s:property value="nombre"/></h2>
<!--     </div> -->
<!--     <div style="float:right"> -->
		<s:set var="rutaimagenActividad">${torneo.parentActividad.rutaimagen}</s:set>
		<s:set var="nombreActividad">${torneo.parentActividad.nombre}</s:set>

				<img src="<s:url value="%{'/images/iconos/actividades/50'}%{rutaimagenActividad}"/>" 
					title="<s:property value="nombreActividad"/>" width="50" height="50" style="margin-left:15px"/>
		<c:if test="${torneo.parentPlataforma != null }">
			<s:set var="rutaimagenPlataforma">${torneo.parentPlataforma.rutaimagen}</s:set>
			<s:set var="nombrePlataforma">${torneo.parentPlataforma.nombre}</s:set>
			
			<img src="<s:url value="%{'/images/iconos/plataformas/50'}%{rutaimagenPlataforma}"/>" 
					title="<s:property value="nombrePlataforma"/>" width="50" height="50" style="margin-left:5px" />
		</c:if>					
<!-- 	</div> -->
<!-- 	</div > -->

<!-- 	<div class="clr"></div> -->


<s:if test="estado == 2">
<!-- margin:5px; float:left -->

	<sjc:chart
	    id="chartBarEstadisticaTor"
	    cssStyle="width: 350px; height: 250px;"	
	    xaxisPosition="bottom"
		yaxisPosition="left"
		xaxisTickDecimals="0"
		yaxisTickDecimals="0"
		xaxisTick="[[1,''], [2,'']]"  
		yaxisMin="0"
		xaxisMax="4" 
		xaxisMin="0.5" 

	>
 	    	
 	    	<sjc:chartData
	    		key="palabra.usuarios"
	    		list="listUsuarios"
	    		listKey="entero"
	    		listValue="segundo"
				bars="{ show:true, barWidth:0.6, align:'center'}"
	    	/>
	    	
	    	<sjc:chartData
	    		key="palabra.equipos"
	    		list="listEquipos"
	    		listKey="entero"
	    		listValue="segundo"
				bars="{ show:true, barWidth:0.6, align:'center'}"
	    	/>	    	

	</sjc:chart>

<!-- float:left -->
	<sjc:chart
	    id="chartPieConfirmados"
	    cssStyle="width: 350px; height: 250px;"
	    pie="true"
	    pieLabel="true"
	    pieInnerRadius="0.3"
    	pieLabelRadius="0.85"
    	pieLabelBackgroundColor="#000"
    	pieLabelBackgroundOpacity="0.8"
	>
	    <sjc:chartData
	    	id="pieSerie1"
	    	label="Partidos Jugados"
	    	data="%{numPartidosConfirmados}"
	    />
	    <sjc:chartData
	    	id="pieSerie2"
	    	label="Partidos No Jugados"
	    	data="%{numPartidosNoConfirmados}"
	    />
	</sjc:chart>

</s:if>

<s:else>
	<sjc:chart
	    id="chartBarEstadisticaTor"
	    cssStyle="width: 600px; height: 250px;"	
	    xaxisPosition="bottom"
		yaxisPosition="left"
		xaxisTickDecimals="0"
		yaxisTickDecimals="0"
		xaxisTick="[[1,''], [2,'']]"  
		yaxisMin="0"
		xaxisMax="4" 
		xaxisMin="0.5" 
	>
 	    	
 	    	<sjc:chartData
	    		key="palabra.usuarios"
	    		list="listUsuarios"
	    		listKey="entero"
	    		listValue="segundo"
				bars="{ show:true, barWidth:0.6, align:'center'}"
	    	/>
	    	
	    	<sjc:chartData
	    		key="palabra.equipos"
	    		list="listEquipos"
	    		listKey="entero"
	    		listValue="segundo"
				bars="{ show:true, barWidth:0.6, align:'center'}"
	    	/>	    	

	</sjc:chart>
</s:else>
