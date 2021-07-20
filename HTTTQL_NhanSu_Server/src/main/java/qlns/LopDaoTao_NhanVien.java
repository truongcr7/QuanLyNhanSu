package qlns;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC, force = true)
public class LopDaoTao_NhanVien {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(targetEntity = ThanhVien.class)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private ThanhVien nv;
	
	@ManyToOne(targetEntity = LopDaoTao.class)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private LopDaoTao lopDaoTao;
}
