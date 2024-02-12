package arrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ArrayListTest {

    private ArrayList<Integer> integerList;

    @BeforeEach
    public void setUp() {
        integerList = new ArrayListImpl<>();
        integerList.add(1);
        integerList.add(2);
        integerList.add(3);
    }

    @Test
    @DisplayName("Создание ArrayList с отрицательной вместимостью")
    public void createNewArrayList_WhenCapacityIsNegative() {
        assertThrows(IllegalArgumentException.class, () ->
                integerList = new ArrayListImpl<>(-7));
    }

    @Test
    @DisplayName("Добавление элемента")
    public void addOneInteger() {
        integerList.add(8);

        assertThat(integerList.size()).isEqualTo(4);
        assertThat(integerList.get(3)).isEqualTo(8);
    }

    @Test
    @DisplayName("Добавление элемента при превышении вместимости")
    public void addInteger_whenTheCapacityRanOut() {
        integerList = new ArrayListImpl<>(4);

        integerList.add(1);
        integerList.add(2);
        integerList.add(3);
        integerList.add(4);
        integerList.add(5);
        integerList.add(6);

        assertThat(integerList.size()).isEqualTo(6);
    }

    @Test
    @DisplayName("Добавление элемента по индексу в начало списка")
    public void addByIndex() {
        integerList.addByIndex(0, 0);

        assertThat(integerList.get(0)).isEqualTo(0);
        assertThat(integerList.size()).isEqualTo(4);
    }

    @Test
    @DisplayName("Добавление элемента по индексу превышающему size")
    public void addByIndex_whenIndexIsNotValid_ThrowException() {

        assertThrows(IndexOutOfBoundsException.class, () ->
                integerList.addByIndex(0, 4)
        );
    }

    @Test
    @DisplayName("Получение элемента по индексу")
    public void get_whenIndexIsValid() {

        Integer a = integerList.get(1);

        assertThat(a).isEqualTo(2);
    }

    @Test
    @DisplayName("Получение элемента по некорректному индексу")
    public void get_whenIndexIsNotValid() {

        assertThrows(IndexOutOfBoundsException.class, () ->
                integerList.get(3));
    }

    @Test
    @DisplayName("Удаление элемента")
    public void remove() {
        integerList.remove(2);

        assertThat(integerList.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("Удаление элемента по неверному индексу")
    public void remove_whenIndexIsNotValid() {
        assertThrows(IndexOutOfBoundsException.class, () ->
                integerList.remove(10));
    }

    @Test
    @DisplayName("Отчищение ArrayList")
    public void clear() {
        integerList.clear();

        assertThat(integerList.size()).isEqualTo(0);
    }

    @Test
    @DisplayName("Получение массива из листа")
    public void toArray() {

        Object[] array = integerList.toArray();

        assertThat(array[0]).isEqualTo(integerList.get(0));
        assertThat(array[1]).isEqualTo(integerList.get(1));
        assertThat(array.length).isEqualTo(3);
    }

    @Test
    @DisplayName("Сортировка элементов с использованием Comparator")
    public void sortWithComparator() {
        integerList.clear();

        integerList.add(9);
        integerList.add(4);
        integerList.add(10);
        integerList.add(2);
        integerList.add(5);
        integerList.add(3);

        integerList.sort((Integer::compareTo));

        assertThat(integerList.get(0)).isEqualTo(2);
        assertThat(integerList.get(1)).isEqualTo(3);
        assertThat(integerList.get(5)).isEqualTo(10);
    }

    @Test
    @DisplayName("Сортировка элементов имплементирующих Comparable")
    public void sortWithoutComparator_WhenElementImplementsComparable() {
        integerList.clear();

        integerList.add(9);
        integerList.add(4);
        integerList.add(10);
        integerList.add(2);
        integerList.add(5);
        integerList.add(3);

        integerList.sort();

        assertThat(integerList.get(0)).isEqualTo(2);
        assertThat(integerList.get(1)).isEqualTo(3);
        assertThat(integerList.get(5)).isEqualTo(10);
    }

    @Test
    @DisplayName("Сортировка набора, если 1 элемент равен null")
    public void sort_whereOneElementIsNull() {
        integerList.clear();

        integerList.add(null);
        integerList.add(3);
        integerList.add(1);
        integerList.add(6);

        integerList.sort();

        assertThat(integerList.get(0)).isEqualTo(1);
        assertThat(integerList.get(1)).isEqualTo(3);
        assertThat(integerList.get(2)).isEqualTo(6);
        assertThat(integerList.get(3)).isNull();
    }

}