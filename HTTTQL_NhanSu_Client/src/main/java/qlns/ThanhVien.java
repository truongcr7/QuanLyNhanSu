package qlns;

import java.util.Date;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC, force = true)
//@Inheritance(strategy = InheritanceType.JOINED)
//@DiscriminatorColumn(name="viTri")
public class ThanhVien {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String username;
	
	private String password;
	
	private String hoTen;
	
	@Temporal(TemporalType.DATE)
	private Date ngaySinh;
	
	private String gioiTinh;
	
	private String diaChi;
	
	private String email;
	
	private String viTri;
	
	@ManyToOne(targetEntity = PhongBan.class)
	private PhongBan pb;
}
