<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sjc" uri="/struts-jquery-chart-tags"%>

    <h2 style="padding-left:15px"><s:property value="nombre"/></h2>
    
	<sjc:chart
	    id="chartBarEstadisticaEq"
	    cssStyle="width: 600px; height: 250px; margin:5px"	
	    xaxisPosition="bottom"
		yaxisPosition="left"
		xaxisTickDecimals="0"
		yaxisTickDecimals="0"
		xaxisTick="[[1,''], [2,''], [3,'']]"  
		yaxisMin="0"
		xaxisMax="5"
		xaxisMin="0.5"  

	>
	    		
	    	<sjc:chartData
	    		key="palabra.comunidades"
	    		list="listComunidades"
	    		listKey="entero"
	    		listValue="segundo"
				bars="{ show:true, barWidth:0.6, align:'center'}"
	    	/>	
	    		    		    	
	    	<sjc:chartData
	    		key="torneos.no.comenzados"
	    		list="listTorneosNoComenzados"
	    		listKey="entero"
	    		listValue="segundo"
				bars="{ show:true, barWidth:0.6, align:'center'}"
 				stack="stack1" 
	    	/>
	    	
	    	<sjc:chartData
 	    		key="torneos.finalizados" 
 	    		list="listTorneosFinalizados" 
 	    		listKey="entero" 
 	    		listValue="segundo" 
 				bars="{ show:true, barWidth:0.6, align:'center'}" 
 				stack="stack1" 
 	    	/>	    	
	    	
	    	<sjc:chartData
 	    		key="torneos.en.juego" 
 	    		list="listTorneosEnJuego" 
 	    		listKey="entero" 
 	    		listValue="segundo" 
 				bars="{ show:true, barWidth:0.6, align:'center'}" 
 				stack="stack1" 
 	    	/>
 	    	
 	    	<sjc:chartData
	    		key="palabra.usuarios"
	    		list="listUsuarios"
	    		listKey="entero"
	    		listValue="segundo"
				bars="{ show:true, barWidth:0.6, align:'center'}"
	    	/>
	    	    	

	</sjc:chart>    