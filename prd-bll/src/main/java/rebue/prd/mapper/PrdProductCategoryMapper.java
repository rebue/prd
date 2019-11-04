package rebue.prd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import rebue.prd.mo.PrdProductCategoryMo;
import rebue.prd.mo.PrdProductMo;
import rebue.robotech.mapper.MybatisBaseMapper;

@Mapper
public interface PrdProductCategoryMapper extends MybatisBaseMapper<PrdProductCategoryMo, Long> {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    int deleteByPrimaryKey(Long id);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    int insert(PrdProductCategoryMo record);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    int insertSelective(PrdProductCategoryMo record);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    PrdProductCategoryMo selectByPrimaryKey(Long id);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    int updateByPrimaryKeySelective(PrdProductCategoryMo record);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    int updateByPrimaryKey(PrdProductCategoryMo record);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    List<PrdProductCategoryMo> selectAll();

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    List<PrdProductCategoryMo> selectSelective(PrdProductCategoryMo record);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    boolean existByPrimaryKey(Long id);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    boolean existSelective(PrdProductCategoryMo record);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    int countSelective(PrdProductCategoryMo record);

    /**
     * 查询顶级分类信息
     *
     * @return
     */
    @Select("SELECT * FROM PRD_PRODUCT_CATEGORY where code like '__' and IS_ENABLED = true")
    List<PrdProductCategoryMo> selectTopProductCategory();

    /**
     * 查询子分类信息
     *
     * @return
     */
    @Select("SELECT * FROM PRD_PRODUCT_CATEGORY where code like '${code}__' and IS_ENABLED = true")
    List<PrdProductCategoryMo> selectSonProductCategory(@Param("code") String code);

    /**
     * 根据分类编码查询分类数量
     * 
     * @param code 分类编码
     * @return
     */
    @Select("select count(*) from PRD_PRODUCT_CATEGORY where CODE like '${code}__'")
    int countBySellerAndShopAndCode(@Param("code") String code);

    /**
     * 禁用/启用产品搜索分类 注：该方法会禁用/启用该分类和该分类下的所有子类
     * 
     * @return
     */
    @Update("update PRD_PRODUCT_CATEGORY set IS_ENABLED=#{isEnabled,jdbcType=TINYINT} where CODE like '${code}%'")
    int enable(@Param("code") String code, @Param("isEnabled") Boolean isEnabled);
    
    
    @Select("select * from PRD_PRODUCT_CATEGORY where code like '${code}__'")
    PrdProductCategoryMo getPrdProductCategoryByCode(@Param("code") String code);
}
