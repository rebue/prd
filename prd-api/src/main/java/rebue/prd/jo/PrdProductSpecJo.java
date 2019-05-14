package rebue.prd.jo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The persistent class for the PRD_PRODUCT_SPEC database table.
 * @mbg.generated 自动生成，如需修改，请删除本行
 */
@Entity
@Table(name = "PRD_PRODUCT_SPEC")
@Getter
@Setter
@ToString
public class PrdProductSpecJo implements Serializable {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
     *  产品规格ID
     *
     *  @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Id
    @Basic(optional = false)
    @Column(name = "ID", nullable = false, length = 19)
    private Long id;

    /**
     *  产品规格名称
     *
     *  @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = false)
    @Column(name = "NAME", nullable = false, length = 50)
    private String name;

    /**
     *  单位
     *
     *  @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = true)
    @Column(name = "UNIT", nullable = true, length = 20)
    private String unit;

    /**
     *  市场价格
     *
     *  @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = true)
    @Column(name = "MARKET_PRICE", nullable = true, precision = 20, scale = 4)
    private BigDecimal marketPrice;

    /**
     *  图片路径
     *
     *  @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Basic(optional = true)
    @Column(name = "PIC_PATH", nullable = true, length = 100)
    private String picPath;

    /**
     *  产品
     *
     *  @mbg.generated 自动生成，如需修改，请删除本行
     */
    @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private PrdProductJo product;

    /**
     *  列表
     *
     *  @mbg.generated 自动生成，如需修改，请删除本行
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productSpec")
    private List<PrdProductSpecAttrJo> prdProductSpecAttrList;

    /**
     *  列表
     *
     *  @mbg.generated 自动生成，如需修改，请删除本行
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productSpec")
    private List<PrdProductSpecCodeJo> prdProductSpecCodeList;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PrdProductSpecJo other = (PrdProductSpecJo) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}
