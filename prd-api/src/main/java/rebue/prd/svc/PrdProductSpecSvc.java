package rebue.prd.svc;

import java.util.List;

import rebue.prd.jo.PrdProductSpecJo;
import rebue.prd.mo.PrdProductSpecMo;
import rebue.prd.to.AddProductSpecTo;
import rebue.robotech.svc.BaseSvc;

/**
 * 产品规格
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
public interface PrdProductSpecSvc extends BaseSvc<java.lang.Long, PrdProductSpecMo, PrdProductSpecJo> {

	/**
	 * 重写查询产品规格信息
	 * @param mo
	 * @return
	 */
	List<AddProductSpecTo> listEx(PrdProductSpecMo mo);
}
