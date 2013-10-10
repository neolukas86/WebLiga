<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


      <div class="menu">
        <ul>          
          <li><s:a action="GoAdminUsuarios"><s:text name="palabra.usuarios" /></s:a></li>
          <li><s:a action="GoAdminEquipos"><s:text name="palabra.equipos"/></s:a></li>
          <li><s:a action="GoAdminComunidades"><s:text name="palabra.comunidades" /></s:a></li>
          <li><s:a action="GoAdminTorneos"><s:text name="palabra.torneos" /></s:a></li>
          <li><s:a action="GoAdminActividades"><s:text name="palabra.actividades" /></s:a></li>
          <li><s:a action="GoAdminPlataformas"><s:text name="palabra.plataformas" /></s:a></li>                      
        </ul>
      </div>