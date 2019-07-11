package com.nyist.controller.admin.product;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.nyist.dao.*;
import com.nyist.entity.*;
import com.nyist.service.*;
import com.nyist.vo.Json.ProductVo;
import com.nyist.vo.LayuiPageVo;
import com.nyist.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ：为天下溪
 * @date ：Created in 2019/4/4 9:35
 * @description：${description}
 * @version: $version$
 */
@Controller
@RequestMapping(value = "${adminPath}")
public class Product1Controller {

    @Autowired
    private ProductService productService;

    @Autowired
    private KindService kindService;
    @Autowired
    private KindMapper kindMapper;
    @Autowired
    private WarehouseService warehouseService;
    @Autowired
    private WarehouseMapper warehouseMapper;
    @Autowired
    private CompanyMapper companyMapper;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private ProductMapper productMapper;

    /**
     * 获取商品信息
     *
     * @param product
     * @param currentPage
     * @param pageSize
     * @return
     */
    @RequestMapping("/test")
    @ResponseBody
    public LayuiPageVo testJson(Product product, @RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
                                @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        product.setName(product.getName());
        LayuiPageVo lpv = new LayuiPageVo();
        PageInfo<Product> pageInfo = null;
        pageInfo = productService.pageList(product, currentPage, pageSize);
        List<Product> productList = pageInfo.getList();
        lpv.setCode(1);
        lpv.setMsg("获取数据成功");
        lpv.setTotal(pageInfo.getTotal());
        lpv.setData(productList);
        return lpv;
    }

    /**
     * 获取品牌、商品类型、营销方式
     *
     * @return
     */
    @RequestMapping("/getKind")
    @ResponseBody
    public String getKind() {
        List<String> kinds = kindService.selectAll();
        List<String> warehouses = warehouseService.selectAll();
        List<String> activity = companyService.selectAll();
        List<List> lists = new ArrayList<>();
        lists.add(kinds);
        lists.add(warehouses);
        lists.add(activity);
        String JsonString = JSON.toJSONString(lists);
        return JsonString;
    }


    /**
     * 新增(编辑)商品信息
     *
     * @param request
     * @param productVo
     * @return
     */
    @RequestMapping("/saveProduct")
    @ResponseBody
    public ResultVo saveProduct(HttpServletRequest request, @RequestBody ProductVo productVo) {
        Product product = new Product();
        product.setId(productVo.getId());
        product.setName(productVo.getName());
        product.setStatus(productVo.getStatus());
        product.setSupply(productVo.getStorage());
        product.setStorage(productVo.getStorage());
        product.setProdDesc(productVo.getProdDesc());
        product.setUnitPrice(new BigDecimal(productVo.getPrice()));
        if (productVo.getImgName() != null) {
            String image="/uploaded/"+productVo.getImgName();
            product.setImage(image);
        }
        product.setSupNo(warehouseMapper.selectByName(productVo.getSupNo1()).getWhNo().toString());
        product.setIsHot(companyMapper.selectByName(productVo.getIsHot1()).getSupNo());
        product.setKindNo((kindMapper.selectByName(productVo.getKindNo1()).getKindNo()).toString());
        if ("1".equals(productVo.getFlag())) {
            if (productService.updateByPrimaryKeySelective(product) != 0) {
                return new ResultVo(1, "商品更新成功", null);
            } else {
                return new ResultVo(0, "商品更新失败", null);
            }
        } else {
            productService.insertSelective(product);
            return new ResultVo(1, "商品添加成功", null);
        }

    }

    /**
     * 删除商品
     * @param request
     * @return
     */
    @RequestMapping("/delProduct")
    @ResponseBody
    public ResultVo delProduct(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        if (productMapper.deleteByPrimaryKey(id) != 0) {
            return new ResultVo(1, "删除成功", null);
        } else {
            return new ResultVo(0, "删除失败", null);
        }
    }
}
