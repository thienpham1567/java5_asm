package com.asm.controller.admin;

import java.util.List;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.asm.entities.DbOrder;
import com.asm.repository.OrderRepository;

import com.asm.service.OrderService;

import antlr.StringUtils;

@Controller
public class MonthAdminController {
	@Autowired
	OrderService orderService;

	@Autowired
	OrderRepository orderRepo;

	@ModelAttribute("month")
	public List<DbOrder> getAllOrder() {
		return orderService.getAll(false);
	}

	@GetMapping("/admin/month")
	public String getAdminOrderPage(Model model) {
		int orderCount = getAllOrder().size();
		Double orderPrice = 0.0;
		for(int i =0;i < getAllOrder().size();i++) {
			orderPrice += getAllOrder().get(i).getOrderAmount(); 
		}
		model.addAttribute("orders", getAllOrder());
		model.addAttribute("order", new DbOrder());
		model.addAttribute("orderPrice", orderPrice);
		model.addAttribute("orderCount", orderCount);
		return "admin/month";
	}

	@PostMapping("/admin/month/search")
	public String search(@RequestParam("month") String month, @RequestParam("year") String year, Model model) {
	    // Gọi phương thức tìm kiếm dữ liệu với month và year được truyền vào
		int monthInt = Integer.parseInt(month);
		int yearInt = Integer.parseInt(year);
	    List<DbOrder> orders = orderRepo.findByMonth(monthInt, yearInt);
	    int orderCount = orders.size();
		Double orderPrice = 0.0;
		for(int i =0;i < orders.size();i++) {
			orderPrice += orders.get(i).getOrderAmount(); 
		}
	    // Thêm kết quả tìm kiếm vào model để hiển thị trên view
	    model.addAttribute("orders", orders);
	    model.addAttribute("order", new DbOrder());
	    model.addAttribute("orderPrice", orderPrice);
		model.addAttribute("orderCount", orderCount);
	    // Trả về view hiển thị kết quả tìm kiếm
	    return "/admin/month";
	}



}
