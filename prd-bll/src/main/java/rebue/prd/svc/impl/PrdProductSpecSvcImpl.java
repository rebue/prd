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

import rebue.prd.dao.PrdProductSpecDao;
import rebue.prd.jo.PrdProductSpecJo;
import rebue.prd.mapper.PrdProductSpecMapper;
import rebue.prd.mo.PrdProductSpecCodeMo;
import rebue.prd.mo.PrdProductSpecMo;
import rebue.prd.so.PrdProductSpecSo;
import rebue.prd.svc.PrdProductSpecCodeSvc;
import rebue.prd.svc.PrdProductSpecEsSvc;
import rebue.prd.svc.PrdProductSpecSvc;
import rebue.prd.to.ModifyProductSpecTo;
import rebue.robotech.svc.impl.BaseSvcImpl;

/**
 * 产品规格
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
public class PrdProductSpecSvcImpl
        extends BaseSvcImpl<java.lang.Long, PrdProductSpecJo, PrdProductSpecDao, PrdProductSpecMo, PrdProductSpecMapper>
        implements PrdProductSpecSvc {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final Logger _log = LoggerFactory.getLogger(PrdProductSpecSvcImpl.class);

    @Resource
    private PrdProductSpecCodeSvc prdProductSpecCodeSvc;

    @Resource
    private PrdProductSpecSvc thisSvc;

    @Resource
    private Mapper dozerMapper;

    @Resource
    private PrdProductSpecEsSvc prdProductSpecEsSvc;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public int add(PrdProductSpecMo mo) {
        _log.info("添加产品规格");
        // 如果id为空那么自动生成分布式id
        if (mo.getId() == null || mo.getId() == 0) {
            mo.setId(_idWorker.getId());
        }
        final int rowCount = super.add(mo);
        // 修改成功时修改搜索引擎中的参数
        if (rowCount == 1) {
            prdProductSpecEsSvc.add(dozerMapper.map(mo, PrdProductSpecSo.class));
        }
        return rowCount;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public int modify(final PrdProductSpecMo mo) {
        _log.info("svc.modify: mo-{}", mo);
        final int rowCount = super.modify(mo);
        // 修改成功时修改搜索引擎中的参数
        if (rowCount == 1) {
            prdProductSpecEsSvc.modify(dozerMapper.map(mo, PrdProductSpecSo.class));
        }
        return rowCount;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public int del(final Long id) {
        _log.info("svc.del: id-{}", id);
        final int rowCount = super.del(id);
        if (rowCount == 1) {
            prdProductSpecEsSvc.del(id.toString());
        }
        return rowCount;
    }

    /**
     * 重写查询产品规格信息
     * 
     * @param mo
     * @return
     */
    @Override
    public List<ModifyProductSpecTo> listEx(PrdProductSpecMo mo) {
        _log.info("listEx PrdProductSpecMo：{}", mo);
        List<ModifyProductSpecTo> list            = new ArrayList<ModifyProductSpecTo>();
        List<PrdProductSpecMo>    productSpecList = thisSvc.list(mo);
        for (PrdProductSpecMo prdProductSpecMo : productSpecList) {
            ModifyProductSpecTo  productSpecTo     = dozerMapper.map(prdProductSpecMo, ModifyProductSpecTo.class);
            PrdProductSpecCodeMo productSpecCodeMo = new PrdProductSpecCodeMo();
            productSpecCodeMo.setProductSpecId(prdProductSpecMo.getId());
            PrdProductSpecCodeMo prdProductSpecCodeMo = prdProductSpecCodeSvc.getOne(productSpecCodeMo);
            if (prdProductSpecCodeMo != null) {
                productSpecTo.setProductSpecCodeId(prdProductSpecCodeMo.getId());
                productSpecTo.setCode(prdProductSpecCodeMo.getCode());
            }
            list.add(productSpecTo);
        }
        return list;
    }
}
