package rebue.prd.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import rebue.prd.mo.PrdProductCategoryMo;
import rebue.robotech.mapper.MybatisBaseMapper;

@Mapper
public interface PrdProductCategoryMapper extends MybatisBaseMapper<PrdProductCategoryMo, Long> {
    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int deleteByPrimaryKey(Long id);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int insert(PrdProductCategoryMo record);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int insertSelective(PrdProductCategoryMo record);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    PrdProductCategoryMo selectByPrimaryKey(Long id);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int updateByPrimaryKeySelective(PrdProductCategoryMo record);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int updateByPrimaryKey(PrdProductCategoryMo record);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    List<PrdProductCategoryMo> selectAll();

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    List<PrdProductCategoryMo> selectSelective(PrdProductCategoryMo record);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    boolean existByPrimaryKey(Long id);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    boolean existSelective(PrdProductCategoryMo record);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int countSelective(PrdProductCategoryMo record);
}