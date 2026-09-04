/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.clm.service;

import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class ContractTerminationSettlementGovernanceService {
    public Assessment assess(Request request) {
        List<String> blockers = new ArrayList<>();
        List<String> actions = new ArrayList<>();
        if (!request.terminationRightVerified()) blockers.add("终止权、解除条件或双方合意依据未核验");
        if (!request.noticeServed()) blockers.add("终止通知尚未按合同约定送达");
        if (!request.openObligationsInventoried()) blockers.add("未完成交付、付款及保密义务盘点");
        if (!request.dataReturnVerified()) blockers.add("数据返还、删除或留存要求未验证");
        if (!request.finalSettlementCalculated()) blockers.add("最终应收应付和违约责任未计算");
        if (!request.legalApproved()) blockers.add("法务尚未批准终止方案");
        if (!request.financeApproved()) blockers.add("财务尚未批准最终结算");
        if (!request.approverSeparated()) blockers.add("终止经办人与审批人未职责分离");
        if (!request.auditReady()) blockers.add("通知、审批和结算证据链不完整");
        if (!request.deliverablesAccepted()) actions.add("完成在途交付物验收或拒收记录");
        if (!request.assetsReturned()) actions.add("跟踪企业资产、账号及介质返还");
        if (!request.taxReviewed()) actions.add("复核终止补偿、退款及发票税务影响");
        if (!request.claimsReserved()) actions.add("登记未决索赔、质保和追偿权利");
        if (!request.counterpartyAcknowledged()) actions.add("取得相对方终止与结算确认");
        Decision decision = !blockers.isEmpty() ? Decision.BLOCKED : !actions.isEmpty() ? Decision.REVIEW : Decision.TERMINATE;
        return new Assessment(request.contractNo(), request.terminationId(), decision,
                List.copyOf(blockers), List.copyOf(actions));
    }

    public record Request(@NotBlank String contractNo, @NotBlank String terminationId,
                          boolean terminationRightVerified, boolean noticeServed,
                          boolean openObligationsInventoried, boolean deliverablesAccepted,
                          boolean dataReturnVerified, boolean assetsReturned,
                          boolean finalSettlementCalculated, boolean taxReviewed,
                          boolean legalApproved, boolean financeApproved, boolean claimsReserved,
                          boolean counterpartyAcknowledged, boolean approverSeparated, boolean auditReady) {}
    public record Assessment(String contractNo, String terminationId, Decision decision,
                             List<String> blockers, List<String> actions) {}
    public enum Decision { TERMINATE, REVIEW, BLOCKED }
}
