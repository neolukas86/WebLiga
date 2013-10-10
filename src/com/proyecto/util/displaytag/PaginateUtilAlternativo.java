package com.proyecto.util.displaytag;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.displaytag.properties.SortOrderEnum;
import org.hibernate.Session;

/**
 * Singleton Class for sort and search of Paginated List
 *
 */
public class PaginateUtilAlternativo {

	public static Paginate getPaginate(Session session,Paginate paginate,String query){
        List<?> results = session.createQuery(query)
        							.setMaxResults(paginate.getObjectsPerPage())
        							.setFirstResult(paginate.getStartPoint())
        							.list();
        
//        System.out.println("*** _-_-_ PaginateUtilAlternativo query --> "+query);
//        System.out.println("*** _-_-_ PaginateUtilAlternativo ObjetosPorPagina --> "+paginate.getObjectsPerPage()+
//		"   paginate.getStartPoint() --> "+paginate.getStartPoint());
        
//        Iterator<?> it = results.iterator();
//        while(it.hasNext()){
//        	System.out.println("PaginateUtilAlternativo  results --> "+it.next());
//        }
        paginate.setList(results);
        
        return paginate;
		
	}
    public static Paginate getPaginate(Session session, String tableName, Paginate paginate, 
    		HashMap<String,String> searchWords, HashMap<String,String> condicionWhere,
    		String orderBy) {

    	String where = "";
    	String condicion = PaginateUtilAlternativo.getWhere(condicionWhere);
    	
    	if(condicion != null && !condicion.equals("")){
    		where = " where "+condicion;
    	}
    	
    	String words = PaginateUtilAlternativo.getSearchWords(searchWords);
    	
    	if(words != null && !words.equals("")){
    		if(where != null && !where.equals("")){
    			where += " and ";
    		}
    		else{
    			where += " where ";
    		}
    		
    		where += words;
    	}
    		
        
        String order = " order by ";
        if(orderBy != null && !orderBy.equals("")){
        	order += orderBy;
        }
        else{
        	order += "1";
        }
        order += (paginate.getSortDirection() == SortOrderEnum.ASCENDING) ? " asc" : " desc";
        
        
        
        
        List<?> results = session.createQuery("from " + tableName + where + order)
        							.setMaxResults(paginate.getObjectsPerPage())
        							.setFirstResult(paginate.getStartPoint())
        							.list();
        
        paginate.setList(results);
        paginate.setFullListSize(fullListSize(session,paginate,tableName,where).intValue());
        
        return paginate;
    }
    
    public static String getWhere(HashMap<String,String> condicionWhere){
    	String where="";
    	
    	if(condicionWhere !=null && !condicionWhere.isEmpty()){
    		Iterator<Entry<String, String>> it = condicionWhere.entrySet().iterator();
    		
    		
    		while(it.hasNext()){
    			Entry<String, String> e = it.next();
    			
    			where += e.getKey()+e.getValue();
    			
    			if(it.hasNext()){
    				where +=" and ";
    			}
    		}
    	}
    	
    	return where;
    }
    
    public static String getSearchWords(HashMap<String,String> searchWords){
		String where="";
    	
    	if(searchWords!=null && !searchWords.isEmpty()){
			Iterator<Entry<String, String>> it = searchWords.entrySet().iterator();
			
			while(it.hasNext()){
				Entry<String, String> e = it.next();
				
				where += e.getKey()+" like '%"+e.getValue()+"%'";
				
				if(it.hasNext()){
					where +=" and ";
				}
			}
		}
    	
    	return where;
    }
    
    public static Number fullListSize(Session session,Paginate paginate,String tableName,String where){
    	return  (Number) session.createQuery("select count(*) from " + tableName + where).uniqueResult();
    }
    
    public static Number fullListSize(Session session,Paginate paginate,String tableName,String aliasTable,String where,String tablasAux){
    	return (Number) session.createQuery("select count("+aliasTable+") from "+ tableName+" as "+aliasTable + tablasAux + where)
    										.uniqueResult();
    }    

}

