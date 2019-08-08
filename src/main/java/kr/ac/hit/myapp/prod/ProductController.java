package kr.ac.hit.myapp.prod;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/product/") //이 컨트롤러 클래스의 메서드들에 공통적인 경로를 클래스에 지정
public class ProductController {
	
	@Resource
	private ProductService productService;
	
	@RequestMapping(value="add.do",method=RequestMethod.GET) 
	public String addForm() {
		return "product/prodAdd";
	}
	
	@RequestMapping(value="add.do",method=RequestMethod.POST) 
	public String add( ProductVo vo ) {
		int num = productService.insert(vo);
		return "redirect:/product/list.do";
	}
	
	@RequestMapping("list.do") 
	public String list(Map model) {
		List<ProductVo> list = productService.selectList();
		model.put("prodList", list);
		return "product/prodList";
	}
	
	@RequestMapping(value="edit.do",method=RequestMethod.GET) 
	public String editForm(int prodNo, Map model) {
		ProductVo vo = productService.select(prodNo);
		model.put("prodVo", vo);
		return "product/prodEdit";
	}
	
	@RequestMapping(value="edit.do",method=RequestMethod.POST) 
	public String edit(ProductVo vo) {
		int num = productService.update(vo);
		return "redirect:/product/list.do";
	}
	
	@RequestMapping("del.do") 
	public String del(int prodNo) {
		int num = productService.delete(prodNo);
		return "redirect:/product/list.do";
	}
	
}









