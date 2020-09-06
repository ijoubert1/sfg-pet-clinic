package local.springframework.sfgpetclinic.controllers;

import local.springframework.sfgpetclinic.model.Owner;
import local.springframework.sfgpetclinic.services.OwnerService;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    @Mock
    OwnerService service;

    @InjectMocks
    OwnerController controller;

    Set<Owner> owners;
    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
       owners = new HashSet<>();
       owners.add(Owner.builder().id(1l).build());
       owners.add(Owner.builder().id(2l).build());
       mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void listOwners() throws Exception {
        when(service.findAll()).thenReturn(owners);
        mockMvc.perform(get("/owners"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/index"))
                .andExpect(model().attribute("owners", IsCollectionWithSize.hasSize(2)));
    }

    @Test
    void findOwners() throws Exception {
        mockMvc.perform(get("/find"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/findOwner"))
                .andExpect(model().attribute("owners", IsCollectionWithSize.hasSize(1)));
    }

    @Test
    void showOwner() throws Exception {
        Owner o1 = Owner.builder().id(3l).build();
        when(service.findById(anyLong())).thenReturn(o1);
        mockMvc.perform(get("/owners/123"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/ownerDetails"))
                .andExpect(model().attribute("owner", hasProperty("id", is(3l))));
    }
}

