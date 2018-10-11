package com.joh.mms.controller;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joh.mms.domain.model.RevenueD;
import com.joh.mms.model.Check;
import com.joh.mms.model.DollarToDinar;
import com.joh.mms.service.CheckService;
import com.joh.mms.service.DollarToDinarService;
import com.joh.mms.service.IncomeService;

@Controller
@RequestMapping(path = "/checks")
public class CheckController {

	private static final Logger logger = Logger.getLogger(CheckController.class);

	@Autowired
	private CheckService checkService;

	@Autowired
	private IncomeService incomeService;

	@Autowired
	private DollarToDinarService dollarToDinarService;

	@GetMapping()
	public String getAllCheck(Model model) {
		logger.info("getAllCheck->fired");

		Iterable<Check> checks = checkService.findAll();
		model.addAttribute("checks", checks);
		return "adminChecks";
	}

	@GetMapping(path = "/add")
	public String getAddingCheck(Model model) {
		logger.info("getAddingCheck->fired");

		Check check = new Check();

		DollarToDinar dollarToDinar = dollarToDinarService.findCurrentDollarToDinar();
		logger.info("dollarToDinar=" + dollarToDinar);
		model.addAttribute("dollarToDinar", dollarToDinar);

		RevenueD revenueD = incomeService.getCurrentRevenue();

		check.setAmount(revenueD.getTotalIncome() - revenueD.getTotalExpense());

		model.addAttribute("check", check);

		return "check/addCheck";

	}

	@PostMapping(path = "/add")
	public String addCheck(@RequestBody @Valid Check check, BindingResult result, Model model) {
		logger.info("addCheck->fired");

		logger.info("check=" + check);

		logger.info("errors=" + result.getAllErrors());

		if (result.hasErrors()) {
			DollarToDinar dollarToDinar = dollarToDinarService.findCurrentDollarToDinar();
			logger.info("dollarToDinar=" + dollarToDinar);
			model.addAttribute("dollarToDinar", dollarToDinar);

			model.addAttribute("check", check);
			return "check/addCheck";
		} else {

			RevenueD revenueD = incomeService.getCurrentRevenue();

			check.setAmount(revenueD.getTotalIncome() - revenueD.getTotalExpense());

			checkService.save(check);

			return "success";
		}

	}

}
