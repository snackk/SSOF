package reader;

import java.util.ArrayList;
import java.util.List;

public class SQLManual {
	
	private List<String> funcs;  //Sanitization functions
	private List<String> sinks;  //Sensitive sinks
	private List<String> points; //Entry points
	
	public SQLManual() {
		this.funcs = new ArrayList<String>() {{
			add("mysql_escape_string");
			add("mysql_real_escape_string");
			add("mysqli_escape_string");
			add("mysqli_real_escape_string");
			add("mysqli_stmt_bind_param");
			add("mysqli::escape_string");
			add("mysqli::real_escape_string");
			add("mysqli_stmt::bind_param");
		}};
		this.sinks = new ArrayList<String>() {{
			add("mysql_query");
			add("mysql_unbuffered_query");
			add("mysqli_query");
			add("mysqli_real_query");
			add("mysqli_master_query");
			add("mysqli_multi_query");
			add("mysqli_stmt_execute");
			add("mysqli::query");
			add("mysqli::multi_query");
			add("mysqli::real_query");
			add("mysqli_stmt::execute");
		}};
		this.points = new ArrayList<String>() {{
			add("$_GET");
			add("$_POST");
			add("$_COOKIE");
			add("$_REQUEST");
			add("HTTP_GET_VARS");
			add("HTTP_POST_VARS");
			add("HTTP_COOKIE_VARS");
			add("HTTP_REQUEST_VARS");
		}};
	
	}
	
	public List<String> getFuncs(){
		return this.funcs;
	}
	
	public List<String> getSinks(){
		return this.sinks;
	}
	
	public List<String> getPoints(){
		return this.points;
	}
	
}