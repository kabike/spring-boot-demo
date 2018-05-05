package com.fake.god.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Validated
@RequestMapping(value = "validator")
public class CustomValidatorDemoController {

	@ResponseBody
	@GetMapping(value = "custom")
	public String validateParameter(
			@Valid RequestFormWithCustomConstraint request) {
		System.out.println(request.getName());
		return "OK";
	}

}

class RequestFormWithCustomConstraint {

	@NameConstraint(allowedValues = { "bar", "foo" }, message = "只允许bar,foo")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NameConstraintValidator.class)
@interface NameConstraint {
	String[] allowedValues();

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	String message();
}

class NameConstraintValidator implements ConstraintValidator<NameConstraint, String> {

	private String[] validValues;

	@Override
	public void initialize(NameConstraint constraintAnnotation) {
		validValues = constraintAnnotation.allowedValues();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		for (String s : this.validValues) {
			if (s.equals(value)) {
				return true;
			}
		}
		return false;
	}

}