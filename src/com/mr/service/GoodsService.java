package com.mr.service;

import org.hibernate.Transaction;

import com.mr.eneity.GoodsSearch;
import com.mr.eneity.Page;
import com.mr.pojoxml.Goods;
import com.mr.pojoxml.GoodsDAO;
import com.mr.util.HibernateSessionFactory;

public class GoodsService {
	GoodsDAO goodsDAO = new GoodsDAO();

	public GoodsService() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public GoodsService(GoodsDAO goodsDAO) {
		super();
		this.goodsDAO = goodsDAO;
	}
	/**
	 * 商品条件查找
	 * @param goodsSearch
	 * @param page
	 * @return
	 */
	public Page<Goods> findGoodsByGoodsSearch(GoodsSearch goodsSearch,Page<Goods> page){
		HibernateSessionFactory.getSession();
		if(goodsSearch.getGname()!=null&&!"".equals(goodsSearch.getGname())){
			goodsSearch.setGname("%"+goodsSearch.getGname()+"%");
		}
		return this.goodsDAO.findGoodsByGoodssearch(goodsSearch, page);
	}
	/**
	 * 根据id查找商品
	 */
	public Goods findGoodsByID(Long gid){
		HibernateSessionFactory.getSession();
		return goodsDAO.findById(gid);
	}
	/**
	 * 修改商品信息
	 * @param goods
	 */
	public void modifyGoods(Goods goods){
		Goods goods2 = this.goodsDAO.findById(goods.getGid());
		goods2.setGlocation(goods.getGlocation());
		goods2.setGname(goods.getGname());
		goods2.setGnum(goods.getGnum());
		goods2.setGprice(goods.getGprice());
		goods2.setGprocedate(goods.getGprocedate());
		goods2.setGtype(goods.getGtype());
		goods2.setImgses(goods.getImgses());
		Transaction transaction =HibernateSessionFactory.getSession().beginTransaction();
		try{
			this.goodsDAO.attachDirty(goods2);
			transaction.commit();
			System.out.println("修改成功");
		}catch(Exception e){
			e.printStackTrace();
			transaction.rollback();
			System.out.println("修改失败");
		}
	}
	/**
	 * 删除商品信息
	 * @param gid
	 */
	public void deleteGoods(Long gid){
		Transaction transaction = HibernateSessionFactory.getSession().beginTransaction();
		Goods goods = this.goodsDAO.findById(gid);
		this.goodsDAO.delete(goods);
		transaction.commit();
	}
	/**
	 * 添加商品信息
	 * @param goods
	 */
	public Long addGoods(Goods goods){
		Transaction transaction = HibernateSessionFactory.getSession().beginTransaction();
		Long gid = this.goodsDAO.save(goods);
		transaction.commit();
		return gid;
	}
}
