package rebue.prd.to;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class AddonlineByCodeTo {
    /**
     * 商品条码
     */
    private String  barcode;
    /** 上线详情名称(旧称上线规格名称) */
    private String  name;
    /** 是否称重商品 */
    private Boolean isWeighGoods;
    /** 销售单位 */
    private String  saleUnit;
    /** 销售价格(单价) */
    private String  salePrice;
    /** 分类名称 */
    private String  categoryName;
    /** 分类id */
    private String  categoryId;
    /** 库存 */
    private String  saleCount;
    /**
     * 操作人
     */
    private String  opId;
    /** 店铺id */
    private String  shopId;
}
