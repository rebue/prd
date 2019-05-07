package rebue.prd.mo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;

/**
 * 产品规格
 *
 * 数据库表: PRD_PRODUCT_SPEC
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@JsonInclude(Include.NON_NULL)
public class PrdProductSpecMo implements Serializable {

    /**
     *    产品规格ID
     *
     *    数据库字段: PRD_PRODUCT_SPEC.ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private Long id;

    /**
     *    产品ID
     *
     *    数据库字段: PRD_PRODUCT_SPEC.PRODUCT_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private Long productId;

    /**
     *    产品规格名称
     *
     *    数据库字段: PRD_PRODUCT_SPEC.NAME
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private String name;

    /**
     *    规格编码
     *
     *    数据库字段: PRD_PRODUCT_SPEC.CODE
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private String code;

    /**
     *    单位
     *
     *    数据库字段: PRD_PRODUCT_SPEC.UNIT
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private String unit;

    /**
     *    图片路径
     *
     *    数据库字段: PRD_PRODUCT_SPEC.PIC_PATH
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private String picPath;

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
     *    产品规格ID
     *
     *    数据库字段: PRD_PRODUCT_SPEC.ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Long getId() {
        return id;
    }

    /**
     *    产品规格ID
     *
     *    数据库字段: PRD_PRODUCT_SPEC.ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *    产品ID
     *
     *    数据库字段: PRD_PRODUCT_SPEC.PRODUCT_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Long getProductId() {
        return productId;
    }

    /**
     *    产品ID
     *
     *    数据库字段: PRD_PRODUCT_SPEC.PRODUCT_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    /**
     *    产品规格名称
     *
     *    数据库字段: PRD_PRODUCT_SPEC.NAME
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getName() {
        return name;
    }

    /**
     *    产品规格名称
     *
     *    数据库字段: PRD_PRODUCT_SPEC.NAME
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *    规格编码
     *
     *    数据库字段: PRD_PRODUCT_SPEC.CODE
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getCode() {
        return code;
    }

    /**
     *    规格编码
     *
     *    数据库字段: PRD_PRODUCT_SPEC.CODE
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     *    单位
     *
     *    数据库字段: PRD_PRODUCT_SPEC.UNIT
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getUnit() {
        return unit;
    }

    /**
     *    单位
     *
     *    数据库字段: PRD_PRODUCT_SPEC.UNIT
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     *    图片路径
     *
     *    数据库字段: PRD_PRODUCT_SPEC.PIC_PATH
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getPicPath() {
        return picPath;
    }

    /**
     *    图片路径
     *
     *    数据库字段: PRD_PRODUCT_SPEC.PIC_PATH
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setPicPath(String picPath) {
        this.picPath = picPath;
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
        sb.append(", productId=").append(productId);
        sb.append(", name=").append(name);
        sb.append(", code=").append(code);
        sb.append(", unit=").append(unit);
        sb.append(", picPath=").append(picPath);
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
        PrdProductSpecMo other = (PrdProductSpecMo) that;
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
