package file.server.services.implement;

import com.google.gson.Gson;
import file.server.models.Video;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class YouTube {

	public static void main(String[] args) throws ParseException {
		new YouTube().convertToPdf();

//		Student student = new Student();
//		student.setRollNo(1);
//		Student.Name name = new Student.Name();
//
//		name.firstName = "Mahesh";
//		name.lastName = "Kumar";
//		student.setName(name);
//
//
//		Gson gson = new Gson();
//		String jsonString = gson.toJson(student);
//		System.out.println(jsonString);
//		student = gson.fromJson(jsonString, Student.class);
//
//		System.out.println(student+"843758345783");
//
//
//		System.out.println("Roll No: "+ student.getRollNo());
//		System.out.println("First Name: "+ student.getName().firstName);
//		System.out.println("Last Name: "+ student.getName().lastName);
//		String nameString = gson.toJson(name);
//		System.out.println(nameString);
//
//		name = gson.fromJson(nameString,Student.Name.class);
//		System.out.println(name.getClass());
//		System.out.println("First Name: "+ name.firstName);
//		System.out.println("Last Name: "+ name.lastName);



	}

//	public static class Student {
//		private int rollNo;
//		private Name name;
//
//		public int getRollNo() {
//			return rollNo;
//		}
//
//		public void setRollNo(int rollNo) {
//			this.rollNo = rollNo;
//		}
//
//		public Name getName() {
//			return name;
//		}
//
//		public void setName(Name name) {
//			this.name = name;
//		}
//
//		public static class Name {
//			public String firstName;
//			public String lastName;
//		}
//	}

	public  String convertToPdf () throws ParseException {

		String gsonTest  = "{\n" +
				"  \"response\": {\n" +
				"    \"count\": 10307,\n" +
				"    \"items\": [\n" +
				"      {\n" +
				"        \"id\": 176514,\n" +
				"        \"from_id\": -114200945,\n" +
				"        \"owner_id\": -114200945,\n" +
				"        \"date\": 1506629224,\n" +
				"        \"marked_as_ads\": 0,\n" +
				"        \"post_type\": \"post\",\n" +
				"        \"text\": \"-я с разбитым сердцем сука,но я всё равно влюблённый.\",\n" +
				"        \"post_source\": {\n" +
				"          \"type\": \"api\"\n" +
				"        },\n" +
				"        \"comments\": {\n" +
				"          \"count\": 1,\n" +
				"          \"groups_can_post\": true,\n" +
				"          \"can_post\": 1\n" +
				"        },\n" +
				"        \"likes\": {\n" +
				"          \"count\": 103,\n" +
				"          \"user_likes\": 0,\n" +
				"          \"can_like\": 1,\n" +
				"          \"can_publish\": 1\n" +
				"        },\n" +
				"        \"reposts\": {\n" +
				"          \"count\": 3,\n" +
				"          \"user_reposted\": 0\n" +
				"        },\n" +
				"        \"views\": {\n" +
				"          \"count\": 1022\n" +
				"        }\n" +
				"      }\n" +
				"    ]\n" +
				"  }\n" +
				"}";


		String gsonTest2 = " {\n" +
				"    \"result\": \"rez\",\n" +
				"    \"name\": \"ok\",\n" +
				"    \"item\": [\n" +
				"        {\n" +
				"            \"id\": 55,\n" +
				"            \"name_Id\": \"k\"\n" +
				"        },\n" +
				"               {\n" +
				"            \"id\": 55,\n" +
				"            \"name_Id\": \"k\"\n" +
				"        }\n" +
				"        ]\n" +
				"    \n" +
				" }";

		Gson g = new Gson();
		Video video = g.fromJson(gsonTest2, Video.class);
		//Video.Item video2 = g.fromJson(gsonTest2, Video.Item.class);

		System.out.println(video.getItem().get(video.getItem().size()-1));
		System.out.println(video.getItem().get(1).getId());

		String dateInString = "19590709";
		LocalDate date = LocalDate.parse(dateInString, DateTimeFormatter.BASIC_ISO_DATE);
		Instant instant = date.atStartOfDay().toInstant(ZoneOffset.UTC);
		System.out.println(instant);


		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
		DateTimeFormatter formatter2 = DateTimeFormatter.BASIC_ISO_DATE;
//		String dateInString2 = "7-Jun-2013";
		Date date2 = (Date) formatter2.parse(dateInString);
		date2.toInstant();
	//	System.out.println(date2.toInstant());


		return video.getResult();

	}

}
