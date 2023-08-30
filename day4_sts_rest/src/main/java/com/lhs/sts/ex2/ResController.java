package com.lhs.sts.ex2;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResController {
	
	/*
	 * 1. @ResponseBody가 붙어 있기 때문에, return한 값이 MappingJackson2HttpMessageConverter 를 거쳐 json 데이터로 변환된다.
	 * 2. 변환 된 데이터를 HttpResponseMessage에 담아, 브라우저로 반환된다.
	 */
	@RequestMapping(value = "/res1")
	@ResponseBody
	public Map<String, Object> res1() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", "hong");
		map.put("name", "홍길동");
		return map;
	}
	
	/*
	 * 1. @ResponseBody가 붙어있지 않기 때문에 InternalResourceViewResolver가 동작한다.
	 * 2. return 한 ModelAndView 객체의 view 정보를 바탕으로, home.jsp로 이동한다.
	 */
	@RequestMapping(value = "/res2")
	public ModelAndView res2() {
		return new ModelAndView("home");
	}


}
