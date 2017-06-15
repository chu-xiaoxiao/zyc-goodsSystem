package com.mr.service;

import java.util.List;

import com.mr.pojoxml.Gtype;
import com.mr.pojoxml.GtypeDAO;
import com.mr.util.HibernateSessionFactory;

public class GtypeService {
	GtypeDAO gtypeDAO = new GtypeDAO();
	public List<Gtype> findAllType(){
		HibernateSessionFactory.getSession();
		return this.gtypeDAO.findAll();
	}
	public Gtype findGtypeById(Short id){
		return gtypeDAO.findById(id);
	}
}
