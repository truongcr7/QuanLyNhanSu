package qlns;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC, force = true)
public class NgayCong {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Temporal(TemporalType.DATE)
	private Date ngay;
	
	@Temporal(TemporalType.TIME)
	private Date gioDen;
	
	@Temporal(TemporalType.TIME)
	private Date gioVe;
	
	@ManyToOne(targetEntity = ThanhVien.class)
	private ThanhVien nv;
	
	@ManyToOne(targetEntity = PhieuLuong.class)
	private PhieuLuong phieuLuong;
}
