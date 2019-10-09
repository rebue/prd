package rebue.prd.svc.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.dozermapper.core.Mapper;

import rebue.onl.ro.OnlOnlineSpecInfoRo;
import rebue.onl.svr.feign.OnlOnlineSpecSvc;
import rebue.prd.dao.PrdProductSpecCodeDao;
import rebue.prd.jo.PrdProductSpecCodeJo;
import rebue.prd.mapper.PrdProductSpecCodeMapper;
import rebue.prd.mo.PrdProductSpecCodeMo;
import rebue.prd.mo.PrdProductSpecMo;
import rebue.prd.ro.BarcodeRo;
import rebue.prd.ro.PrdOnlineDetailRo;
import rebue.prd.ro.ProductDetailRo;
import rebue.prd.svc.PrdProductSpecCodeSvc;
import rebue.prd.svc.PrdProductSpecSvc;
import rebue.robotech.svc.impl.BaseSvcImpl;

/**
 * 产品规格编码
 *
 * 在单独使用不带任何参数的 @Transactional 注释时，
 * propagation(传播模式)=REQUIRED，readOnly=false，
 * isolation(事务隔离级别)=READ_COMMITTED，
 * 而且事务不会针对受控异常（checked exception）回滚。
 *
 * 注意：
 * 一般是查询的数据库操作，默认设置readOnly=true, propagation=Propagation.SUPPORTS
 * 而涉及到增删改的数据库操作的方法，要设置 readOnly=false, propagation=Propagation.REQUIRED
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
@Service
public class PrdProductSpecCodeSvcImpl extends
        BaseSvcImpl<java.lang.Long, PrdProductSpecCodeJo, PrdProductSpecCodeDao, PrdProductSpecCodeMo, PrdProductSpecCodeMapper>
        implements PrdProductSpecCodeSvc {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final Logger _log = LoggerFactory.getLogger(PrdProductSpecCodeSvcImpl.class);

    @Resource
    private PrdProductSpecSvc prdProductSpecSvc;

    @Resource
    private OnlOnlineSpecSvc onlOnlineSpecSvc;

    @Resource
    Mapper dozerMapper;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public int add(PrdProductSpecCodeMo mo) {
        _log.info("添加产品规格编码");
        // 如果id为空那么自动生成分布式id
        if (mo.getId() == null || mo.getId() == 0) {
            mo.setId(_idWorker.getId());
        }
        final int rowCount = super.add(mo);
        return rowCount;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public int modify(final PrdProductSpecCodeMo mo) {
        _log.info("svc.modify: mo-{}", mo);
        final int rowCount = super.modify(mo);
        return rowCount;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public int del(final Long id) {
        _log.info("svc.del: id-{}", id);
        final int rowCount = super.del(id);
        return rowCount;
    }

    @Override
    public BarcodeRo getGoodsDetailByBarcode(String barcode) {
        BarcodeRo            result    = new BarcodeRo();
        PrdProductSpecCodeMo getCodeMo = new PrdProductSpecCodeMo();
        getCodeMo.setCode(barcode);
        List<PrdProductSpecCodeMo> codeResult = super.list(getCodeMo);
        if (codeResult.size() == 1) {
            // 获取产品规格信息
            _log.info("获取产品规格信息的参数为:-{}", codeResult.get(0).getProductSpecId());
            PrdProductSpecMo specResult = prdProductSpecSvc.getById(codeResult.get(0).getProductSpecId());
            _log.info("获取产品规格信息的结果为:-{}", specResult);
            // 获取上线规格信息
            List<OnlOnlineSpecInfoRo> onlineSpecResult = onlOnlineSpecSvc
                    .selectOnlineSpecByProductSpecId(specResult.getId());
            if (onlineSpecResult.size() != 0) {
                List<PrdOnlineDetailRo> onlineDetailList = new ArrayList<PrdOnlineDetailRo>();
                PrdOnlineDetailRo       prdOnlineDetail  = new PrdOnlineDetailRo();
                prdOnlineDetail.setId(onlineSpecResult.get(0).getSpecId());
                prdOnlineDetail.setSalePrice(onlineSpecResult.get(0).getSalePrice());
                prdOnlineDetail.setSaleUnit(specResult.getUnit());
                prdOnlineDetail.setSpec(onlineSpecResult.get(0).getOnlineSpec());
                onlineDetailList.add(prdOnlineDetail);
                result.setMsg("找到一条上线规格信息");
                result.setBarcode(barcode);
                result.setResult((byte) 1);
                result.setOnlineDetailList(onlineDetailList);
            } else {
                List<ProductDetailRo> productDetailList = new ArrayList<ProductDetailRo>();
                ProductDetailRo       productDetail     = new ProductDetailRo();
                productDetail.setId(specResult.getId());
                productDetail.setSalePrice(specResult.getMarketPrice());
                productDetail.setSaleUnit(specResult.getUnit());
                productDetail.setSpec(specResult.getName());
                productDetailList.add(productDetail);
                result.setMsg("找到一条产品规格信息");
                result.setBarcode(barcode);
                result.setResult((byte) 1);
                result.setProductDetailList(productDetailList);
            }

        } else if (codeResult.size() > 1) {
            List<PrdOnlineDetailRo> onlineDetailList  = new ArrayList<PrdOnlineDetailRo>();
            List<ProductDetailRo>   productDetailList = new ArrayList<ProductDetailRo>();

            _log.info("找到多条编码详情");
            for (PrdProductSpecCodeMo item : codeResult) {
                _log.info("开始获取上线详情或产品详情-----------------------");
                _log.info("获取产品规格信息的参数为:-{}", item.getProductSpecId());
                PrdProductSpecMo specResult = prdProductSpecSvc.getById(item.getProductSpecId());
                _log.info("获取产品规格信息的结果为:-{}", specResult);
                List<OnlOnlineSpecInfoRo> onlineSpecResult = onlOnlineSpecSvc
                        .selectOnlineSpecByProductSpecId(specResult.getId());
                if (onlineSpecResult.size() != 0) {
                    PrdOnlineDetailRo prdOnlineDetail = new PrdOnlineDetailRo();
                    prdOnlineDetail.setId(onlineSpecResult.get(0).getSpecId());
                    prdOnlineDetail.setSalePrice(onlineSpecResult.get(0).getSalePrice());
                    prdOnlineDetail.setSaleUnit(specResult.getUnit());
                    prdOnlineDetail.setSpec(onlineSpecResult.get(0).getOnlineSpec());
                    onlineDetailList.add(prdOnlineDetail);
                    result.setOnlineDetailList(onlineDetailList);
                } else {
                    ProductDetailRo productDetail = new ProductDetailRo();
                    productDetail.setId(specResult.getId());
                    productDetail.setSalePrice(specResult.getMarketPrice());
                    productDetail.setSaleUnit(specResult.getUnit());
                    productDetail.setSpec(specResult.getName());
                    productDetailList.add(productDetail);
                    result.setProductDetailList(productDetailList);
                }

                _log.info("结束获取上线详情或产品详情+++++++++++++++++++++++");
            }
            result.setMsg("找到产品规格或者上线信息");
            result.setBarcode(barcode);
            result.setResult((byte) 1);
        } else {
            result.setMsg("没有找到商品");
            result.setResult((byte) -1);
            result.setBarcode(barcode);
        }
        _log.info("即将返回的结果:-{}", result);
        return result;
    }
}
