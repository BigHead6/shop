package com.nyist.controller.admin.product;

import com.github.pagehelper.PageInfo;
import com.nyist.dao.CompanyMapper;
import com.nyist.entity.Company;
import com.nyist.service.CompanyService;
import com.nyist.vo.LayuiPageVo;
import com.nyist.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author ：为天下溪
 * @date ：Created in 2019/4/9 11:55
 * @description：${description}
 * @version: $version$
 */
@Controller
@RequestMapping(value = "${adminPath}")
public class activityController {
    @Autowired
    private CompanyMapper companyMapper;
    @Autowired
    private CompanyService companyService;


    /**
     * 获取活动信息
     *
     * @param currentPage
     * @param pageSize
     * @return
     */
    @RequestMapping("/getActivity")
    @ResponseBody
    public LayuiPageVo getActivity(@RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
                                   @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        LayuiPageVo lpv = new LayuiPageVo();
        PageInfo<Company> pageInfo = null;
        pageInfo = companyService.pageList(currentPage, pageSize);
        List<Company> productList = pageInfo.getList();
        lpv.setCode(1);
        lpv.setMsg("获取数据成功");
        lpv.setTotal(pageInfo.getTotal());
        lpv.setData(productList);
        return lpv;
    }


    /**
     * 编辑商品活动信息
     * @param request
     * @return
     */
    @RequestMapping("/editActivity")
    @ResponseBody
    public ResultVo editActivity(HttpServletRequest request) {
        Integer supNo = Integer.parseInt(request.getParameter("supNo"));
        String name = request.getParameter("name");
        Company company = new Company();
        company.setSupNo(supNo);
        company.setSupName(name);
        if (companyService.updateByPrimaryKeySelective(company) != 0) {
            return new ResultVo(1, "信息更新成功", null);
        } else {
            return new ResultVo(0, "信息更新失败", null);
        }
    }

    /**
     * 添加商品活动信息
     * @param request
     * @return
     */
    @RequestMapping("/addActivity")
    @ResponseBody
    public ResultVo addActivity(HttpServletRequest request) {
        String name = request.getParameter("name");
        Company company = new Company();
        company.setSupName(name);
        if (companyService.insertSelective(company)!=0) {
            return new ResultVo(1, "活动添加成功", null);
        } else {
            return new ResultVo(0, "活动添加失败", null);
        }
    }

    /**
     * 删除商品活动信息
     * @param request
     * @return
     */
    @RequestMapping("/delActivity")
    @ResponseBody
    public ResultVo delActivity(HttpServletRequest request) {
        Integer supNo = Integer.parseInt(request.getParameter("supNo"));
        Company company = new Company();
        company.setSupNo(supNo);
        company.setFlag("0");
        if (companyService.updateByPrimaryKeySelective(company)!=0) {
            return new ResultVo(1, "活动删除成功", null);
        } else {
            return new ResultVo(0, "活动删除失败", null);
        }
    }
}