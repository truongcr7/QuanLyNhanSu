package qlns;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC, force = true)
public class PhieuLuong {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Date thang;
	
	private float soNgayNghi;
	
	private float soGioLt;
	
	private float tongTien;
	
	@OneToMany(mappedBy = "phieuLuong")
	private List<NgayCong> listNgayCong;
	
	@ManyToOne(targetEntity = ThanhVien.class)
	private ThanhVien nv;
	
	@ManyToOne(targetEntity = HopDong.class)
	private HopDong hopDong;
	
	@ManyToOne(targetEntity = ThanhVien.class)
	private ThanhVien keToan;
}
