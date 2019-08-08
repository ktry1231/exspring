package kr.ac.hit.myapp.prod;

import java.util.List;

public interface ProductService {
	public int insert(ProductVo vo);
	public List<ProductVo> selectList();
	public ProductVo select(int prodNo);
	public int update(ProductVo vo);
	public int delete(int prodNo);
}
