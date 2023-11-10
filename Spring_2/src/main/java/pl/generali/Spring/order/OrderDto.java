package pl.generali.Spring.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import pl.generali.Spring.order.customer.Customer;

@Data
@AllArgsConstructor
public class OrderDto {


    private Long monitorId;
    private Long laptopId;
    private Long customerId;

}
