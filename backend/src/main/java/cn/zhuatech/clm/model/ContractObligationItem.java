/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.clm.model;
import jakarta.persistence.*;import java.time.LocalDate;
@Entity @Table(name="clm_contract_obligation_item",uniqueConstraints=@UniqueConstraint(columnNames={"contractId","obligationNo"})) public class ContractObligationItem extends BaseEntity {
 public enum State{OPEN,COMPLETED,OVERDUE}
 @Column(nullable=false) private Long contractId;@Column(nullable=false,length=40) private String obligationNo;@Column(nullable=false,length=160) private String title;
 @Column(nullable=false,length=50) private String owner;@Column(nullable=false) private LocalDate dueDate;@Enumerated(EnumType.STRING) @Column(nullable=false,length=20) private State state;
 @Column(length=300) private String completionEvidence;protected ContractObligationItem(){}
 public ContractObligationItem(Long contractId,String obligationNo,String title,String owner,LocalDate dueDate){this.contractId=contractId;this.obligationNo=obligationNo;this.title=title;this.owner=owner;this.dueDate=dueDate;this.state=State.OPEN;}
 public void complete(String evidence){state=State.COMPLETED;completionEvidence=evidence;}
 public Long getContractId(){return contractId;}public String getObligationNo(){return obligationNo;}public String getTitle(){return title;}public String getOwner(){return owner;}public LocalDate getDueDate(){return dueDate;}public State getState(){return state;}public String getCompletionEvidence(){return completionEvidence;}
}
