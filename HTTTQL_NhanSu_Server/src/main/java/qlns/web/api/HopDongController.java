package qlns.web.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import qlns.HopDong;
import qlns.data.HopDongRepo;

@RestController
@RequestMapping("/manage-contract")
@CrossOrigin(origins = "*")
public class HopDongController {
	private HopDongRepo hdRepo;
	
	@Autowired
	EntityLinks entityLinks;
	
	public HopDongController(HopDongRepo hdRepo) {
		this.hdRepo = hdRepo;
	}
	
	@PostMapping(path="/add", consumes="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public HopDong addContract(@RequestBody HopDong hd) {
		return hdRepo.save(hd);
	}
}
