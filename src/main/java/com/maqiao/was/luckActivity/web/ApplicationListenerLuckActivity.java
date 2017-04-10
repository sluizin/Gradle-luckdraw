/**
 * 
 */
package com.maqiao.was.luckActivity.web;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Scope;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

/**
 * ApplicationListener 监听器，设置环境变量
 * @author Sunjian
 * @version 1.0
 * @since jdk1.7
 */
@Service
@Scope("singleton")
public class ApplicationListenerLuckActivity implements ApplicationListener<ContextRefreshedEvent> {

	/*
	 * (non-Javadoc)
	 * @see org.springframework.context.ApplicationListener#onApplicationEvent(org.springframework.context.ApplicationEvent)
	 */
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		Object obj = PropertyConfigurer.getContextProperty("ENVIRONMENT");
		if (obj != null) {
			LuckActivityConsts.ENV = LuckActivityConsts.ENV.testENVKey(obj.toString());
		}

	}

}
