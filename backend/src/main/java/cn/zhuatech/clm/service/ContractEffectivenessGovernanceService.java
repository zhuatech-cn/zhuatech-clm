/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.clm.service;
import jakarta.validation.constraints.NotBlank; import org.springframework.stereotype.Service;
import java.util.ArrayList; import java.util.List;
@Service
public class ContractEffectivenessGovernanceService{
 public Assessment assess(Request r){List<String>b=new ArrayList<>();List<String>a=new ArrayList<>();
  if(!r.approvedVersion())b.add("待生效文本不是最终批准版本"); if(!r.legalApproved())b.add("法务审批未完成");
  if(!r.counterpartyVerified())b.add("合同相对方主体未核验"); if(!r.signaturesComplete())b.add("签署或印章未完成");
  if(!r.sanctionsCleared())b.add("相对方受限方筛查未通过"); if(!r.conditionsPrecedentSatisfied())a.add("完成合同生效先决条件");
  if(!r.financeApproved())a.add("取得财务条款批准"); if(!r.dataProtectionApproved())a.add("完成数据保护与跨境条款评审");
  if(!r.obligationsRegistered())a.add("登记交付、付款、续约和通知义务");
  Decision d=!b.isEmpty()?Decision.HOLD:!a.isEmpty()?Decision.REVIEW:Decision.EFFECTIVE;
  return new Assessment(r.contractNo(),d,List.copyOf(b),List.copyOf(a));}
 public record Request(@NotBlank String contractNo,boolean approvedVersion,boolean legalApproved,boolean financeApproved,
                       boolean counterpartyVerified,boolean signaturesComplete,boolean conditionsPrecedentSatisfied,
                       boolean dataProtectionApproved,boolean sanctionsCleared,boolean obligationsRegistered){}
 public record Assessment(String contractNo,Decision decision,List<String> blockers,List<String> actions){}
 public enum Decision{EFFECTIVE,REVIEW,HOLD}
}
