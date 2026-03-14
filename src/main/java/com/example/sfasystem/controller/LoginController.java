package com.example.sfasystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * ログインコントローラー
 */
@Controller
public class LoginController {

	/**
	 * ログイン画面を表示する
	 *
	 * @return ログイン画面
	 */
	@GetMapping("/login")
	public String login() {
		return "login";
	}
}