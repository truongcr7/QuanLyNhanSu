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
public class HopDong_PhuCap {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(targetEntity = PhuCap.class)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private PhuCap phuCap;
	
	@ManyToOne(targetEntity = HopDong.class)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private HopDong hopDong;
}
