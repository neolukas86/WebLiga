<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags"%>

<s:if test="#request.locale.language.equals('es')">
	<div class="noticia">
		<h1>Conéctate a WebLiga</h1>
		<img src="<s:url value="/images/template/paginaNoticia.jpg" />" alt="picture" width="675" height="190" 
			style="border: 5px solid #BAD340; margin-bottom:15px; margin-top:5px;"/>
		<p><strong>Conéctate ya a Webliga.</strong><br /></p>
		¿Eres un adicto a las redes sociales? Ahora ya puedes seguir a WebLiga en las principales redes sociales y enterarte
		al instante de las últimas novedades, o de interactuar con el equipo de WebLiga. <br><br>
		<a href='<s:property value="%{getText('contacto.twitter')}" />'>Twitter</a> es sin duda una plataforma en pleno auge, 
		su sistema de mensajes cortos hace que todo sea más rapido y directo, manténte informado y siguenos para no perderte nada.<br> 
		También podrás informarte, comentar, y participar en nuestra páginas de <a href='<s:property value="%{getText('contacto.facebook')}" />'>Facebook</a>
		 y <a href='<s:property value="%{getText('contacto.google')}" />'>Google+</a>.<br><br>
		 <s:a action="VolverIndex" cssStyle="font:bold 14px Arial, Helvetica, sans-serif; color:black">« <s:text name="volver"/></s:a>
	</div>
</s:if>
	
<s:if test="#request.locale.language.equals('en')">
	<div class="noticia">
		<h1>Connect to WebLiga</h1>
		<img src="<s:url value="/images/template/paginaNoticia.jpg" />" alt="picture" width="675" height="190" 
			style="border: 5px solid #BAD340; margin-bottom:15px; margin-top:5px;"/>
		<p><strong>Connect now to WebLiga.</strong><br /></p>
		Are you addicted to social networking? Now you can follow WebLiga in major social networks and find
		instantly with the latest news, or to interact with the WebLiga staff.<br><br>
		<a href='<s:property value="%{getText('contacto.twitter')}" />'>Twitter</a> is definitely a booming platform,
		its short message system makes everything faster and more direct, stay informed and follow us to not miss anything.<br>
		You can also inform, comment, and participate in our <a href='<s:property value="%{getText('contacto.facebook')}" />'>Facebook</a>
		 and <a href='<s:property value="%{getText('contacto.google')}" />'>Google+</a> pages.<br><br>
		 <s:a action="VolverIndex" cssStyle="font:bold 14px Arial, Helvetica, sans-serif; color:black">« <s:text name="volver"/></s:a>
	</div>
</s:if>

<s:if test="#request.locale.language.equals('ca')">
	<div class="noticia">
		<h1>Connecta't a WebLiga</h1>
		<img src="<s:url value="/images/template/paginaNoticia.jpg" />" alt="picture" width="675" height="190" 
			style="border: 5px solid #BAD340; margin-bottom:15px; margin-top:5px;"/>
		<p><strong>Connecta't ja a WebLiga.</strong><br /></p>
		Ets un addicte a les xarxes socials? Ara ja pots seguir WebLiga en les principals xarxes socials i assabentar
		l'instant de les últimes novetats, o d'interactuar amb l'equip de WebLiga.<br><br>
		<a href='<s:property value="%{getText('contacto.twitter')}" />'>Twitter</a> és sens dubte una plataforma en plena expansió,
		seu sistema de missatges curts fa que tot sigui més ràpid i directe, mantingues informat i segueix-per no perdre't res.<br>
		També podràs informar-te, comentar, i participar en la nostra pàgines de <a href='<s:property value="%{getText('contacto.facebook')}" />'>Facebook</a>
		 i <a href='<s:property value="%{getText('contacto.google')}" />'>Google+.</a><br><br>
		 <s:a action="VolverIndex" cssStyle="font:bold 14px Arial, Helvetica, sans-serif; color:black">« <s:text name="volver"/></s:a>
	</div>
</s:if>


