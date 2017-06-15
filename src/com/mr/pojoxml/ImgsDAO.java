package com.mr.pojoxml;

import java.util.List;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for Imgs
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.mr.pojoxml.Imgs
 * @author MyEclipse Persistence Tools
 */
public class ImgsDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(ImgsDAO.class);
	// property constants
	public static final String IMGPATH = "imgpath";

	public void save(Imgs transientInstance) {
		log.debug("saving Imgs instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Imgs persistentInstance) {
		log.debug("deleting Imgs instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Imgs findById(java.lang.Integer id) {
		log.debug("getting Imgs instance with id: " + id);
		try {
			Imgs instance = (Imgs) getSession().get("com.mr.pojoxml.Imgs", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Imgs instance) {
		log.debug("finding Imgs instance by example");
		try {
			List results = getSession().createCriteria("com.mr.pojoxml.Imgs")
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
		log.debug("finding Imgs instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Imgs as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByImgpath(Object imgpath) {
		return findByProperty(IMGPATH, imgpath);
	}

	public List findAll() {
		log.debug("finding all Imgs instances");
		try {
			String queryString = "from Imgs";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Imgs merge(Imgs detachedInstance) {
		log.debug("merging Imgs instance");
		try {
			Imgs result = (Imgs) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Imgs instance) {
		log.debug("attaching dirty Imgs instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Imgs instance) {
		log.debug("attaching clean Imgs instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}