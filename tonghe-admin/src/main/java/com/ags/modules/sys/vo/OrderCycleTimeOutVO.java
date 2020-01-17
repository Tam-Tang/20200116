package com.ags.modules.sys.vo;

import com.ags.modules.sys.dto.OrderCycleTimeOutDTO;
import com.ags.modules.sys.dto.OrderCycleTimeOutPerctDTO;
import lombok.Data;
import java.util.List;

@Data
public class OrderCycleTimeOutVO {
    private List<OrderCycleTimeOutDTO> orderCycleTimeOutDTOS;
    private OrderCycleTimeOutPerctDTO orderCycleTimeOutPerctDTO;
}
