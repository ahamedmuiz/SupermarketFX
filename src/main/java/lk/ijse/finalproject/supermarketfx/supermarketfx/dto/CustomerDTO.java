package lk.ijse.finalproject.supermarketfx.supermarketfx.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class CustomerDTO {


    private String customerId;
    private String customerName;
    private String customerNic;
    private String customerEmail;
    private String customerPhone;

}
