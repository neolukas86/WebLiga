<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="sjc" uri="/struts-jquery-chart-tags"%>

<%-- <sjc:chart id="chartPoints" cssStyle="width: 600px; height: 500px; margin:5px" --%>
<%-- 		xaxisPosition="bottom" --%>
<%-- 		yaxisPosition="left" --%>
<%-- 		xaxisTickDecimals="0" --%>
<%-- 		yaxisTickDecimals="0" --%>
<%-- 		xaxisLabel="%{getText('palabra.jornada')}" --%>
<%-- 		yaxisLabel="%{getText('tantos')}" --%>
<%-- 		> --%>
<%--     	<sjc:chartData --%>
<%--     		label="Tantos a Favor" --%>
<%--     		list="listaTantosFavor" --%>
<%--     		listKey="entero" --%>
<%--     		listValue="segundo" --%>
<%--     		points="{show:true}" --%>
<%--     		lines="{show:true}" --%>
<%--     	/> --%>
<%--     	<sjc:chartData --%>
<%--     		label="Tantos en Contra" --%>
<%--     		list="listaTantosContra" --%>
<%--     		listKey="entero" --%>
<%--     		listValue="segundo" --%>
<%--     		points="{show:true}" --%>
<%--     		lines="{show:true}" --%>
<%--     	/> --%>
<%--     </sjc:chart> --%>
    
    
	<sjc:chart
	    id="chartBarEstadistica"
	    cssStyle="width: 650px; height: 400px; margin:5px"	
	    xaxisPosition="bottom"
		yaxisPosition="left"
		xaxisTickDecimals="0"
		yaxisTickDecimals="0"
		xaxisLabel="%{getText('palabra.jornada')}"
		yaxisLabel="%{getText('tantos')}"    

	>
	    	<sjc:chartData
	    		label="Tantos a Favor"
	    		list="listaTantosFavor"
	    		listKey="entero"
	    		listValue="segundo"
				bars="{ show:true, barWidth:0.35, align:'right'}"
	    	/>
	    	
	    	<sjc:chartData
	    		label="Tantos en Contra"
	    		list="listaTantosContra"
	    		listKey="entero"
	    		listValue="segundo"
				bars="{ show:true, barWidth:0.35, align:'left'}"
	    	/>

	</sjc:chart>    