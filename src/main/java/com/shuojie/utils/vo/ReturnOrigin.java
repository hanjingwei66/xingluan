package com.shuojie.utils.vo;

import com.shuojie.domain.maps.OriginLine;
import lombok.Data;

import java.util.List;
@Data
public class ReturnOrigin {

    private List orName;

    private List<OriginLine> originAndLine;

}
