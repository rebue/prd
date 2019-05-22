package com.rebue.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import rebue.prd.mo.PrdProductPicMo;
import rebue.prd.to.AddProductSpecTo;
import rebue.prd.to.AddProductTo;
import rebue.wheel.DbUtils;
import rebue.wheel.OkhttpUtils;
import rebue.wheel.StrUtils;
import rebue.wheel.idworker.IdWorker3;

public class BarCodeImpl {

	private final ObjectMapper _objectMapper = new ObjectMapper();

//	@Test
	public void test01() throws SQLException {
		// 连接数据库
		Connection msSqlConn = DbUtils.getMsSqlConn();

		// 查询除测试之外的商品
		String sql = "SELECT * FROM prd.vbl_goods where goodsName not like '%测试%'";
		PreparedStatement prepareStatement = msSqlConn.prepareStatement(sql);
		ResultSet executeQuery = prepareStatement.executeQuery();
		while (executeQuery.next()) {
			// 自动生成id
			IdWorker3 idWorker = new IdWorker3();
			// 商品名称
			String goodsName = executeQuery.getString(6);
			// 该商品名称包含了品牌和商品标题
			String[] goods = goodsName.split("】");
			// 品牌名称
			String brandName = goods[0].substring(1, goods[0].length());

			String addGoodsSql = "INSERT INTO prd.PRD_PRODUCT(ID,CATEGORY_ID,PRODUCT_NAME,IS_ENABLED,MANUFACTURER,BRAND,PRODUCT_DETAIL_PATH,OP_ID,CREATE_TIME)VALUES("
					+ idWorker.getId() + ",)";
		}
	}

	@Test
	public void test02() throws SQLException, IOException {
		// 连接数据库
		Connection msSqlConn = DbUtils.getMsSqlConn();
		List<Map<String, Object>> list = categoryTree(msSqlConn, "0", "", "", "");
		System.out.println(String.valueOf(list));
	}

	public List<Map<String, Object>> categoryTree(Connection msSqlConn, String parentId, String classCode,
			String fullName, String classIds) throws SQLException, IOException {
		// 查询分类信息
		String sql = "SELECT * FROM prd.vbl_goods_class where parentId=" + parentId;
		PreparedStatement statement = msSqlConn.prepareStatement(sql);
		ResultSet result = statement.executeQuery();

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		long i = 0;
		while (result.next()) {
			// 自动生成id
			IdWorker3 idWorker = new IdWorker3();
			// 新分类id
			Long newClassId = idWorker.getId();

			// 分类ID
			String classId = result.getString(1);
			// 分类名称
			String className = result.getString(3);
			// 分类编码
			String code = classCode + StrUtils.padLeft(String.valueOf(i++), 3, '0');
			String newFullName = "";
			if (fullName.equals("") || fullName == null || fullName.equals("null")) {
				newFullName = className;
			} else {
				newFullName = fullName + "-" + className;
			}
			System.out.println(newFullName);
			String classIdss = "";
			if (classIds.equals("") || classIds == null || classIds.equals("null")) {
				classIdss = classId;
			} else {
				classIdss = classIds + "_" + classId;
			}
			System.out.println(classIdss);
			// 子分类
			List<Map<String, Object>> categoryTree = categoryTree(msSqlConn, classId, code, newFullName, classIdss);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("classId", classId);
			map.put("className", className);
			map.put("code", code);
			map.put("sonClass", categoryTree);
			map.put("fullName", newFullName);
			map.put("classIdss", classIdss);
			list.add(map);

			String addSql = "INSERT INTO prd.PRD_PRODUCT_CATEGORY(ID,NAME,CODE,IS_ENABLED,OP_ID,CREATE_TIME,FULL_NAME)VALUES("
					+ newClassId + ",'" + className + "','" + code + "',true,193201,'2019-05-13 16:13:46','"
					+ newFullName + "')";
			PreparedStatement statementAdd = msSqlConn.prepareStatement(addSql);
			int addClassResult = statementAdd.executeUpdate();
			if (addClassResult != 1) {
				break;
			}

			// 查询商品信息
			String selectGoodsSql = "SELECT * FROM prd.vbl_goods where classId = '" + classIdss + "'";
			PreparedStatement goodsStatement = msSqlConn.prepareStatement(selectGoodsSql);
			ResultSet goodsResult = goodsStatement.executeQuery();
			while (goodsResult.next()) {
				// 商品id
				String goodsId = goodsResult.getString(1);
				// 品牌id
				String brandId = goodsResult.getString(4);
				// 商品名称
				String goodsName = goodsResult.getString(6);
				// 商品主图
				String goodsQsmm = goodsResult.getString(8);

				// 查询品牌信息
				String brandSql = "SELECT * FROM prd.vbl_goods_brand where brandId=" + brandId;
				PreparedStatement brandStatement = msSqlConn.prepareStatement(brandSql);
				ResultSet brandResult = brandStatement.executeQuery();
				// 品牌名称
				String brandName = "";
				while (brandResult.next()) {
					brandName = brandResult.getString(2);
				}

				// 查询sku信息
				String skuSql = "SELECT * FROM prd.vbl_goods_sku where goodsId=" + goodsId;
				PreparedStatement skuStatement = msSqlConn.prepareStatement(skuSql);
				ResultSet skuResult = skuStatement.executeQuery();
				List<AddProductSpecTo> skuList = new ArrayList<AddProductSpecTo>();
				while (skuResult.next()) {
					// skuId
					String skuId = skuResult.getString(1);
					// sku名称
					String skuName = skuResult.getString(4);
					// 单位Id
					String unitId = skuResult.getString(8).split(",")[0];
					// 单位名称
					String unitName = "";
					// 规格条码
					String barcode = "";
					// 市场价格
					BigDecimal marketPrice = BigDecimal.ZERO;

					// 查询规格条形码
					String barcodeSql = "SELECT * FROM prd.vbl_goods_barcode where skuId=" + skuId + " and goodsId="
							+ goodsId;
					PreparedStatement barcodeStatement = msSqlConn.prepareStatement(barcodeSql);
					ResultSet barcodeResult = barcodeStatement.executeQuery();
					while (barcodeResult.next()) {
						barcode = barcodeResult.getString(1);
					}

					// 查询单位名称
					String unitSql = "SELECT * FROM prd.vbl_goods_unit where id=" + unitId;
					PreparedStatement unitStatement = msSqlConn.prepareStatement(unitSql);
					ResultSet unitResult = unitStatement.executeQuery();
					while (unitResult.next()) {
						unitName = unitResult.getString(3);
					}

					// 查询规格价格
					String priceSql = "SELECT * FROM prd.vbl_goods_price where goodsId=" + goodsId + " and skuId="
							+ skuId;
					PreparedStatement priceStatement = msSqlConn.prepareStatement(priceSql);
					ResultSet priceResult = priceStatement.executeQuery();
					while (priceResult.next()) {
						marketPrice = new BigDecimal(priceResult.getString(7)).divide(new BigDecimal(100));
					}
					AddProductSpecTo addProductSpecTo = new AddProductSpecTo();
					addProductSpecTo.setName(skuName);
					addProductSpecTo.setUnit(unitName);
					addProductSpecTo.setMarketPrice(marketPrice);
					addProductSpecTo.setCode(barcode);
					skuList.add(addProductSpecTo);
				}

				// 查询商品图片
				String picSql = "SELECT * FROM prd.vbl_goods_photo where goodsId=" + goodsId;
				PreparedStatement picStatement = msSqlConn.prepareStatement(picSql);
				ResultSet picResult = picStatement.executeQuery();
				List<PrdProductPicMo> pics = new ArrayList<PrdProductPicMo>();
				while (picResult.next()) {
					PrdProductPicMo productPicMo = new PrdProductPicMo();
					productPicMo.setPicPath("/oldGoods/" + picResult.getString(4));
					productPicMo.setPicType((byte) 0);
					pics.add(productPicMo);
				}
				PrdProductPicMo productPicMo = new PrdProductPicMo();
				productPicMo.setPicPath("/oldGoods/" + goodsQsmm);
				productPicMo.setPicType((byte) 1);
				pics.add(productPicMo);

				// 查询商品详情
				String detailSql = "SELECT * FROM prd.vbl_goods_detail where goodsId=" + goodsId;
				PreparedStatement detailStatement = msSqlConn.prepareStatement(detailSql);
				ResultSet detailResult = detailStatement.executeQuery();
				// 商品详情
				String goodsDetail = "";
				while (detailResult.next()) {
					String detail = detailResult.getString(2);
					if (detail.contains("http://img.wboly.com/details")) {
						detail = detail.replaceAll("http://img.wboly.com/details", "/ise-svr/files/productDetail");
					}
					goodsDetail = detail;
				}

				// 查询厂家信息
				String factorySql = "SELECT * FROM prd.vbl_goods_produce where goodsId=" + goodsId;
				PreparedStatement factoryStatement = msSqlConn.prepareStatement(factorySql);
				ResultSet factoryResult = factoryStatement.executeQuery();
				String manufacturer = "";
				while (factoryResult.next()) {
					manufacturer = factoryResult.getString(4);
				}

				AddProductTo addProductTo = new AddProductTo();
				addProductTo.setCategoryId(newClassId);
				addProductTo.setProductName(goodsName);
				addProductTo.setBrand(brandName);
				addProductTo.setSpec(skuList);
				addProductTo.setPics(pics);
				addProductTo.setProductDetail(goodsDetail);
				addProductTo.setManufacturer(manufacturer);
				System.out.println("添加产品信息的参数为：" + addProductTo);
				String addProductResult = OkhttpUtils.postByJsonParams("http://127.0.0.1:20195/prd/product",
						addProductTo);
				System.out.println("添加产品信息的返回值为：" + addProductResult);
			}
		}
		return list;
	}

//	@Test
	public void test03() throws SQLException {
		// 连接数据库
		Connection msSqlConn = DbUtils.getMsSqlConn();
		// 查询顶级分类
		String sql = "SELECT * FROM prd.PRD_PRODUCT_CATEGORY where code like '___'";
		PreparedStatement prepareStatement = msSqlConn.prepareStatement(sql);
		ResultSet result = prepareStatement.executeQuery();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		while (result.next()) {
			String code = result.getString(3);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", result.getString(1));
			map.put("name", result.getString(2));
			map.put("code", code);
			map.put("isEnabled", result.getString(4));
			map.put("opId", result.getString(5));
			map.put("createTime", result.getString(6));
			List<Map<String, Object>> classTree = classTree(code);
			if (classTree.size() != 0) {
				map.put("list", classTree);
			}
			list.add(map);
			System.out.println(String.valueOf(list));
		}
	}

	public List<Map<String, Object>> classTree(String code) throws SQLException {
		Connection msSqlConn = DbUtils.getMsSqlConn();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "SELECT * FROM prd.PRD_PRODUCT_CATEGORY where code like '" + code + "___'";
		PreparedStatement prepareStatement = msSqlConn.prepareStatement(sql);
		ResultSet result = prepareStatement.executeQuery();
		while (result.next()) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", result.getString(1));
			map.put("name", result.getString(2));
			map.put("code", result.getString(3));
			map.put("isEnabled", result.getString(4));
			map.put("opId", result.getString(5));
			map.put("createTime", result.getString(6));
			List<Map<String, Object>> classTree = classTree(result.getString(3));
			if (classTree.size() != 0) {
				map.put("list", classTree);
			}
			list.add(map);
			System.out.println(list.toString());
		}
		return list;
	}
}
