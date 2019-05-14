package rebue.prd.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import rebue.prd.mo.PrdProductSpecCodeMo;
import rebue.robotech.mapper.MybatisBaseMapper;

@Mapper
public interface PrdProductSpecCodeMapper extends MybatisBaseMapper<PrdProductSpecCodeMo, Long> {
    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int deleteByPrimaryKey(Long id);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int insert(PrdProductSpecCodeMo record);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int insertSelective(PrdProductSpecCodeMo record);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    PrdProductSpecCodeMo selectByPrimaryKey(Long id);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int updateByPrimaryKeySelective(PrdProductSpecCodeMo record);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int updateByPrimaryKey(PrdProductSpecCodeMo record);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    List<PrdProductSpecCodeMo> selectAll();

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    List<PrdProductSpecCodeMo> selectSelective(PrdProductSpecCodeMo record);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    boolean existByPrimaryKey(Long id);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    boolean existSelective(PrdProductSpecCodeMo record);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int countSelective(PrdProductSpecCodeMo record);
}