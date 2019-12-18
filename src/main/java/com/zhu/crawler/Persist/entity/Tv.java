package com.zhu.crawler.Persist.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author zjw
 * @since 2019-12-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Tv对象", description="")
public class Tv extends Model<Tv> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.UUID)
    private String id;

    @ApiModelProperty(value = "标题")
    @TableField("TITLE")
    private String title;

    @ApiModelProperty(value = "播放")
    @TableField("PLAY")
    private Double play;

    @ApiModelProperty(value = "弹幕")
    @TableField("DANMU")
    private Double danmu;

    @ApiModelProperty(value = "作者")
    @TableField("AUTHOR")
    private String author;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
