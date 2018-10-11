package com.joh.mms.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joh.mms.dao.DollarToDinarDAO;
import com.joh.mms.model.DollarToDinar;
import com.joh.mms.model.Income;
import com.joh.mms.model.IncomeCategory;
import com.joh.mms.service.DollarToDinarService;
import com.joh.mms.service.IncomeCategoryService;
import com.joh.mms.service.IncomeService;
import com.joh.mms.validator.IncomeValidator;

@Controller
@RequestMapping(path = "/dollarToDinars")
public class DollarToDinarController {

	private static final Logger logger = Logger.getLogger(DollarToDinarController.class);

	@Autowired
	private DollarToDinarService dollarToDinarService;

	@GetMapping()
	public String getAllDinarToDolars(Model model) {
		logger.info("getAllDinarToDolars->fired");

		List<DollarToDinar> dollarToDinars = dollarToDinarService.findTop10();
		model.addAttribute("dollarToDinars", dollarToDinars);

		return "dollarToDinars";
	}

	@GetMapping(path = "/add")
	public String getAddingIncome(Model model) {
		logger.info("getAddingIncome->fired");

		DollarToDinar dollarToDinar = new DollarToDinar();
		dollarToDinar.setDollar(1);
		logger.info("dollarToDinar=" + dollarToDinar);

		model.addAttribute("dollarToDinar", dollarToDinar);

		return "dollarToDinar/addDollarToDinar";
	}

	@PostMapping(path = "/add")
	public String addDollarToDinar(@RequestBody DollarToDinar dollarToDinar, BindingResult result, Model model) {
		logger.info("addDollarToDinar->fired");
		logger.info("dollarToDinar=" + dollarToDinar);

		logger.info("errors=" + result.getAllErrors());

		if (result.hasErrors()) {

			model.addAttribute("dollarToDinar", dollarToDinar);

			return "dollarToDinar/addDollarToDinar";

		} else {
			dollarToDinarService.save(dollarToDinar);
			return "success";
		}

	}

}
