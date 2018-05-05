package com.fake.god.validator;

import javax.validation.constraints.Size;

import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Validated
@RequestMapping(value = "validator")
public class ParameterValidatorDemoController {

	@ResponseBody
	@GetMapping(value = "simple")
	public String validateParameter(@Size(min = 1, max = 5) String name) {
		System.out.println(name);
		return "OK";
	}

}
