package qlns.data;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import qlns.ThanhVien;

public interface ThanhVienRepo extends CrudRepository<ThanhVien, Long>{
	@Query(value = "select * from thanh_vien where username = ?1 and password = ?2", nativeQuery = true)
	public ThanhVien checkLogin(String username, String password);
	
	@Query(value = "select * from thanh_vien where ho_ten like %?1%"
			+ " and vi_tri != 'quan ly' and vi_tri != 'ke toan'", nativeQuery = true)
	public List<ThanhVien> searchStaff(String name);
	
	@Query(value = "select * from thanh_vien where pb_id = ?1", nativeQuery=true)
	public List<ThanhVien> searchStaffByPb(Long idPb);

}
