package qlns.web.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import qlns.PhuCap;
import qlns.data.PhuCapRepo;

@RestController
@RequestMapping(path = "/manage-allowance")
@CrossOrigin(origins="*")
public class PhuCapController{
	private PhuCapRepo pcRepo;
	
	@Autowired
	EntityLinks entityLinks;
	public PhuCapController(PhuCapRepo pcRepo) {
		this.pcRepo = pcRepo;
	}
	
	// findAll
	@GetMapping("/list")
	public Iterable<PhuCap> findAllPc(){
		return pcRepo.findAll();
	}
	
	// add allowance
	@PostMapping(path="/add", consumes="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public PhuCap postPc(@RequestBody PhuCap pc) {
		return pcRepo.save(pc);
	}
}
