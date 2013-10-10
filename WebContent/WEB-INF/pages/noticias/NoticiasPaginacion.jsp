<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


  
<s:if test="noticiaPaginatedList.pageNumber != 1">
	[<span><s:a action="%{accion}">
		<s:param name="id" value="id"/><s:param name="page" value="1"/>
		<s:property value="%{getText('primera')}"/></s:a></span>
	/<span><s:a action="%{accion}">
		<s:param name="id" value="id"/><s:param name="page" value="noticiaPaginatedList.pageNumber - 1"/>
		<s:property value="%{getText('previa')}"/></s:a></span>]
	 <span><s:a action="%{accion}">
		<s:param name="id" value="id"/><s:param name="page" value="1"/>1</s:a></span>
	<s:if test="noticiaPaginatedList.pageNumber - 2 > 1">
		<s:if test="noticiaPaginatedList.pageNumber - 3 != 1">
			,...
		</s:if>
		,<span><s:a action="%{accion}">
			<s:param name="id" value="id"/><s:param name="page" value="noticiaPaginatedList.pageNumber - 2"/>
			<s:property value="%{noticiaPaginatedList.pageNumber - 2}"/></s:a></span>
	</s:if>

	<s:if test="noticiaPaginatedList.pageNumber - 1 > 1">
		,<span><s:a action="%{accion}">
			<s:param name="id" value="id"/><s:param name="page" value="noticiaPaginatedList.pageNumber - 1"/>
			<s:property value="%{noticiaPaginatedList.pageNumber - 1}"/></s:a></span>
	</s:if>

	,<b><s:property value="noticiaPaginatedList.pageNumber"/></b>

	<s:if test="noticiaPaginatedList.fullListSize > noticiaPaginatedList.objectsPerPage*noticiaPaginatedList.pageNumber">
		,<span><s:a action="%{accion}">
			<s:param name="id" value="id"/><s:param name="page" value="noticiaPaginatedList.pageNumber + 1"/>
			<s:property value="%{noticiaPaginatedList.pageNumber + 1}"/></s:a></span>
			
		<s:if test="noticiaPaginatedList.fullListSize > noticiaPaginatedList.objectsPerPage*(noticiaPaginatedList.pageNumber+1)">
		,<span><s:a action="%{accion}">
			<s:param name="id" value="id"/><s:param name="page" value="noticiaPaginatedList.pageNumber + 2"/>
			<s:property value="%{noticiaPaginatedList.pageNumber + 2}"/></s:a></span>
		</s:if>
		
		<s:if test="noticiaPaginatedList.fullListSize > noticiaPaginatedList.objectsPerPage*(noticiaPaginatedList.pageNumber+2)">
			<s:if test="noticiaPaginatedList.fullListSize > noticiaPaginatedList.objectsPerPage*(noticiaPaginatedList.pageNumber+3)">
				,...
			</s:if>
		
		
<%-- 		<s:if test="noticiaPaginatedList.fullListSize % noticiaPaginatedList.objectsPerPage == 0"> --%>
<%-- 			<s:set var="ultima" value="noticiaPaginatedList.fullListSize/noticiaPaginatedList.objectsPerPage"/> --%>
<%-- 		</s:if> --%>
<%-- 		<s:else> --%>
<%-- 			<s:set var="ultima" value="(noticiaPaginatedList.fullListSize/noticiaPaginatedList.objectsPerPage)+1"/> --%>
<%-- 		</s:else> --%>
		 	
			,<span><s:a action="%{accion}">
				<s:param name="id" value="id"/>
				<s:param name="page">
					<fmt:formatNumber maxFractionDigits="0">
					${noticiaPaginatedList.fullListSize/noticiaPaginatedList.objectsPerPage + (noticiaPaginatedList.fullListSize/noticiaPaginatedList.objectsPerPage % 1)}
					</fmt:formatNumber>
				</s:param>
			
				<fmt:formatNumber maxFractionDigits="0">
					${noticiaPaginatedList.fullListSize/noticiaPaginatedList.objectsPerPage + (noticiaPaginatedList.fullListSize/noticiaPaginatedList.objectsPerPage % 1)}
				</fmt:formatNumber>				
			</s:a></span>
		</s:if>
			
		[<span><s:a action="%{accion}">
			<s:param name="id" value="id"></s:param><s:param name="page" value="noticiaPaginatedList.pageNumber + 1"></s:param>
			<s:property value="%{getText('siguiente')}"/></s:a></span>
		/<span><s:a action="%{accion}">
			<s:param name="id" value="id"></s:param><s:param name="page" value="ultima"></s:param>
			<s:property value="%{getText('ultima')}"/></s:a></span>]				
	</s:if>

	<s:else>
	 [<s:property value="%{getText('siguiente')}"/>/<s:property value="%{getText('ultima')}"/>]
	</s:else>
</s:if>

<s:else>
[<s:property value="%{getText('primera')}"/>/<s:property value="%{getText('previa')}"/>] <b>1</b>
	<s:if test="noticiaPaginatedList.fullListSize > noticiaPaginatedList.objectsPerPage">
		,<span><s:a action="%{accion}">
			<s:param name="id" value="id"></s:param><s:param name="page" value="2"></s:param>2</s:a></span>
			
		<s:if test="noticiaPaginatedList.fullListSize > noticiaPaginatedList.objectsPerPage*2">
			,<span><s:a action="%{accion}">
				<s:param name="id" value="id"></s:param><s:param name="page" value="3"></s:param>3</s:a></span>
			
			<s:if test="noticiaPaginatedList.fullListSize > noticiaPaginatedList.objectsPerPage*(noticiaPaginatedList.pageNumber+3)">
				,...
				<s:if test="noticiaPaginatedList.fullListSize % noticiaPaginatedList.objectsPerPage == 0">
					,<span><s:a action="%{accion}">
						<s:param name="id" value="id"></s:param><s:param name="page" value="noticiaPaginatedList.fullListSize / noticiaPaginatedList.objectsPerPage"></s:param>
						<s:property value="noticiaPaginatedList.fullListSize / noticiaPaginatedList.objectsPerPage"/></s:a></span>				
				</s:if>
				<s:else>
					,<span><s:a action="%{accion}">
						<s:param name="id" value="id"></s:param><s:param name="page" value="(noticiaPaginatedList.fullListSize / noticiaPaginatedList.objectsPerPage)+1"></s:param>
						<s:property value="(noticiaPaginatedList.fullListSize / noticiaPaginatedList.objectsPerPage)+1"/></s:a></span>				
				</s:else>
			</s:if>
		</s:if>
		
		<s:if test="noticiaPaginatedList.fullListSize % noticiaPaginatedList.objectsPerPage == 0">
			[<span><s:a action="%{accion}">
				<s:param name="id" value="id"></s:param><s:param name="page" value="noticiaPaginatedList.pageNumber + 1"></s:param>
				<s:property value="%{getText('siguiente')}"/></s:a></span>
			/<span><s:a action="%{accion}">
				<s:param name="id" value="id"></s:param><s:param name="page" value="noticiaPaginatedList.fullListSize / noticiaPaginatedList.objectsPerPage"></s:param>
				<s:property value="%{getText('ultima')}"/></s:a></span>]
		</s:if>
		<s:else>
			[<span><s:a action="%{accion}">
				<s:param name="id" value="id"></s:param><s:param name="page" value="noticiaPaginatedList.pageNumber + 1"></s:param>
				<s:property value="%{getText('siguiente')}"/></s:a></span>
			/<span><s:a action="%{accion}">
				<s:param name="id" value="id"></s:param><s:param name="page" value="(noticiaPaginatedList.fullListSize / noticiaPaginatedList.objectsPerPage)+1"></s:param>
				<s:property value="%{getText('ultima')}"/></s:a></span>]
		</s:else>
				
	</s:if>
	
	<s:else>
	[<s:property value="%{getText('siguiente')}"/>/<s:property value="%{getText('ultima')}"/>]
	</s:else>
</s:else>