# 企业合同生命周期 V2.0

> 上海如静知华信息科技有限公司 · [知华科技官网](https://www.zhuatech.cn/)

## 新增业务模型

V2.0 在续约风险、义务敞口和原合同审查工作台之外，新增独立的企业合同主数据：

- 合同编号、名称、相对方、组织、负责人、币种、金额、起止日期和自动续约条款；
- 草稿与生效状态控制；
- 业务版本号与数据库乐观锁；
- 变更前后金额、结束日期、变更原因、操作人与版本轨迹；
- 合同履约义务、责任人、截止日和完成证据；
- Flyway V2 正式迁移，生产与测试均启用 Hibernate schema validate。

## 合同闭环

```text
创建草稿 → 生效 → 登记履约义务 → 完成并留存证据 → 合同变更 → 到期/续约风险处置
```

## 接口

| 接口 | 说明 |
| --- | --- |
| `GET /api/admin/contracts` | 查询企业合同 |
| `POST /api/admin/contracts` | 创建合同草稿 |
| `GET /api/admin/contracts/{id}` | 查询合同、变更和履约义务明细 |
| `POST /api/admin/contracts/{id}/activate` | 使合同生效 |
| `POST /api/admin/contracts/{id}/amendments` | 按 expectedVersion 执行合同变更 |
| `POST /api/admin/contracts/{id}/obligations` | 登记履约义务 |
| `POST /api/admin/contracts/{id}/obligations/{obligationId}/complete` | 以证据说明完成义务 |

这些接口仅允许合同经理或管理员访问，并继续使用项目既有 JWT 鉴权。

## 外部系统边界

真实电子签署、CA、可信时间戳、ERP、采购和 OA 审批平台应通过适配器集成；本仓库不包含任何真实厂商密钥。生产使用必须补充企业租户隔离、文件对象存储、防病毒扫描、备份恢复、监控告警及等保所需安全控制。

