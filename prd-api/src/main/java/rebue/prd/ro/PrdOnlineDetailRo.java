package rebue.prd.ro;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import rebue.onl.mo.OnlOnlineMo;

/**
 * 上线详情Ro
 * 
 * @author jjl
 *
 */
@Data
@JsonInclude(Include.NON_NULL)
public class PrdOnlineDetailRo {

    /**
     * 上线规格ID
     */
    private Long onlineSpecId;

    /**
     * 上线ID
     */
    private Long   onlineId;
    /**
     * 上线规格名称
     */
    private String spec;

    /**
     * 销售单价
     */
    private BigDecimal salePrice;
    /**
     * 单位
     */
    private String     saleUnit;

    /**
     * 上线信息
     */
    private OnlOnlineMo onlOnlineMo;
}
