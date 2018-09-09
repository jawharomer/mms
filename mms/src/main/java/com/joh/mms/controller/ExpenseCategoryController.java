package com.joh.mms.controller;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joh.mms.model.ExpenseCategory;
import com.joh.mms.model.Income;
import com.joh.mms.model.IncomeCategory;
import com.joh.mms.service.ExpenseCategoryService;
import com.joh.mms.service.IncomeCategoryService;

@Controller
@RequestMapping(path = "/expenseCategories")
public class ExpenseCategoryController {

	private static final Logger logger = Logger.getLogger(ExpenseCategoryController.class);

	@Autowired
	private ExpenseCategoryService expenseCategoryService;

	@GetMapping()
	public String getAllExpenseCategories(Model model) {
		logger.info("getAllExpenseCategories->fired");

		Iterable<ExpenseCategory> expenseCategories = expenseCategoryService.findAll();

		logger.info("expenseCategories=" + expenseCategories);

		model.addAttribute("expenseCategories", expenseCategories);

		return "expenseCategories";
	}

	@GetMapping(path = "/add")
	public String getAddingExpenseCategory(Model model) {
		logger.info("getAddingExpenseCategory->fired");

		model.addAttribute("expenseCategory", new ExpenseCategory());
		return "expenseCategory/addExpenseCategory";

	}

	@PostMapping(path = "/add")
	public String addExpenseCategory(@RequestBody @Valid ExpenseCategory expenseCategory, BindingResult result,
			Model model) {
		logger.info("addExpenseCategory->fired");

		logger.info("expenseCategory=" + expenseCategory);

		logger.info("errors=" + result.getAllErrors());

		if (result.hasErrors()) {
			model.addAttribute("expenseCategory", expenseCategory);
			return "expenseCategory/addExpenseCategory";
		} else {
			expenseCategoryService.save(expenseCategory);
			return "success";
		}

	}

	@GetMapping(path = "/edit/{id}")
	public String getEditingExpenseCategory(@PathVariable int id, Model model) {
		logger.info("getEditingExpenseCategory->fired");
		logger.info("id=" + id);

		ExpenseCategory expenseCategory = expenseCategoryService.findOne(id);

		model.addAttribute("expenseCategory", expenseCategory);
		return "expenseCategory/editExpenseCategory";

	}

	@PostMapping(path = "/update")
	public String updateExpenseCategory(@RequestBody @Valid ExpenseCategory expenseCategory, BindingResult result,
			Model model) {
		logger.info("updateExpenseCategory->fired");

		logger.info("expenseCategory=" + expenseCategory);

		logger.info("errors=" + result.getAllErrors());

		if (result.hasErrors()) {
			model.addAttribute("expenseCategory", expenseCategory);
			return "expenseCategory/editExpenseCategory";
		} else {
			expenseCategoryService.update(expenseCategory);
			return "success";
		}

	}

	@PostMapping(path = "/delete/{id}")
	public String deleteExpenseCategory(@PathVariable int id) {
		logger.info("deleteExpenseCategory->fired");
		logger.info("id=" + id);
		expenseCategoryService.delete(id);
		return "success";
	}

}
