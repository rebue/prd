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

import rebue.prd.dao.PrdProductCategoryDao;
import rebue.prd.jo.PrdProductCategoryJo;
import rebue.prd.mapper.PrdProductCategoryMapper;
import rebue.prd.mo.PrdProductCategoryMo;
import rebue.prd.ro.PrdProductCategoryTreeRo;
import rebue.prd.svc.PrdProductCategorySvc;
import rebue.robotech.svc.impl.BaseSvcImpl;

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
}
