/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.processmining.service;
import jakarta.validation.constraints.*;
import org.springframework.stereotype.Service;
import java.util.*;
@Service public class DomainDecisionService {
 public DecisionResult assess(DecisionRequest request) { if(request.conformingCases()>request.totalCases())throw new IllegalArgumentException("合规实例数不能大于流程实例数");double conformance=request.conformingCases()*100d/request.totalCases();double cycleRatio=request.medianCycleHours()*100d/request.targetCycleHours();int score=(int)Math.round((conformance+request.eventCoverage())/2);List<String> actions=new ArrayList<>();if(request.eventCoverage()<95){score-=20;actions.add("补齐关键活动事件数据");}if(conformance<90){score-=25;actions.add("调查高频流程偏差");}if(cycleRatio>120){score-=20;actions.add("治理等待和交接瓶颈");}if(request.reworkRate()>8){score-=20;actions.add("消除重复审批与返工循环");}if(request.slaBreachRate()>5){score-=20;actions.add("处理SLA高风险流程变体");}if(!request.ownerAssigned()){score-=30;actions.add("指定流程负责人和改进责任人");}if(!request.piiMasked()){score-=60;actions.add("脱敏个人信息后再发布分析");}return result(score,actions,"RELEASE_INSIGHT","IMPROVE","BLOCKED",Map.of("conformanceRate",Math.round(conformance*10)/10d,"cycleToTargetPercent",Math.round(cycleRatio),"reworkRate",request.reworkRate(),"eventCoverage",request.eventCoverage())); }
 private DecisionResult result(int raw,List<String> actions,String good,String warn,String bad,Map<String,Object> metrics) { int score=Math.max(0,Math.min(100,raw));String decision=score>=80?good:score>=50?warn:bad;return new DecisionResult(decision,score,metrics,List.copyOf(actions)); }
 private DecisionResult riskResult(int raw,List<String> actions,String good,String warn,String bad,Map<String,Object> metrics) { int score=Math.max(0,Math.min(100,raw));String decision=score>=70?bad:score>=40?warn:good;return new DecisionResult(decision,score,metrics,List.copyOf(actions)); }
 public record DecisionRequest(
        @NotBlank String processCode,
        @Positive int totalCases,
        @PositiveOrZero int conformingCases,
        @PositiveOrZero double medianCycleHours,
        @Positive double targetCycleHours,
        @DecimalMin("0") @DecimalMax("100") double reworkRate,
        @DecimalMin("0") @DecimalMax("100") double slaBreachRate,
        @DecimalMin("0") @DecimalMax("100") double eventCoverage,
        boolean ownerAssigned,
        boolean piiMasked) {}
 public record DecisionResult(String decision,int score,Map<String,Object> metrics,List<String> actions) {}
}
