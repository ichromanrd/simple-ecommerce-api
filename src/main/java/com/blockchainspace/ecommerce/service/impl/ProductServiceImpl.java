package com.blockchainspace.ecommerce.service.impl;

import com.blockchainspace.ecommerce.persistence.Product;
import com.blockchainspace.ecommerce.persistence.mapper.ProductMapper;
import com.blockchainspace.ecommerce.service.IProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Ichroman Raditya Duwila
 * @since 2022-03-30
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

}
