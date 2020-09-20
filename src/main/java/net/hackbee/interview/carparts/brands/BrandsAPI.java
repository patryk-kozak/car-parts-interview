package net.hackbee.interview.carparts.brands;

import net.hackbee.interview.carparts.brands.model.BrandProfile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/brands")
public class BrandsAPI {

    private final BrandService brandService;

    public BrandsAPI(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping("/{brand}")
    public BrandProfile information(@PathVariable String brand) {
        return this.brandService.profile(brand);
    }

}
