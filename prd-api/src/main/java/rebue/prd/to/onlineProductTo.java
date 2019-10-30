package rebue.prd.to;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import rebue.onl.to.OnlOnlineSpecTo;

@Data
@JsonInclude(Include.NON_NULL)
public class onlineProductTo {
    /**
     * 产品id
     */
    private Long productId;

    /**
     * 产品详情id
     */
    private Long productSpecId;

    /**
     * 上线id
     */
    private Long onlineId;

    /**
     * 上线详情id
     */
    private Long onlineSpecId;

    /**
     * 上线商品名称
     */
    private String onlineName;

    /**
     * 商品模块
     */
    private Byte subjectType;

    /**
     * 商品规格信息
     */
    private List<OnlOnlineSpecTo> onlineSpecs;

    /**
     * 商品主图
     */
    private String goodsQsmm;

    /**
     * 商品轮播图
     */
    private List<Map<String, Object>> slideshow;

    /**
     * 商品详情
     */
    private String onlineDetail;

    /**
     * 操作人id
     */
    private Long opId;

    /**
     * 供应商ID
     */
    private Long supplierId;

    /**
     * 发货组织ID(默认填入上线组织ID，可变更为供应商的ID)
     */
    private Long deliverOrgId;

    /**
     * 操作组织ID
     */
    private Long onlineOrgId;

    /**
     * 是否能修改供应商，0：否，1：是，2：是，且修改该商品未结算订单的供应商和发货组织。
     */
    private Byte isEditSupplier;

    /**
     * 上线规格属性名
     */
    private String[] attrNames;

    /**
     * 上线规格属性值
     */
    private String[][] attrValues;

    /**
     * 搜索分类id
     */
    private List<Long> classificationId;
}
