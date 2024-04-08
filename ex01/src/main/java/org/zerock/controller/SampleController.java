package org.zerock.controller;

import org.springframework.http.HttpHeaders;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.SampleDTO;
import org.zerock.domain.TodoDTO;

import com.google.gson.Gson;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping("/sample/*")
public class SampleController {

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
		binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(dateFormat, false));
	}

	@RequestMapping("")
	public void basic() {
		log.info("basic....");
	}

	@RequestMapping(value = "/basic", method = { RequestMethod.GET })
	public void basicGetPost() {
		log.info("basic get............");
	}

	@RequestMapping(value = "/basic", method = { RequestMethod.POST })
	public void basicGetPost2() {
		log.info("basic post............");
	}

	@GetMapping(value = "/basic2")
	public void basicGet2() {
		log.info("basic get3.......");
	}

	@PostMapping("/basic2")
	public void basicPost2() {
		log.info("basic post2.......");
	}

	@GetMapping("/ex01")
	public String ex01(SampleDTO dto) {
		log.info(dto);
		return "ex01";
	}

	@PostMapping("/ex01")
	public String ex01_(SampleDTO dto) {
		log.info(dto);
		return "ex01";
	}

	@GetMapping("/ex02")
	public String ex02(@RequestParam("name") String name, @RequestParam("age") int age) {
		log.info(name);
		log.info(age);
		return "ex02";
	}

	@PostMapping("/ex02")
	public String ex02_(@RequestParam("name") String name, @RequestParam("age") int age) {
		log.info(name);
		log.info(age);
		return "ex02";
	}

	@GetMapping("/ex03")
	public String ex03(TodoDTO dto) {
		log.info(dto);
		log.info(dto.getTitle());
		log.info(dto.getDueDate());
		return "ex03";
	}

	@GetMapping("/ex04")
	public String ex04(SampleDTO dto, int page, Model model) {
		log.info("dto: " + dto);
		log.info("page: " + page);

		model.addAttribute("list", dto);
		model.addAttribute("page", page);

		return "/sample/ex04";
	}

	@GetMapping("/ex044")
	public String ex04_(@RequestParam("age2") int age2, RedirectAttributes rttr) {

		rttr.addAttribute("age2", age2);

		return "redirect:/sample/ex01";

	}

	@GetMapping("/ex05") // /sample/ex05 => WEB-INF/view/sample/ex05.jsp
	public void ex05() {
		log.info("ex05");

	}

	@GetMapping("/ex06")
	@ResponseBody
	public SampleDTO ex06() {
		SampleDTO sampleDTO = new SampleDTO();

		sampleDTO.setName("bbb");
		sampleDTO.setAge(100);
		return sampleDTO;
	}

	@GetMapping("/ex07")
	public ResponseEntity<String> ex07() {
		log.info("ex07..");

		String msg = "{\"name\":\"홍길동\"}";

		HttpHeaders headers = new HttpHeaders(null);
		headers.add("Content-Type", "application/json; charset=utf-8");

		return new ResponseEntity<>(msg, headers, HttpStatus.ACCEPTED);
	}

	@GetMapping("/ex08")
	public ResponseEntity<String> ex08() {
		log.info("ex08..");

		String msg = "{\"name\":\"홍길동\"}";

		SampleDTO dto = new SampleDTO();
		dto.setName("홍길동");
		dto.setAge(10);

		Gson gson = new Gson();
		String jsonStr = gson.toJson(dto);

		log.info(jsonStr);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=utf-8");

		return new ResponseEntity<>(jsonStr, headers, HttpStatus.ACCEPTED);
	}
@GetMapping("/exUpload") //locadhost:8181/sample/exUpload   > (void) 
public void exUpload() {
	log.info("exUpload");
}

@PostMapping("/exUploadPost")
public void exUploadPost(ArrayList<MultipartFile> files) {
	files.forEach(file -> {
		log.info("------------");
		log.info("name: "+file.getOriginalFilename());
		log.info("size : "+ file.getSize());
	});
}

}
