package com.proyecto.util.displaytag;

import java.util.HashMap;
import java.util.List;

import org.displaytag.properties.SortOrderEnum;
import org.hibernate.Session;

/**
 * Singleton Class for sort and search of Paginated List
 * @author fri13th
 *
 */
public class PaginateUtil {

    static HashMap<String, String[]> sortTables = new HashMap<String, String[]>();
    static HashMap<String, String[]> searchTables = new HashMap<String, String[]>();

    static { // Aqui entiendo que habria que rellenarlo para todas las tablas que quieras que pagine resultados
        sortTables.put("Comunidad", new String [] {"id","nombre","fechaCreacion","usuariosMax","torneosActivosMax","torneosEnRegistroMax","torneosMax"});
        searchTables.put("Comunidad", new String [] {"id","nombre","fechaCreacion","usuariosMax","torneosActivosMax","torneosEnRegistroMax","torneosMax"});
        
        sortTables.put("User", new String [] {"id","alias","nombre","apellido","email","sexo","nacimiento","fechaRegistro"});
        searchTables.put("User", new String [] {"id","alias","nombre","apellido","email","sexo","nacimiento","fechaRegistro"});
        
        sortTables.put("Torneo", new String [] {"id","nombre","fechaCreacion","fechaComienzo","fechaFinal","online","porEquipos","liga","sorteado","rondas"});
        searchTables.put("Torneo", new String [] {"id","nombre","fechaCreacion","fechaComienzo","fechaFinal","online","porEquipos","liga","sorteado","rondas"});
    }

    public static Paginate getPaginate(Session session, String tableName, Paginate paginate, String searchWord, String condicionWhere) {

        String [] sortItems = (String [])sortTables.get(tableName);
        String [] searchItems = (String [])searchTables.get(tableName);
        if (sortItems == null || searchItems == null) System.err.println("error! on paginate util");

        String where = ""; // where clause
        if  (searchWord != null && !searchWord.equals(""))
             where = PaginateUtil.getWhere(searchItems, searchWord);
        
        if(condicionWhere != null && !condicionWhere.equals("")){
        	if  (searchWord != null && !searchWord.equals("")){
        		where +="and ";
        	}
        	else{
        		where = " where ";
        	}
        	
        	where += condicionWhere;
        }
        
        String order = " order by " + PaginateUtil.getSortItem(sortItems, paginate.getSortItemNo());
        order += (paginate.getSortDirection() == SortOrderEnum.ASCENDING) ? " asc" : " desc";
        if (!PaginateUtil.getSortItem(sortItems, paginate.getSortItemNo()).equals("id"))
            order += ", id desc";

//        int fullListSize = ((Integer)session.createQuery("select count(*) from " + tableName + where).iterate().next()).intValue();
        
        Number fullListSize =  (Number) session.createQuery("select count(*) from " + tableName + where).uniqueResult();
        
        List<?> results = session.createQuery("from " + tableName + where + order)
        							.setMaxResults(paginate.getObjectsPerPage())
        							.setFirstResult(paginate.getStartPoint())
        							.list();
        paginate.setFullListSize(fullListSize.intValue());
        paginate.setList(results);
        
        
        return paginate;
    }

    public static String getSortItem(String [] items, int sortItemNo) {

        if (sortItemNo < 0 || sortItemNo > items.length)
            return items[0];
        else
            return items[sortItemNo];
    }

    public static String getWhere(String [] items, String searchWord) {
        String where = " where ";
        for (int i = 0; i < items.length; i++) {
            where += "lower(" + items[i]+ ") like ‘%" + searchWord + "%’ ";
            if (i != items.length-1) where += "or ";
        }
        return where;
    }
}
