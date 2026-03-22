package com.example.sfasystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * ホームコントローラー
 */
@Controller
public class HomeController {

	/**
	 * ホーム画面を表示する
	 * 
	 * @return ホーム画面
	 */
	@GetMapping("/home")
	public String home() {
		return "home";
	}
}
