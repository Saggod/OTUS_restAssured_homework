package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StoreDTO {
	private int id;
	private int petId;
	private int quantity;
	private String shipDate;
	private String status;
	private boolean complete;

}
