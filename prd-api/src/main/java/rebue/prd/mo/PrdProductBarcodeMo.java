package rebue.prd.mo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;

/**
 * 产品条形码
 *
 * 数据库表: PRD_PRODUCT_BARCODE
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@JsonInclude(Include.NON_NULL)
public class PrdProductBarcodeMo implements Serializable {

    /**
     *    产品条形码ID
     *
     *    数据库字段: PRD_PRODUCT_BARCODE.ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private Long id;

    /**
     *    产品规格ID
     *
     *    数据库字段: PRD_PRODUCT_BARCODE.PRODUCT_SPEC_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private Long productSpecId;

    /**
     *    编码
     *
     *    数据库字段: PRD_PRODUCT_BARCODE.CODE
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private String code;

    /**
     *    店铺ID
     *
     *    数据库字段: PRD_PRODUCT_BARCODE.SHOP_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private Long shopId;

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
     *    产品条形码ID
     *
     *    数据库字段: PRD_PRODUCT_BARCODE.ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Long getId() {
        return id;
    }

    /**
     *    产品条形码ID
     *
     *    数据库字段: PRD_PRODUCT_BARCODE.ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *    产品规格ID
     *
     *    数据库字段: PRD_PRODUCT_BARCODE.PRODUCT_SPEC_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Long getProductSpecId() {
        return productSpecId;
    }

    /**
     *    产品规格ID
     *
     *    数据库字段: PRD_PRODUCT_BARCODE.PRODUCT_SPEC_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setProductSpecId(Long productSpecId) {
        this.productSpecId = productSpecId;
    }

    /**
     *    编码
     *
     *    数据库字段: PRD_PRODUCT_BARCODE.CODE
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getCode() {
        return code;
    }

    /**
     *    编码
     *
     *    数据库字段: PRD_PRODUCT_BARCODE.CODE
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     *    店铺ID
     *
     *    数据库字段: PRD_PRODUCT_BARCODE.SHOP_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Long getShopId() {
        return shopId;
    }

    /**
     *    店铺ID
     *
     *    数据库字段: PRD_PRODUCT_BARCODE.SHOP_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", productSpecId=").append(productSpecId);
        sb.append(", code=").append(code);
        sb.append(", shopId=").append(shopId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        PrdProductBarcodeMo other = (PrdProductBarcodeMo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()));
    }

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        return result;
    }
}
