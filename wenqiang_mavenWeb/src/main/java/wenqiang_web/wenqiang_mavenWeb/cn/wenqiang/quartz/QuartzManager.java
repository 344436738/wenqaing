package wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.quartz;

import java.text.ParseException;  
import java.util.Date;  
import java.util.List;  
  






import javax.annotation.Resource;
import javax.annotation.Resources;

import org.apache.log4j.Logger;  
import org.quartz.JobDetail;  
import org.quartz.Scheduler;  
import org.springframework.beans.BeansException;  
import org.springframework.beans.factory.BeanFactory;  
import org.springframework.beans.factory.BeanFactoryAware;  
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.scheduling.quartz.CronTriggerBean;  
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;  
import org.springframework.stereotype.Service;

import wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.emty.task;
import wenqiang_web.wenqiang_mavenWeb.cn.wenqiang.service.TaskService;
  

  
  
@Service

public class QuartzManager implements BeanFactoryAware {  
    private Logger log = Logger.getLogger(QuartzManager.class);  
    private Scheduler scheduler;  
    private static BeanFactory beanFactory = null;  
     
    @Autowired  
    private  TaskService  wsdocService;  
  
    @SuppressWarnings("unused")  
    private void reScheduleJob() throws Exception, ParseException {  
        // 通过查询数据库里计划任务来配置计划任务  
          
        List<task> quartzList = wsdocService.listAllMenuQxByPage();//从数据库中获取所有的配置信息，根据自己的获取方式来写，此不赘述  

	    //this.getConfigQuartz();  
	    if (quartzList != null && quartzList.size() > 0) {  
	        for (task tbcq1 : quartzList) {  
	            configQuatrz(tbcq1);  
	        }  
	    }  
    }  
  
		public boolean configQuatrz(task tbcq) {  
		    boolean result = false;  
		    try {  
		        // 运行时可通过动态注入的scheduler得到trigger  
		        CronTriggerBean trigger = (CronTriggerBean) scheduler.getTrigger(  
		                tbcq.getTriggername(), Scheduler.DEFAULT_GROUP);  
		        // 如果计划任务已存在则调用修改方法  
		        if (trigger != null) {  
		            change(tbcq, trigger);  
		        } else {  
		            // 如果计划任务不存在并且数据库里的任务状态为可用时,则创建计划任务  
		            if (tbcq.getState().equals("1")) {  
		                this.createCronTriggerBean(tbcq);  
		            }  
		        }  
		        result = true;  
		    } catch (Exception e) {  
		        result = false;  
		        e.printStackTrace();  
		    }  
		  
		    return result;  
		}  
  
public void change(task tbcq, CronTriggerBean trigger)  
        throws Exception {  
    // 如果任务为可用  
    if (tbcq.getState().equals("1")) {  
        // 判断从DB中取得的任务时间和现在的quartz线程中的任务时间是否相等  
        // 如果相等，则表示用户并没有重新设定数据库中的任务时间，这种情况不需要重新rescheduleJob  
        if (!trigger.getCronExpression().equalsIgnoreCase(  
                tbcq.getCronexpression())) {  
            trigger.setCronExpression(tbcq.getCronexpression());  
            scheduler.rescheduleJob(tbcq.getTriggername(),  
                    Scheduler.DEFAULT_GROUP, trigger);  
            log.info(new Date() + ": 更新" + tbcq.getTriggername() + "计划任务");  
        }  
    } else {  
        // 不可用  
        scheduler.pauseTrigger(trigger.getName(), trigger.getGroup());// 停止触发器  
        scheduler.unscheduleJob(trigger.getName(), trigger.getGroup());// 移除触发器  
        scheduler.deleteJob(trigger.getJobName(), trigger.getJobGroup());// 删除任务  
        log.info(new Date() + ": 删除" + tbcq.getTriggername() + "计划任务");  
  
    }  
  
}  
  
/** 
 * 创建/添加计划任务 
 *  
 * @param tbcq 
 *            计划任务配置对象 
 * @throws Exception 
 */  
public void createCronTriggerBean(task tbcq) throws Exception {  
    // 新建一个基于Spring的管理Job类  
    MethodInvokingJobDetailFactoryBean mjdfb = new MethodInvokingJobDetailFactoryBean();  
    mjdfb.setName(tbcq.getJobdetailname());// 设置Job名称  
    // 如果定义的任务类为Spring的定义的Bean则调用 getBean方法  
    if (tbcq.getIsspringbean().equals("1")) {  
        mjdfb.setTargetObject(beanFactory.getBean(tbcq.getTargetobject()));// 设置任务类  
    } else {  
        // 否则直接new对象  
        mjdfb.setTargetObject(Class.forName(tbcq.getTargetobject())  
                .newInstance());// 设置任务类  
    }  
  
    mjdfb.setTargetMethod(tbcq.getMethodname());// 设置任务方法  
    mjdfb.setConcurrent(tbcq.getConcurrent().equals("0") ? false : true); // 设置是否并发启动任务  
    mjdfb.afterPropertiesSet();// 将管理Job类提交到计划管理类  
    // 将Spring的管理Job类转为Quartz管理Job类  
    JobDetail jobDetail = new JobDetail();  
    jobDetail = (JobDetail) mjdfb.getObject();  
    jobDetail.setName(tbcq.getJobdetailname());  
    scheduler.addJob(jobDetail, true); // 将Job添加到管理类  
    // 新一个基于Spring的时间类  
    CronTriggerBean c = new CronTriggerBean();  
    c.setCronExpression(tbcq.getCronexpression());// 设置时间表达式  
    c.setName(tbcq.getTriggername());// 设置名称  
    c.setJobDetail(jobDetail);// 注入Job  
    c.setJobName(tbcq.getJobdetailname());// 设置Job名称  
    scheduler.scheduleJob(c);// 注入到管理类  
    scheduler.rescheduleJob(tbcq.getTriggername(), Scheduler.DEFAULT_GROUP,  
            c);// 刷新管理类  
    log.info(new Date() + ": 新建" + tbcq.getTriggername() + "计划任务");  
}  
  
public Scheduler getScheduler() {  
    return scheduler;  
}  
  
public void setScheduler(Scheduler scheduler) {  
    this.scheduler = scheduler;  
}  
  
public void setBeanFactory(BeanFactory factory) throws BeansException {  
    this.beanFactory = factory;  
  
}  
  
	public BeanFactory getBeanFactory() {  
	    return beanFactory;  
	}  
}
