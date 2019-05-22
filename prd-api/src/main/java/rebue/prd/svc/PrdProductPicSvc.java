package rebue.prd.svc;

import java.util.List;

import rebue.prd.jo.PrdProductPicJo;
import rebue.prd.mo.PrdProductPicMo;
import rebue.robotech.ro.Ro;
import rebue.robotech.svc.BaseSvc;

/**
 * 产品图片
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
public interface PrdProductPicSvc extends BaseSvc<java.lang.Long, PrdProductPicMo, PrdProductPicJo> {

	/**
	 * 根据产品id删除产品图片
	 * 
	 * @param productId
	 * @return
	 */
	Ro delByProductId(Long productId);

	/**
	 * 根据产品id批量添加产品图片
	 * 
	 * @param pics
	 * @return
	 */
	Ro batchAddPicByProductId(List<PrdProductPicMo> pics, Long productId);
}
