package com.xxnjdg.heimacms.servicemodel.cmsservice;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

/**
 * @author djr
 * @version 1.0
 * @date 2020/3/15 14:33
 */
@ApiModel("sys_dictionary 系统字典实体")
@Document(collection = "sys_dictionary")
@Data
public class SysDictionary {
    @Id
    @ApiModelProperty("id")
    private String id;

    @ApiModelProperty("名字")
    @Field("d_name")
    private String dName;

    @ApiModelProperty("类型")
    @Field("d_type")
    private String dType;

    @ApiModelProperty("字典选项数组")
    @Field("d_value")
    private List<SysDictionaryOption> dValue;
}
