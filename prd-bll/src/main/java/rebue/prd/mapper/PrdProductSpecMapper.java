package rebue.prd.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import rebue.prd.mo.PrdProductSpecMo;
import rebue.robotech.mapper.MybatisBaseMapper;

@Mapper
public interface PrdProductSpecMapper extends MybatisBaseMapper<PrdProductSpecMo, Long> {

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int deleteByPrimaryKey(Long id);

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int insert(PrdProductSpecMo record);

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int insertSelective(PrdProductSpecMo record);

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    PrdProductSpecMo selectByPrimaryKey(Long id);

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int updateByPrimaryKeySelective(PrdProductSpecMo record);

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int updateByPrimaryKey(PrdProductSpecMo record);

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    List<PrdProductSpecMo> selectAll();

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    List<PrdProductSpecMo> selectSelective(PrdProductSpecMo record);

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    boolean existByPrimaryKey(Long id);

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    boolean existSelective(PrdProductSpecMo record);

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int countSelective(PrdProductSpecMo record);
}
