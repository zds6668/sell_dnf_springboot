package com.imooc.controller;

import com.imooc.dataobject.ProductCategory;
import com.imooc.dataobject.ProductInfo;
import com.imooc.enums.ResultEnum;
import com.imooc.exception.SellException;
import com.imooc.form.ProductForm;
import com.imooc.service.CategoryService;
import com.imooc.service.ProductService;
import com.imooc.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/seller/product")
public class SellerProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size,
                             Map<String, Object> map) {
        PageRequest request = new PageRequest(page - 1, size);
        Page<ProductInfo> ProductInfoPage = productService.findAll(request);
        map.put("ProductInfoPage", ProductInfoPage);
        map.put("currentPage", page);
        map.put("currentSize", size);
        return new ModelAndView("product/list", map);
    }

    @RequestMapping("/onsale")
    public ModelAndView onSale(@RequestParam("productId") String productId,
                               Map<String, Object> map) {
        try {
            productService.onSale(productId);
        } catch (SellException e) {
            map.put("msg", e.getMessage());
            map.put("url", "/seller/product/list");
            map.put("status", 12);
            return new ModelAndView("/common/error", map);
        }
        map.put("url", "/seller/product/list");
        map.put("msg", ResultEnum.SUCCESS.getMsg());
        map.put("status", ResultEnum.SUCCESS.getCode());
        return new ModelAndView("/common/error", map);
    }

    @RequestMapping("/offsale")
    public ModelAndView offSale(@RequestParam("productId") String productId,
                               Map<String, Object> map) {
        try {
            productService.offSale(productId);
        } catch (SellException e) {
            map.put("msg", e.getMessage());
            map.put("url", "/seller/product/list");
            map.put("status", 12);
            return new ModelAndView("/common/error", map);
        }
        map.put("url", "/seller/product/list");
        map.put("msg", ResultEnum.SUCCESS.getMsg());
        map.put("status", ResultEnum.SUCCESS.getCode());
        return new ModelAndView("/common/error", map);
    }

    @RequestMapping("/index")
    public ModelAndView index(@RequestParam(value = "productId", required = false) String productId,
                                Map<String, Object> map) {
        if (!StringUtils.isEmpty(productId)) {
            ProductInfo productInfo = productService.findOne(productId);
            map.put("productInfo", productInfo);
        }
        List<ProductCategory> productCategoryList = categoryService.findAll();
        map.put("productCategoryList", productCategoryList);
        return new ModelAndView("/product/index", map);
    }

    @PostMapping("/save")
    public ModelAndView save(@Valid ProductForm form,
                             BindingResult bindingResult,
                             Map<String, Object> map) {
        if (bindingResult.hasErrors()) {
            map.put("msg", bindingResult.getFieldErrors());
            map.put("url", "/seller/product/index");
            map.put("status", 12);
            return new ModelAndView("/common/error", map);
        }
        ProductInfo productInfo = new ProductInfo();
        if (!StringUtils.isEmpty(form.getProductId())) {
            productInfo = productService.findOne(form.getProductId());
        } else {
            form.setProductId(KeyUtil.genUniqueKey());
        }
        BeanUtils.copyProperties(form, productInfo);
        try {
            productService.save(productInfo);
        } catch (SellException e) {
            map.put("msg", e.getMessage());
            map.put("url", "/seller/product/index");
            map.put("status", 12);
            return new ModelAndView("/common/error", map);
        }
        map.put("msg", ResultEnum.SUCCESS.getMsg());
        map.put("url", "/seller/product/list");
        map.put("status", ResultEnum.SUCCESS.getCode());
        return new ModelAndView("/common/error", map);
    }
}
