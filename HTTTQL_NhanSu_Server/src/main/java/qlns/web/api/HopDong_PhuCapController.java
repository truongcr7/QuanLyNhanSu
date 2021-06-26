package qlns.web.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import qlns.HopDong_PhuCap;
import qlns.data.HopDong_PhuCapRepo;

@RestController
@CrossOrigin(origins = "*")
public class HopDong_PhuCapController {
	private HopDong_PhuCapRepo hd_pcRepo;
	
	@Autowired
	EntityLinks entityLinks;
	public HopDong_PhuCapController(HopDong_PhuCapRepo hd_pcRepo) {
		this.hd_pcRepo = hd_pcRepo;
	}
	
	@PostMapping(path="/contract-allowance/add", consumes="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public HopDong_PhuCap addContractAllowance(@RequestBody HopDong_PhuCap hd_pc) {
		return hd_pcRepo.save(hd_pc);
	}
}
