package kr.ac.hit.myapp.prod;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
	@Resource
	private ProductDao productDao;
	
	@Override
	public int insert(ProductVo vo) {
		return productDao.insert(vo);
	}

	@Override
	public List<ProductVo> selectList() {
		return productDao.selectList();
	}

	@Override
	public ProductVo select(int prodNo) {
		return productDao.select(prodNo);
	}

	@Override
	public int update(ProductVo vo) {
		return productDao.update(vo);
	}

	@Override
	public int delete(int prodNo) {
		return productDao.delete(prodNo);
	}

}




