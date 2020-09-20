package net.hackbee.interview.carparts.sales;

import net.hackbee.interview.carparts.persistence.SaleArgumentRepository;
import org.springframework.stereotype.Service;

@Service
class SaleService {

    private final SaleArgumentRepository saleArgumentRepository;

    SaleService(SaleArgumentRepository saleArgumentRepository) {
        this.saleArgumentRepository = saleArgumentRepository;
    }

    boolean cleanByPart(Long partId) {
        return saleArgumentRepository.deleteByPart_Id(partId) > 0;
    }
}
