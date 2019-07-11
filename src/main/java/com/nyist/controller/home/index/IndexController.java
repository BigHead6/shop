package com.nyist.controller.home.index;

import com.nyist.dao.CompanyMapper;
import com.nyist.dao.KindMapper;
import com.nyist.dao.ProductMapper;
import com.nyist.dao.WarehouseMapper;
import com.nyist.entity.Company;
import com.nyist.entity.Kind;
import com.nyist.entity.Product;
import com.nyist.entity.Warehouse;
import com.nyist.service.CompanyService;
import com.nyist.service.KindService;
import com.nyist.service.ProductService;
import com.nyist.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ：为天下溪
 * @date ：Created in 2019/3/6 13:42
 * @description：${description}
 * @version: $version$
 */
@Controller
@RequestMapping(value = "${frontPath}")
public class IndexController {

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
     * 首页数据展示
     *
     * @param model
     * @return
     */
    @RequestMapping("/index")
    public String index1(HttpServletRequest request, HttpSession session, Model model) {

        //获取今日推荐商品
        List<Product> productList1 = productService.selectByHot(2);
        List<Product> tjList = new ArrayList();
        if (productList1.size() > 3) {
            tjList = productList1.subList(0, 3);
        } else {
            tjList = productList1;
        }
        model.addAttribute("tjList", tjList);

        //获取活动商品
        List<Product> productList2 = productService.selectByHot(1);
        List<Product> activityList = new ArrayList();
        if (productList2.size() > 4) {
            activityList = productList2.subList(0, 4);
        } else {
            activityList = productList2;
        }
        model.addAttribute("activityList", activityList);

        //获取水果
        List<Product> productList3 = productService.selectByHot(3);
        List<Product> fruitList = new ArrayList();
        if (productList3.size() > 8) {
            fruitList = productList3.subList(0, 8);
        } else {
            fruitList = productList3;
        }
        model.addAttribute("fruitList", fruitList);

        //获取页面导航栏
        List<Kind> kinds = kindMapper.selectAll();
        Map<Kind,List<Product>> map = new HashMap<>();
        for (int i = 0; i < kinds.size(); i++) {
            List<Product> product = productService.selectByKindNo(kinds.get(i).getKindNo().toString());
            map.put(kinds.get(i),product);
        }
        model.addAttribute("map",map);


        //获取首页导航
        List<Company> companies=companyMapper.selectAll();
        model.addAttribute("companies",companies);
        return "home/home";
    }
    @RequestMapping("/login")
    public String login() {
        return "home/login";
    }

    @RequestMapping("/register")
    public String register() {
        return "home/register";
    }
    @RequestMapping("/search")
    public String search() {
        return "home/search";
    }


    @RequestMapping("/sort")
    public String sort() {
        return "home/sort";
    }

    @RequestMapping("/forgetPwd")
    public String forgetPwd() {
        return "home/forgetPwd";
    }

}
