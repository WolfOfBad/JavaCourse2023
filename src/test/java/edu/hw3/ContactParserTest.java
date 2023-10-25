package edu.hw3;

import edu.hw3.ContactList.Contact;
import edu.hw3.ContactList.ContactsParser;
import edu.hw3.ContactList.SortingOrder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class ContactParserTest {

    @Test
    @DisplayName("Тест сортировки по возрастанию")
    public void ascendingOrderTest() {
        List<String> names = List.of("A", "B A", "A A", "B");

        ContactsParser obj = new ContactsParser();
        List<Contact> result = obj.parseContact(names, SortingOrder.ASC);

        assertThat(result.get(0)).isEqualTo(new Contact("A"));
        assertThat(result.get(1)).isEqualTo(new Contact("A", "A"));
        assertThat(result.get(2)).isEqualTo(new Contact("A", "B"));
        assertThat(result.get(3)).isEqualTo(new Contact("B"));
    }

    @Test
    @DisplayName("Тест сортировки по убыванию")
    public void descendingOrderTest() {
        List<String> names = List.of("A", "B A", "A A", "B");

        ContactsParser obj = new ContactsParser();
        List<Contact> result = obj.parseContact(names, SortingOrder.DESC);

        assertThat(result.get(0)).isEqualTo(new Contact("B"));
        assertThat(result.get(1)).isEqualTo(new Contact("A", "B"));
        assertThat(result.get(2)).isEqualTo(new Contact("A", "A"));
        assertThat(result.get(3)).isEqualTo(new Contact("A"));
    }

    @Test
    @DisplayName("Тест на null")
    public void nullTest() {
        List<String> names = null;

        ContactsParser obj = new ContactsParser();
        List<Contact> result = obj.parseContact(names, SortingOrder.DESC);

        assertThat(result).isNotNull();
        assertThat(result).asList().isEmpty();
    }

    @Test
    @DisplayName("Тест на пустой массив")
    public void emptyListTest() {
        List<String> names = new ArrayList<>();

        ContactsParser obj = new ContactsParser();
        List<Contact> result = obj.parseContact(names, SortingOrder.DESC);

        assertThat(result).isNotNull();
        assertThat(result).asList().isEmpty();
    }
}
