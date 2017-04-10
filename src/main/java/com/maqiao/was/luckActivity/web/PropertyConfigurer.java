/**
 * 
 */
package com.maqiao.was.luckActivity.web;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * @author Sunjian
 * @version 1.0
 * @since jdk1.7
 */
public class PropertyConfigurer extends PropertyPlaceholderConfigurer {
	private static Map<String, Object> ctxPropertiesMap;

	protected void processProperties(ConfigurableListableBeanFactory beanFactory, Properties props) throws BeansException {
		super.processProperties(beanFactory, props);
		Iterator<Object> localIterator;
		if (ctxPropertiesMap == null) {
			ctxPropertiesMap = new HashMap<String, Object>();
			for (localIterator = props.keySet().iterator(); localIterator.hasNext();) {
				Object key = localIterator.next();
				String keyStr = key.toString();
				String value = props.getProperty(keyStr);
				ctxPropertiesMap.put(keyStr, value);
			}
		}
	}

	public static Object getContextProperty(String name) {
		return ctxPropertiesMap.get(name);
	}

	public static Map<String, Object> getContextProperty() {
		return ctxPropertiesMap;
	}

}
