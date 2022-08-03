package backend.rentacar.business.concretes;

import backend.rentacar.business.abstracts.ColorService;
import backend.rentacar.core.utilities.results.DataResult;
import backend.rentacar.core.utilities.results.Result;
import backend.rentacar.core.utilities.results.SuccessDataResult;
import backend.rentacar.core.utilities.results.SuccessResult;
import backend.rentacar.repositories.abstracts.ColorRepository;
import backend.rentacar.entities.concretes.Color;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColorManager implements ColorService {
    private final ColorRepository colorRepository;

    @Autowired
    public ColorManager(ColorRepository colorRepository) {
        this.colorRepository = colorRepository;
    }

    @Override
    public DataResult<List<Color>> getAll() {

        return new SuccessDataResult<List<Color>>(this.colorRepository.findAll(),"Colors listed.");
    }

    @Override
    public DataResult<List<Color>> getAllByColorNameAsc() {
        Sort sort = Sort.by(Sort.Direction.ASC,"colorName");
        return new SuccessDataResult<List<Color>>(this.colorRepository.findAll(sort),"The colors were sorted in ascending alphabet of the color name.");
    }

    @Override
    public DataResult<List<Color>> getAllByColorNameDesc() {
        Sort sort = Sort.by(Sort.Direction.DESC,"colorName");
        return new SuccessDataResult<List<Color>>(this.colorRepository.findAll(sort),"The colors were sorted in descending alphabet of the color name.");
    }

    @Override
    public Result add(Color color) {
        this.colorRepository.save(color);
        return new SuccessResult("Color added.");
    }

    @Override
    public DataResult<Color> findByColorId(int colorId) {
        return new SuccessDataResult<Color>(this.colorRepository.findByColorId(colorId),"Color listed by id.");
    }

    @Override
    public DataResult<Color> findByColorName(String colorName) {
        return new SuccessDataResult<Color>(this.colorRepository.findByColorName(colorName),"Color listed by name.");
    }
}
