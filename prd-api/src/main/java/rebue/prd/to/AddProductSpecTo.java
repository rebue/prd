package rebue.prd.to;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import rebue.prd.mo.PrdProductSpecMo;

@Getter
@Setter
@ToString
@JsonInclude(Include.NON_NULL)
public class AddProductSpecTo extends PrdProductSpecMo {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4181669087676987210L;
	/**
	 * 产品规格编码
	 */
	private String code;
}
