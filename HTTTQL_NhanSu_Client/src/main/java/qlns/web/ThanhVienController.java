package qlns.web;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import qlns.ThanhVien;

@Controller
public class ThanhVienController {
	private RestTemplate rest = new RestTemplate();
	
	@GetMapping("/")
	public String showLoginView(Model model) {
		ThanhVien tv = new ThanhVien();
		model.addAttribute("tv", tv);
		return "login";
	}
	
	@PostMapping("/checkLogin")
	public String checkLogin(ThanhVien tv, Model model, HttpSession session) {
		ThanhVien thanhVien = tv;
		tv = rest.postForObject("http://localhost:8080/checkLogin", tv, ThanhVien.class);
		if(tv == null) {
			String message = "Login Failed!";
			model.addAttribute("message", message);
			model.addAttribute("tv", thanhVien);
			return "login";
		}else {
			if(tv.getViTri().equals("quan ly")) {
				session.setAttribute("quanly", tv);
				return "redirect:/manager-home";
			}else if(tv.getViTri().equals("ke toan")) {
				session.setAttribute("ketoan", tv);
				return "redirect:/accountant-home";
			}else{				
				session.setAttribute("nhanvien", tv);
				return "test";
			}
		}	
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
	
	@GetMapping("/searchStaff")
	public String searchStaff(@RequestParam("tenNv") String tenNv, HttpSession session) {
		List<ThanhVien> listTv = Arrays.asList(rest.getForObject("http://localhost:8080/searchStaff/{tenNv}", ThanhVien[].class, tenNv));
		session.setAttribute("listTv", listTv);
		return "manage-staff";
	}
}
