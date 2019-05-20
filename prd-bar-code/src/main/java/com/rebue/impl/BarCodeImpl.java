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
			String code = classCode + StrUtils.padLeft(String.valueOf(i++), 3, '0');
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
		}
		return list;
	}

	@Test
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
