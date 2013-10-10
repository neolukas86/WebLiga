<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="sjc" uri="/struts-jquery-chart-tags"%>

	<sjc:chart
	    id="chartPieConfirmados"
	    cssStyle="width: 650px; height: 400px;"
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

