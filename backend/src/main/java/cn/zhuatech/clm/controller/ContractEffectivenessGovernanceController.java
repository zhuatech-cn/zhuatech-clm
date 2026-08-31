/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.clm.controller;
import cn.zhuatech.clm.common.ApiResponse; import cn.zhuatech.clm.service.ContractEffectivenessGovernanceService;
import jakarta.validation.Valid; import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/enterprise/clm")
public class ContractEffectivenessGovernanceController{
 private final ContractEffectivenessGovernanceService service; public ContractEffectivenessGovernanceController(ContractEffectivenessGovernanceService service){this.service=service;}
 @PostMapping("/contract-effectiveness") public ApiResponse<ContractEffectivenessGovernanceService.Assessment> assess(@Valid @RequestBody ContractEffectivenessGovernanceService.Request request){return ApiResponse.ok("合同生效评估完成",service.assess(request));}
}
