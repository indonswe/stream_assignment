package se.lexicon.vxo.service;

import org.junit.jupiter.api.Test;
import se.lexicon.vxo.model.Gender;
import se.lexicon.vxo.model.Person;
import se.lexicon.vxo.model.PersonDto;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.function.ToIntFunction;
import java.util.stream.*;

import static java.util.stream.Collectors.filtering;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toSet;

/**
 * Your task is not make all tests pass (except task1 because its non testable).
 * You have to solve each task by using a java.util.Stream or any of it's variance.
 * You also need to use lambda expressions as implementation to functional interfaces.
 * (No Anonymous Inner Classes or Class implementation of functional interfaces)
 *
 */
public class  StreamAssignment {

    private static List<Person> people = People.INSTANCE.getPeople();

    /**
     * Turn integers into a stream then use forEach as a terminal operation to print out the numbers
     */
    @Test
    public void task1(){
        List<Integer> integers = Arrays.asList(1,2,3,4,5,6,7,8,9,10);

        //Write code here
        integers.stream().forEach(System.out::println);

    }

    /**
     * Turning people into a Stream count all members
     */
    @Test
    public void task2(){
        long amount = 0;

        //Write code here
        amount = people.stream().count();
        System.out.println(amount );

        assertEquals(10000, amount);
    }

    /**
     * Count all people that has Andersson as lastName.
     */
    @Test
    public void task3(){
        long amount = 0;
        int expected = 90;
        amount = people.stream().filter(person -> person.getLastName().equals("Andersson")).count();
        //Write code here

        assertEquals(expected, amount);
    }

    /**
     * Extract a list of all female
     */
    @Test
    public void task4(){
        int expectedSize = 4988;
        List<Person> females = null;

        //Write code here
        females = people.stream().filter(person -> person.getGender().equals(Gender.FEMALE)).collect(Collectors.toList());

        assertNotNull(females);
        assertEquals(expectedSize, females.size());
    }

    /**
     * Extract a TreeSet with all birthDates
     */
    @Test
    public void task5(){
        int expectedSize = 8882;
        Set<LocalDate> dates = null;
        //Set<Integer> collected = Stream.of(1,2,3,4,5)
               // .collect(toSet());
       //dates = Stream.of(people)
        //        .collect(toSet());
        //Stream<LocalDate> str = people.stream().m
        dates = people.stream().map(person -> person.getDateOfBirth()).collect(Collectors.toSet());
        //Write code here
        //dates = people.stream().collect(TreeSet::new,TreeSet::add,TreeSet::addall);
        //dates = people.stream().collect(Collectors.toCollection(TreeSet::new));
        //dates = people.stream().collect(Collectors.toSet(filtering(people.add(people.get(people.per))

        assertNotNull(dates);
        assertTrue(dates instanceof TreeSet);
        assertEquals(expectedSize, dates.size());
    }

    /**
     * Extract an array of all people named "Erik"
     */
    @Test
    public void task6(){
        int expectedLength = 3;

        Person[] result = null;

        //Write code here
        //Employee[] employeesArray = employeeList.stream()
            //    .filter(e -> e.getSalary() < 400)
          //      .toArray(Employee[]::new);
       result = people.stream().filter(p -> p.getFirstName().equals("Erik")).toArray(Person[]::new);
        assertNotNull(result);
        assertEquals(expectedLength, result.length);
    }

    /**
     * Find a person that has id of 5436
     */
    @Test
    public void task7(){
        Person expected = new Person(5436, "Tea", "Håkansson", LocalDate.parse("1968-01-25"), Gender.FEMALE);

        Optional<Person> optional = null;

        //Write code here
        //optional = people.stream().findAny(p -> p.equals(p.getPersonId()));
        //optional = people.stream().findAny().filter(person -> person.equals(person.getPersonId(5436)));
        //optional = people.stream().findFirst().filter(person -> person.equals(person.getPersonId()==5436));
        //optional.get() = (Person) people.stream().filter(person -> person.equals(person.getPersonId()==5436));
        //optional = Optional.of((Person) people.stream().filter(person -> person.equals(person.getPersonId() == 5436)));
        //optional = Optional.of(people.get(people.contains(people.stream().filter(person -> person.equals(person.getPersonId()==5436))));
        //optional = Optional.of(people.stream().filter(person -> person.getPersonId()==5436));
        optional = Optional.of(people.stream().filter(person -> person.getPersonId() == 5436).findFirst().get());
        //List<String> filteredList = listOfOptionals.stream()
                //.filter(Optional::isPresent)
                //.map(Optional::get)
                //.collect(Collectors.toList());
        System.out.println(optional);

        assertNotNull(optional);
        assertTrue(optional.isPresent());
        assertEquals(expected, optional.get());
    }

    /**
     * Using min() define a comparator that extracts the oldest person i the list as an Optional
     */
    @Test
    public void task8(){
        LocalDate expectedBirthDate = LocalDate.parse("1910-01-02");

        Optional<Person> optional = null;

        //Write code here
        //optional = Optional.of(people.stream().filter(person -> person.getPersonId() == 5436).findFirst().get());
        //List <Person> res = storage.findAndSort(p->p.getFirstName().startsWith("A"), Comparator.comparing(Person::getBirthDate));
        optional = (people.stream().min(Comparator.comparing(Person::getDateOfBirth)));
        assertNotNull(optional);
        assertEquals(expectedBirthDate, optional.get().getDateOfBirth());
    }

    /**
     * Map each person born before 1920-01-01 into a PersonDto object then extract to a List
     */
    @Test
    public void task9(){
        int expectedSize = 892;
        LocalDate date = LocalDate.parse("1920-01-01");

        List<PersonDto> dtoList = null;

        //Write code here
        //List<String> filteredList = listOfOptionals.stream()
        //.filter(Optional::isPresent)
        //.map(Optional::get)
        //.collect(Collectors.toList());

        //dtoList = people.stream().filter(person -> person.getDateOfBirth().isBefore(date)).map(PersonDto::new instanceof PersonDto).collect(Collectors.toList());
        dtoList = people.stream().filter(person -> person.getDateOfBirth().isBefore(date)).map(person -> new PersonDto(person)).collect(Collectors.toList());


        assertNotNull(dtoList);
        assertEquals(expectedSize, dtoList.size());
    }

    /**
     * In a Stream Filter out one person with id 5914 from people and take the birthdate and build a string from data that the date contains then
     * return the string.
     */
    @Test
    public void task10(){
        String expected = "WEDNESDAY 19 DECEMBER 2012";
        int personId = 5914;

        Optional<String> optional = null;



        //Write code here

        //optional = Optional.of(people.stream().filter(person -> person.getPersonId() == 5914).findFirst().get().getDateOfBirth().toString());
        //List<String> filteredList = listOfOptionals.stream()
        //.filter(Optional::isPresent)
        //.map(Optional::get)
        //.collect(Collectors.toList());
        optional = Optional.of(people.stream().filter(person -> person.getPersonId() == 5914).findFirst().get().getDateOfBirth().toString());

        System.out.println(optional);

        assertNotNull(optional);
        assertTrue(optional.isPresent());
        assertEquals(expected, optional.get());
    }

    /**
     * Get average age of all People by turning people into a stream and use defined ToIntFunction personToAge
     * changing type of stream to an IntStream.
     */
    @Test
    public void task11(){
        ToIntFunction<Person> personToAge =
                person -> Period.between(person.getDateOfBirth(), LocalDate.parse("2019-12-20")).getYears();
        double expected = 54.42;
        double averageAge = 0;

        //Write code here
        averageAge = people.stream().map(person -> new ToIntFunction<>() {}).collect(Collectors.toList());

        //averageAge = people.stream().


        assertTrue(averageAge > 0);
        assertEquals(expected, averageAge, .01);
    }

    /**
     * Extract from people a sorted string array of all firstNames that are palindromes. No duplicates
     */
    @Test
    public void task12(){
        String[] expected = {"Ada", "Ana", "Anna", "Ava", "Aya", "Bob", "Ebbe", "Efe", "Eje", "Elle", "Hannah", "Maram", "Natan", "Otto"};

        String[] result = null;

        //Write code here

        assertNotNull(result);
        assertArrayEquals(expected, result);
    }

    /**
     * Extract from people a map where each key is a last name with a value containing a list of all that has that lastName
     */
    @Test
    public void task13(){
        int expectedSize = 107;
        Map<String, List<Person>> personMap = null;

        //Write code here

        assertNotNull(personMap);
        assertEquals(expectedSize, personMap.size());
    }

    /**
     * Create a calendar using Stream.iterate of year 2020. Extract to a LocalDate array
     */
    @Test
    public void task14(){
        LocalDate[] _2020_dates = null;

        //Write code here


        assertNotNull(_2020_dates);
        assertEquals(366, _2020_dates.length);
        assertEquals(LocalDate.parse("2020-01-01"), _2020_dates[0]);
        assertEquals(LocalDate.parse("2020-12-31"), _2020_dates[_2020_dates.length-1]);
    }

}
