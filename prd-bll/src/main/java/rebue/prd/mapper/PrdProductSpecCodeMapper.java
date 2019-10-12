package rebue.prd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import rebue.prd.mo.PrdProductSpecCodeMo;
import rebue.robotech.mapper.MybatisBaseMapper;

@Mapper
public interface PrdProductSpecCodeMapper extends MybatisBaseMapper<PrdProductSpecCodeMo, Long> {
    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    int deleteByPrimaryKey(Long id);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    int insert(PrdProductSpecCodeMo record);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    int insertSelective(PrdProductSpecCodeMo record);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    PrdProductSpecCodeMo selectByPrimaryKey(Long id);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    int updateByPrimaryKeySelective(PrdProductSpecCodeMo record);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    int updateByPrimaryKey(PrdProductSpecCodeMo record);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    List<PrdProductSpecCodeMo> selectAll();

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    List<PrdProductSpecCodeMo> selectSelective(PrdProductSpecCodeMo record);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    boolean existByPrimaryKey(Long id);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    boolean existSelective(PrdProductSpecCodeMo record);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    int countSelective(PrdProductSpecCodeMo record);

    @Select("SELECT * FROM PRD_PRODUCT_SPEC_CODE where CODE like CONCAT('%',#{code}) ;")
    List<PrdProductSpecCodeMo> selectByCode(@Param("code") String code);
}