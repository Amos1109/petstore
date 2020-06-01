package com.petstore.servlet.customer;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.petstore.dto.PetDTO;
import com.petstore.service.CustomerService;
import com.petstore.util.Pager;
import com.petstore.util.StringUtil;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet(name = "IndexServlet", urlPatterns = {"/index"})
public class IndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doPost(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        String cidStr = request.getParameter("cid");
        String name=request.getParameter("name");
        // 校验pageNum参数输入合法性
        String pageNumStr = request.getParameter("pageNum");
        if(pageNumStr !=null && !StringUtil.isNum(pageNumStr)){
            request.setAttribute("errorMsg", "参数传输错误");
            request.getRequestDispatcher("index").forward(request, response);
            return;
        }
        int cid=0;
        if(cidStr!=null&& !"".equals(cidStr.trim())){
            cid=Integer.parseInt(cidStr);
        }
        int pageNum = 1; //显示第几页数据
        if(pageNumStr!=null && !"".equals(pageNumStr.trim())){
            pageNum = Integer.parseInt(pageNumStr);
        }

        int pageSize = 3;  // 每页显示多少条记录
        String pageSizeStr = request.getParameter("pageSize");
        if(pageSizeStr!=null && !"".equals(pageSizeStr.trim())){
            pageSize = Integer.parseInt(pageSizeStr);
        }

        //组装查询条件
        PetDTO petModel=new PetDTO();
        petModel.setC_id(cid);
        petModel.setName(name);

        CustomerService customerService=new CustomerService();
        //调用service 获取查询结果
        Pager<PetDTO> result = customerService.findPet(petModel,pageNum, pageSize);
        request.setAttribute("name", name);
        request.setAttribute("cid", cid);
        request.setAttribute("result", result);
        request.getRequestDispatcher("index.jsp").forward(request, response);
//        String cid = request.getParameter("cid");
//        CustomerService customerService = new CustomerService();
//        List<Map<String, Object>> petlist = customerService.getPetListByCId(cid);
//        request.setAttribute("petlist", petlist);
//        request.getRequestDispatcher("index.jsp").forward(request, response);
//        response.getWriter().println("indexServlet");
    }

}
