<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

      <div class="menu">
        <ul>          
          <li><s:a action="GoCrearEquipo"><s:text name="register.equipo" /></s:a></li>
          <li><s:a action="GoCrearComunidad"><s:text name="register.comunidad" /></s:a></li>
          <li><s:a action="ListarTorneosPropios"><s:text name="mis.torneos" /></s:a></li>
          <li><s:a action="ListarComunidadesPropias"><s:text name="mis.comunidades" /></s:a></li>
          <li><s:a action="ListarEquiposPropios"><s:text name="mis.equipos"/></s:a></li>          
        </ul>
      </div>