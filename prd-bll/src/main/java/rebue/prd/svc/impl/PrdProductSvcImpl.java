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
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import rebue.ise.ro.SaveFileRo;
import rebue.ise.svr.feign.IserSvc;
import rebue.ise.to.SaveFileTo;
import rebue.onl.mo.OnlOnlineMo;
import rebue.onl.mo.OnlOnlineSpecMo;
import rebue.onl.mo.OnlSearchCategoryMo;
import rebue.onl.mo.OnlSearchCategoryOnlineMo;
import rebue.onl.svr.feign.OnlOnlineSpecSvc;
import rebue.onl.svr.feign.OnlOnlineSvc;
import rebue.onl.svr.feign.OnlSearchCategoryOnlineSvc;
import rebue.onl.svr.feign.OnlSearchCategorySvc;
import rebue.onl.to.AddOnlineTo;
import rebue.prd.dao.PrdProductDao;
import rebue.prd.jo.PrdProductJo;
import rebue.prd.mapper.PrdProductCategoryMapper;
import rebue.prd.mapper.PrdProductMapper;
import rebue.prd.mo.PrdProductCategoryMo;
import rebue.prd.mo.PrdProductMo;
import rebue.prd.mo.PrdProductSpecCodeMo;
import rebue.prd.mo.PrdProductSpecMo;
import rebue.prd.ro.GetProductRo;
import rebue.prd.ro.PrdProductListRo;
import rebue.prd.svc.PrdProductCategorySvc;
import rebue.prd.svc.PrdProductPicSvc;
import rebue.prd.svc.PrdProductSpecCodeSvc;
import rebue.prd.svc.PrdProductSpecSvc;
import rebue.prd.svc.PrdProductSvc;
import rebue.prd.to.AddProductSpecTo;
import rebue.prd.to.AddProductTo;
import rebue.prd.to.ImportProductTo;
import rebue.prd.to.ImportTo;
import rebue.prd.to.ModifyProductSpecTo;
import rebue.prd.to.ModifyProductTo;
import rebue.prd.to.OnlineProductTo;
import rebue.robotech.dic.ResultDic;
import rebue.robotech.ro.Ro;
import rebue.robotech.svc.impl.BaseSvcImpl;

/**
 * 产品
 *
 * 在单独使用不带任何参数的 @Transactional 注释时， propagation(传播模式)=REQUIRED，readOnly=false，
 * isolation(事务隔离级别)=READ_COMMITTED， 而且事务不会针对受控异常（checked exception）回滚。
 *
 * 注意： 一般是查询的数据库操作，默认设置readOnly=true, propagation=Propagation.SUPPORTS
 * 而涉及到增删改的数据库操作的方法，要设置 readOnly=false, propagation=Propagation.REQUIRED
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
@Service
public class PrdProductSvcImpl
        extends BaseSvcImpl<java.lang.Long, PrdProductJo, PrdProductDao, PrdProductMo, PrdProductMapper>
        implements PrdProductSvc {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final Logger _log = LoggerFactory.getLogger(PrdProductSvcImpl.class);

    @Resource
    private PrdProductSvc thisSvc;

    @Resource
    private PrdProductSpecSvc prdProductSpecSvc;

    @Resource
    private PrdProductSpecCodeSvc prdProductSpecCodeSvc;

    @Resource
    private PrdProductPicSvc prdProductPicSvc;

    @Resource
    private PrdProductCategorySvc prdProductCategorySvc;

    @Resource
    private IserSvc iseSvc;

    @Resource
    private OnlOnlineSvc onlOnlineSvc;

    @Resource
    private OnlOnlineSpecSvc onlOnlineSpecSvc;

    @Resource
    private Mapper dozerMapper;

    @Resource
    private PrdProductCategoryMapper prdProductCategoryMapper;

    @Resource
    private OnlSearchCategorySvc onlSearchCategorySvc;

    @Resource
    private OnlSearchCategoryOnlineSvc onlSearchCategoryOnlineSvc;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public int add(PrdProductMo mo) {
        _log.info("添加产品");
        // 如果id为空那么自动生成分布式id
        if (mo.getId() == null || mo.getId() == 0) {
            mo.setId(_idWorker.getId());
        }
        return super.add(mo);
    }

    /**
     * 添加产品信息
     * 
     * @param to
     * @return
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Ro addProduct(AddProductTo to) {
        _log.info("添加产品信息的请求参数为：{}", to);
        Ro ro = new Ro();
        if (to.getCategoryId() == null || to.getProductName() == null || to.getSpec().size() == 0) {
            _log.error("添加产品信息时出现参数为null,请求的参数为：{}", to);
            ro.setResult(ResultDic.PARAM_ERROR);
            ro.setMsg("参数有误");
            return ro;
        }

        // 产品ID
        Long productId = _idWorker.getId();
        PrdProductMo productMo = new PrdProductMo();

        if (to.getProductDetail() != null) {
            SaveFileTo saveFileTo = new SaveFileTo();
            saveFileTo.setContent(to.getProductDetail());
            saveFileTo.setModuleName("productDetail");
            saveFileTo.setFileType("html");
            _log.info("添加产品信息保存产品详情文件的参数为：{}", saveFileTo);
            SaveFileRo saveFileRo = iseSvc.saveFile(saveFileTo);
            _log.info("添加产品信息保存产品详情文件的返回值为：{}", saveFileRo);
            if (saveFileRo.getResult() != 1) {
                _log.error("添加产品信息保存产品详情文件时出现异常，请求的参数为：{}", saveFileTo);
                ro.setResult(ResultDic.FAIL);
                ro.setMsg("保存产品详情出现异常");
                return ro;
            }
            productMo.setProductDetailPath(saveFileRo.getFilePath());
        }

        productMo.setId(productId);
        productMo.setCategoryId(to.getCategoryId());
        productMo.setProductName(to.getProductName());
        productMo.setIsEnabled(true);
        productMo.setManufacturer(to.getManufacturer());
        productMo.setBrand(to.getBrand());
        productMo.setOpId(to.getOpId());
        productMo.setCreateTime(new Date());
        _log.info("添加产品信息的参数为：{}", productMo);
        int addProductResult = this.add(productMo);
        _log.info("添加产品信息的返回值为：{}", addProductResult);
        if (addProductResult != 1) {
            _log.error("添加产品信息时出现错误，请求的参数为：{}", productMo);
            ro.setResult(ResultDic.FAIL);
            ro.setMsg("添加产品出现异常");
            return ro;
        }

        for (AddProductSpecTo addProductSpecTo : to.getSpec()) {
            PrdProductSpecMo productSpecMo = dozerMapper.map(addProductSpecTo, PrdProductSpecMo.class);
            // 产品规格ID
            Long productSpecId = _idWorker.getId();
            productSpecMo.setId(productSpecId);
            productSpecMo.setProductId(productId);
            _log.info("添加产品信息添加产品规格信息的参数为：{}", productSpecMo);
            int addProductSpecResult = prdProductSpecSvc.add(productSpecMo);
            _log.info("添加产品信息添加产品规格信息的返回值为：{}", addProductSpecResult);
            if (addProductSpecResult != 1) {
                _log.error("添加产品信息添加产品规格信息出现错误，请求的参数为：{}", productSpecMo);
                throw new RuntimeException("添加规格信息出现异常");
            }

            PrdProductSpecCodeMo productSpecCodeMo = new PrdProductSpecCodeMo();
            productSpecCodeMo.setId(_idWorker.getId());
            productSpecCodeMo.setProductSpecId(productSpecId);
            productSpecCodeMo.setCode(addProductSpecTo.getCode());
            _log.info("添加产品信息添加产品规格编码信息的参数为：{}", productSpecCodeMo);
            int addProductSpecCodeResult = prdProductSpecCodeSvc.add(productSpecCodeMo);
            _log.info("添加产品信息添加产品规格编码信息的返回值为：{}", addProductSpecCodeResult);
            if (addProductSpecCodeResult != 1) {
                _log.error("添加产品信息添加产品规格编码出现错误，请求的参数为：{}", productSpecCodeMo);
                throw new RuntimeException("添加规格编码出现异常");
            }
        }

        if (to.getPics().size() != 0) {
            _log.info("添加产品信息批量添加产品图片的参数为：list-{}, productId-{}", to.getPics(), productId);
            Ro batchAddPicByProductIdRo = prdProductPicSvc.batchAddPicByProductId(to.getPics(), productId);
            _log.info("添加产品信息批量添加产品图片的返回值为：{}", batchAddPicByProductIdRo);
            if (batchAddPicByProductIdRo.getResult() != ResultDic.SUCCESS) {
                _log.info("添加产品信息批量添加产品图片出现异常，请求的参数为：{}", to);
                throw new RuntimeException("添加产品图片失败");
            }
        }

        _log.info("添加产品信息成功，请求的参数为：{}", to);
        ro.setResult(ResultDic.SUCCESS);
        ro.setMsg("添加成功");
        return ro;
    }

    /**
     * 修改产品信息
     * 
     * @param to
     * @return
     */
    @Override
    public Ro modifyProduct(ModifyProductTo to) {
        _log.info("修改产品信息的请求参数为：{}", to);
        Ro ro = new Ro();
        if (to.getId() == null || to.getProductName() == null || to.getCategoryId() == null || to.getOpId() == null) {
            _log.error("修改产品信息时发现产品有误，请求的参数为：{}", to);
            ro.setResult(ResultDic.PARAM_ERROR);
            ro.setMsg("参数错误");
            return ro;
        }

        SaveFileTo saveFileTo = new SaveFileTo();
        saveFileTo.setContent(to.getProductDetail());
        saveFileTo.setFileType("html");
        saveFileTo.setModuleName("productDetail");
        saveFileTo.setOldFilePath(to.getProductDetailPath());
        _log.info("修改产品信息保存文件的参数为：{}", saveFileTo);
        SaveFileRo saveFileRo = iseSvc.saveFile(saveFileTo);
        _log.info("修改产品信息保存文件的返回值为：{}", saveFileRo);
        if (saveFileRo == null) {
            _log.error("修改产品信息保存文件时出现异常，请求的参数为：{}", to);
            ro.setResult(ResultDic.FAIL);
            ro.setMsg("保存产品详情出现异常");
            return ro;
        }

        PrdProductMo productMo = new PrdProductMo();
        productMo.setId(to.getId());
        productMo.setCategoryId(to.getCategoryId());
        productMo.setProductName(to.getProductName());
        productMo.setIsEnabled(to.getIsEnabled());
        productMo.setManufacturer(to.getManufacturer());
        productMo.setBrand(to.getBrand());
        productMo.setProductDetailPath(saveFileRo.getFilePath());
        productMo.setOpId(to.getOpId());
        productMo.setCreateTime(new Date());
        _log.info("修改产品信息的参数为：{}", productMo);
        int modifyProductResult = thisSvc.modify(productMo);
        _log.info("修改产品信息的返回值为：{}", modifyProductResult);
        if (modifyProductResult != 1) {
            _log.error("修改产品信息时出现错误，请求的参数为：{}", productMo);
            throw new RuntimeException("修改产品信息出错");
        }

        for (ModifyProductSpecTo modifyProductSpecTo : to.getSpec()) {
            PrdProductSpecMo prdProductSpecMo = dozerMapper.map(modifyProductSpecTo, PrdProductSpecMo.class);
            if (modifyProductSpecTo.getId().toString().length() > 13) { // 这里是因为页面为了reactjs里面的key，所以新的记录也会有一个id，不过是时间戳，所以这里这样判断。
                _log.info("修改产品信息修改产品规格信息的参数为：{}", prdProductSpecMo);
                int modifyProductSpecResult = prdProductSpecSvc.modify(prdProductSpecMo);
                _log.info("修改产品信息修改产品规格信息的返回值为：{}", modifyProductSpecResult);
                if (modifyProductSpecResult < 0) {
                    _log.error("修改产品信息修改产品规格信息出现异常，请求的参数为：{}", prdProductSpecMo);
                    throw new RuntimeException("修改产品规格出现异常");
                }

                PrdProductSpecCodeMo productSpecCodeMo = new PrdProductSpecCodeMo();
                productSpecCodeMo.setId(modifyProductSpecTo.getProductSpecCodeId());
                productSpecCodeMo.setCode(modifyProductSpecTo.getCode());
                _log.info("修改产品信息修改产品规格编码信息的参数为：{}", productSpecCodeMo);
                int modifyProductSpecCodeResult = prdProductSpecCodeSvc.modify(productSpecCodeMo);
                _log.info("修改产品信息修改产品规格编码信息的参数为：{}", modifyProductSpecCodeResult);
                if (modifyProductSpecCodeResult < 0) {
                    _log.error("修改产品信息修改产品规格编码出现异常，请求的参数为：{}", productSpecCodeMo);
                    throw new RuntimeException("修改规格编码出现异常");
                }
            } else {
                // 产品规格ID
                Long productSpecId = _idWorker.getId();
                prdProductSpecMo.setId(productSpecId);
                prdProductSpecMo.setProductId(to.getId());
                _log.info("添加产品信息添加产品规格信息的参数为：{}", prdProductSpecMo);
                int addProductSpecResult = prdProductSpecSvc.add(prdProductSpecMo);
                _log.info("添加产品信息添加产品规格信息的返回值为：{}", addProductSpecResult);
                if (addProductSpecResult != 1) {
                    _log.error("添加产品信息添加产品规格信息出现错误，请求的参数为：{}", prdProductSpecMo);
                    throw new RuntimeException("添加规格信息出现异常");
                }

                PrdProductSpecCodeMo productSpecCodeMo = new PrdProductSpecCodeMo();
                productSpecCodeMo.setId(_idWorker.getId());
                productSpecCodeMo.setProductSpecId(productSpecId);
                productSpecCodeMo.setCode(modifyProductSpecTo.getCode());
                _log.info("添加产品信息添加产品规格编码信息的参数为：{}", productSpecCodeMo);
                int addProductSpecCodeResult = prdProductSpecCodeSvc.add(productSpecCodeMo);
                _log.info("添加产品信息添加产品规格编码信息的返回值为：{}", addProductSpecCodeResult);
                if (addProductSpecCodeResult != 1) {
                    _log.error("添加产品信息添加产品规格编码出现错误，请求的参数为：{}", productSpecCodeMo);
                    throw new RuntimeException("添加规格编码出现异常");
                }
            }
        }

        Ro delByProductIdResult = prdProductPicSvc.delByProductId(to.getId());
        _log.info("修改产品信息删除产品图片的返回值为：{}", delByProductIdResult);
        if (delByProductIdResult.getResult() != ResultDic.SUCCESS) {
            _log.error("修改产品信息删除产品图片出现异常，请求的参数为：{}", to.getId());
            throw new RuntimeException("删除产品图片失败");
        }

        _log.info("修改产品信息批量添加产品图片的参数为：list-{}, productId-{}", to.getPics(), to.getId());
        Ro batchAddPicByProductIdRo = prdProductPicSvc.batchAddPicByProductId(to.getPics(), to.getId());
        _log.info("修改产品信息批量添加产品图片的返回值为：{}", batchAddPicByProductIdRo);
        if (batchAddPicByProductIdRo.getResult() != ResultDic.SUCCESS) {
            _log.info("修改产品信息批量添加产品图片出现异常，请求的参数为：{}", to);
            throw new RuntimeException("添加产品图片失败");
        }

        _log.info("修改产品信息成功，请求的参数为：{}", to);
        ro.setResult(ResultDic.SUCCESS);
        ro.setMsg("修改成功");

        return ro;
    }

    /**
     * 查询产品分页信息
     * 
     * @param mo
     * @param pageNum
     * @param pageSize
     * @param orderBy
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public PageInfo<PrdProductListRo> pageList(PrdProductMo mo, Integer pageNum, final int pageSize,
            final String orderBy) {
        _log.info("分页查询产品信息的参数为：PrdProductMo-{}, pageNUm-{}, pageSize-{}, orderBy-{}", mo, pageNum, pageSize, orderBy);
        PageInfo<PrdProductListRo> pageInfo = new PageInfo<PrdProductListRo>();
        List<PrdProductListRo> list = new ArrayList<PrdProductListRo>();
        PageInfo<PrdProductMo> selectPageInfo = PageHelper.startPage(pageNum, pageSize, orderBy)
                .doSelectPageInfo(() -> _mapper.selectSelective(mo));
        for (PrdProductMo prdProductMo : selectPageInfo.getList()) {
            PrdProductListRo productListRo = dozerMapper.map(prdProductMo, PrdProductListRo.class);
            _log.info("获取分类的参数为-{}", prdProductMo.getCategoryId());
            PrdProductCategoryMo productCategoryMo = prdProductCategorySvc.getById(prdProductMo.getCategoryId());
            _log.info("获取分类的结果为-{}", productCategoryMo);

            productListRo.setFullName(productCategoryMo.getFullName());

            if (prdProductMo.getProductDetailPath() != null) {
                String readFileResult = iseSvc.readFileByByte(prdProductMo.getProductDetailPath());
                _log.info("分页查询产品信息读取产品详情文件的返回值为：{}", readFileResult);
                productListRo.setProductDetail(readFileResult);
            }

            list.add(productListRo);
        }
        pageInfo = dozerMapper.map(selectPageInfo, PageInfo.class);
        pageInfo.setList(list);
        return pageInfo;
    }

    /**
     * 禁用或启用产品
     * 
     * @param id
     *            产品ID
     * @param isEnabled
     * @return
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Ro enable(Long id, Boolean isEnabled) {
        _log.info("禁用或启用产品的请求参数为：id-{}, isEnabled-{}", id, isEnabled);
        Ro ro = new Ro();
        if (id == null || isEnabled == null) {
            _log.error("禁用或启用产品时发现参数不正确，请求的参数为：id-{}, isEnabled-{}", id, isEnabled);
            ro.setResult(ResultDic.PARAM_ERROR);
            ro.setMsg("参数不正确");
            return ro;
        }

        int enableResult = _mapper.enable(id, isEnabled);
        _log.info("禁用或启用产品的返回值为：{}", enableResult);
        if (enableResult != 1) {
            _log.error("禁用或启用产品出现错误，请求的参数为：id-{}, isEnabled-{}", id, isEnabled);
            ro.setResult(ResultDic.FAIL);
            ro.setMsg("操作失败");
            return ro;
        }

        _log.error("禁用或启用产品成功，请求的参数为：id-{}, isEnabled-{}", id, isEnabled);
        ro.setResult(ResultDic.SUCCESS);
        ro.setMsg("操作成功");
        return ro;
    }

    /**
     * 1:添加一个分类
     * 2:添加一个产品
     * 3:添加一个产品规格
     * 4:添加一个产品规格编码
     * 5:添加一条上线信息
     * 6:添加一条上线规格信息
     */
    @Override
    public Ro importProduct(ImportTo to) {

        for (ImportProductTo item : to.getImportProduct()) {
            PrdProductCategoryMo getOneMo = new PrdProductCategoryMo();
            getOneMo.setName(item.getClassName());
            _log.info("查询分类的参数mo-{}", getOneMo);
            PrdProductCategoryMo result = prdProductCategorySvc.getOne(getOneMo);
            _log.info("查询分类的结果为:{}", result);
            // 添加一个分类
            String code = "";
            if (result == null) {
                List<PrdProductCategoryMo> getLengthResult = prdProductCategorySvc.list(new PrdProductCategoryMo());
                PrdProductCategoryMo addMo = new PrdProductCategoryMo();
                if (getLengthResult.size() <= 9) {
                    code = "0" + String.valueOf(getLengthResult.size());
                } else {
                    code = String.valueOf(getLengthResult.size());
                }
                addMo.setName(item.getClassName());
                addMo.setCode(code);
                addMo.setIsEnabled(true);
                addMo.setOpId(123456l);
                addMo.setCreateTime(new Date());
                addMo.setFullName(item.getClassName());
                _log.info("添加一个新分类的参数为-{}", addMo);
                if (prdProductCategorySvc.add(addMo) != 1) {
                    throw new RuntimeException("添加一个新产品分类失败");
                }
                _log.info("添加一个新分类成功");

                result = addMo;
            }
            // 添加一个产品
            PrdProductMo addProductMo = new PrdProductMo();
            addProductMo.setCategoryId(result.getId());
            addProductMo.setProductName(item.getName());
            addProductMo.setIsEnabled(true);
            addProductMo.setOpId(123456l);
            addProductMo.setCreateTime(new Date());
            if (this.add(addProductMo) != 1) {
                throw new RuntimeException("添加一个新产品失败");
            }
            _log.info("添加一个新产品成功");
            // 添加一个产品规格
            PrdProductSpecMo addProductSpecMo = new PrdProductSpecMo();
            addProductSpecMo.setProductId(addProductMo.getId());
            addProductSpecMo.setMarketPrice(item.getPrice()); // 市场价格就是售价
            addProductSpecMo.setName(item.getName()); // 这里是导入的商品名称，因为导入的数据并没有规格名称。
            addProductSpecMo.setUnit(item.getUnit());
            if (prdProductSpecSvc.add(addProductSpecMo) != 1) {
                throw new RuntimeException("添加一个新产品规格失败");
            }
            _log.info("添加一个新产品规格成功");
            // 添加一个产品规格编码
            PrdProductSpecCodeMo addProductSpecCodeMo = new PrdProductSpecCodeMo();
            addProductSpecCodeMo.setCode(item.getGoodCode());
            addProductSpecCodeMo.setProductSpecId(addProductSpecMo.getId());
            if (prdProductSpecCodeSvc.add(addProductSpecCodeMo) != 1) {
                throw new RuntimeException("添加一个产品规格编码失败");
            }
            _log.info("添加一个产品规格编码成功");
            // 添加一条上线信息
            OnlOnlineMo addOnlineMo = new OnlOnlineMo();
            addOnlineMo.setId(_idWorker.getId()); // 这里生成以便下面获取
            addOnlineMo.setSubjectType((byte) 0);
            addOnlineMo.setOnlineTitle(item.getName());
            addOnlineMo.setOnlineOrgId(517928358546243584l);// 线上微薄利的ID
            addOnlineMo.setDeliverOrgId(517928358546243584l);// 线上微薄利的ID
            addOnlineMo.setOpId(123456l);
            addOnlineMo.setOnlineState((byte) 1);
            addOnlineMo.setOnlineTime(new Date());
            addOnlineMo.setProductId(addProductMo.getId()); // 线上原先这个值是上线ID
            addOnlineMo.setIsBelow(true);
            addOnlineMo.setIsOnline(false);
            addOnlineMo.setIsOnlinePlatform(false);
            if (onlOnlineSvc.importOnline(addOnlineMo) != 1) {
                throw new RuntimeException("添加上线信息失败");
            }
            _log.info("添加上线信息成功");

            // 添加一个商品分类
            if (code != "") {
                OnlSearchCategoryMo addSearch = new OnlSearchCategoryMo();
                addSearch.setId(_idWorker.getId());
                addSearch.setCode(code);
                addSearch.setIsEnabled(true);
                addSearch.setName(item.getClassName());
                addSearch.setSellerId(517928358546243584l);// 线上微薄利的ID
                addSearch.setShopId(670157330226085890l);// 线上龙岗母婴店id
                _log.info("添加商品分类addSearch-{}",addSearch);
                if (onlSearchCategorySvc.addSearchCategory(addSearch) != 1) {
                    throw new RuntimeException("添加一个新商品分类失败");
                }
                _log.info("添加一个新商品分类成功");

                // 添加上线搜索分类
                OnlSearchCategoryOnlineMo searchCategoryOnline = new OnlSearchCategoryOnlineMo();
                searchCategoryOnline.setOnlineId(addOnlineMo.getId());
                searchCategoryOnline.setSearchCategoryId(addSearch.getId());
                if (onlSearchCategoryOnlineSvc.add(searchCategoryOnline).getResult().getCode() != 1) {
                    throw new RuntimeException("添加上线搜索分类失败");
                }
            }

            // 添加上线规格
            OnlOnlineSpecMo addOnlineSpecMo = new OnlOnlineSpecMo();
            addOnlineSpecMo.setOnlineId(addOnlineMo.getId());
            addOnlineSpecMo.setProductSpecId(addProductSpecMo.getId());
            addOnlineSpecMo.setSalePrice(item.getPrice());
            addOnlineSpecMo.setCostPrice(item.getInPrice());
            addOnlineSpecMo.setCashbackAmount(new BigDecimal("0"));
            addOnlineSpecMo.setCurrentOnlineCount(item.getStock());
            addOnlineSpecMo.setOnlineSpec(item.getName());
            addOnlineSpecMo.setSeqNo(0);
            addOnlineSpecMo.setSaleCount(new BigDecimal("0"));
            addOnlineSpecMo.setSaleUnit(item.getUnit());
            if (onlOnlineSpecSvc.add(addOnlineSpecMo).getResult().getCode() != 1) {
                throw new RuntimeException("添加上线规格信息失败");
            }
            _log.info("添加上线规格信息成功");

        }
        return new Ro(ResultDic.SUCCESS, "导入成功");
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public int del(final Long id) {
        _log.info("svc.del: id-{}", id);
        final int rowCount = super.del(id);
        return rowCount;
    }

    /**
     * 从产品中上线商品
     */
    @Override
    public Ro onlineFormProduct(OnlineProductTo to) {
        _log.info("从产品中上线商品: to-{}", to);
        AddOnlineTo onlineTo = new AddOnlineTo();
        // 添加上线信息
        onlineTo.setProductId(to.getProductId());
        onlineTo.setIsBelowOnline((byte) 1);
        onlineTo.setIsOnlinePlatform((byte) 0);
        onlineTo.setSupplierId(to.getSupplierId());
        onlineTo.setOnlineDetail(to.getOnlineDetail());
        onlineTo.setOpId(to.getOpId());
        onlineTo.setOnlineOrgId(to.getOnlineOrgId());
        onlineTo.setOnlineName(to.getOnlineName());
        onlineTo.setSubjectType(to.getSubjectType());
        onlineTo.setDeliverOrgId(to.getDeliverOrgId());
        onlineTo.setIsEditSupplier(to.getIsEditSupplier());
        // 上线规格
        onlineTo.setOnlineSpecs(to.getOnlineSpecs());
        // 上线规格属性
        onlineTo.setAttrNames(to.getAttrNames());
        onlineTo.setAttrValues(to.getAttrValues());
        // 搜索分类上线
        onlineTo.setClassificationId(to.getClassificationId());
        // 商品图片
        onlineTo.setGoodsQsmm(to.getGoodsQsmm());
        onlineTo.setSlideshow(to.getSlideshow());
        _log.info("从产品中上线商品的上线信息为: to-{}", onlineTo);

        onlOnlineSvc.onlineToPos(onlineTo);
        return new Ro(ResultDic.SUCCESS, "从产品中上线商品成功");
    }

    /**
     * 1：先获取产品信息
     * 2：获取规格信息
     */
    @Override
    public GetProductRo getProductById(Long id) {
        GetProductRo result = new GetProductRo();
        PrdProductMo mo = super.getById(id);
        _log.info("获取产品的结果为-{}", mo);
        result.setProductName(mo.getProductName());
        if (mo.getProductDetailPath() != null) {
            String readFileResult = iseSvc.readFileByByte(mo.getProductDetailPath());
            result.setProductDetail(readFileResult);
            _log.info("分页查询产品信息读取产品详情文件的返回值为：{}", readFileResult);
        }
        return result;
    }
}
