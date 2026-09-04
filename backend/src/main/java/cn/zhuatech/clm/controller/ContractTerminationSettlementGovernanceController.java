/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.clm.controller;

import cn.zhuatech.clm.common.ApiResponse;
import cn.zhuatech.clm.service.ContractTerminationSettlementGovernanceService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/enterprise/clm")
public class ContractTerminationSettlementGovernanceController {
    private final ContractTerminationSettlementGovernanceService service;
    public ContractTerminationSettlementGovernanceController(ContractTerminationSettlementGovernanceService service) { this.service = service; }

    @PostMapping("/termination-settlement")
    public ApiResponse<ContractTerminationSettlementGovernanceService.Assessment> assess(
            @Valid @RequestBody ContractTerminationSettlementGovernanceService.Request request) {
        return ApiResponse.ok("合同终止与结算评估完成", service.assess(request));
    }
}
