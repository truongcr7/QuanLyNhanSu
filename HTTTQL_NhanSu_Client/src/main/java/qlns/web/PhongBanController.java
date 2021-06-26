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

import qlns.PhongBan;
import qlns.ThanhVien;

@Controller
@RequestMapping("/manage-department")
public class PhongBanController {
	private RestTemplate rest = new RestTemplate();
	
	@GetMapping
	public String showManageDepartmentView(HttpSession session) {
		List<PhongBan> listPb = Arrays.asList(rest.getForObject("http://localhost:8080/manage-department/list", PhongBan[].class));
		session.setAttribute("listPb", listPb);
		return "manage-department";
	}
	
	@GetMapping("/add")
	public String showAddDepartView(Model model) {
		PhongBan pb = new PhongBan();
		model.addAttribute("pb",pb);
		return "edit-department";
	}
	
	@PostMapping("/save")
	public String addDepart(PhongBan pb) {
		rest.postForObject("http://localhost:8080/manage-department/save", pb, PhongBan.class);
		return "redirect:/manage-department";
	}
	
	@GetMapping("/{idPb}/view-staff")
	public String showViewStaff(@PathVariable("idPb") Long idPb, HttpSession session) {
		List<ThanhVien> listTvPb = Arrays.asList(rest.getForObject("http://localhost:8080/manage-department/{idPb}/view-staff", ThanhVien[].class, idPb));
		session.setAttribute("listTvPb", listTvPb);
		return "view-staff-depart";
	}
	
	@GetMapping("/edit/{indexPb}")
	public String showEditPb(@PathVariable("indexPb") int indexPb, HttpSession session, Model model) {
		List<PhongBan> listPb = (List<PhongBan>) session.getAttribute("listPb");
		PhongBan pb = listPb.get(indexPb);
		model.addAttribute("pb", pb);
		return "edit-department";
	}
	
	@GetMapping("/delete/{indexPb}")
	public String deletePb(@PathVariable("indexPb") int indexPb, HttpSession session) {
		List<PhongBan> listPb = (List<PhongBan>) session.getAttribute("listPb");
		PhongBan pb = listPb.get(indexPb);
		rest.delete("http://localhost:8080/manage-department/delete/{id}", pb.getId());
		return "redirect:/manage-department";
	}
}
