package com.mr.pojoxml.copy;

import java.util.HashSet;
import java.util.Set;


/**
 * Gtype entity. @author MyEclipse Persistence Tools
 */

public class Gtype  implements java.io.Serializable {


    // Fields    

     private Short gtypeid;
     private String gtypename;
     private Set goodses = new HashSet(0);


    // Constructors

    /** default constructor */
    public Gtype() {
    }

    
    /** full constructor */
    public Gtype(String gtypename, Set goodses) {
        this.gtypename = gtypename;
        this.goodses = goodses;
    }

   
    // Property accessors

    public Short getGtypeid() {
        return this.gtypeid;
    }
    
    public void setGtypeid(Short gtypeid) {
        this.gtypeid = gtypeid;
    }

    public String getGtypename() {
        return this.gtypename;
    }
    
    public void setGtypename(String gtypename) {
        this.gtypename = gtypename;
    }

    public Set getGoodses() {
        return this.goodses;
    }
    
    public void setGoodses(Set goodses) {
        this.goodses = goodses;
    }
   








}