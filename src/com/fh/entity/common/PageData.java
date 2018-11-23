package com.fh.entity.common;

import java.io.BufferedReader;
import java.io.Reader;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.druid.proxy.jdbc.ClobProxyImpl;

/** 
 * 参数封装Map
 */
@SuppressWarnings("rawtypes")
public class PageData extends HashMap implements Map {
	
	private static final long serialVersionUID = 1L;
	HttpServletRequest request;
	Map map = null;
	
	public PageData() {
		map = new HashMap();
	}
	
	@SuppressWarnings("unchecked")
	public PageData(HttpServletRequest request){
		this.request = request;
		Map properties = request.getParameterMap();
		Map returnMap = new HashMap(); 
		Iterator entries = properties.entrySet().iterator(); 
		Map.Entry entry; 
		String name = "";  
		String value = "";  
		while (entries.hasNext()) {
			//获取每一个节点
			entry = (Map.Entry) entries.next(); 
			//获取键
			name = (String) entry.getKey(); 
			//获取值
			Object valueObj = entry.getValue(); 
			if(null == valueObj){ 
				value = ""; 
			}else if(valueObj instanceof String[]){ 
				String[] values = (String[])valueObj;
				for(int i=0;i<values.length;i++){ 
					 value = values[i] + ",";
				}
				value = value.substring(0, value.length()-1); 
			}else{
				value = valueObj.toString(); 
			}
			returnMap.put(name, value); 
		}
		map = returnMap;
	}
	
	@Override
	public Object get(Object key) {
		Object obj = null;
		if(map.get(key) instanceof Object[]) {
			Object[] arr = (Object[])map.get(key);
			obj = request == null ? arr:(request.getParameter((String)key) == null ? arr:arr[0]);
		} else {
			obj = map.get(key);
		}
		return obj;
	}
	
	public String getString(Object key) {
		return (String)get(key);
	}
	
	public Integer getInteger(Object key) {
		return (Integer)get(key);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Object put(Object key, Object value) {
		if(value instanceof ClobProxyImpl){ 			//读取oracle Clob类型数据
			try {
				ClobProxyImpl cpi = (ClobProxyImpl)value;
				Reader is = cpi.getCharacterStream(); 	//获取流
				BufferedReader bufferReader = new BufferedReader(is);
				String str = bufferReader.readLine();
				StringBuffer strBuffer = new StringBuffer();
				while(str != null){						//循环读取数据拼接到字符串
					strBuffer.append(str);
					strBuffer.append("\n");
					str = bufferReader.readLine();
				}
				value = strBuffer.toString();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return map.put(key, value);
	}
	
	@Override
	public Object remove(Object key) {
		return map.remove(key);
	}

	public void clear() {
		map.clear();
	}

	public boolean containsKey(Object key) {
		return map.containsKey(key);
	}

	public boolean containsValue(Object value) {
		return map.containsValue(value);
	}

	public Set entrySet() {
		return map.entrySet();
	}

	public boolean isEmpty() {
		return map.isEmpty();
	}

	public Set keySet() {
		return map.keySet();
	}

	@SuppressWarnings("unchecked")
	public void putAll(Map t) {
		map.putAll(t);
	}

	public int size() {
		return map.size();
	}

	public Collection values() {
		return map.values();
	}
	
}
