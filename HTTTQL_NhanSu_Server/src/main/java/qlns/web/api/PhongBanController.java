package qlns.web.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import qlns.PhongBan;
import qlns.ThanhVien;
import qlns.data.PhongBanRepo;
import qlns.data.ThanhVienRepo;

@RestController
@RequestMapping(path = "/manage-department")
@CrossOrigin(origins="*")
public class PhongBanController{
	private PhongBanRepo pbRepo;
	
	@Autowired
	EntityLinks entityLinks;
	public PhongBanController(PhongBanRepo pbRepo) {
		this.pbRepo = pbRepo;
	}
	
	// findAll
	@GetMapping("/list")
	public Iterable<PhongBan> findAllPb(){
		return pbRepo.findAll();
	}
	
	// add department
	@PostMapping(path="/save", consumes="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public PhongBan postPb(@RequestBody PhongBan pb) {
		return pbRepo.save(pb);
	}

	@DeleteMapping("/delete/{id}")
	public void deletePb(@PathVariable("id") Long id) {
		pbRepo.deleteById(id);
	}
	
}
