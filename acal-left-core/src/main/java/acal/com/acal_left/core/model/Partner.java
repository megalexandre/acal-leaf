package acal.com.acal_left.core.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Partner {
    private Integer id;
    private Person person;

    public String getName(){
        return person.getName();
    }

    public String getDocument(){
        return person.getDocument();
    }
}
