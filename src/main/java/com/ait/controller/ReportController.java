package com.ait.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.ait.entity.CitizenPlan;
import com.ait.request.SearchRequest;
import com.ait.service.ReportService;

@Controller
public class ReportController {

	@Autowired
	private ReportService service;
	
	@PostMapping("/search")
	public String search( SearchRequest request, Model model) {
		List<CitizenPlan> plans = service.search(request);
		model.addAttribute("customerplans", plans);
		init(model);
		model.addAttribute("search",request);
		
		return "index";
		
	}
	
	@GetMapping("/")
	public String indexpage(Model model) {

		init(model);
		
	    return "index";
	
	}

	private void init(Model model) {
		model.addAttribute("search",new  SearchRequest());
		model.addAttribute("plannames",service.getPlanNames());
	    model.addAttribute("planstatuses",service.getPlanStatuses());
	}
}
