package kr.ac.hit.myapp.prod;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductDao {
	public int insert(ProductVo vo);
	public List<ProductVo> selectList();
	public ProductVo select(int prodNo);
	public int update(ProductVo vo);
	public int delete(int prodNo);
}
