package file.server.models;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;

@Entity
@Table(name = "files")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class File {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long fileId;
	private String path;
	private String fileName;
	private Long employeeId;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;


}