package net.hackbee.interview.carparts.brands;

import net.hackbee.interview.carparts.brands.model.BrandProfile;
import net.hackbee.interview.carparts.brands.model.Model;
import net.hackbee.interview.carparts.brands.model.ModelPartMapper;
import net.hackbee.interview.carparts.persistence.ModelRepository;
import net.hackbee.interview.carparts.persistence.entity.ModelEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
class BrandService {

    private final ModelRepository modelRepository;
    private final ModelPartMapper modelPartMapper;

    BrandService(ModelRepository modelRepository, ModelPartMapper modelPartMapper) {
        this.modelRepository = modelRepository;
        this.modelPartMapper = modelPartMapper;
    }

    BrandProfile profile(String brand) {
        BrandProfile profile = new BrandProfile(brand);
        List<ModelEntity> models = modelRepository.findByBrand_name(brand);
        for (ModelEntity model : models) {
            Model modelDto = new Model(model.getName());
            modelDto.setParts(model.getParts().stream()
                    .map(this.modelPartMapper::entityToModelPart)
                    .collect(Collectors.toList()));
            profile.getModels().add(modelDto);
        }
        return profile;
    }
}
