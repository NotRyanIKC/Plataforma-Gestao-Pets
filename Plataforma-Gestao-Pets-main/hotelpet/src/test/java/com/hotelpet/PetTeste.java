package com.hotelpet;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PetTeste {

    @Test
    public void CriarPetCorretamente() {
        Tutor tutor = new Tutor("João", "11999999999");
        Plano plano = new Standard();
        Pet pet = new Pet("Rex", "Cachorro", "Poodle", 4, 10.5, tutor, plano);

        assertEquals("Rex", pet.getNome());
        assertEquals("Cachorro", pet.getEspecie());
        assertEquals("Poodle", pet.getRaca());
        assertEquals(4, pet.getIdade());
        assertEquals(10.5, pet.getPeso());
        assertEquals("João", pet.getTutor().getNome());
        assertEquals("12312312345", pet.getTutor().getContato());
        assertEquals("Standard", pet.getPlano().getNomePlano());
    }
}

