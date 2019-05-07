package rebue.prd.mo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 产品
 *
 * 数据库表: PRD_PRODUCT
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@JsonInclude(Include.NON_NULL)
public class PrdProductMo implements Serializable {

    /**
     *    产品ID
     *
     *    数据库字段: PRD_PRODUCT.ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private Long id;

    /**
     *    产品分类ID
     *
     *    数据库字段: PRD_PRODUCT.CATEGORY_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private Long categoryId;

    /**
     *    产品名称
     *
     *    数据库字段: PRD_PRODUCT.PRODUCT_NAME
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private String productName;

    /**
     *    是否启用
     *
     *    数据库字段: PRD_PRODUCT.IS_ENABLED
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private Boolean isEnabled;

    /**
     *    生产厂家
     *
     *    数据库字段: PRD_PRODUCT.MANUFACTURER
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private String manufacturer;

    /**
     *    品牌
     *
     *    数据库字段: PRD_PRODUCT.BRAND
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private String brand;

    /**
     *    操作人
     *
     *    数据库字段: PRD_PRODUCT.OP_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private Long opId;

    /**
     *    创建时间
     *
     *    数据库字段: PRD_PRODUCT.CREATE_TIME
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     *    产品描述
     *
     *    数据库字段: PRD_PRODUCT.PRODUCT_DESC
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private String productDesc;

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
     *    产品ID
     *
     *    数据库字段: PRD_PRODUCT.ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Long getId() {
        return id;
    }

    /**
     *    产品ID
     *
     *    数据库字段: PRD_PRODUCT.ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *    产品分类ID
     *
     *    数据库字段: PRD_PRODUCT.CATEGORY_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Long getCategoryId() {
        return categoryId;
    }

    /**
     *    产品分类ID
     *
     *    数据库字段: PRD_PRODUCT.CATEGORY_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    /**
     *    产品名称
     *
     *    数据库字段: PRD_PRODUCT.PRODUCT_NAME
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getProductName() {
        return productName;
    }

    /**
     *    产品名称
     *
     *    数据库字段: PRD_PRODUCT.PRODUCT_NAME
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     *    是否启用
     *
     *    数据库字段: PRD_PRODUCT.IS_ENABLED
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Boolean getIsEnabled() {
        return isEnabled;
    }

    /**
     *    是否启用
     *
     *    数据库字段: PRD_PRODUCT.IS_ENABLED
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setIsEnabled(Boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    /**
     *    生产厂家
     *
     *    数据库字段: PRD_PRODUCT.MANUFACTURER
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     *    生产厂家
     *
     *    数据库字段: PRD_PRODUCT.MANUFACTURER
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    /**
     *    品牌
     *
     *    数据库字段: PRD_PRODUCT.BRAND
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getBrand() {
        return brand;
    }

    /**
     *    品牌
     *
     *    数据库字段: PRD_PRODUCT.BRAND
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     *    操作人
     *
     *    数据库字段: PRD_PRODUCT.OP_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Long getOpId() {
        return opId;
    }

    /**
     *    操作人
     *
     *    数据库字段: PRD_PRODUCT.OP_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setOpId(Long opId) {
        this.opId = opId;
    }

    /**
     *    创建时间
     *
     *    数据库字段: PRD_PRODUCT.CREATE_TIME
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     *    创建时间
     *
     *    数据库字段: PRD_PRODUCT.CREATE_TIME
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     *    产品描述
     *
     *    数据库字段: PRD_PRODUCT.PRODUCT_DESC
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getProductDesc() {
        return productDesc;
    }

    /**
     *    产品描述
     *
     *    数据库字段: PRD_PRODUCT.PRODUCT_DESC
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
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
        sb.append(", categoryId=").append(categoryId);
        sb.append(", productName=").append(productName);
        sb.append(", isEnabled=").append(isEnabled);
        sb.append(", manufacturer=").append(manufacturer);
        sb.append(", brand=").append(brand);
        sb.append(", opId=").append(opId);
        sb.append(", createTime=").append(createTime);
        sb.append(", productDesc=").append(productDesc);
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
        PrdProductMo other = (PrdProductMo) that;
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
