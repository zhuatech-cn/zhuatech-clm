/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.clm.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ContractTerminationSettlementGovernanceServiceTest {
    private final ContractTerminationSettlementGovernanceService service = new ContractTerminationSettlementGovernanceService();

    @Test void terminatesFullySettledContract() {
        var result = service.assess(request(true, true, true, true, true));
        assertEquals(ContractTerminationSettlementGovernanceService.Decision.TERMINATE, result.decision());
        assertTrue(result.blockers().isEmpty());
        assertTrue(result.actions().isEmpty());
    }

    @Test void reviewsTerminationWithOperationalActions() {
        var result = service.assess(request(false, false, false, false, false));
        assertEquals(ContractTerminationSettlementGovernanceService.Decision.REVIEW, result.decision());
        assertEquals(5, result.actions().size());
    }

    @Test void blocksUncontrolledContractTermination() {
        var result = service.assess(new ContractTerminationSettlementGovernanceService.Request(
                "CLM-003", "TERM-003", false, false, false, true, false, true,
                false, true, false, false, true, true, false, false));
        assertEquals(ContractTerminationSettlementGovernanceService.Decision.BLOCKED, result.decision());
        assertEquals(9, result.blockers().size());
    }

    private ContractTerminationSettlementGovernanceService.Request request(boolean deliverables, boolean assets,
                                                                            boolean tax, boolean claims,
                                                                            boolean acknowledged) {
        return new ContractTerminationSettlementGovernanceService.Request("CLM-001", "TERM-001", true, true,
                true, deliverables, true, assets, true, tax, true, true, claims, acknowledged, true, true);
    }
}
