package com.proyecto.util.displaytag;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.displaytag.pagination.PaginatedList;
import org.displaytag.properties.SortOrderEnum;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;

public class Paginate implements PaginatedList {

    private final int itemsPerPage = 10; // default

    private List<?> list;
    private int fullListSize;
    private int objectsPerPage;
    private int pageNumber;
    private String searchId; // for database cache, i do not use
    private String sortCriterion;
    private SortOrderEnum sortDirection;

    // get from parameter
    private int sortItemNo;

    public Paginate () {
        this(SortOrderEnum.DESCENDING, 1, 0,null);
    }

    public Paginate (SortOrderEnum sortDirection, int pageNumber, int sortItemNo, Integer ObjsPerPage) {
        //default
    	if(ObjsPerPage!=null){
    		this.objectsPerPage = ObjsPerPage;
    	}
    	else{
    		this.objectsPerPage = itemsPerPage;	
    	}
        this.searchId = null;
        // fill later
        this.fullListSize = 0;
        this.list = null;
        this.sortCriterion = "";
        // set first
        this.sortDirection = sortDirection;
        this.pageNumber = pageNumber;
        this.sortItemNo = sortItemNo; // id
        

    }
    public Paginate (String tableId, HttpServletRequest request,Integer ObjsPerPage) {
        this(tableId, request, SortOrderEnum.DESCENDING, 1, 0,ObjsPerPage);
    }
    public Paginate (String tableId, HttpServletRequest request, SortOrderEnum sortDirection, Integer ObjsPerPage) {
        this(tableId, request, sortDirection, 1, 0,ObjsPerPage);

    }
    public Paginate (String tableId, HttpServletRequest request, SortOrderEnum sortDirection, int pageNumber, int sortItemNo, Integer ObjsPerPage) {
        this(sortDirection, pageNumber, sortItemNo,ObjsPerPage); // set default

//        String pageNo = request.getParameter((new ParamEncoder(tableId)
//            .encodeParameterName(TableTagParameters.PARAMETER_PAGE)));
        System.out.println("Dentro del Paginate(), Objetos por página --> "+this.objectsPerPage);
        String pageNo = request.getParameter("page");
        this.pageNumber = (pageNo != null) ? Integer.parseInt(pageNo) : 1;
        
        System.out.println("Dentro del Paginate(), PageNumber --> "+this.pageNumber);
//        System.out.println("(Paginate() this.pagenumber -> "+this.pageNumber+"   pageNo -> "+pageNo+"  TableTagParameters.PARAMETER_PAGE -> "+TableTagParameters.PARAMETER_PAGE);
//        System.out.println("ParamEncoder(tableId) -> "+new ParamEncoder(tableId).encodeParameterName(TableTagParameters.PARAMETER_PAGE));
        
//        Enumeration enumer = request.getParameterNames();
//        while (enumer.hasMoreElements()) {
//          String name = (String) enumer.nextElement();
//          String values[] = request.getParameterValues(name);
//          if (values != null) {
//            for (int i = 0; i < values.length; i++) {
//              System.out.println(name + " (" + i + "): " + values[i]);
//            }
//          }
//        }

        // set default
        String sortDir = request.getParameter((new ParamEncoder(tableId)
            .encodeParameterName(TableTagParameters.PARAMETER_ORDER)));
        if (sortDirection == null) {
            this.sortDirection = (sortDir == null || sortDir.equals("2"))
                ? SortOrderEnum.DESCENDING : SortOrderEnum.ASCENDING;
        }
        else {
            this.sortDirection = sortDirection;
            if (sortDir != null) {
                this.sortDirection = (sortDir.equals("2")) ?
                        SortOrderEnum.DESCENDING : SortOrderEnum.ASCENDING;
            }
        }

        String sortItem = request.getParameter((new ParamEncoder(tableId)
            .encodeParameterName(TableTagParameters.PARAMETER_SORT)));
        this.sortItemNo = (sortItem != null) ? Integer.parseInt(sortItem) : 0; // id
        
        // Parece que lo del sortItemNo no arregla una mierda.

    }

    public int getStartPoint() {
        return (this.pageNumber-1)*this.objectsPerPage;
    }

    public int getFullListSize() {

        return this.fullListSize;
    }

    public void setFullListSize(int fullListSize) {
        this.fullListSize = fullListSize;
    }

    public List<?> getList() {
        return this.list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }

    public int getObjectsPerPage() {
        return this.objectsPerPage;
    }

    public void setObjectsPerPage(int objectsPerPage) {
        this.objectsPerPage = objectsPerPage;
    }

    public int getPageNumber() {
        return this.pageNumber;
    }
    public void setPageNumber(int pageNumber) {
        if (pageNumber < 0) pageNumber = 0;
        this.pageNumber = pageNumber;
    }

    public String getSearchId() {
        return searchId;
    }

    public void setSearchId(String searchId){
        this.searchId = searchId;
    }

    public String getSortCriterion() {
        return this.sortCriterion;
    }

    public void setSortCriterion(String sortCriterion){
        this.sortCriterion = sortCriterion;
    }

    public SortOrderEnum getSortDirection() {
        return this.sortDirection;
    }

    public void setSortDirection(SortOrderEnum sortDirection) {
        this.sortDirection = sortDirection;
    }

    public int getSortItemNo() {
        return this.sortItemNo;
    }

    public void setSortItemNo(int sortItemNo) {
        this.sortItemNo = sortItemNo;
    }

    public String toString() {
        return new ToStringBuilder(this).append("sortItemNo", this.sortItemNo)
                .append("searchId", this.searchId).append("sortDirection",
                        this.sortDirection).append("objectsPerPage",
                        this.objectsPerPage).append("list", this.list).append(
                        "sortCriterion", this.sortCriterion).append(
                        "fullListSize", this.fullListSize).append("pageNumber",
                        this.pageNumber).toString();
    }

 

}
