package qlns.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/manage-training")
public class LopDaoTaoController {
	private RestTemplate rest = new RestTemplate();
	
	@GetMapping
	public void showManageTrainingView() {
	}
}
