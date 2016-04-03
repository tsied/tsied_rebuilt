package com.elk.utils;

import java.io.File;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import freemarker.template.Configuration;
import freemarker.template.Template;

@Component
public class TemplateUtil {
	
	private static Logger log = LoggerFactory.getLogger(TemplateUtil.class);
	/**
	 *  format ES data
	 * @param pdata
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	public String formatData(Map<String,String> paramMap){
		ObjectMapper mapper = new ObjectMapper();
		Configuration cfg = new Configuration();//init freemarker template
		String result = null;
		try{
			cfg.setDirectoryForTemplateLoading(new File(Thread.currentThread().getContextClassLoader().getResource("").getPath()+"resource/template/freemarker")); // get template direction
			Template temp = cfg.getTemplate(paramMap.get("templateName"));
			Map<String, Object> map = new HashMap<String, Object>();
			JsonNode goodsArray = mapper.readTree(paramMap.get("data"));
			StringWriter sw = new StringWriter();
			if (goodsArray.isArray()) {
				List<?> data = mapper.readValue(paramMap.get("data"), List.class);
				map.put("data", data);
				temp.process(map, sw);
			}
			else{
				Map<String, Object> data = mapper.readValue(paramMap.get("data"), Map.class);
				map.put("data", data);
				temp.process(data, sw);
			}
			result = sw.toString();
		}catch(Exception e){
			e.printStackTrace();
			log.error(e.getMessage());
		}
		return result;
	}
}
