package qlns.data;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import qlns.PhongBan;

public interface PhongBanRepo extends CrudRepository<PhongBan, Long>{
	
}
