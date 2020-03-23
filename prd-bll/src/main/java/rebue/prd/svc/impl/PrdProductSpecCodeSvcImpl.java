package rebue.prd.svc.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.dozermapper.core.Mapper;

import rebue.onl.mo.OnlOnlineSpecMo;
import rebue.onl.ro.AddOnlineRo;
import rebue.onl.ro.OnlOnlineSpecInfoRo;
import rebue.onl.svr.feign.OnlOnlineSpecSvc;
import rebue.onl.svr.feign.OnlOnlineSvc;
import rebue.onl.to.AddOnlineByPosTo;
import rebue.onl.to.OnlOnlineSpecTo;
import rebue.prd.dao.PrdProductSpecCodeDao;
import rebue.prd.jo.PrdProductSpecCodeJo;
import rebue.prd.mapper.PrdProductSpecCodeMapper;
import rebue.prd.mo.PrdProductCategoryMo;
import rebue.prd.mo.PrdProductMo;
import rebue.prd.mo.PrdProductSpecCodeMo;
import rebue.prd.mo.PrdProductSpecMo;
import rebue.prd.ro.BarcodeRo;
import rebue.prd.ro.PrdDetailRo;
import rebue.prd.ro.PrdOnlineDetailRo;
import rebue.prd.ro.ProductDetailRo;
import rebue.prd.svc.PrdProductCategorySvc;
import rebue.prd.svc.PrdProductSpecCodeSvc;
import rebue.prd.svc.PrdProductSpecSvc;
import rebue.prd.svc.PrdProductSvc;
import rebue.prd.to.AddonlineByCodeTo;
import rebue.robotech.dic.ResultDic;
import rebue.robotech.ro.Ro;
import rebue.robotech.svc.impl.BaseSvcImpl;
import rebue.slr.mo.SlrShopMo;
import rebue.slr.svr.feign.SlrShopSvc;

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
    private OnlOnlineSvc onlOnlineSvc;

    @Resource
    private PrdProductSvc prdProductSvc;

    @Resource
    private PrdProductCategorySvc prdProductCategorySvc;

    @Resource
    Mapper dozerMapper;

    @Resource
    private SlrShopSvc slrShopSvc;

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

    /**
     * 这里的两段注释是因为不上线的商品不给买
     */
    @Override
    public BarcodeRo getGoodsDetailByBarcode(String barcode,String shopId) {
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
            List<OnlOnlineSpecMo> onlineSpecResult = onlOnlineSpecSvc.selectOnlineSpec(specResult.getId(),Long.valueOf(shopId));
            if (onlineSpecResult.size() != 0) {
                List<PrdOnlineDetailRo> onlineDetailList = new ArrayList<PrdOnlineDetailRo>();
                PrdOnlineDetailRo       prdOnlineDetail  = new PrdOnlineDetailRo();
                prdOnlineDetail.setOnlineSpecId(onlineSpecResult.get(0).getId());
                prdOnlineDetail.setOnlineId(onlineSpecResult.get(0).getOnlineId());
                prdOnlineDetail.setSalePrice(onlineSpecResult.get(0).getSalePrice());
                prdOnlineDetail.setSaleUnit(specResult.getUnit());
                prdOnlineDetail.setSpec(onlineSpecResult.get(0).getOnlineSpec());
                onlineDetailList.add(prdOnlineDetail);
                result.setMsg("找到一条上线规格信息");
                result.setBarcode(barcode);
                result.setResult((byte) 1);
                result.setOnlineDetailList(onlineDetailList);
            }
//            else {
//                List<ProductDetailRo> productDetailList = new ArrayList<ProductDetailRo>();
//                ProductDetailRo productDetail = new ProductDetailRo();
//                productDetail.setProductSpecId(specResult.getId());
//                productDetail.setProductId(specResult.getProductId());
//                productDetail.setSalePrice(specResult.getMarketPrice());
//                productDetail.setSaleUnit(specResult.getUnit());
//                productDetail.setSpec(specResult.getName());
//                productDetailList.add(productDetail);
//                result.setMsg("找到一条产品规格信息");
//                result.setBarcode(barcode);
//                result.setResult((byte) 1);
//                result.setProductDetailList(productDetailList);
//            }

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
                    prdOnlineDetail.setOnlineSpecId(onlineSpecResult.get(0).getSpecId());
                    prdOnlineDetail.setOnlineId(onlineSpecResult.get(0).getOnlineId());
                    prdOnlineDetail.setSalePrice(onlineSpecResult.get(0).getSalePrice());
                    prdOnlineDetail.setSaleUnit(specResult.getUnit());
                    prdOnlineDetail.setSpec(onlineSpecResult.get(0).getOnlineSpec());
                    onlineDetailList.add(prdOnlineDetail);
                    result.setOnlineDetailList(onlineDetailList);
                }
//                else {
//                    ProductDetailRo productDetail = new ProductDetailRo();
//                    productDetail.setProductSpecId(specResult.getId());
//                    productDetail.setProductId(specResult.getProductId());
//                    productDetail.setSalePrice(specResult.getMarketPrice());
//                    productDetail.setSaleUnit(specResult.getUnit());
//                    productDetail.setSpec(specResult.getName());
//                    productDetailList.add(productDetail);
//                    result.setProductDetailList(productDetailList);
//                }

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

    /**
     * 根据条码后N位模糊查找
     */
    @Override
    public List<PrdProductSpecCodeMo> selectByCode(String code) {
        _log.info("根据条码后N位模糊查找的参数为:code-{}", code);
        return _mapper.selectByCode(code);
    }

    /**
     * 根据条码查询产品
     */
    @Override
    public PrdDetailRo getDetailByCode(String Code) {
        PrdProductSpecCodeMo getCodeMo = new PrdProductSpecCodeMo();
        getCodeMo.setCode(Code);
        List<PrdProductSpecCodeMo> codeResult = super.list(getCodeMo);
        PrdDetailRo                result     = new PrdDetailRo();
        if (codeResult.size() == 1) {
            // 获取产品规格信息
            _log.info("获取产品规格信息的参数为:{}", codeResult.get(0).getProductSpecId());
            PrdProductSpecMo specResult = prdProductSpecSvc.getById(codeResult.get(0).getProductSpecId());
            _log.info("获取产品规格信息的返回值为:{}", specResult);

            _log.info("获取产品信息的参数为:{}", specResult.getProductId());
            PrdProductMo prdProductResult = prdProductSvc.getById(specResult.getProductId());
            _log.info("获取产品规格信息的返回值为:{}", prdProductResult);

            if (prdProductResult != null) {
                _log.info("获取产品分类的参数为:{}", prdProductResult.getCategoryId());
                PrdProductCategoryMo prdProductCategoryResult = prdProductCategorySvc
                        .getById(prdProductResult.getCategoryId());
                _log.info("获取产品分类的返回值为:{}", prdProductCategoryResult);
                result.setCategoryId(prdProductCategoryResult.getId());
                result.setCategoryName(prdProductCategoryResult.getName());
            }

            result.setResult((byte) 1);
            result.setPrdProductSpecMo(specResult);
        } else {
            result.setResult((byte) -1);
        }
        return result;
    }

    /**
     * 根据条码上线商品
     */
    @Override
    public Ro addOnlineBycode(AddonlineByCodeTo to) {
        Ro ro = new Ro();
        _log.info("根据条码上线商品的参数 to-{}", to);
        PrdProductSpecCodeMo specCode = new PrdProductSpecCodeMo();
        specCode.setCode(to.getBarcode());
        _log.info("根据条码查询产品信息的参数:{}", specCode);
        List<PrdProductSpecCodeMo> list = super.list(specCode);
        _log.info("根据条码查询产品信息的返回值:{}", list);
        PrdProductSpecCodeMo productSpecCode = null;
        Long                 productId       = _idWorker.getId();
        Long                 ProductSpecId   = _idWorker.getId();
        // 商品条码
        if (list.size() == 1) {
            productSpecCode = list.get(0);
            PrdProductSpecMo prdProductSpecMo = prdProductSpecSvc.getById(productSpecCode.getProductSpecId());
            productId     = prdProductSpecMo.getProductId();
            ProductSpecId = prdProductSpecMo.getId();
        } else {
            _log.info("没有查询到商品条码,即没有此产品");
            // 添加产品
            PrdProductMo productMo = new PrdProductMo();
            productMo.setId(productId);
            productMo.setProductName(to.getName());
            productMo.setOpId(Long.valueOf(to.getOpId()));
            productMo.setCreateTime(new Date());
            // 获取分类信息
            if (to.getCategoryId().equals("")) {
                PrdProductCategoryMo category = new PrdProductCategoryMo();
                category.setName(to.getCategoryName());
                List<PrdProductCategoryMo> categoryList = prdProductCategorySvc.list(category);
                if (categoryList.size() > 0) {
                    productMo.setCategoryId(categoryList.get(0).getId());
                } else {
                    _log.info("没有分类id,即没有查询到此分类");
                    PrdProductCategoryMo categoryMo = new PrdProductCategoryMo();
                    categoryMo.setId(_idWorker.getId());
                    categoryMo.setName(to.getCategoryName());
                    categoryMo.setFullName(to.getCategoryName());
                    categoryMo.setOpId(Long.valueOf(to.getOpId()));
                    categoryMo.setCreateTime(new Date());
                    _log.info("添加产品分类的参数:{}", categoryMo);
                    Ro categoryResult = prdProductCategorySvc.addEx(categoryMo);
                    if (categoryResult.getResult().getCode() == 1) {
                        _log.info("创建分类成功");
                        productMo.setCategoryId(categoryMo.getId());
                    } else {
                        ro.setResult(ResultDic.FAIL);
                        ro.setMsg("创建分类失败");
                        return ro;
                    }
                }
            } else {
                productMo.setCategoryId(Long.valueOf(to.getCategoryId()));
            }
            _log.info("添加产品的参数:{}", productMo);
            int productResult = prdProductSvc.add(productMo);
            if (productResult != 1) {
                ro.setResult(ResultDic.FAIL);
                ro.setMsg("添加产品失败");
                return ro;
            }
            // 添加产品规格
            PrdProductSpecMo productSpec = new PrdProductSpecMo();
            productSpec.setId(ProductSpecId);
            productSpec.setName(to.getName());
            productSpec.setProductId(productId);
            productSpec.setMarketPrice(new BigDecimal(to.getSalePrice()));
            productSpec.setUnit(to.getSaleUnit());
            _log.info("添加产品规格的参数:{}", productSpec);
            int productSpecResult = prdProductSpecSvc.add(productSpec);
            if (productSpecResult != 1) {
                ro.setResult(ResultDic.FAIL);
                ro.setMsg("添加产品规格失败");
                return ro;
            }
        }
        SlrShopMo shopMo = slrShopSvc.getById(Long.valueOf(to.getShopId()));
        // 添加产品规格编码
        PrdProductSpecCodeMo codeMo = new PrdProductSpecCodeMo();
        codeMo.setProductSpecId(ProductSpecId);
        codeMo.setCode(to.getBarcode());
        if(this.getOne(codeMo) == null) {
            codeMo.setId(_idWorker.getId());
            int codeResult = this.add(codeMo);
            if (codeResult != 1) {
                ro.setResult(ResultDic.FAIL);
                ro.setMsg("添加产品规格编码失败");
                return ro;
            }
        }
       
        // 上线
        AddOnlineByPosTo posTo = new AddOnlineByPosTo();
        posTo.setIsWeighGoods(to.getIsWeighGoods());
        posTo.setDeliverOrgId(shopMo.getSellerId());
        posTo.setOnlineId(_idWorker.getId());
        posTo.setOnlineName(to.getName());
        posTo.setOnlineOrgId(shopMo.getSellerId());
        posTo.setOpId(Long.valueOf(to.getOpId()));
        posTo.setProductId(productId);
        posTo.setSubjectType((byte) 2);
        posTo.setSupplierId(shopMo.getSellerId());
        posTo.setShopId(Long.valueOf(to.getShopId()));
        // 上线详情
        OnlOnlineSpecTo onlSpec = new OnlOnlineSpecTo();
        onlSpec.setId(_idWorker.getId());
        onlSpec.setProductSpecId(ProductSpecId);
        onlSpec.setOnlineId(posTo.getOnlineId());
        onlSpec.setOnlineSpec(to.getName());
        onlSpec.setCostPrice(BigDecimal.ZERO);
        onlSpec.setCashbackAmount(BigDecimal.ZERO);
        onlSpec.setCommissionAmount(BigDecimal.ZERO);
        onlSpec.setSaleUnit(to.getSaleUnit());
        onlSpec.setOnlineTotal(new BigDecimal(to.getSaleCount()));
        onlSpec.setSaleCount(BigDecimal.ZERO);
        onlSpec.setLimitCount(BigDecimal.ZERO);
        onlSpec.setSeqNo(1);
        onlSpec.setSalePrice(new BigDecimal(to.getSalePrice()));
        onlSpec.setBuyCount(BigDecimal.ZERO);
        onlSpec.setBuyPoint(new BigDecimal(to.getSalePrice()));
        onlSpec.setFirstBuyPoint(new BigDecimal(to.getSalePrice()));

        List<OnlOnlineSpecTo> specToList = new ArrayList<OnlOnlineSpecTo>();
        specToList.add(onlSpec);
        posTo.setOnlineSpecs(specToList);

        // 收银机扫码上线
        _log.info("收银机扫码上线的参数:{}", posTo);
        AddOnlineRo addOnlineResult = onlOnlineSvc.addOnlineByPos(posTo);
        _log.info("收银机扫码上线的返回值数:{}", addOnlineResult);

        if (addOnlineResult.getResult().getCode() == 1) {
            ro.setMsg("上线成功");
            ro.setResult(ResultDic.SUCCESS);
        } else {
            ro.setMsg(addOnlineResult.getMsg());
            ro.setResult(ResultDic.FAIL);
        }
        return ro;
    }
}
