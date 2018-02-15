package Beans;

import java.util.ArrayList;
import java.util.List;

public class DepartmentDocument {

		// TODO Auto-generated method stub
		int _id;
		String department_name;
		String department_manager;
		List<String> dept_locations = new ArrayList<String>();
		
		public String getDepartment_name() {
			return department_name;
		}
		public int get_id() {
			return _id;
		}
		public void set_id(int _id) {
			this._id = _id;
		}
		public void setDepartment_name(String department_name) {
			this.department_name = department_name;
		}
		public String getDepartment_manager() {
			return department_manager;
		}
		public void setDepartment_manager(String department_manager) {
			this.department_manager = department_manager;
		}
		public List<String> getDept_locations() {
			return dept_locations;
		}
		public void setDept_locations(List<String> dept_locations) {
			this.dept_locations = dept_locations;
		}

}

