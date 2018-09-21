package com.joh.mms.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joh.mms.dao.IncomeDAO;
import com.joh.mms.service.IncomeCategoryService;
import com.joh.mms.service.IncomeService;

@Controller
@RequestMapping(path = "/admin")
public class AdminController {

	private static final Logger logger = Logger.getLogger(AdminController.class);
	@Autowired
	private IncomeDAO incomeDAO;

	@Autowired
	private IncomeService incomeService;

	@Autowired
	private IncomeCategoryService incomeCategoryService;

	@GetMapping()
	public String getAdminHome(Model model) {
		logger.info("getAdminHome->fired");
		return "adminRoot";
	}

	@GetMapping(path = "/revenue")
	public String getAllRevenue(Model model) {
		logger.info("getAllRevenue->fired");
		model.addAttribute("revenues", incomeDAO.findAllRevenue());
		return "adminAllRevenue";
	}

}
