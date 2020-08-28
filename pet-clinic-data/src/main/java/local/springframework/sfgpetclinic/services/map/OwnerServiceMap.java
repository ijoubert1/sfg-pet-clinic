package local.springframework.sfgpetclinic.services.map;

import local.springframework.sfgpetclinic.model.Owner;
import local.springframework.sfgpetclinic.model.Pet;
import local.springframework.sfgpetclinic.services.OwnerService;
import local.springframework.sfgpetclinic.services.PetService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements OwnerService{
    private final PetTypeServiceMap petTypeServiceMap;
    private final PetService petService;

    public OwnerServiceMap(PetTypeServiceMap petTypeServiceMap, PetService petService) {
        this.petTypeServiceMap = petTypeServiceMap;
        this.petService = petService;
    }

    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Owner object) {
        super.delete(object);

    }

    @Override
    public Owner save(Owner object) {
        if(object != null) {
            if(object.getPets() != null) {
                object.getPets().forEach(pet -> {
                    if(pet.getPetType() != null){
                        if(pet.getPetType().getId() == null){
                            pet.setPetType(petTypeServiceMap.save(pet.getPetType()));
                        }
                    } else {
                        throw new RuntimeException("PetType is required");
                    }

                    if(pet.getId() == null){
                        Pet savedPet = petService.save(pet);
                        pet.setId(savedPet.getId());
                    }
                });
            }
            return super.save(object);
        } else {
            return null;
        }
    }

    @Override
    public Owner findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Owner findByLastName(String lastName) {
        return null;
    }
}
