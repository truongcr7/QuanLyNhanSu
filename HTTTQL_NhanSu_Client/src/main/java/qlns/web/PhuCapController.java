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

import qlns.PhuCap;

@Controller
@RequestMapping("/manage-allowance")
public class PhuCapController {
	private RestTemplate rest = new RestTemplate();
	
	@GetMapping
	public String showManageAllowanceView(HttpSession session) {
		List<PhuCap> listPc = Arrays.asList(rest.getForObject("http://localhost:8080/manage-allowance/list", PhuCap[].class));
		session.setAttribute("listPc", listPc);
		return "manage-allowance";
	}
	
	@GetMapping("/add")
	public String showAddAllowanceView(Model model) {
		PhuCap pc = new PhuCap();
		model.addAttribute("pc",pc);
		return "edit-allowance";
	}
	
	@PostMapping("/add")
	public String addAllowance(PhuCap pc) {
		rest.postForObject("http://localhost:8080/manage-allowance/add", pc, PhuCap.class);
		return "redirect:/manage-allowance";
	}
	
	@GetMapping("/edit/indexPc={indexPc}")
	public String showEditAllowance(@PathVariable("indexPc") int indexPc, HttpSession session, Model model) {
		List<PhuCap> listPc = (List<PhuCap>) session.getAttribute("listPc");
		PhuCap pc = listPc.get(indexPc);
		model.addAttribute("pc", pc);
		return "edit-allowance";
	}
}
