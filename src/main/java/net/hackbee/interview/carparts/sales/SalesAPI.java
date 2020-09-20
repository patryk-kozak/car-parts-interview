package net.hackbee.interview.carparts.sales;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/api/sales")
public class SalesAPI {

    private final SaleService salesService;

    public SalesAPI(SaleService salesService) {
        this.salesService = salesService;
    }

    @Transactional
    @DeleteMapping("/part/{partId}")
    public ResponseEntity<HttpStatus> removePartSales(@PathVariable Long partId) {
        if(this.salesService.cleanByPart(partId)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.noContent().build();
        }
    }
    
}
