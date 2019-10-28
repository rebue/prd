package rebue.prd.svc.impl;

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

import rebue.prd.dao.PrdProductCategoryDao;
import rebue.prd.jo.PrdProductCategoryJo;
import rebue.prd.mapper.PrdProductCategoryMapper;
import rebue.prd.mo.PrdProductCategoryMo;
import rebue.prd.ro.PrdProductCategoryTreeRo;
import rebue.prd.svc.PrdProductCategorySvc;
import rebue.robotech.dic.ResultDic;
import rebue.robotech.ro.Ro;
import rebue.robotech.svc.impl.BaseSvcImpl;
import rebue.wheel.StrUtils;

/**
 * 产品分类
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
public class PrdProductCategorySvcImpl extends
        BaseSvcImpl<java.lang.Long, PrdProductCategoryJo, PrdProductCategoryDao, PrdProductCategoryMo, PrdProductCategoryMapper>
        implements PrdProductCategorySvc {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final Logger _log = LoggerFactory.getLogger(PrdProductCategorySvcImpl.class);

    @Resource
    private Mapper dozerMapper;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public int add(PrdProductCategoryMo mo) {
        _log.info("添加产品分类");
        // 如果id为空那么自动生成分布式id
        if (mo.getId() == null || mo.getId() == 0) {
            mo.setId(_idWorker.getId());
        }
        final int rowCount = super.add(mo);
        return rowCount;
    }

    /**
     * 添加产品分类 流程： 1、判断参数 2、判断code是否为null，如果为null说明为顶级分类，否则为子类
     * 3、如果为顶级分类时，如果数量小于10（不包含10）， 则前面补0
     * 4、如果为子类时，根据传过来的code查询该子类下面的分类，如果数量小于10（不包含10）， 则前面补0 5、添加店铺搜索分类
     * 注意：顶级分类为两位数，子类则在父类下面补两位
     * 
     * @param mo
     * @return
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Ro addEx(PrdProductCategoryMo mo) {
        _log.info("添加产品分类的参数为：{}", mo);
        Ro ro = new Ro();
        if (mo.getFullName() == null || mo.getName() == null) {
            _log.error("添加产品分类时出现参数错误，请求的参数为：{}", mo);
            ro.setResult(ResultDic.PARAM_ERROR);
            ro.setMsg("参数错误");
            return ro;
        }

        _log.info("添加产品分类根据分类编码查询分类数量的参数为：code-{}", mo.getCode());
        int count = _mapper.countBySellerAndShopAndCode(mo.getCode());
        _log.info("添加产品分类根据分类编码查询分类数量的返回值为：{}", count);
        // 分类编码
        String code = StrUtils.padLeft(String.valueOf(count), 2, '0');
        // 如果添加的分类为子类，则先计算子类的code在与父类的code拼接
        if (mo.getCode() != null && !"".equals(mo.getCode())) {
            code = mo.getCode() + code;
        }
        mo.setCode(code);
        mo.setCreateTime(new Date());
        _log.info("添加产品分类的参数为：{}", mo);
        int addResult = thisSvc.add(mo);
        _log.info("添加产品分类的返回值为：{}", addResult);
        if (addResult != 1) {
            _log.error("添加产品分类时出现错误，请求的参数为：{}", mo);
            throw new RuntimeException("添加出错");
        }

        _log.info("添加产品分类成功，请求的参数为：{}", mo);
        ro.setResult(ResultDic.SUCCESS);
        ro.setMsg("添加成功");
        return ro;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public int modify(final PrdProductCategoryMo mo) {
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

    @Resource
    private PrdProductCategorySvc thisSvc;

    /**
     * 获取产品分类树
     *
     * @return
     */
    @Override
    public List<PrdProductCategoryTreeRo> categoryTree() {
        _log.info("-------------开始获取产品分类-------------");
        List<PrdProductCategoryTreeRo> list = new ArrayList<PrdProductCategoryTreeRo>();
        // 查询顶级分类信息
        List<PrdProductCategoryMo> topProductCategoryList = _mapper.selectTopProductCategory();
        _log.info("获取产品分类查询顶级分类信息的返回值为：{}", String.valueOf(topProductCategoryList));
        for (PrdProductCategoryMo prdProductCategoryMo : topProductCategoryList) {
            PrdProductCategoryTreeRo       productCategoryTreeRo  = dozerMapper.map(prdProductCategoryMo,
                    PrdProductCategoryTreeRo.class);
            List<PrdProductCategoryTreeRo> categoryTreeByCodeList = thisSvc
                    .categoryTreeByCode(prdProductCategoryMo.getCode());
            if (categoryTreeByCodeList.size() != 0) {
                productCategoryTreeRo.setCategoryList(categoryTreeByCodeList);
            }
            list.add(productCategoryTreeRo);
        }
        return list;
    }

    /**
     * 根据分类编码获取产品分类树
     *
     * @param code
     * @return
     */
    @Override
    public List<PrdProductCategoryTreeRo> categoryTreeByCode(String code) {
        _log.info("根据分类编码获取产品分类树的参数为：{}", code);
        List<PrdProductCategoryTreeRo> list                   = new ArrayList<PrdProductCategoryTreeRo>();
        List<PrdProductCategoryMo>     sonProductCategoryList = _mapper.selectSonProductCategory(code);
        _log.info("根据分类编码获取产品分类树的返回值为：{}", String.valueOf(sonProductCategoryList));
        for (PrdProductCategoryMo prdProductCategoryMo : sonProductCategoryList) {
            PrdProductCategoryTreeRo       productCategoryTreeRo  = dozerMapper.map(prdProductCategoryMo,
                    PrdProductCategoryTreeRo.class);
            List<PrdProductCategoryTreeRo> categoryTreeByCodeList = categoryTreeByCode(prdProductCategoryMo.getCode());
            if (categoryTreeByCodeList.size() != 0) {
                productCategoryTreeRo.setCategoryList(categoryTreeByCodeList);
            }
            list.add(productCategoryTreeRo);
        }
        return list;
    }

    /**
     * 禁用/启用产品搜索分类 注：该方法会禁用/启用该分类和该分类下的所有子类
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Ro enable(PrdProductCategoryMo mo) {
        _log.info("禁用/启用产品搜索分类的参数为：{}", mo);
        Ro ro = new Ro();
        if (mo.getCode() == null || mo.getIsEnabled() == null) {
            _log.error("禁用/启用产品搜索分类时出现参数错误，请求的参数为：sellerId-{}, shopId-{}, code-{}", mo);
            ro.setResult(ResultDic.PARAM_ERROR);
            ro.setMsg("参数错误");
            return ro;
        }

        int enableResult = _mapper.enable(mo.getCode(), mo.getIsEnabled());
        _log.info("禁用/启用产品搜索分类的返回值为：{}", enableResult);
        if (enableResult < 0) {
            _log.error("禁用/启用产品搜索分类时出现错误，请求的参数为：{}", mo);
            ro.setResult(ResultDic.FAIL);
            ro.setMsg("设置失败");
            return ro;
        }

        _log.info("禁用/启用产品搜索分类成功，请求的参数为：{}", mo);
        ro.setResult(ResultDic.SUCCESS);
        ro.setMsg("设置成功");
        return ro;
    }
}
