package rebue.prd.svc;

import rebue.prd.jo.PrdProductJo;
import rebue.prd.mo.PrdProductMo;
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
}
