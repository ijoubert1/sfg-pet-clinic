package local.springframework.sfgpetclinic.services.map;

import local.springframework.sfgpetclinic.model.Pet;
import local.springframework.sfgpetclinic.services.PetService;
import org.springframework.stereotype.Service;

import java.util.Collection;
@Service
public class PetServiceMap extends AbstractMapService<Pet, Long> implements PetService {
    @Override
    public Collection<Pet> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Pet object) {
        super.delete(object);
    }

    @Override
    public Pet save(Pet object) {
        return super.save(object.getId(), object);
    }

    @Override
    public Pet findById(Long id) {
        return super.findById(id);
    }
}
