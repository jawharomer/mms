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

import com.joh.mms.model.Expense;
import com.joh.mms.model.ExpenseCategory;
import com.joh.mms.model.Income;
import com.joh.mms.model.IncomeCategory;
import com.joh.mms.service.ExpenseCategoryService;
import com.joh.mms.service.ExpenseService;
import com.joh.mms.service.IncomeCategoryService;
import com.joh.mms.service.IncomeService;
import com.joh.mms.validator.ExpenseValidator;
import com.joh.mms.validator.IncomeValidator;

@Controller
@RequestMapping(path = "/expenses")
public class ExpenseController {

	private static final Logger logger = Logger.getLogger(ExpenseController.class);

	@Autowired
	private ExpenseService expenseService;

	@Autowired
	private ExpenseCategoryService expenseCategoryService;

	@GetMapping()
	public String getAllExpense(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date from,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date to, Model model) {
		logger.info("getAllExpense->fired");
		logger.info("from=" + from);
		logger.info("to=" + to);

		Iterable<Expense> expenses = expenseService.findAllByTimeBetween(from, to);

		logger.info("expenses=" + expenses);
		model.addAttribute("expenses", expenses);
		model.addAttribute("from", from);
		model.addAttribute("to", to);

		return "expenses";
	}

	@GetMapping(path = "/add")
	public String getAddingExpense(Model model) {
		logger.info("getAddingExpense->fired");

		Iterable<ExpenseCategory> expenseCategories = expenseCategoryService.findAll();
		model.addAttribute("expenseCategories", expenseCategories);

		model.addAttribute("expense", new Expense());

		return "expense/addExpense";
	}

	@PostMapping(path = "/add")
	public String addExpense(@RequestBody @Validated(ExpenseValidator.Insert.class) Expense expense,
			BindingResult result, Model model) {
		logger.info("addExpense->fired");
		logger.info("expense=" + expense);

		logger.info("errors=" + result.getAllErrors());

		if (result.hasErrors()) {
			Iterable<ExpenseCategory> expenseCategories = expenseCategoryService.findAll();
			model.addAttribute("expenseCategories", expenseCategories);

			model.addAttribute("expense", expense);
			return "expense/addExpense";
		} else {
			expenseService.save(expense);
			return "success";
		}

	}

	@PostMapping(path = "/delete/{id}")
	public String deleteExpense(@PathVariable int id) {
		logger.info("deleteExpense->fired");
		logger.info("id=" + id);
		expenseService.delete(id);
		return "success";

	}

	@GetMapping(path = "/{id}")
	public String getExpense(@PathVariable int id, Model model) {
		logger.info("getExpense->fired");
		logger.info("id=" + id);
		Expense expense = expenseService.findOne(id);
		logger.info("expense=" + expense);
		model.addAttribute("expense", expense);

		return "getExpense";

	}

}
