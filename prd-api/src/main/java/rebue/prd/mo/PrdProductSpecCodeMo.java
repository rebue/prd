package rebue.prd.mo;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;

/**
产品规格编码

数据库表: PRD_PRODUCT_SPEC_CODE

@mbg.generated 自动生成的注释，如需修改本注释，请删除本行
*/
@JsonInclude(Include.NON_NULL)
public class PrdProductSpecCodeMo implements Serializable {
    /**
    产品规格编码ID
    
    数据库字段: PRD_PRODUCT_SPEC_CODE.ID
    
    @mbg.generated 自动生成，如需修改，请删除本行
    */
    private Long id;

    /**
    产品规格ID
    
    数据库字段: PRD_PRODUCT_SPEC_CODE.PRODUCT_SPEC_ID
    
    @mbg.generated 自动生成，如需修改，请删除本行
    */
    private Long productSpecId;

    /**
    产品规格编码(目前存放的是条形码)
    
    数据库字段: PRD_PRODUCT_SPEC_CODE.CODE
    
    @mbg.generated 自动生成，如需修改，请删除本行
    */
    private String code;

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
    产品规格编码ID
    
    数据库字段: PRD_PRODUCT_SPEC_CODE.ID
    
    @mbg.generated 自动生成，如需修改，请删除本行
    */
    public Long getId() {
        return id;
    }

    /**
    产品规格编码ID
    
    数据库字段: PRD_PRODUCT_SPEC_CODE.ID
    
    @mbg.generated 自动生成，如需修改，请删除本行
    */
    public void setId(Long id) {
        this.id = id;
    }

    /**
    产品规格ID
    
    数据库字段: PRD_PRODUCT_SPEC_CODE.PRODUCT_SPEC_ID
    
    @mbg.generated 自动生成，如需修改，请删除本行
    */
    public Long getProductSpecId() {
        return productSpecId;
    }

    /**
    产品规格ID
    
    数据库字段: PRD_PRODUCT_SPEC_CODE.PRODUCT_SPEC_ID
    
    @mbg.generated 自动生成，如需修改，请删除本行
    */
    public void setProductSpecId(Long productSpecId) {
        this.productSpecId = productSpecId;
    }

    /**
    产品规格编码(目前存放的是条形码)
    
    数据库字段: PRD_PRODUCT_SPEC_CODE.CODE
    
    @mbg.generated 自动生成，如需修改，请删除本行
    */
    public String getCode() {
        return code;
    }

    /**
    产品规格编码(目前存放的是条形码)
    
    数据库字段: PRD_PRODUCT_SPEC_CODE.CODE
    
    @mbg.generated 自动生成，如需修改，请删除本行
    */
    public void setCode(String code) {
        this.code = code;
    }

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
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
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
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
        PrdProductSpecCodeMo other = (PrdProductSpecCodeMo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
        ;
    }

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        return result;
    }
}