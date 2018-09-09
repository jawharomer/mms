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

import com.joh.mms.model.IncomeCategory;
import com.joh.mms.service.IncomeCategoryService;

@Controller
@RequestMapping(path = "/incomeCategories")
public class IncomeCategoryController {

	private static final Logger logger = Logger.getLogger(IncomeCategoryController.class);

	@Autowired
	private IncomeCategoryService incomeCategoryService;

	@GetMapping()
	public String getAllIncomeCategories(Model model) {
		logger.info("getAllIncomeCategories->fired");

		Iterable<IncomeCategory> incomeCategories = incomeCategoryService.findAll();

		logger.info("incomeCategories=" + incomeCategories);

		model.addAttribute("incomeCategories", incomeCategories);

		return "incomeCategories";
	}

	@GetMapping(path = "/add")
	public String getAddingIncomeCategory(Model model) {
		logger.info("getAddingIncomeCategory->fired");

		model.addAttribute("incomeCategory", new IncomeCategory());
		return "incomeCategory/addIncomeCategory";

	}

	@PostMapping(path = "/add")
	public String addIncomeCategory(@RequestBody @Valid IncomeCategory incomeCategory, BindingResult result,
			Model model) {
		logger.info("addIncomeCategory->fired");

		logger.info("incomeCategory=" + incomeCategory);

		logger.info("errors=" + result.getAllErrors());

		if (result.hasErrors()) {
			model.addAttribute("incomeCategory", incomeCategory);
			return "incomeCategory/addIncomeCategory";
		} else {
			incomeCategoryService.save(incomeCategory);
			return "success";
		}

	}

	@GetMapping(path = "/edit/{id}")
	public String getEditingIncomeCategory(@PathVariable int id, Model model) {
		logger.info("getEditingIncomeCategory->fired");
		logger.info("id=" + id);

		IncomeCategory incomeCategory = incomeCategoryService.findOne(id);

		model.addAttribute("incomeCategory", incomeCategory);
		return "incomeCategory/editIncomeCategory";

	}

	@PostMapping(path = "/update")
	public String updateIncomeCategory(@RequestBody @Valid IncomeCategory incomeCategory, BindingResult result,
			Model model) {
		logger.info("updateIncomeCategory->fired");

		logger.info("incomeCategory=" + incomeCategory);

		logger.info("errors=" + result.getAllErrors());

		if (result.hasErrors()) {
			model.addAttribute("incomeCategory", incomeCategory);
			return "incomeCategory/editIncomeCategory";
		} else {
			incomeCategoryService.update(incomeCategory);
			return "success";
		}

	}

	@PostMapping(path = "/delete/{id}")
	public String deleteIncomeCategory(@PathVariable int id) {
		logger.info("deleteIncomeCategory->fired");
		logger.info("id=" + id);
		incomeCategoryService.delete(id);
		return "success";
	}

}
