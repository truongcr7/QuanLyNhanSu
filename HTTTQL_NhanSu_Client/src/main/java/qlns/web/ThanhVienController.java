package qlns.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import qlns.ThanhVien;

@Controller
public class ThanhVienController {
	private RestTemplate rest = new RestTemplate();
	
	@GetMapping("/")
	public String home() {
		return "redirect:/log-in";
	}
	
	@GetMapping("/log-in")
	public String showLoginView(Model model, HttpSession session) {
		if(session.getAttribute("quanly") != null) {
			return "redirect:/manager-home";
		} else if(session.getAttribute("ketoan") != null) {
			return "redirect:/accountant-home";
		} else {
			ThanhVien tv = new ThanhVien();
			model.addAttribute("tv", tv);
			return "login";
		}
	}
	
	@PostMapping("/check-login")
	public String checkLogin(ThanhVien tv, Model model, HttpSession session) {
		ThanhVien thanhVien = tv;
		tv = rest.postForObject("http://localhost:8080/check-login", tv, ThanhVien.class);
		if(tv == null) {
			String message = "Login Failed!";
			model.addAttribute("message", message);
			model.addAttribute("tv", thanhVien);
			return "login";
		}else {
			if(tv.getViTri().equals("Quản lý")) {
				session.setAttribute("quanly", tv);
				return "redirect:/manager-home";
			}else if(tv.getViTri().equals("Kế toán")) {
				session.setAttribute("ketoan", tv);
				return "redirect:/accountant-home";
			}else{				
				session.setAttribute("nhanvien", tv);
				return "test";
			}
		}	
	}
	
	@GetMapping("/log-out")
	public String logOut(HttpSession session) {
		if(session != null) {
			session.invalidate();
		}
		return "redirect:/log-in";
	}
	
	@GetMapping("/manager-home")
	public String showManagerHomeView() {
		return "manager-home";
	}
	
	@GetMapping("/accountant-home")
	public String showAccountantHomeView() {
		return "accountant-home";
	}
	
	@GetMapping("/manage-staff")
	public String showManageStaffView() {
		return "manage-staff";
	}
	
	@GetMapping("/manage-staff/search")
	public String searchStaff(@RequestParam("name") String name, HttpSession session) {
		if(name.isEmpty()) {
			name = "%";
		}
		List<ThanhVien> listTv = Arrays.asList(rest.getForObject("http://localhost:8080/manage-staff/search/{name}", ThanhVien[].class, name));
		session.setAttribute("listTv", listTv);
		session.setAttribute("nameTv", name);
		return "manage-staff";
	}
	
	@GetMapping("/manage-staff/delete/nameTv={nameTv}/indexTv={indexTv}")
	public String deleteStaff(@PathVariable("indexTv") int indexTv, HttpSession session) {
		List<ThanhVien> listTv = (List<ThanhVien>) session.getAttribute("listTv");
		ThanhVien tv = listTv.get(indexTv);
		rest.delete("http://localhost:8080/manage-staff/delete/{id}", tv.getId());
		return "redirect:/manage-staff/search?name={nameTv}";
	}
	
	@GetMapping("/manage-staff/edit/nameTv={nameTv}/indexTv={indexTv}")
	public String editStaff(@PathVariable("indexTv") int indexTv, HttpSession session, Model model) {
		List<ThanhVien> listTv = (List<ThanhVien>) session.getAttribute("listTv");
		ThanhVien tv = listTv.get(indexTv);
		model.addAttribute("tv", tv);
		return "edit-staff";
	}
	
	@PostMapping("/manage-staff/save/nameTv={nameTv}")
	public String saveStaff(ThanhVien tv) {
		rest.postForObject("http://localhost:8080/manage-staff/save", tv, ThanhVien.class);
		return "redirect:/manage-staff/search?name={nameTv}";
	}
}
