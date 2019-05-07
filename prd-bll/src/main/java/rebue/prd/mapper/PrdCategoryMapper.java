package rebue.prd.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import rebue.prd.mo.PrdCategoryMo;
import rebue.robotech.mapper.MybatisBaseMapper;

@Mapper
public interface PrdCategoryMapper extends MybatisBaseMapper<PrdCategoryMo, Long> {
    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int deleteByPrimaryKey(Long id);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int insert(PrdCategoryMo record);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int insertSelective(PrdCategoryMo record);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    PrdCategoryMo selectByPrimaryKey(Long id);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int updateByPrimaryKeySelective(PrdCategoryMo record);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int updateByPrimaryKey(PrdCategoryMo record);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    List<PrdCategoryMo> selectAll();

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    List<PrdCategoryMo> selectSelective(PrdCategoryMo record);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    boolean existByPrimaryKey(Long id);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    boolean existSelective(PrdCategoryMo record);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int countSelective(PrdCategoryMo record);
}