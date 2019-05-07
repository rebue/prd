package rebue.prd.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;
import rebue.prd.mo.PrdProductSpecMo;
import rebue.robotech.dic.ResultDic;
import rebue.robotech.ro.IdRo;
import rebue.robotech.ro.Ro;
import rebue.wheel.OkhttpUtils;
import rebue.wheel.RandomEx;

/**
 * 产品规格
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
public class PrdProductSpecTests {

    private final String hostUrl = "http://127.0.0.1:9009";

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private final ObjectMapper _objectMapper = new ObjectMapper();

    /**
     *  测试基本的增删改查
     *
     *  @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Test
    public void testCrud() throws IOException, ReflectiveOperationException {
        PrdProductSpecMo mo = null;
        for (int i = 0; i < 20; i++) {
            mo = (PrdProductSpecMo) RandomEx.randomPojo(PrdProductSpecMo.class);
            mo.setId(null);
            System.out.println("添加产品规格的参数为：" + mo);
            final String addResult = OkhttpUtils.postByJsonParams(hostUrl + "/prd/productspec", mo);
            System.out.println("添加产品规格的返回值为：" + addResult);
            final IdRo idRo = _objectMapper.readValue(addResult, IdRo.class);
            System.out.println(idRo);
            Assert.assertEquals(ResultDic.SUCCESS, idRo.getResult());
            mo.setId(Long.valueOf(idRo.getId()));
        }
        final String listResult = OkhttpUtils.get(hostUrl + "/prd/productspec?pageNum=1&pageSize=5");
        System.out.println("查询产品规格的返回值为：" + listResult);
        System.out.println("获取单个产品规格的参数为：" + mo.getId());
        final String getByIdResult = OkhttpUtils.get(hostUrl + "/prd/productspec/getbyid?id=" + mo.getId());
        System.out.println("获取单个产品规格的返回值为：" + getByIdResult);
        System.out.println("修改产品规格的参数为：" + mo);
        final String modifyResult = OkhttpUtils.putByJsonParams(hostUrl + "/prd/productspec", mo);
        System.out.println("修改积分日志类型的返回值为：" + modifyResult);
        final Ro modifyRo = _objectMapper.readValue(modifyResult, Ro.class);
        System.out.println(modifyRo);
        Assert.assertEquals(ResultDic.SUCCESS, modifyRo.getResult());
        System.out.println("删除产品规格的参数为：" + mo.getId());
        final String deleteResult = OkhttpUtils.delete(hostUrl + "/prd/productspec?id=" + mo.getId());
        System.out.println("删除产品规格的返回值为：" + deleteResult);
        final Ro deleteRo = _objectMapper.readValue(deleteResult, Ro.class);
        System.out.println(deleteRo);
        Assert.assertEquals(ResultDic.SUCCESS, deleteRo.getResult());
    }
}
