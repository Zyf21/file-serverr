package file.server.models;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Video {

	String result;
	List<Item> item;

	@Data
	public class Item  {
		Integer id;
		@SerializedName("name_Id")
		String name;

	}
}
