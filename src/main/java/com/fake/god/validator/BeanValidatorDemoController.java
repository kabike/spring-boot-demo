package com.fake.god.validator;

import javax.validation.Valid;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "validator")
public class BeanValidatorDemoController {

	@ResponseBody
	@GetMapping(value = "bean")
	public String validate(@Valid RequestForm request) {
		System.out.println(request.getName());
		return "OK";
	}

}

class RequestForm {

	@Size(min = 1, max = 5)
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}