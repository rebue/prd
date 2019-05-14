package rebue.prd.mo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

/**
产品分类

数据库表: PRD_PRODUCT_CATEGORY

@mbg.generated 自动生成的注释，如需修改本注释，请删除本行
*/
@JsonInclude(Include.NON_NULL)
public class PrdProductCategoryMo implements Serializable {
    /**
    产品分类ID
    
    数据库字段: PRD_PRODUCT_CATEGORY.ID
    
    @mbg.generated 自动生成，如需修改，请删除本行
    */
    private Long id;

    /**
    分类名称
    
    数据库字段: PRD_PRODUCT_CATEGORY.NAME
    
    @mbg.generated 自动生成，如需修改，请删除本行
    */
    private String name;

    /**
    分类编码
    
    数据库字段: PRD_PRODUCT_CATEGORY.CODE
    
    @mbg.generated 自动生成，如需修改，请删除本行
    */
    private String code;

    /**
    是否启用
    
    数据库字段: PRD_PRODUCT_CATEGORY.IS_ENABLED
    
    @mbg.generated 自动生成，如需修改，请删除本行
    */
    private Boolean isEnabled;

    /**
    操作人ID
    
    数据库字段: PRD_PRODUCT_CATEGORY.OP_ID
    
    @mbg.generated 自动生成，如需修改，请删除本行
    */
    private Long opId;

    /**
    创建时间
    
    数据库字段: PRD_PRODUCT_CATEGORY.CREATE_TIME
    
    @mbg.generated 自动生成，如需修改，请删除本行
    */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
    产品分类ID
    
    数据库字段: PRD_PRODUCT_CATEGORY.ID
    
    @mbg.generated 自动生成，如需修改，请删除本行
    */
    public Long getId() {
        return id;
    }

    /**
    产品分类ID
    
    数据库字段: PRD_PRODUCT_CATEGORY.ID
    
    @mbg.generated 自动生成，如需修改，请删除本行
    */
    public void setId(Long id) {
        this.id = id;
    }

    /**
    分类名称
    
    数据库字段: PRD_PRODUCT_CATEGORY.NAME
    
    @mbg.generated 自动生成，如需修改，请删除本行
    */
    public String getName() {
        return name;
    }

    /**
    分类名称
    
    数据库字段: PRD_PRODUCT_CATEGORY.NAME
    
    @mbg.generated 自动生成，如需修改，请删除本行
    */
    public void setName(String name) {
        this.name = name;
    }

    /**
    分类编码
    
    数据库字段: PRD_PRODUCT_CATEGORY.CODE
    
    @mbg.generated 自动生成，如需修改，请删除本行
    */
    public String getCode() {
        return code;
    }

    /**
    分类编码
    
    数据库字段: PRD_PRODUCT_CATEGORY.CODE
    
    @mbg.generated 自动生成，如需修改，请删除本行
    */
    public void setCode(String code) {
        this.code = code;
    }

    /**
    是否启用
    
    数据库字段: PRD_PRODUCT_CATEGORY.IS_ENABLED
    
    @mbg.generated 自动生成，如需修改，请删除本行
    */
    public Boolean getIsEnabled() {
        return isEnabled;
    }

    /**
    是否启用
    
    数据库字段: PRD_PRODUCT_CATEGORY.IS_ENABLED
    
    @mbg.generated 自动生成，如需修改，请删除本行
    */
    public void setIsEnabled(Boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    /**
    操作人ID
    
    数据库字段: PRD_PRODUCT_CATEGORY.OP_ID
    
    @mbg.generated 自动生成，如需修改，请删除本行
    */
    public Long getOpId() {
        return opId;
    }

    /**
    操作人ID
    
    数据库字段: PRD_PRODUCT_CATEGORY.OP_ID
    
    @mbg.generated 自动生成，如需修改，请删除本行
    */
    public void setOpId(Long opId) {
        this.opId = opId;
    }

    /**
    创建时间
    
    数据库字段: PRD_PRODUCT_CATEGORY.CREATE_TIME
    
    @mbg.generated 自动生成，如需修改，请删除本行
    */
    public Date getCreateTime() {
        return createTime;
    }

    /**
    创建时间
    
    数据库字段: PRD_PRODUCT_CATEGORY.CREATE_TIME
    
    @mbg.generated 自动生成，如需修改，请删除本行
    */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
        sb.append(", name=").append(name);
        sb.append(", code=").append(code);
        sb.append(", isEnabled=").append(isEnabled);
        sb.append(", opId=").append(opId);
        sb.append(", createTime=").append(createTime);
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
        PrdProductCategoryMo other = (PrdProductCategoryMo) that;
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