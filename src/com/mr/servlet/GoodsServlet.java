package com.mr.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.mr.eneity.GoodsSearch;
import com.mr.eneity.Page;
import com.mr.pojoxml.Goods;
import com.mr.pojoxml.Gtype;
import com.mr.service.GoodsService;
import com.mr.service.GtypeService;
import com.mr.service.ImgService;

@WebServlet(urlPatterns = { "/goods/GoodsList.action","/goods/GoodsList",
		"/goods/modifyGoods.action", "/goods/modifyAction.action",
		"/goods/update.action","/goods/xiangxi","/goods/deleteGoods.action",
		"/goods/addGoods.action","/goods/pageaddgoods"})
@MultipartConfig
public class GoodsServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public GoodsServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = request.getRequestURI();
		if (url.contains("GoodsList")) {
			this.list(request, response);
		}
		if (url.contains("modifyGoods")) {
			this.modifyGoods(request, response);
		}
		if (url.contains("modifyAction")) {
			this.modifuAction(request, response);
		}
		if (url.contains("update")) {
			this.updateImg(request, response);
		}
		if(url.contains("xiangxi")){
			this.xiangxi(request, response);
		}
		if(url.contains("deleteGoods")){
			this.deleteGOods(request, response);
		}
		if(url.contains("addGoods")){
			this.addGoods(request, response);
		}
		if(url.contains("pageaddgoods")){
			this.addGoodsPage(request, response);
		}
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

	/**
	 * 根据条件查询商品并返回page类型结果
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//业务层
		GoodsService goodsService = new GoodsService();
		GtypeService gtypeService = new GtypeService();
		// 处理前台表单数据
		Double goodsGpriceMin = null;
		Double goodsGpriceMax = null;
		Gtype gtype = null;
		Integer imgsCount = null;
		String goodsName = null;
		if (request.getParameter("goodsName") != null
				&& !"".equals(request.getParameter("goodsName"))) {
			goodsName = request
					.getParameter("goodsName");
		}
		if (request.getParameter("GoodsGpriceMin") != null
				&& !"".equals(request.getParameter("GoodsGpriceMin"))) {
			goodsGpriceMin = Double.parseDouble(request
					.getParameter("GoodsGpriceMin"));
		}
		if (request.getParameter("GoodsGpriceMax") != null
				&& !"".equals(request.getParameter("GoodsGpriceMax"))) {
			goodsGpriceMax = Double.parseDouble(request
					.getParameter("GoodsGpriceMax"));
		}
		if (request.getParameter("Gtype") != null
				&& !"".equals(request.getParameter("Gtype"))) {
			gtype = gtypeService.findGtypeById(Short.parseShort(request
					.getParameter("Gtype")));
		}
		if (request.getParameter("imgsCount") != null
				&& !"".equals(request.getParameter("imgsCount"))) {
			imgsCount = Integer.parseInt(request.getParameter("imgsCount"));
		}else{
			imgsCount = null;
		}
		//构造商品查询类
		GoodsSearch goodsSearch = new GoodsSearch(goodsName, goodsGpriceMax,
				goodsGpriceMin, gtype,imgsCount);
		// 构造分页类
		Integer currentPage = null;
		Integer sieze = null;
		if (request.getParameter("currentPage") == null) {
			currentPage = 1;
		} else {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		if (request.getParameter("sieze") == null) {
			sieze = 5;
		} else {
			sieze = Integer.parseInt(request.getParameter("sieze"));
		}
		Page<Goods> page = new Page<Goods>(currentPage, sieze, null, null);
		// 调用service
		page = goodsService.findGoodsByGoodsSearch(goodsSearch, page);
		request.setAttribute("page", page);
		request.setAttribute("goodsName", goodsName);
		request.setAttribute("goodsGpriceMin", goodsGpriceMin);
		request.setAttribute("goodsGpriceMax", goodsGpriceMax);
		request.setAttribute("Gtypes", gtypeService.findAllType());
		request.setAttribute("selectGtype", gtype);
		request.setAttribute("imgsCount", imgsCount);
		request.getRequestDispatcher("list.jsp").forward(request, response);
	}

	/**
	 * 根据id跳转至修改商品页面
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void modifyGoods(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Long long1 = Long.parseLong(request.getParameter("gid"));
		GoodsService goodsService = new GoodsService();
		GtypeService gtypeService = new GtypeService();
		Goods goods = goodsService.findGoodsByID(long1);
		request.setAttribute("Gtypes", gtypeService.findAllType());
		request.setAttribute("goods", goods);
		request.getRequestDispatcher("modify.jsp").forward(request, response);
	}

	/**
	 * 修改商品
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void modifuAction(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Long gid = Long.parseLong(request.getParameter("gid"));
		GoodsService goodsService = new GoodsService();
		String gname = null;
		Short gnum = null;
		Double gprice = null;
		String goodLocation = null;
		if (request.getParameter("gname") != null
				&& !"".equals(request.getParameter("gname"))) {
			gname = request.getParameter("gname");
		}
		if (request.getParameter("gnum") != null
				&& !"".equals(request.getParameter("gnum"))) {
			gnum = Short.parseShort(request.getParameter("gnum"));
		}
		if (request.getParameter("gprice") != null
				&& !"".equals(request.getParameter("gprice"))) {
			gprice = Double.parseDouble(request.getParameter("gprice"));
		}
		if (request.getParameter("glocation") != null
				&& !"".equals(request.getParameter("glocation"))) {
			goodLocation = request.getParameter("glocation");
		}
		Gtype gtype = new GtypeService().findGtypeById(Short.parseShort(request
				.getParameter("gtype")));
		Goods goods = new Goods();
		goods.setGid(gid);
		goods.setGname(gname);
		goods.setGnum(gnum);
		goods.setGprice(gprice);
		goods.setGtype(gtype);
		goods.setGlocation(goodLocation);
		goodsService.modifyGoods(goods);
		request.getRequestDispatcher("list.jsp").forward(request, response);
	}

	/**
	 * 上传图片
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void updateImg(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Collection<Part> parts = request.getParts();
		Long gid = Long.parseLong(request.getParameter("gid"));
		ImgService imgService = new ImgService();
		GoodsService goodsService = new GoodsService();
		GtypeService gtypeService = new GtypeService();
		PrintWriter out = response.getWriter();
		for (Part temp : parts) {
			if (temp.getName().contains("goodsImg")) {
				try {
					String tempString = temp.getHeader("Content-Disposition");
					tempString = tempString.substring(
							tempString.lastIndexOf(";") + 12,
							tempString.length() - 1);
					String[] fileStrings = tempString.split("\\.");
					String filename = fileStrings[0];
					String filehouzhui = fileStrings[1];
					String uuid = UUID.randomUUID().toString();
					String savePath = request.getServletContext().getRealPath(
							"/imgs/")
							+ uuid + "." + filehouzhui;
					temp.write(savePath);
					imgService.uploadImgForGoods("/imgs/" + uuid + "."
							+ filehouzhui, gid);
					out.print(savePath);
				} catch (Exception e) {
				}
			}
		}
		Goods goods = goodsService.findGoodsByID(gid);
		request.setAttribute("goods", goods);
		request.setAttribute("Gtypes", gtypeService.findAllType());
		request.getRequestDispatcher("modify.jsp").forward(request, response);
	}
	/**
	 * 显示商品详细信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void xiangxi(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Long gid = Long.parseLong(request.getParameter("gid"));
		GoodsService goodsService = new GoodsService();
		Goods goods = goodsService.findGoodsByID(gid);
		request.setAttribute("goods", goods);
		request.getRequestDispatcher("xiangxi.jsp").forward(request, response);
	}
	/**
	 * 删除商品信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void deleteGOods(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Long gid = Long.parseLong(request.getParameter("gid"));
		GoodsService goodsService = new GoodsService();
		goodsService.deleteGoods(gid);
		request.getRequestDispatcher("list.jsp").forward(request, response);
	}
	/**
	 * 添加商品
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void addGoods(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		GoodsService goodsService = new GoodsService();
		String gname = null;
		Short gnum = null;
		Double gprice = null;
		String goodLocation = null;
		if (request.getParameter("gname") != null
				&& !"".equals(request.getParameter("gname"))) {
			gname = request.getParameter("gname");
		}
		if (request.getParameter("gnum") != null
				&& !"".equals(request.getParameter("gnum"))) {
			gnum = Short.parseShort(request.getParameter("gnum"));
		}
		if (request.getParameter("gprice") != null
				&& !"".equals(request.getParameter("gprice"))) {
			gprice = Double.parseDouble(request.getParameter("gprice"));
		}
		if (request.getParameter("glocation") != null
				&& !"".equals(request.getParameter("glocation"))) {
			goodLocation = request.getParameter("glocation");
		}
		Gtype gtype = new GtypeService().findGtypeById(Short.parseShort(request
				.getParameter("gtype")));
		Goods goods = new Goods();
		goods.setGname(gname);
		goods.setGnum(gnum);
		goods.setGprice(gprice);
		goods.setGtype(gtype);
		goods.setGlocation(goodLocation);
		Long gid = goodsService.addGoods(goods);
		Goods goods2 = goodsService.findGoodsByID(gid);
		request.setAttribute("goods", goods2);
		request.getRequestDispatcher("xiangxi.jsp").forward(request, response);
	}
	/**
	 * 跳转添加商品页面并初始化
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void addGoodsPage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		GtypeService gtypeService = new GtypeService();
		request.setAttribute("Gtypes", gtypeService.findAllType());
		request.getRequestDispatcher("addgoods.jsp").forward(request, response);
	}
}
