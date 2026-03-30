package com.eDecree.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eDecree.model.ActionResponse;
import com.eDecree.model.CaseFileDetail;
import com.eDecree.model.Search;
import com.eDecree.service.CaseFileDetailService;
import com.eDecree.service.SearchService;
import com.eDecree.utility.GlobalFunction;

@Controller
@RequestMapping("/search")
public class SearchController {
	@Autowired
	private SearchService searchService;
	
	@Autowired
	private CaseFileDetailService caseFileDetailService;
	
	private GlobalFunction globalfunction;
	public SearchController() {
		globalfunction = new GlobalFunction();
	}
	
	@RequestMapping(value = "/searchByCaseDetail", method = RequestMethod.GET)
	public String searchByCaseDetail() {
		return "/search/searchByCaseDetail";
	}
	@RequestMapping(value = "/searchByParty", method = RequestMethod.GET)
	public String searchByParty() {
		return "/search/searchByParty";
	}
	@RequestMapping(value = "/searchByCounsel", method = RequestMethod.GET)
	public String searchByCounsel() {
		return "/search/searchByCounsel";
	}
	@RequestMapping(value = "/searchByJudge", method = RequestMethod.GET)
	public String searchByJudge() {
		return "/search/searchByJudge";
	}
	@RequestMapping(value = "/impugnedOrderWise", method = RequestMethod.GET)
	public String impugnedCourtWise() {
		return "/search/searchByImpugned";
	}
	@RequestMapping(value = "/getByCaseDetail", method = RequestMethod.POST)
	public @ResponseBody String getByCaseDetail(@RequestBody Search search) {
		String jsonData = null;
		ActionResponse<CaseFileDetail> response=new ActionResponse<>();
		
		List<CaseFileDetail> reports=searchService.getByCaseDetail(search);
		Integer total_count=searchService.getByCaseDetailCount(search);
		response.setResponse("TRUE");
		response.setModelList(reports);
		response.setData(total_count);
		jsonData = globalfunction.convert_to_json(response);

		return jsonData;
		
	}
	@RequestMapping(value = "/getByParty", method = RequestMethod.POST)
	public @ResponseBody String getByParty(@RequestBody Search search) {
		String jsonData = null;
		ActionResponse<CaseFileDetail> response=new ActionResponse<>();
		
		List<CaseFileDetail> reports=searchService.getByParty(search);
		Integer total_count=searchService.getByPartyCount(search);
		response.setResponse("TRUE");
		response.setModelList(reports);
		response.setData(total_count);
		jsonData = globalfunction.convert_to_json(response);

		return jsonData;
		
	}
	@RequestMapping(value = "/getByCounsel", method = RequestMethod.POST)
	public @ResponseBody String getByCounsel(@RequestBody Search search) {
		String jsonData = null;
		ActionResponse<CaseFileDetail> response=new ActionResponse<>();
		
		List<CaseFileDetail> reports=searchService.getByCounsel(search);
		Integer total_count=searchService.getByCounselCount(search);
		response.setResponse("TRUE");
		response.setModelList(reports);
		response.setData(total_count);
		jsonData = globalfunction.convert_to_json(response);

		return jsonData;
		
	}
	@RequestMapping(value = "/getByJudge", method = RequestMethod.POST)
	public @ResponseBody String getByJudge(@RequestBody Search search) {
		String jsonData = null;
		ActionResponse<CaseFileDetail> response=new ActionResponse<>();
		
		List<CaseFileDetail> reports=searchService.getByJudge(search);
		Integer total_count=searchService.getByJudgeCount(search);
		response.setResponse("TRUE");
		response.setModelList(reports);
		response.setData(total_count);
		jsonData = globalfunction.convert_to_json(response);

		return jsonData;
		
	}
	@RequestMapping(value = "/getByImpugnedDetail", method = RequestMethod.POST)
	public @ResponseBody String getByImpugnedDetail(@RequestBody Search search) {
		String jsonData = null;
		ActionResponse<CaseFileDetail> response=new ActionResponse<>();
		
		List<CaseFileDetail> reports=searchService.getByImpugnedDetail(search);
		Integer total_count=searchService.getByImpugnedDetailCount(search);
		response.setResponse("TRUE");
		response.setModelList(reports);
		response.setData(total_count);
		jsonData = globalfunction.convert_to_json(response);

		return jsonData;
		
	}
}
