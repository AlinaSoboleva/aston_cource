package arrayList;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

/**
 * Класс ArrayList - упорядоченный набор данных, реализует интерфейс {@code ArrayList}.
 * В основе лежит массив Object.
 *
 * @param <T> тип элементов в наборе.
 * @author Ляшенко Алина Эдурадовна
 * @see ArrayList
 */
public class ArrayListImpl<T> implements ArrayList<T> {

    /**
     * Массив объектов. Используется для хранения данных типа T.
     */
    private Object[] elements;

    /**
     * Дефолтная вместимость
     */
    private final int DEFAULT_CAPACITY = 10;

    /**
     * Вместимость
     */
    private int capacity;

    /**
     * Количество элементов в наборе
     */
    private int size;

    /**
     * Коструктор без параметров.
     * <br>
     * Вместимость ArrayList равняется 10.
     */
    public ArrayListImpl() {
        this.elements = new Object[DEFAULT_CAPACITY];
        this.capacity = elements.length;
    }

    /**
     * Коструктор.
     *
     * @param capacity используется для обозначения вместимости массива.
     *                 Если указать 0, то вместимость будет дефолтной (10).
     *                 <br>
     *                 НЕ МОЖЕТ БЫТЬ ОТРИЦАТЕЛЬНЫМ ЧИСЛОМ.
     * @throws IllegalArgumentException
     */
    public ArrayListImpl(int capacity) {
        if (capacity > 0) {
            this.capacity = capacity;
            this.elements = new Object[capacity];
        }
        if (capacity == 0) {
            this.elements = new Object[DEFAULT_CAPACITY];
            this.capacity = elements.length;
        }
        if (capacity < 0) {
            throw new IllegalArgumentException("Capacity can not be less than 0");
        }
    }

    /**
     * Метод добавления элемента.
     * <p>Вызывает внутри метод addByIndex, добавляя элемент на последнюю позицию</p>
     *
     * @param t добавляемый элемент
     */
    @Override
    public void add(T t) {
        addByIndex(t, size);
    }


    /**
     * Добавляет элемент на конкретную позицию. <br>
     * Проверяет корректность введеного индекса. Если при добавлении элемента массив
     * был послностью заполнен, то создает новый массив вместимостью в 1,5 раза больше предыдущего
     * и заполняет его элементами из старого массива. Заменяет старый массив новым.
     *
     * @param t     добавляемый элемент
     * @param index индекс, на позицию которого будет добавлен элемент
     * @throws IndexOutOfBoundsException
     */
    @Override
    public void addByIndex(T t, int index) {
        checkIndex(index);
        if (size == elements.length - 1) {
            this.capacity = capacity + (capacity / 2);
            Object[] newElements = new Object[capacity];
            for (int i = 0; i < elements.length; i++) {
                newElements[i] = elements[i];
            }
            elements = newElements;
        }
        size++;
        elements[index] = t;
    }

    /**
     * Возвращает элемент по индексу.
     *
     * @param index индекс элемента, который необходимо получить
     * @return T элемент в наборе
     */
    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return (T) elements[index];
    }

    /**
     * Используется для сортировки набора элементов, имплементирующих
     * интерфейс {@code Comparable<T>}.
     */
    public void sort() {
        T[] array = (T[]) toArray();
        this.elements = QuickSort.quickSort(array, 0, array.length - 1);
    }

    /**
     * Используется для сортировки набора элементов.
     *
     * @param c Comparator для сравнения элементов
     */
    public void sort(Comparator<? super T> c) {
        T[] array = (T[]) toArray();
        this.elements = QuickSort.quickSort(array, 0, array.length - 1, c);
    }

    /**
     * Удаляет элемент по указанному индексу.
     *
     * @param index индекс удаляемого элемента
     * @throws IndexOutOfBoundsException если был введен некоректный индекс
     */
    @Override
    public void remove(int index) {
        Objects.checkIndex(index, size);
        System.arraycopy(elements, index + 1, elements, index, size - index);
        elements[--size] = null;
    }

    /**
     * Создает новый пустой массив с дефолтной вместимостью.
     */
    @Override
    public void clear() {
        elements = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    /**
     * @return int количество элементов в наборе
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Преобразует ArrayList в массив
     *
     * @return Object []
     */
    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elements, size);
    }

    /**
     * Возвращает строковое представление объекта.
     *
     * @return String
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Object element : elements) {
            if (element != null) {
                builder.append(element).append(" ");
            }
        }
        return "ArrayListImpl{" +
                "elements=" + builder +
                '}';
    }

    /**
     * Проверяет индекс. <br>
     * Индекс не должен быть меньше 0 или больше чем size.
     *
     * @param index
     * @throws IndexOutOfBoundsException
     */
    private void checkIndex(int index) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Incorrect index: " + index);
    }
}
