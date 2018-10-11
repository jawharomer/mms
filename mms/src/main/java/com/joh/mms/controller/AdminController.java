package com.joh.mms.controller;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.joh.mms.service.IncomeService;

@Controller
@RequestMapping(path = "/admin")
public class AdminController {

	private static final Logger logger = Logger.getLogger(AdminController.class);

	@Autowired
	private IncomeService incomeService;

	@GetMapping()
	public String getAdminHome(Model model) {
		logger.info("getAdminHome->fired");
		return "adminRoot";
	}

	@GetMapping(path = "/revenue")
	public String getAllRevenue(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date from,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date to, Model model) {
		logger.info("getAllRevenue->fired");

		logger.info("from=" + from);
		logger.info("to=" + to);
		model.addAttribute("revenues", incomeService.findAllRevenue(from, to));

		model.addAttribute("from", from);
		model.addAttribute("to", to);

		return "adminAllRevenue";
	}

}
