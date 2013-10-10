<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sjc" uri="/struts-jquery-chart-tags"%>
	

	<sjc:chart
	    id="chartPie"
	    cssStyle="width: 650px; height: 400px;"
	    pie="true"
	    pieLabel="true"
	    pieInnerRadius="0.3"
    	pieLabelRadius="0.85"
    	pieLabelBackgroundColor="#000"
    	pieLabelBackgroundOpacity="0.8"
	>
	    <s:iterator value="IntegerFromMap" status="IntegerStatus">
	    	<sjc:chartData
	    		label="%{key}"
	    		data="%{value}"
	    	/>
	    </s:iterator>
	</sjc:chart>
	
	


