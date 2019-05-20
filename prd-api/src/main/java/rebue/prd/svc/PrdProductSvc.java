package rebue.prd.svc;

import com.github.pagehelper.PageInfo;

import rebue.prd.jo.PrdProductJo;
import rebue.prd.mo.PrdProductMo;
import rebue.prd.ro.PrdProductListRo;
import rebue.prd.to.AddProductTo;
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
	 * 查询产品分页信息
	 * 
	 * @param mo
	 * @param pageNum
	 * @param pageSize
	 * @param orderBy
	 * @return
	 */
	PageInfo<PrdProductListRo> pageList(PrdProductMo mo, Integer pageNum, int pageSize, String orderBy);
}
