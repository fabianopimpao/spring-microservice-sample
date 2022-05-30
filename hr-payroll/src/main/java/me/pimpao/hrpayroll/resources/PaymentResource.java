package me.pimpao.hrpayroll.resources;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import me.pimpao.hrpayroll.entities.Payment;
import me.pimpao.hrpayroll.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/payments")
public class PaymentResource {
    @Autowired
    private PaymentService paymentService;

    @HystrixCommand(fallbackMethod = "getPaymentAlternative")
    @GetMapping(path = "/{workerId}/days/{days}")
    public ResponseEntity<Payment> getPayment(@PathVariable Long workerId, @PathVariable Integer days) {
        return ResponseEntity.ok(paymentService.getPayment(workerId, days));
    }

    public ResponseEntity<Payment> getPaymentAlternative(Long workerId, Integer days) {
        return ResponseEntity.ok(new Payment("Brann", 400.0, days));
    }
}
