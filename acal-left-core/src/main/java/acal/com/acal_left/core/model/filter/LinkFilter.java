package acal.com.acal_left.core.model.filter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LinkFilter {
    private String name;
    private Boolean active;

    public void reset() {
        this.name = null;
    }

}
