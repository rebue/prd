package rebue.prd.ro;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import rebue.prd.mo.PrdProductMo;

@Getter
@Setter
@ToString
@JsonInclude(Include.NON_NULL)
public class PrdProductListRo extends PrdProductMo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9109528460020386888L;

	/**
	 * 产品分类
	 */
	private String fullName;

	/**
	 * 操作人
	 */
	private String opName;

	/**
	 * 产品详情
	 */
	private String productDetail;
}
