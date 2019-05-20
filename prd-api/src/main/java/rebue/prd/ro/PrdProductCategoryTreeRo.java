package rebue.prd.ro;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

/**
 * 产品搜索分类树
 * 
 * @author lbl
 *
 */
@Data
@JsonInclude(Include.NON_NULL)
public class PrdProductCategoryTreeRo {

	/**
	 * 产品分类ID
	 */
	private Long id;

	/**
	 * 产品分类名称
	 */
	private String name;

	/**
	 * 产品分类编码
	 */
	private String code;

	/**
	 * 产品子分类
	 */
	private List<PrdProductCategoryTreeRo> categoryList;
}
