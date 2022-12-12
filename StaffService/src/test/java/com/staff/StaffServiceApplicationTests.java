package com.staff;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.staff.exception.StaffNotFoundException;
import com.staff.model.Staff;
import com.staff.repository.StaffRepository;
import com.staff.service.StaffServiceImplementation;

@SpringBootTest
class StaffServiceApplicationTests {
	
	@Autowired
	private StaffServiceImplementation service;
	
	@MockBean
	private StaffRepository staffRepository;

	@Test
	public void ShowAllStaffTest() {
		List<Staff>staff = new ArrayList<>();
		Staff s = new Staff();
		
		s.setId(1);
		s.setDepartmentId(122);
		s.setEmployeeName("Sundari");
		s.setPhoneNo("9876656570");
		s.setEmail("krssundari@gmail.com");
		s.setAge(45);
		s.setEmployeeAddress("Urapakkam,Chennai");
		s.setSalary(50000);
		
		staff.add(s);

		when(staffRepository.findAll()).thenReturn(staff);
		assertEquals(1, service.showAllStaffDetails().size());
	}
	
	@Test
	public void ShowStaffById() throws StaffNotFoundException {
		
        Staff s = new Staff();
		
		s.setId(1);
		s.setDepartmentId(122);
		s.setEmployeeName("Sundari");
		s.setPhoneNo("9876656570");
		s.setEmail("krssundari@gmail.com");
		s.setAge(45);
		s.setEmployeeAddress("Urapakkam,Chennai");
		s.setSalary(50000);
		
		Optional<Staff>staff =Optional.of(s);
		when(staffRepository.findById(1)).thenReturn(staff);
		assertEquals(s, service.showStaffById(1));
		
	}
	
	@Test
	public void addStaffTest() throws StaffNotFoundException {
		
        Staff s = new Staff();
        
        s.setId(1);
		s.setDepartmentId(122);
		s.setEmployeeName("Sundari");
		s.setPhoneNo("9876656570");
		s.setEmail("krssundari@gmail.com");
		s.setAge(45);
		s.setEmployeeAddress("Urapakkam,Chennai");
		s.setSalary(50000);

		when(staffRepository.insert(s)).thenReturn(s);
		assertEquals(s, service.addStaff(s));
	}
	
	@Test
	public void updateStaffTest() throws StaffNotFoundException {
		
        Staff s1 = new Staff();
        Staff s2 = new Staff();

        
        s1.setId(1);
		s1.setDepartmentId(122);
		s1.setEmployeeName("Sundari");
		s1.setPhoneNo("9876656570");
		s1.setEmail("krssundari@gmail.com");
		s1.setAge(45);
		s1.setEmployeeAddress("Urapakkam,Chennai");
		s1.setSalary(50000);
		
		s2.setId(1);
		s2.setDepartmentId(122);
		s2.setEmployeeName("Sundari");
		s2.setPhoneNo("9876656570");
		s2.setEmail("krssundari@gmail.com");
		s2.setAge(45);
		s2.setEmployeeAddress("Revathipuram,Chennai");
		s2.setSalary(50000);
		
	       Optional<Staff> staff = Optional.of(s1);

		when(staffRepository.findById(1)).thenReturn(staff);
		when(staffRepository.save(s2)).thenReturn(s2);
		assertEquals(s2, service.updateStaff(s2));
	}
	
	@Test
	public void deleteStaffTest() throws StaffNotFoundException {
		
        Staff s = new Staff();
        
        s.setId(1);
		s.setDepartmentId(122);
		s.setEmployeeName("Sundari");
		s.setPhoneNo("9876656570");
		s.setEmail("krssundari@gmail.com");
		s.setAge(45);
		s.setEmployeeAddress("Urapakkam,Chennai");
		s.setSalary(50000);

		Optional<Staff>staff = Optional.of(s);
		when(staffRepository.findById(1)).thenReturn(staff);
		assertEquals("Staff with the 1 Deleted Successfully!", service.deleteStaff(1));
	
}
}
