# ZhuaTech CLM｜知华科技合同生命周期管理系统

## 企业级增强：合同生效治理

新增版本、法务财务、相对方、签署、受限方、先决条件、数据保护和义务登记门禁，详见 [合同生效治理](docs/ENTERPRISE_CONTRACT_EFFECTIVENESS.md)。

> 从合同起草、法务审查、会签、履约到归档，建立统一、可追溯的合同工作台。

[![Java 21](https://img.shields.io/badge/Java-21-24456b)](backend/pom.xml) [![Vue 3](https://img.shields.io/badge/Vue-3-42b883)](frontend/package.json) [![MySQL 8](https://img.shields.io/badge/MySQL-8-4479a1)](compose.yaml) [![License](https://img.shields.io/badge/license-personal_non--commercial-c28a3c)](LICENSE)

ZhuaTech CLM 是知华科技（上海如静知华信息科技有限公司）发布的合同生命周期管理社区源码版，采用 Java 21、Spring Boot、Vue 3 与 MySQL 8 前后端分离架构。产品及企业服务请访问[知华科技官网](https://www.zhuatech.cn/)。

## 产品视图

### 合同组合管理端

集中展示在审合同、合同组合负荷、法务复核、履约义务与签署时限。

![知华科技 CLM 合同组合管理端](docs/images/clm-admin-dashboard.png)

### 合同专员 H5 工作台

支持合同接收、条款预审、模板调用、审查意见录入、会签与义务预警。

![知华科技 CLM 合同专员 H5 工作台](docs/images/clm-specialist-h5.png)

## 业务闭环

V2.0 新增企业合同主数据、草稿/生效控制、业务版本与乐观锁、合同变更前后快照、履约义务及完成证据，并通过 Flyway V2 迁移进行严格结构校验。详见[企业合同生命周期 V2.0](docs/enterprise-contract-v2.md)。

```text
合同接收 → 条款预审 → 法务审查 → 多方会签 → 履约跟踪 → 合同归档
```

核心能力包括合同组合与主体档案、标准条款模板、审查任务、版本记录、审批轨迹、履约节点、到期续约提醒和管理分析。演示数据均为虚构数据。

## 技术结构

| 模块 | 说明 |
| --- | --- |
| `backend` | Java 21、Spring Boot、Spring Security、JWT、JPA、Flyway，包名 `cn.zhuatech.clm` |
| `frontend` | Vue 3、Pinia、Vue Router、Axios、Vite，管理端与 H5 岗位端 |
| 数据库 | MySQL 8，数据库名 `zhuatech_clm`；H2 用于集成测试 |
| 部署 | Docker Compose、Nginx、环境变量与健康检查 |

## 本地体验

```bash
cd frontend
npm install
npm run dev:demo
```

访问 `http://localhost:5173`。管理端：`planner / Demo@2026`；合同专员端：`operator / Demo@2026`。

完整环境：

```bash
cp .env.example .env
# 修改数据库密码和 JWT_SECRET
docker compose up --build
```

## 使用边界与商业授权

本工程仅允许个人、非商业性的学习、研究和技术交流，**不得商用**。企业内部使用、生产部署、SaaS、客户交付、收费培训、咨询实施及品牌替换，均须事先取得上海如静知华信息科技有限公司书面授权。完整条款见 [LICENSE](LICENSE)。

如需深度开发、私有化部署、系统集成或商业授权，请访问[知华科技官网](https://www.zhuatech.cn/)，或扫码添加微信咨询：

| 微信咨询 1 | 微信咨询 2 |
| --- | --- |
| ![知华科技微信咨询二维码 1](docs/images/zhuatech-wechat-consulting.png) | ![知华科技微信咨询二维码 2](docs/images/zhuatech-wechat-consulting-2.png) |

关键词：合同管理系统源码、CLM、Java 合同管理、Vue 合同审批、履约管理、知华科技、上海如静知华信息科技有限公司。

## 合同续约风险闸门

`POST /api/admin/renewal-risk` 综合到期日、通知期、合同金额、自动续约条款、未完成义务和责任人配置输出续约风险。进入通知窗口的高风险合同会标记 `ACTION_NOW` 并生成明确处置清单。

## 合同义务敞口

新增 `POST /api/clm/insights/obligation-exposure`。系统汇总临近到期及已逾期义务，结合潜在违约金额与合同总额计算敞口比例，输出 `CONTROLLED`、`ACT_NOW` 或 `ESCALATE`，便于法务和业务负责人优先处理高影响合同义务。
