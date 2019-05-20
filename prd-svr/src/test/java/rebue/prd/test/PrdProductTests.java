package rebue.prd.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import rebue.prd.mo.PrdProductMo;
import rebue.prd.to.AddProductTo;
import rebue.robotech.dic.ResultDic;
import rebue.robotech.ro.IdRo;
import rebue.robotech.ro.Ro;
import rebue.wheel.OkhttpUtils;
import rebue.wheel.RandomEx;
import rebue.wheel.StrUtils;

/**
 * 产品
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
public class PrdProductTests {

	private final String hostUrl = "http://127.0.0.1:9009";

	/**
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	private final ObjectMapper _objectMapper = new ObjectMapper();

	/**
	 * 测试基本的增删改查
	 *
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
//	@Test
	public void testCrud() throws IOException, ReflectiveOperationException {
		PrdProductMo mo = null;
		for (int i = 0; i < 20; i++) {
			mo = (PrdProductMo) RandomEx.randomPojo(PrdProductMo.class);
			mo.setId(null);
			System.out.println("添加产品的参数为：" + mo);
			final String addResult = OkhttpUtils.postByJsonParams(hostUrl + "/prd/product", mo);
			System.out.println("添加产品的返回值为：" + addResult);
			final IdRo idRo = _objectMapper.readValue(addResult, IdRo.class);
			System.out.println(idRo);
			Assert.assertEquals(ResultDic.SUCCESS, idRo.getResult());
			mo.setId(Long.valueOf(idRo.getId()));
		}
		final String listResult = OkhttpUtils.get(hostUrl + "/prd/product?pageNum=1&pageSize=5");
		System.out.println("查询产品的返回值为：" + listResult);
		System.out.println("获取单个产品的参数为：" + mo.getId());
		final String getByIdResult = OkhttpUtils.get(hostUrl + "/prd/product/getbyid?id=" + mo.getId());
		System.out.println("获取单个产品的返回值为：" + getByIdResult);
		System.out.println("修改产品的参数为：" + mo);
		final String modifyResult = OkhttpUtils.putByJsonParams(hostUrl + "/prd/product", mo);
		System.out.println("修改积分日志类型的返回值为：" + modifyResult);
		final Ro modifyRo = _objectMapper.readValue(modifyResult, Ro.class);
		System.out.println(modifyRo);
		Assert.assertEquals(ResultDic.SUCCESS, modifyRo.getResult());
		System.out.println("删除产品的参数为：" + mo.getId());
		final String deleteResult = OkhttpUtils.delete(hostUrl + "/prd/product?id=" + mo.getId());
		System.out.println("删除产品的返回值为：" + deleteResult);
		final Ro deleteRo = _objectMapper.readValue(deleteResult, Ro.class);
		System.out.println(deleteRo);
		Assert.assertEquals(ResultDic.SUCCESS, deleteRo.getResult());
	}

	public void addProductTest() {
		AddProductTo to = new AddProductTo();
		to.setCategoryId(603729859054665728L);
		to.setProductName("咸鸭蛋");
//    	to.set
	}

	@Test
	public void saveFileTest() throws IOException {
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("content", "<p>士大夫撒旦法师从vv</p><p></p><p></p><p></p><p></p>");
//		map.put("fileName", "560761261214793728");
//		map.put("fileType", "html");
//		String str = OkhttpUtils.postByJsonParams("http://192.168.1.222:20180/ise/save", map);
//		System.out.println(str);

		String str = OkhttpUtils.get(
				"http://127.0.0.1:20180/ise/read?filePath=goodsDetail/2019/05/20/09/27/3A7BC247A90F4E16B5B12F6A7F175D90.html");
		System.out.println(str);
	}
}
