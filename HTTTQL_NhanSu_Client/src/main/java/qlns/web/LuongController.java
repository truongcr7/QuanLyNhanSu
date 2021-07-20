package qlns.web;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import qlns.Luong;

@Controller
@RequestMapping("/manage-salary")
public class LuongController {
	private RestTemplate rest = new RestTemplate();

	@GetMapping
	public String showManageSalaryView(HttpSession session){
		List<Luong> listLuong = Arrays.asList(rest.getForObject("http://localhost:8080/manage-salary/list", Luong[].class));
		session.setAttribute("listLuong", listLuong);
		return "manage-salary";
	}
	
	@GetMapping("/add")
	public String showAddSalaryView(Model model) {
		Luong luong = new Luong();
		model.addAttribute("luong", luong);
		return "edit-salary";
	}
	
	@PostMapping("/add")
	public String addSalary(Luong luong) {
		rest.postForObject("http://localhost:8080/manage-salary/add", luong, Luong.class);
		return "redirect:/manage-salary";
	}
	
	@GetMapping("/edit/indexLuong={indexLuong}")
	public String showEditLuong(@PathVariable("indexLuong") int indexLuong, HttpSession session, Model model) {
		List<Luong> listLuong = (List<Luong>) session.getAttribute("listLuong");
		Luong luong = listLuong.get(indexLuong);
		model.addAttribute("luong", luong);
		return "edit-salary";
	}
}
