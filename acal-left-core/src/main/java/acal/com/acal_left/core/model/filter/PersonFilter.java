package acal.com.acal_left.core.model.filter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PersonFilter {
    private String name;

    public void setName(String name) {
        this.name = (name == null || name.trim().isEmpty()) ? null : name.trim();
    }
}
