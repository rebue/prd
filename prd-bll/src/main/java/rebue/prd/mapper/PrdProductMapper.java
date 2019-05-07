package rebue.prd.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import rebue.prd.mo.PrdProductMo;
import rebue.robotech.mapper.MybatisBaseMapper;

@Mapper
public interface PrdProductMapper extends MybatisBaseMapper<PrdProductMo, Long> {

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int deleteByPrimaryKey(Long id);

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int insert(PrdProductMo record);

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int insertSelective(PrdProductMo record);

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    PrdProductMo selectByPrimaryKey(Long id);

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int updateByPrimaryKeySelective(PrdProductMo record);

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int updateByPrimaryKey(PrdProductMo record);

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    List<PrdProductMo> selectAll();

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    List<PrdProductMo> selectSelective(PrdProductMo record);

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    boolean existByPrimaryKey(Long id);

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    boolean existSelective(PrdProductMo record);

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int countSelective(PrdProductMo record);
}
