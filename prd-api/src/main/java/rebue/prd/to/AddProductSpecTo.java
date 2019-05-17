package rebue.prd.to;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class AddProductSpecTo {

	/**
	 * 产品规格名称
	 */
	private String productSpecName;

	/**
	 * 产品规格单位
	 */
	private String unit;

	/**
	 * 市场价格
	 */
	private BigDecimal marketPrice;

	/**
	 * 产品规格图片路径
	 */
	private String productSpecPicPath;

	/**
	 * 产品规格编码
	 */
	private String productSpecCode;
}
