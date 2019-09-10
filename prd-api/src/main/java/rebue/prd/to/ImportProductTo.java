package rebue.prd.to;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class ImportProductTo {

    private String goodCode;

    private String name;

    private String className;

    private BigDecimal price;

    private String unit;

    private BigDecimal inPrice;

    private BigDecimal stock;

}
