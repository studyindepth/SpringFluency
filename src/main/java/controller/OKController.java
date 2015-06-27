package controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OKController {
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String ok1() {
		return "12345";
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public String ok2() {
		return "12345";
	}
}
