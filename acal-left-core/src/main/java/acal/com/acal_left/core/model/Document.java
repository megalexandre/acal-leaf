package acal.com.acal_left.core.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Document {

    private String value;

    public String getNumber() {
        if(value == null){
            return null;
        }

        return value.replaceAll("\\D", "");
    }

}
