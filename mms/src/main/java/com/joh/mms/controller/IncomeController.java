package com.joh.mms.controller;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.joh.mms.model.Income;
import com.joh.mms.model.IncomeCategory;
import com.joh.mms.service.IncomeCategoryService;
import com.joh.mms.service.IncomeService;
import com.joh.mms.validator.IncomeValidator;

@Controller
@RequestMapping(path = "/incomes")
public class IncomeController {

	private static final Logger logger = Logger.getLogger(IncomeController.class);

	@Autowired
	private IncomeService incomeService;

	@Autowired
	private IncomeCategoryService incomeCategoryService;

	@GetMapping()
	public String getAllIncomes(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date from,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date to, Model model) {
		logger.info("getAllIncomes->fired");
		logger.info("from=" + from);
		logger.info("to=" + to);

		Iterable<Income> incomes = incomeService.findAllByTimeBetween(from, to);

		logger.info("incomes=" + incomes);
		model.addAttribute("incomes", incomes);
		model.addAttribute("from", from);
		model.addAttribute("to", to);

		return "incomes";
	}

	@GetMapping(path = "/add")
	public String getAddingIncome(Model model) {
		logger.info("getAddingIncome->fired");

		Iterable<IncomeCategory> incomeCategories = incomeCategoryService.findAll();
		model.addAttribute("incomeCategories", incomeCategories);

		model.addAttribute("income", new Income());

		return "income/addIncome";
	}

	@PostMapping(path = "/add")
	public String addIncome(@RequestBody @Validated(IncomeValidator.Insert.class) Income income, BindingResult result,
			Model model) {
		logger.info("addIncome->fired");
		logger.info("income=" + income);

		logger.info("errors=" + result.getAllErrors());

		if (result.hasErrors()) {
			Iterable<IncomeCategory> incomeCategories = incomeCategoryService.findAll();
			model.addAttribute("incomeCategories", incomeCategories);

			model.addAttribute("income", income);
			return "income/addIncome";
		} else {
			incomeService.save(income);
			return "success";
		}

	}

	@PostMapping(path = "/delete/{id}")
	public String deleteIncome(@PathVariable int id) {
		logger.info("deleteIncome->fired");
		logger.info("id=" + id);
		incomeService.delete(id);
		return "success";

	}

	@GetMapping(path = "/{id}")
	public String getIncome(@PathVariable int id, Model model) {
		logger.info("getIncome->fired");
		logger.info("id=" + id);
		Income income = incomeService.findOne(id);
		logger.info("income=" + income);
		model.addAttribute("income", income);

		return "getIncome";

	}

}
