package rebue.prd.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import rebue.prd.mo.PrdProductBarcodeMo;
import rebue.robotech.mapper.MybatisBaseMapper;

@Mapper
public interface PrdProductBarcodeMapper extends MybatisBaseMapper<PrdProductBarcodeMo, Long> {
    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int deleteByPrimaryKey(Long id);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int insert(PrdProductBarcodeMo record);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int insertSelective(PrdProductBarcodeMo record);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    PrdProductBarcodeMo selectByPrimaryKey(Long id);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int updateByPrimaryKeySelective(PrdProductBarcodeMo record);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int updateByPrimaryKey(PrdProductBarcodeMo record);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    List<PrdProductBarcodeMo> selectAll();

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    List<PrdProductBarcodeMo> selectSelective(PrdProductBarcodeMo record);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    boolean existByPrimaryKey(Long id);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    boolean existSelective(PrdProductBarcodeMo record);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int countSelective(PrdProductBarcodeMo record);
}