package rebue.prd.to;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import rebue.prd.mo.PrdProductPicMo;

@Data
@JsonInclude(Include.NON_NULL)
public class AddProductTo {

	/**
	 * 产品分类ID
	 */
	private Long categoryId;

	/**
	 * 产品名称
	 */
	private String productName;

	/**
	 * 生产厂家
	 */
	private String manufacturer;

	/**
	 * 品牌
	 */
	private String brand;

	/**
	 * 产品详情路径
	 */
	private String productDetail;
	
	/**
	 * 操作人ID
	 */
	private Long opId;

	/**
	 * 产品规格信息
	 */
	private List<AddProductSpecTo> spec;

	/**
	 * 产品图片
	 */
	private List<PrdProductPicMo> pics;
}
