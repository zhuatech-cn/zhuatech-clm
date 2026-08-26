/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.clm.repository;import cn.zhuatech.clm.model.ContractAmendment;import org.springframework.data.jpa.repository.JpaRepository;import java.util.List;
public interface ContractAmendmentRepository extends JpaRepository<ContractAmendment,Long>{List<ContractAmendment> findByContractIdOrderByToVersionDesc(Long contractId);}
