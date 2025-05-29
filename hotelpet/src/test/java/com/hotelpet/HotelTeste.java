package com.hotelpet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HotelTeste {

    private Hotel hotel;

    @BeforeEach
    public void setup() {
        hotel = new Hotel();
    }

    @Test
    public void testCadastrarPet() {
        int inicial = hotel.getPets().size();
        Tutor tutor = new Tutor("Joao", "11999999999");
        Plano plano = new Standard();
        Pet pet = new Pet("Rex", "cachorro", "Labrador", 3, 25.0, tutor, plano);
        hotel.getPets().add(pet);

        assertEquals(inicial + 1, hotel.getPets().size());
        assertEquals("Rex", hotel.getPets().get(0).getNome());
    }

    @Test
    public void testBuscarPetPorNomeExistente() {
        Tutor tutor = new Tutor("Maria", "11988888888");
        Pet pet = new Pet("Mimi", "gato", "Persa", 2, 4.5, tutor, new Premium());
        hotel.getPets().add(pet);

        Pet encontrado = hotel.buscarPetPorNome("Mimi");
        assertNotNull(encontrado);
        assertEquals("Mimi", encontrado.getNome());
    }

    @Test
    public void testBuscarPetPorNomeInexistente() {
        Pet resultado = hotel.buscarPetPorNome("Fantasma");
        assertNull(resultado);
    }

    @Test
    public void testImportarPetsCSV() {
        hotel.importarPetsDeCSV();
        List<Pet> pets = hotel.getPets();

        // Assumindo que o CSV tem pelo menos um pet
        assertFalse(pets.isEmpty());
        assertNotNull(pets.get(0).getTutor());
        assertNotNull(pets.get(0).getPlano());
    }

    @Test
    public void testPlanoCorretoAposImportacao() {
        hotel.importarPetsDeCSV();
        for (Pet pet : hotel.getPets()) {
            String nomePlano = pet.getPlano().getNomePlano();
            assertTrue(nomePlano.equals("Standard") || nomePlano.equals("Premium") || nomePlano.equals("Vip"));
        }
    }

    @Test
    public void testHoraEntradaEhRegistrada() {
        Tutor tutor = new Tutor("Carlos", "11977777777");
        Pet pet = new Pet("Bolt", "cachorro", "Beagle", 4, 12.0, tutor, new Vip());
        assertNotNull(pet.getHoraEntrada());
    }
}