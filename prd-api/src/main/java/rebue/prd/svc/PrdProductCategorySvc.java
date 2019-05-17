package rebue.prd.svc;

import java.util.List;

import rebue.prd.jo.PrdProductCategoryJo;
import rebue.prd.mo.PrdProductCategoryMo;
import rebue.prd.to.PrdProductCategoryTreeRo;
import rebue.robotech.svc.BaseSvc;

/**
 * 产品分类
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
public interface PrdProductCategorySvc extends BaseSvc<java.lang.Long, PrdProductCategoryMo, PrdProductCategoryJo> {

	/**
	 * 获取产品分类树
	 * 
	 * @return
	 */
	List<PrdProductCategoryTreeRo> categoryTree();

	/**
	 * 根据分类编码获取产品分类树
	 * 
	 * @param code
	 * @return
	 */
	List<PrdProductCategoryTreeRo> categoryTreeByCode(String code);

}
