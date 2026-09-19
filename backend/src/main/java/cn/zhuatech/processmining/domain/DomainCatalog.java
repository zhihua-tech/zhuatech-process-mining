/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.processmining.domain;
import org.springframework.stereotype.Component;
import java.util.*;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@Component
public class DomainCatalog {
    private final Map<String, WorkflowAction> actions = new LinkedHashMap<>();
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public DomainCatalog() {
        actions.put("SUBMIT", new WorkflowAction("SUBMIT", "提交流程分析", List.of("草稿"), "分析中", "OPERATOR"));
        actions.put("REVIEW", new WorkflowAction("REVIEW", "复核流程洞察", List.of("分析中"), "待发布", "ADMIN"));
        actions.put("PUBLISH", new WorkflowAction("PUBLISH", "发布改进方案", List.of("待发布"), "已发布", "ADMIN"));
    }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public String systemName() { return "知华科技企业流程挖掘与优化平台"; }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public String scene() { return "数据接入、事件日志、流程发现、流程变体、一致性检查、瓶颈、返工、SLA与改进闭环"; }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public String initialStatus() { return "草稿"; }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public String partyLabel() { return "业务流程/流程负责人"; }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public String amountLabel() { return "改善价值"; }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public String quantityLabel() { return "流程实例数"; }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public String dueLabel() { return "分析期限"; }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public List<ModuleDefinition> modules() { return List.of(
            new ModuleDefinition("SOURCE", "数据源接入", "连接ERP、CRM、OA、ITSM和文件并登记抽取批次"),
            new ModuleDefinition("EVENT_LOG", "事件日志", "标准化案例、活动、时间、执行人和业务属性"),
            new ModuleDefinition("DISCOVERY", "流程发现", "从事件序列生成实际流程模型和关键路径"),
            new ModuleDefinition("VARIANT", "流程变体", "识别主路径、长尾路径和异常高频变体"),
            new ModuleDefinition("CONFORMANCE", "一致性检查", "将真实路径与标准流程对比并定位偏差"),
            new ModuleDefinition("BOTTLENECK", "瓶颈分析", "分析等待、交接、拥塞和周期时间损失"),
            new ModuleDefinition("REWORK", "返工分析", "识别重复活动、回退循环、重复审批和返工成本"),
            new ModuleDefinition("SLA", "时效与合规", "评估SLA、职责分离、跳步和控制点执行情况"),
            new ModuleDefinition("IMPROVEMENT", "改进闭环", "管理优化假设、责任人、收益、验证和持续监控")
        ); }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public Map<String, WorkflowAction> actions() { return Collections.unmodifiableMap(actions); }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public record ModuleDefinition(String code,String name,String description) {}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public record WorkflowAction(String code,String label,List<String> from,String to,String requiredRole) {}
}
