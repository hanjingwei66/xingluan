package com.shuojie.domain.maps;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class CurrentInfo {

    @TableId
    private Long cuid;//当前点id

    private String lineName;//当前点name

    private String currentShijian;//切换时间

}