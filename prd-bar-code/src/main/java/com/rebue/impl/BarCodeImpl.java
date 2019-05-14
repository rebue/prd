package com.rebue.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rebue.ro.MxnzpRo;

import rebue.wheel.DbUtils;
import rebue.wheel.Ean13Utils;
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

//	@Test
	public void test02() throws SQLException {
		// 连接数据库
		Connection msSqlConn = DbUtils.getMsSqlConn();
		List<Map<String, Object>> list = categoryTree(msSqlConn, "0", "");
		System.out.println(String.valueOf(list));
	}

	public List<Map<String, Object>> categoryTree(Connection msSqlConn, String parentId, String classCode)
			throws SQLException {
		// 查询分类信息
		String sql = "SELECT * FROM prd.vbl_goods_class where parentId=" + parentId;
		PreparedStatement statement = msSqlConn.prepareStatement(sql);
		ResultSet result = statement.executeQuery();

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		long i = 0;
		while (result.next()) {
			// 自动生成id
			IdWorker3 idWorker = new IdWorker3();
			// 分类ID
			String classId = result.getString(1);
			// 分类名称
			String className = result.getString(3);
			// 分类编码
			String code = classCode + StrUtils.padLeft(String.valueOf(i++), 2, '0');
			// 子分类
			List<Map<String, Object>> categoryTree = categoryTree(msSqlConn, classId, code);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("classId", classId);
			map.put("className", className);
			map.put("code", code);
			map.put("sonClass", categoryTree);
			list.add(map);

			// 新分类id
			Long newClassId = idWorker.getId();
			String addSql = "INSERT INTO prd.PRD_PRODUCT_CATEGORY(ID,NAME,CODE,IS_ENABLED,OP_ID,CREATE_TIME)VALUES("
					+ newClassId + ",'" + className + "','" + code + "',true,193201,'2019-05-13 16:13:46')";
			System.out.println(addSql);
			PreparedStatement statementAdd = msSqlConn.prepareStatement(addSql);
			int addClassResult = statementAdd.executeUpdate();
			if (addClassResult != 1) {
				break;
			}

			// 根据分类id查询商品信息
			String selectGoodsSql = "SELECT * FROM prd.vbl_goods where classId='" + classId
					+ "' and goodsName not like '%测试%'";
			PreparedStatement goodsStatement = msSqlConn.prepareStatement(selectGoodsSql);
			ResultSet goodsResults = goodsStatement.executeQuery();
			while (goodsResults.next()) {
				// 商品id
				String goodsId = goodsResults.getString(1);
				// 商品名称
				String goodsName = goodsResults.getString(6);
				// 该商品名称包含了品牌和商品标题
				String[] goods = goodsName.split("】");
				// 品牌名称
				String brandName = goods[0].substring(1, goods[0].length());
				// 新商品id
				Long newGoodsId = idWorker.getId();
				String addGoodsSql = "INSERT INTO prd.PRD_PRODUCT(ID,CATEGORY_ID,PRODUCT_NAME,IS_ENABLED,MANUFACTURER,BRAND,PRODUCT_DETAIL_PATH,OP_ID,CREATE_TIME)VALUES("
						+ newGoodsId + ", " + newClassId + ", '" + goodsName + "',true,null,'" + brandName
						+ "',null,193201,'2019-05-13 16:30:25')";
				PreparedStatement addGoodsStatement = msSqlConn.prepareStatement(addGoodsSql);
				int addGoodsResult = addGoodsStatement.executeUpdate();
				if (addGoodsResult != 1) {
					break;
				}

				// 根据商品id查询规格信息
				String selectSkuSql = "SELECT * FROM prd.vbl_goods_sku where goodsId=" + goodsId;
				PreparedStatement skuStatement = msSqlConn.prepareStatement(selectSkuSql);
				ResultSet skuResults = skuStatement.executeQuery();
				while (skuResults.next()) {
					
				}
			}
		}
		return list;
	}

}
