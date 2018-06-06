package app.dto;

import app.model.Type;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RouteDTO {
    private long id;
    private String name;
    private String type;
    private List<StationDTO> stations;
}
