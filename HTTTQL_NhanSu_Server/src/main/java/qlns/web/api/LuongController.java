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

import qlns.Luong;
import qlns.data.LuongRepo;

@RestController
@RequestMapping("/manage-salary")
@CrossOrigin(origins = "*")
public class LuongController {
	private LuongRepo luongRepo;
	
	@Autowired
	EntityLinks entityLinks;
	
	public LuongController(LuongRepo luongRepo) {
		this.luongRepo = luongRepo;
	}
	
	// find all
	@GetMapping("/list")
	public Iterable<Luong> findAllLuong(){
		return luongRepo.findAll();
	}
	
	// add salary
	@PostMapping(path="/add", consumes="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Luong postAddSalary(@RequestBody Luong luong) {
		return luongRepo.save(luong);
	}
}
