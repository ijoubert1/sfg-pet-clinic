package local.springframework.sfgpetclinic.services.springdatajpa;

import local.springframework.sfgpetclinic.model.Owner;
import local.springframework.sfgpetclinic.repositories.OwnerRepository;
import local.springframework.sfgpetclinic.repositories.PetRepository;
import local.springframework.sfgpetclinic.repositories.PetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerSDJPAServiceTest {
    static final String lastName = "Smith";
    static final Long id = 1l;
    Owner owner;

    @Mock
    OwnerRepository ownerRepository;
    @Mock
    PetRepository petRepository;
    @Mock
    PetTypeRepository petTypeRepository;

    @InjectMocks
    OwnerSDJPAService service;

    @BeforeEach
    void setUp() {
        Owner owner = Owner.builder().id(id).lastName(lastName).build();
    }

    @Test
    void findByLastName() {
        when(service.findByLastName(anyString())).thenReturn(owner);

        Owner found =  service.findByLastName(lastName);
        assertNotNull(found);
        assertEquals(owner.getLastName(), found.getLastName());
    }

    @Test
    void findAll() {
        Set<Owner> owners = new HashSet<>();
        owners.add(owner);
        when(service.findAll()).thenReturn(owners);

        Set<Owner> all = service.findAll();
        assertEquals(1, all.size());
    }

    @Test
    void findById() {
        when(service.findById(anyLong())).thenReturn(owner);

        Owner found = service.findById(id);
        assertNotNull(found);
        assertEquals(owner.getId(), found.getId());
    }

    @Test
    void save() {
        Owner owner2 = Owner.builder().id(2l).build();
        when(service.save(any())).thenReturn(owner2);

        Owner saved = service.save(Owner.builder().id(2l).build());
        assertNotNull(saved);
        assertEquals(owner2.getId(), saved.getId());
    }

    @Test
    void delete() {
        service.delete(owner);
        verify(service, times(1)).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(id);
        verify(service, times(1)).deleteById(anyLong());
    }
}