package org.springblade.modules.app.practice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.core.mp.base.BaseEntity;

@Data
@TableName("blade_cpt_setting")
@EqualsAndHashCode()
@ApiModel(value = "cpt_setting", description = "cpt_setting对象")
public class CptSetting extends BaseEntity {

	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;
	private String randomTime;
	private String appearTime;
	private String intervalTime;
	private String gaugeTime;
}
