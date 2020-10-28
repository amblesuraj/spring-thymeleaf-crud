package com.ngu.controller;

import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ngu.Helper.CodeHelper;
import com.ngu.Model.Employee;
import com.ngu.Service.EmployeeService;
@Controller
public class EmployeeController 
{

	
			@Autowired
			private EmployeeService employeeService;


			@RequestMapping(value= {"/","/index"},method = RequestMethod.GET)
			public String index(Model model)
			{
			   	model.addAttribute("employee", new Employee());
			   	return "index";
			}
			
			@RequestMapping(value="/showEmployee" , method = RequestMethod.GET)
			public String showEmpoyees(ModelMap modelMap)
			{
				modelMap.put("employees",employeeService.findAll());
				return "success";
			}

			@RequestMapping(value="/save")
			public String save(@ModelAttribute("employee") @Valid Employee employee,BindingResult result, ModelMap modelMap,RedirectAttributes redirectAttributes)
			{
				
				if(result.hasErrors())
				{
					
					return "index";
				}
				else
				{
					Employee newEmployee = employeeService.create(employee);
					modelMap.put("newEmployee",newEmployee);
					redirectAttributes.addFlashAttribute("msg", "Employee with Username "+newEmployee.getUsername()+" is addedd Successfully");
					return "redirect:index";
				}
			}
			
			@RequestMapping(value="/update/{id}", method=RequestMethod.GET)
			public ModelAndView ShowEditEmployee(@PathVariable int id)
			{
				ModelAndView mv = new ModelAndView("index");
				
				mv.addObject("employee", employeeService.get(id));
				return mv;
			}
			@RequestMapping(value="/update", method= RequestMethod.POST )
			public String update(@ModelAttribute("employee") Employee employee,BindingResult result)
			{
				
				
				if(result.hasErrors())
				{
					return "index";
				}
				else
				{
					employeeService.update(employee);
					return "index";
				}
			}
			@RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
			public String delete(@PathVariable int id)
			{
				employeeService.delete(id);
				return "redirect:/showEmployee";
			}
			
			@RequestMapping(value = "/deleteSelected",method = RequestMethod.POST)
			public String deleteSelected(HttpServletRequest request,ModelMap modelMap)
			{
				
				try {
						
						if(request.getParameterValues("usernameDelete")!= null) 
						{
							for(String username : request.getParameterValues("usernameDelete"))
							{
								employeeService.delete(employeeService.findByUsername(username));
							}
						
						}
							return "redirect:/employee/showEmployee";
				}
				catch (Exception e) 
				{
					modelMap.put("error",e.getMessage());
					modelMap.put("employee",employeeService.findAll());
				}
				return "redirect:/showEmployee";
			}
			
//			@RequestMapping(value="/status/{id}/activation", method= RequestMethod.GET )
//			@ResponseBody
//			public String changeStatus(@PathVariable int id)
//			{
//				Employee employee = employeeService.get(id);
//				boolean isActive = employee.isActive();
//				employee.setActive(!isActive);
//				employeeService.update(employee);
//				return (isActive) ? 
//						"you have successfully De-Activated the user :" + employee.getUsername() :
//						"you have successfully Activated the user :" + employee.getUsername();
//			}
//			
//			@RequestMapping(value="/changeRole", method= RequestMethod.GET )
//			@ResponseBody
//			public String changeRole(Employee employee, @RequestParam Integer id,@RequestParam String role)
//			{
//				try 
//				{
//					employee = employeeService.get(id);
//					String username = employee.getUsername();
//					employee.setRole(role);
//				
//					employeeService.update(employee);
//					return username;
//				}
//				catch (Exception e )
//				{
//					return "Unable to Change Role of User";
//				}
//				
//				
//			}
			
			
			@RequestMapping(value = "/qrCode/{username}" ,method =RequestMethod.GET)
			public void QrCode(@PathVariable String username , HttpServletResponse response) throws Exception
			{
				response.setContentType("Image/png");
				OutputStream outputStream = response.getOutputStream();
				outputStream.write(CodeHelper.getQRCodeImage(username, 200, 200));
				outputStream.flush();
				outputStream.close();
				
			}
			
			//I have given pathvariable as "Username" and In success page I gave the url as /barcode/${employee.phone} it is showing
			
			@RequestMapping(value = "/barcode/{username}" ,method=RequestMethod.GET)
			public void Barcode(@PathVariable String username, HttpServletResponse response) throws Exception
			{
				response.setContentType("Image/png");
				OutputStream outputStream = response.getOutputStream();
				outputStream.write(CodeHelper.getBarcodeImage(username, 200, 200));
				outputStream.flush();
				outputStream.close();
				
			}
			
			
}