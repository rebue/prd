package rebue.prd.ctrl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;

import rebue.prd.mo.PrdProductCategoryMo;
import rebue.prd.ro.PrdProductCategoryTreeRo;
import rebue.prd.svc.PrdProductCategorySvc;
import rebue.robotech.dic.ResultDic;
import rebue.robotech.ro.Ro;

/**
 * 产品分类
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@RestController
public class PrdProductCategoryCtrl {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final Logger _log = LoggerFactory.getLogger(PrdProductCategoryCtrl.class);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Resource
    private PrdProductCategorySvc svc;

    /**
     * 有唯一约束的字段名称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private String _uniqueFilesName = "某字段内容";

    /**
     * 添加产品分类
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PostMapping("/prd/productcategory")
    Ro add(@RequestBody PrdProductCategoryMo mo) throws Exception {
        _log.info("add PrdProductCategoryMo: {}", mo);
        Ro ro = new Ro();
        try {
            return svc.addEx(mo);
        } catch (DuplicateKeyException e) {
            String msg = "添加失败，" + _uniqueFilesName + "已存在，不允许出现重复";
            _log.error(msg + ": mo-" + mo, e);
            ro.setMsg(msg);
            ro.setResult(ResultDic.FAIL);
            return ro;
        } catch (RuntimeException e) {
            String msg = "添加失败，出现运行时异常";
            _log.error(msg + ": mo-" + mo, e);
            ro.setMsg(msg);
            ro.setResult(ResultDic.FAIL);
            return ro;
        }
    }

    /**
     * 修改产品分类
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PutMapping("/prd/productcategory")
    Ro modify(@RequestBody PrdProductCategoryMo mo) throws Exception {
        _log.info("modify PrdProductCategoryMo: {}", mo);
        Ro ro = new Ro();
        try {
            if (svc.modify(mo) == 1) {
                String msg = "修改成功";
                _log.info("{}: mo-{}", msg, mo);
                ro.setMsg(msg);
                ro.setResult(ResultDic.SUCCESS);
                return ro;
            } else {
                String msg = "修改失败";
                _log.error("{}: mo-{}", msg, mo);
                ro.setMsg(msg);
                ro.setResult(ResultDic.FAIL);
                return ro;
            }
        } catch (DuplicateKeyException e) {
            String msg = "修改失败，" + _uniqueFilesName + "已存在，不允许出现重复";
            _log.error(msg + ": mo=" + mo, e);
            ro.setMsg(msg);
            ro.setResult(ResultDic.FAIL);
            return ro;
        } catch (RuntimeException e) {
            String msg = "修改失败，出现运行时异常";
            _log.error(msg + ": mo-" + mo, e);
            ro.setMsg(msg);
            ro.setResult(ResultDic.FAIL);
            return ro;
        }
    }

    /**
     * 删除产品分类
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @DeleteMapping("/prd/productcategory")
    Ro del(@RequestParam("id") java.lang.Long id) {
        _log.info("del PrdProductCategoryMo by id: {}", id);
        int result = svc.del(id);
        Ro  ro     = new Ro();
        if (result == 1) {
            String msg = "删除成功";
            _log.info("{}: id-{}", msg, id);
            ro.setMsg(msg);
            ro.setResult(ResultDic.SUCCESS);
            return ro;
        } else {
            String msg = "删除失败，找不到该记录";
            _log.error("{}: id-{}", msg, id);
            ro.setMsg(msg);
            ro.setResult(ResultDic.FAIL);
            return ro;
        }
    }

    /**
     * 查询产品分类
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/prd/productcategory")
    PageInfo<PrdProductCategoryMo> list(PrdProductCategoryMo mo,
            @RequestParam(value = "pageNum", required = false) Integer pageNum,
            @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        if (pageNum == null)
            pageNum = 1;
        if (pageSize == null)
            pageSize = 5;
        _log.info("list PrdProductCategoryMo:" + mo + ", pageNum = " + pageNum + ", pageSize = " + pageSize);
        if (pageSize > 50) {
            String msg = "pageSize不能大于50";
            _log.error(msg);
            throw new IllegalArgumentException(msg);
        }
        PageInfo<PrdProductCategoryMo> result = svc.list(mo, pageNum, pageSize);
        _log.info("result: " + result);
        return result;
    }

    /**
     * 获取单个产品分类
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/prd/productcategory/getbyid")
    PrdProductCategoryMo getById(@RequestParam("id") java.lang.Long id) {
        _log.info("get PrdProductCategoryMo by id: {}", id);
        return svc.getById(id);
    }

    /**
     * 获取产品分类树
     *
     * @return
     */
    @GetMapping("/prd/productcategory/tree")
    List<PrdProductCategoryTreeRo> categoryTree() {
        return svc.categoryTree();
    }

    /**
     * 禁用/启用产品搜索分类 注：该方法会禁用/启用该分类和该分类下的所有子分类
     * 
     * @param mo
     * @return
     */
    @PutMapping("/prd/productcategory/enable")
    Ro enable(@RequestBody PrdProductCategoryMo mo) {
        _log.info("禁用/启用产品搜索分类的请求参数为：{}", mo);
        return svc.enable(mo);
    }
}
