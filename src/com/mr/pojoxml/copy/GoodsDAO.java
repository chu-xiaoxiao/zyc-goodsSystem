package com.mr.pojoxml.copy;

import java.util.Date;
import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mr.eneity.GoodsSearch;
import com.mr.eneity.Page;

/**
 * A data access object (DAO) providing persistence and search support for Goods
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.mr.pojoxml.Goods
 * @author MyEclipse Persistence Tools
 */
public class GoodsDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(GoodsDAO.class);
	// property constants
	public static final String GNAME = "gname";
	public static final String GNUM = "gnum";
	public static final String GPRICE = "gprice";
	public static final String GLOCATION = "glocation";

	public void save(Goods transientInstance) {
		log.debug("saving Goods instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Goods persistentInstance) {
		log.debug("deleting Goods instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Goods findById(java.lang.Short id) {
		log.debug("getting Goods instance with id: " + id);
		try {
			Goods instance = (Goods) getSession().get("com.zyc.pojoxml.Goods",
					id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Goods instance) {
		log.debug("finding Goods instance by example");
		try {
			List results = getSession().createCriteria("com.zyc.pojoxml.Goods")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Goods instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Goods as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByGname(Object gname) {
		return findByProperty(GNAME, gname);
	}

	public List findByGnum(Object gnum) {
		return findByProperty(GNUM, gnum);
	}

	public List findByGprice(Object gprice) {
		return findByProperty(GPRICE, gprice);
	}

	public List findByGlocation(Object glocation) {
		return findByProperty(GLOCATION, glocation);
	}

	public List findAll() {
		log.debug("finding all Goods instances");
		try {
			String queryString = "from Goods";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Goods merge(Goods detachedInstance) {
		log.debug("merging Goods instance");
		try {
			Goods result = (Goods) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Goods instance) {
		log.debug("attaching dirty Goods instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Goods instance) {
		log.debug("attaching clean Goods instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	/**
	 * @param goodsSearch
	 * @param page
	 * @return
	 */
	public Page<Goods> findGoodsByGoodssearch(GoodsSearch goodsSearch,Page<Goods> page){
		try{
			StringBuffer hql = new	StringBuffer("from Goods where 1=1");
			if(goodsSearch.getGname()!=null){
				hql.append(" and gname like :gname");
			}
			if(goodsSearch.getMax()!=null){
				hql.append(" and gprice < :max");
			}
			if(goodsSearch.getMax()!=null){
				hql.append(" and gprice > :min");
			}
			Query query = getSession().createQuery(hql.toString());
			if (page!=null) {
				if (page.getSieze() != null) {
					query.setMaxResults(page.getSieze());
				} else {
					query.setMaxResults(5);
				}
				if (page.getCurrentPage() != null) {
					query.setFirstResult((page.getCurrentPage() - 1)
							* page.getSieze());
				} else {
					query.setFetchSize(0);
				}
			}
			query.setProperties(goodsSearch);
			page.setLists(query.list());
			page.setCurrentPage(page.getCurrentPage());
			page.setSieze(page.getSieze());
			page.setAllPage((allResult(goodsSearch).intValue()+page.getSieze()-1)/page.getSieze());
		}catch(RuntimeException e){
			e.printStackTrace();
		}
		return page;
	}
	/**
	 * @param goodsSearch
	 * @return
	 */
	public Long allResult(GoodsSearch goodsSearch){
		StringBuffer hql = new	StringBuffer("select count (*) from Goods where 1=1");
		if(goodsSearch.getGname()!=null){
			hql.append(" and gname like :gname");
		}
		if(goodsSearch.getMax()!=null){
			hql.append(" and gprice <= :max");
		}
		if(goodsSearch.getMax()!=null){
			hql.append(" and gprice >= :min");
		}
		Query query = getSession().createQuery(hql.toString());
		query.setProperties(goodsSearch);
		Long long1 = (Long) query.uniqueResult();
		return long1;
	}
}