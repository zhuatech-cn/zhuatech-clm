/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.clm.repository;import cn.zhuatech.clm.model.EnterpriseContract;import org.springframework.data.jpa.repository.JpaRepository;import java.util.*;
public interface EnterpriseContractRepository extends JpaRepository<EnterpriseContract,Long>{Optional<EnterpriseContract> findByContractNo(String contractNo);List<EnterpriseContract> findAllByOrderByEndDateAsc();}
