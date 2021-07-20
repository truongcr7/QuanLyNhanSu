package qlns.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import qlns.HopDong;
import qlns.HopDong_PhuCap;
import qlns.Luong;
import qlns.PhongBan;
import qlns.PhuCap;
import qlns.ThanhVien;

@Controller
@RequestMapping("/manage-contract")
public class HopDongController {
	private RestTemplate rest = new RestTemplate();
	
	@GetMapping
	public String showManageContractView(HttpSession session, Model model) {
		List<Luong> listLuong = Arrays.asList(rest.getForObject("http://localhost:8080/manage-salary/list", Luong[].class));
		List<PhuCap> listPc = Arrays.asList(rest.getForObject("http://localhost:8080/manage-allowance/list", PhuCap[].class));
		List<PhongBan> listPb = Arrays.asList(rest.getForObject("http://localhost:8080/manage-department/list", PhongBan[].class));
		session.setAttribute("listLuong", listLuong);
		session.setAttribute("listPc", listPc);
		session.setAttribute("listPb", listPb);
		
		return "manage-contract";
	}
	
	@PostMapping("/add")
	public String addContract(@RequestParam("hoTen") String hoTen, @RequestParam("ngaySinh") String ngaySinh,
			@RequestParam("gioiTinh") String gioiTinh, @RequestParam("diaChi") String diaChi, @RequestParam("email") String email,
			@RequestParam("viTri") String viTri, @RequestParam("indexPb") int indexPb,
			@RequestParam("indexLuong") int indexLuong, @RequestParam("indexPc") List<Integer> listIndexPc,
			@RequestParam("thoiHan") float thoiHan, @RequestParam("ngayKy") String ngayKy,
			HttpSession session, Model model) throws ParseException {
		
		ThanhVien tv = new ThanhVien();
		tv.setHoTen(hoTen);
		tv.setNgaySinh(new SimpleDateFormat("yyyy-MM-dd").parse(ngaySinh));
		tv.setGioiTinh(gioiTinh);
		tv.setDiaChi(diaChi);
		tv.setEmail(email);
		tv.setViTri(viTri);
		List<PhongBan> listPb = (List<PhongBan>) session.getAttribute("listPb");
		tv.setPb(listPb.get(indexPb));
		tv = rest.postForObject("http://localhost:8080/manage-staff/save", tv, ThanhVien.class);
		
		List<Luong> listLuong = (List<Luong>) session.getAttribute("listLuong");
		Luong luong = listLuong.get(indexLuong);
		Date ngayKyCvt = new SimpleDateFormat("yyyy-MM-dd").parse(ngayKy);
		HopDong hd = new HopDong();
		hd.setThoiHan(thoiHan);
		hd.setNgayKy(ngayKyCvt);
		hd.setQl((ThanhVien) session.getAttribute("quanly"));
		hd.setNv(tv);
		hd.setLuong(luong);
		hd = rest.postForObject("http://localhost:8080/manage-contract/add", hd, HopDong.class);
		model.addAttribute("hd", hd);
		
		List<PhuCap> listPc = (List<PhuCap>) session.getAttribute("listPc");
		List<PhuCap> listSelectedPc = new ArrayList<PhuCap>();
		for(int indexPc : listIndexPc) {
			PhuCap pc = listPc.get(indexPc);
			listSelectedPc.add(pc);
			HopDong_PhuCap hd_pc = new HopDong_PhuCap();
			hd_pc.setHopDong(hd);
			hd_pc.setPhuCap(pc);
			rest.postForObject("http://localhost:8080/contract-allowance/add", hd_pc, HopDong_PhuCap.class);
		}
		model.addAttribute("listSelectedPc", listSelectedPc);
		
		String message = "Tạo hợp đồng thành công!";
		model.addAttribute("message", message);
		
		return "view-contract";
	}
}
