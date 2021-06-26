package qlns;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC, force = true)
public class HopDong {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private float thoiHan;
	
	@Temporal(TemporalType.DATE)
	private Date ngayKy;
	
	@ManyToOne(targetEntity = ThanhVien.class)
	private ThanhVien ql;

	@ManyToOne(targetEntity = ThanhVien.class)
	private ThanhVien nv;
	
	@ManyToOne(targetEntity = Luong.class)
	private Luong luong;
}
