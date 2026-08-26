/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.clm.repository;import cn.zhuatech.clm.model.ContractObligationItem;import org.springframework.data.jpa.repository.JpaRepository;import java.util.*;
public interface ContractObligationItemRepository extends JpaRepository<ContractObligationItem,Long>{List<ContractObligationItem> findByContractIdOrderByDueDateAsc(Long contractId);Optional<ContractObligationItem> findByContractIdAndObligationNo(Long contractId,String obligationNo);}
