package rebue.prd.so;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import rebue.robotech.so.So;

/**
 * 产品规格搜索对象
 */
@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class PrdProductSpecSo extends So implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 产品ID
     */
    private Long productId;

    /**
     * 产品规格名称
     */
    private String name;

    /**
     * 单位
     */
    private String unit;

    /**
     * 市场价格
     */
    private BigDecimal marketPrice;

    /**
     * 图片路径
     */
    private String picPath;

}