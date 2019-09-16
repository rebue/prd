package rebue.prd.ro;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import rebue.prd.mo.PrdProductMo;

@Data
@JsonInclude(Include.NON_NULL)
public class ProductDetailRo {

    /**
     * 产品规格ID
     */
    private Long id;

    /**
     * 规格名称
     */
    private String spec;

    /**
     * 销售单价(产品规格里面的市场价格)
     */
    private BigDecimal salePrice;
    /**
     * 单位
     */
    private String     saleUnit;

    /**
     * 产品信息
     */
    private PrdProductMo prdProductMo;
}
