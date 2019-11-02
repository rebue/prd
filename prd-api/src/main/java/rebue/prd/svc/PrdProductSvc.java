package rebue.prd.svc;

import com.github.pagehelper.PageInfo;

import rebue.prd.jo.PrdProductJo;
import rebue.prd.mo.PrdProductMo;
import rebue.prd.ro.PrdProductListRo;
import rebue.prd.to.AddProductTo;
import rebue.prd.to.ImportTo;
import rebue.prd.to.ModifyProductTo;
import rebue.prd.to.OnlineProductTo;
import rebue.robotech.ro.Ro;
import rebue.robotech.svc.BaseSvc;

/**
 * 产品
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
public interface PrdProductSvc extends BaseSvc<java.lang.Long, PrdProductMo, PrdProductJo> {

    /**
     * 添加产品信息
     * 
     * @param to
     * @return
     */
    Ro addProduct(AddProductTo to);

    /**
     * 修改产品信息
     * 
     * @param to
     * @return
     */
    Ro modifyProduct(ModifyProductTo to);

    /**
     * 查询产品分页信息
     * 
     * @param mo
     * @param pageNum
     * @param pageSize
     * @param orderBy
     * @return
     */
    PageInfo<PrdProductListRo> pageList(PrdProductMo mo, Integer pageNum, int pageSize, String orderBy);

    /**
     * 禁用或启用产品
     * 
     * @param id
     *                  产品ID
     * @param isEnabled
     * @return
     */
    Ro enable(Long id, Boolean isEnabled);

    /**
     * 导入产品
     * 
     * @param to
     * @return
     */
    Ro importProduct(ImportTo to);

    /**
     * 从产品中上线商品
     * 
     * @param to
     * @return
     */
    Ro onlineFormProduct(OnlineProductTo to);
}
