-- Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/
-- 企业合同主数据、变更版本与履约义务。兼容 MySQL 8 与测试使用的 H2 MySQL 模式。
CREATE TABLE clm_enterprise_contract (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  contract_no VARCHAR(40) NOT NULL UNIQUE,
  title VARCHAR(160) NOT NULL,
  counterparty VARCHAR(120) NOT NULL,
  organization_code VARCHAR(40) NOT NULL,
  owner VARCHAR(50) NOT NULL,
  currency VARCHAR(3) NOT NULL,
  amount DECIMAL(18,2) NOT NULL,
  start_date DATE NOT NULL,
  end_date DATE NOT NULL,
  auto_renew BOOLEAN NOT NULL,
  notice_period_days INT NOT NULL,
  state VARCHAR(20) NOT NULL,
  business_version INT NOT NULL,
  row_version BIGINT NOT NULL,
  created_at DATETIME(6) NOT NULL,
  updated_at DATETIME(6) NOT NULL
);

CREATE TABLE clm_contract_amendment (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  contract_id BIGINT NOT NULL,
  from_version INT NOT NULL,
  to_version INT NOT NULL,
  previous_amount DECIMAL(18,2) NOT NULL,
  new_amount DECIMAL(18,2) NOT NULL,
  previous_end_date DATE NOT NULL,
  new_end_date DATE NOT NULL,
  reason VARCHAR(300) NOT NULL,
  changed_by VARCHAR(50) NOT NULL,
  created_at DATETIME(6) NOT NULL,
  updated_at DATETIME(6) NOT NULL,
  CONSTRAINT fk_clm_amendment_contract FOREIGN KEY(contract_id) REFERENCES clm_enterprise_contract(id)
);

CREATE TABLE clm_contract_obligation_item (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  contract_id BIGINT NOT NULL,
  obligation_no VARCHAR(40) NOT NULL,
  title VARCHAR(160) NOT NULL,
  owner VARCHAR(50) NOT NULL,
  due_date DATE NOT NULL,
  state VARCHAR(20) NOT NULL,
  completion_evidence VARCHAR(300),
  created_at DATETIME(6) NOT NULL,
  updated_at DATETIME(6) NOT NULL,
  CONSTRAINT uk_clm_contract_obligation UNIQUE(contract_id,obligation_no),
  CONSTRAINT fk_clm_obligation_contract FOREIGN KEY(contract_id) REFERENCES clm_enterprise_contract(id)
);

CREATE INDEX idx_clm_enterprise_contract_end ON clm_enterprise_contract(state,end_date);
CREATE INDEX idx_clm_amendment_contract ON clm_contract_amendment(contract_id,to_version);
CREATE INDEX idx_clm_obligation_due ON clm_contract_obligation_item(contract_id,state,due_date);
