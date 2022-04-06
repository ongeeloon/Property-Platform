package com.example.team5ad.controller;

import com.example.team5ad.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class LoginController {

	@GetMapping("/consumer")
	public String consumer() {
		return "consumerlogin";
	}
	@GetMapping("/business")
	public String business() {
		return "businesslogin";
	}

}
