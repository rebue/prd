package rebue.prd.so;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import rebue.robotech.so.So;

/**
 * 产品搜索对象
 * 
 */
@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class PrdProductSo extends So implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 产品分类ID
     */
    private Long categoryId;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 是否启用
     */
    private Boolean isEnabled;

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
    private String productDetailPath;

    /**
     * 操作人
     */
    private Long opId;

    /**
     * 创建时间
     */

    private Date createTime;

}