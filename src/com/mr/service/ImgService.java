package com.mr.service;

import org.hibernate.Transaction;

import com.mr.pojoxml.Goods;
import com.mr.pojoxml.Imgs;
import com.mr.pojoxml.ImgsDAO;
import com.mr.util.HibernateSessionFactory;

public class ImgService {
	private ImgsDAO imgsDAO = new ImgsDAO();
	/**
	 * 上传商品图片
	 * @param path
	 * @param gid
	 */
	public void uploadImgForGoods(String path,Long gid){
		Transaction transaction = HibernateSessionFactory.getSession().beginTransaction();
		Imgs imgs = new Imgs();
		Goods goods = new Goods();
		goods.setGid(gid);
		imgs.setImgpath(path);
		imgs.setGoods(goods);
		imgsDAO.attachDirty(imgs);
		transaction.commit();
	}
	/**
	 * 删除图片
	 * @param id
	 */
	public void deleteImg(Integer id){
		Transaction transaction = HibernateSessionFactory.getSession().beginTransaction();
		Imgs imgs = new Imgs();
		imgs.setImgid(id);
		this.imgsDAO.delete(imgs);
		transaction.commit();
	}
}
